package com.jsf.chart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;
import com.jsf.entity.Chart;

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
	
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze(); // init Database
		connection = DatabaseManager.getConnection();

		if (LoginManager.isLoggedIn()) {
			this.userName = LoginManager.getUsername();
			this.loggedIn = true;
		}

		receiveChart();
		initTotals();
	}
	
	public void initTotals() {
		long diff = this.chart.getReturningDate().getTime()-this.chart.getReceivingDate().getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		if(diffDays == 0 && diffHours != 0) {
			totalDays = "1";
			totalPrice=chart.getDailyPrice();
		}else if(diffDays != 0){
			totalDays=String.valueOf(diffDays);
			long dailyPrice = Long.parseLong(chart.getDailyPrice());
			long totalPrice = dailyPrice*diffDays;
			this.totalPrice = String.valueOf(totalPrice);
		}
		
	}

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

}
