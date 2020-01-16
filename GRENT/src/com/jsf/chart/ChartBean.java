package com.jsf.chart;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;
import com.jsf.entity.Chart;


/**
 * Controller class of the chart page.
 */
@ManagedBean
@ViewScoped
public class ChartBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
	private Chart chart;
	private Connection connection;
	private boolean loggedIn;
	private String totalDays;
	private String totalPrice;
	private boolean showChart;
	private String returnStringDate;
	private String receiveStringDate;

	/**
	 * Initialize method of the class
	 */
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze(); // init Database
		connection = DatabaseManager.getConnection();

		if (LoginManager.isLoggedIn()) {
			this.userName = LoginManager.getUsername();
			this.loggedIn = true;
		}

		receiveChart();
		initShowChart();
		initTotals();
	}
	
	/**
	 * Checks whether are there any data to show in the chart for corresponding user
	 */
	public void initShowChart() {
		if(LoginManager.isLoggedIn() == false) {
			showChart=false;
		}else if(chart.getReturningDate() == null) {
			showChart = false;
		}else {
			showChart=true;
		}
	}
	

	/**
	 * Calculate totalDays and totalPrice.
	 */
	public void initTotals() {
		if (this.showChart) {
			long diff = this.chart.getReturningDate().getTime() - this.chart.getReceivingDate().getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			if (diffDays == 0 && diffHours != 0) {
				totalDays = "1";
				totalPrice = chart.getDailyPrice();
			} else if (diffDays != 0) {
				totalDays = String.valueOf(diffDays);
				long dailyPrice = Long.parseLong(chart.getDailyPrice());
				long totalPrice = dailyPrice * diffDays;
				this.totalPrice = String.valueOf(totalPrice);
			}
		}
	}

	/**
	 * Deletes the corresponding Vehicle which is in the chart of current RegisteredUser
	 */
	public void deleteVehicle() {
		if (LoginManager.isLoggedIn()) {
			try {
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM chart WHERE userUserName = ? ");
				pstmt.setString(1, LoginManager.getUsername());
				ResultSet resultSet1 = pstmt.executeQuery();

				ResultSetMetaData rsMetaData = resultSet1.getMetaData();

				resultSet1.beforeFirst();
				if (resultSet1.next()) {
					PreparedStatement pstmt2 = connection.prepareStatement(
							"UPDATE chart SET receivingOffice = ?, returningOffice = ?, receivingDate = ?, returningDate = ?,"
									+ "dailyPrice = ?, vehicleName = ?, vehicleBrand = ?, vehiclePlateNumber = ? where userUserName = ? ");
					pstmt2.setString(1, null);
					pstmt2.setString(2, null);
					pstmt2.setString(3, null);
					pstmt2.setString(4, null);
					pstmt2.setString(5, null);
					pstmt2.setString(6, null);
					pstmt2.setString(7, null);
					pstmt2.setString(8, null);
					pstmt2.setString(9, LoginManager.getUsername());
					pstmt2.executeUpdate();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			redirectChartPage();
		}
	}

	/**
	 * Redirects the user to the chart page
	 */
	public void redirectChartPage() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/chart.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Receive the chart information of the corresponding user.
	 */
	public void receiveChart() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM chart WHERE userUserName = ? ");
			pstmt.setString(1, this.userName);
			ResultSet resultSet1 = pstmt.executeQuery();

			resultSet1.beforeFirst();
			while (resultSet1.next()) {
				String userUserName = resultSet1.getString("userUserName");
				
				Date returningDate = resultSet1.getDate("returningDate");
				Date receivingDate = resultSet1.getDate("receivingDate");
				returnStringDate = resultSet1.getString("returningDate");
				receiveStringDate = resultSet1.getString("receivingDate");
				
				String returningOffice = resultSet1.getString("returningOffice");
				String receivingOffice = resultSet1.getString("receivingOffice");
				String userName = resultSet1.getString("userName");
				String userSurname = resultSet1.getString("userSurname");
				String userAddress = resultSet1.getString("userAddress");
				String userPhone = resultSet1.getString("userPhone");
				String totalPrice = resultSet1.getString("totalPrice");
				String vehicleName = resultSet1.getString("vehicleName");
				String vehicleBrand = resultSet1.getString("vehicleBrand");
				String vehiclePlateNumber = resultSet1.getString("vehiclePlateNumber");
				String discountId = resultSet1.getString("discountId");
				String dailyPrice = resultSet1.getString("dailyPrice");
				this.chart = new Chart(userUserName, returningDate, receivingDate, returningOffice, receivingOffice,
						userName, userSurname, userAddress, userPhone, totalPrice, vehicleName, vehicleBrand,
						vehiclePlateNumber, discountId, dailyPrice);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void goToCheckout() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM vehicle WHERE plateNumber = ? ");
			pstmt.setString(1, this.chart.getVehiclePlateNumber());
			ResultSet resultSet1 = pstmt.executeQuery();
		    resultSet1.beforeFirst();
		    
		    String rentingStatuString = "";
		    while(resultSet1.next()) {
		    	rentingStatuString = resultSet1.getString("rentingStatus");
		    }
		    
		    if(rentingStatuString.equals("Rented")) {
				
				cleanUserCart();
				redirectChartPage();
				
				String warningMessage = "Sorry! The vehicle in your cart is already rented.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage));
				
				String warningMessage2 = "Vehicle is removed from your cart.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage2));
				
		    }else {
		    	directToCheckoutPage();
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void directToCheckoutPage() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/checkout.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cleanUserCart() {
		try {
			PreparedStatement pstmt2 = connection.prepareStatement(
					"UPDATE chart SET receivingOffice = ?, returningOffice = ?, receivingDate = ?, returningDate = ?,"
							+ "dailyPrice = ?, vehicleName = ?, vehicleBrand = ?, vehiclePlateNumber = ? where userUserName = ? ");
			pstmt2.setString(1, null);
			pstmt2.setString(2, null);
			pstmt2.setString(3, null);
			pstmt2.setString(4, null);
			pstmt2.setString(5, null);
			pstmt2.setString(6, null);
			pstmt2.setString(7, null);
			pstmt2.setString(8, null);
			pstmt2.setString(9, LoginManager.getUsername());
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String getTotalDays() {
		return totalDays;
	}

	public String getTotalPrice() {
		return totalPrice;
	}
	public boolean isShowChart() {
		return showChart;
	}
	
	public String getReceiveStringDate() {
		return receiveStringDate;
	}
	public String getReturnStringDate() {
		return returnStringDate;
	}

}
