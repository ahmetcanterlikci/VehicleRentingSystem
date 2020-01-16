package checkout;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.jsf.entity.RegisteredUser;

@ManagedBean
@ViewScoped
public class CheckoutBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private RegisteredUser registeredUser;
	private String namesurname;
	private String creditcardno;
	private String expdate;
	private String cvc2;
	private Date maxdate;
	private Chart chart;
	private String totalDays;
	private String totalPrice;
	private String scn;
	private String returnStringDate;
	private String receiveStringDate;
	
	
	/**
	 * Initialize method of the class
	 */
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze();
		connection = DatabaseManager.getConnection();
		receiveAccountInfo();
		receiveChart();
		initTotals();
		this.maxdate = new Date();
	}
	
	/**
	 * Receives the data of the corresponding RegisteredUser
	 */
	public void receiveAccountInfo() {
		if (LoginManager.isLoggedIn()) {
			try {
				PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM registereduser WHERE username = ?  ");
				pstmt.setString(1, LoginManager.getUsername());
				ResultSet resultSet1 = pstmt.executeQuery();

				resultSet1.beforeFirst();
				if (resultSet1.next()) {
					String username = resultSet1.getString("username");
					String isDeleted = resultSet1.getString("isDeleted");
					String email = resultSet1.getString("email");
					String password = resultSet1.getString("password");
					String name = resultSet1.getString("name");
					String surname = resultSet1.getString("surname");
					Date birthdate = resultSet1.getDate("birthdate");
					String phone = resultSet1.getString("phone");
					String gender = resultSet1.getString("gender");
					String address = resultSet1.getString("address");
					String city = resultSet1.getString("city");
					String country = resultSet1.getString("country");
					Date driverLicenseDate = resultSet1.getDate("driverLicenseDate");
					String discountId = resultSet1.getString("discountId");
					RegisteredUser newRegisteredUser = new RegisteredUser(username, isDeleted, email, password, name,
							surname, birthdate, phone, gender, address, city, country, driverLicenseDate, discountId);
					this.registeredUser = newRegisteredUser;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void receiveChart() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM chart WHERE userUserName = ? ");
			pstmt.setString(1, LoginManager.getUsername());
			ResultSet resultSet1 = pstmt.executeQuery();

			resultSet1.beforeFirst();
			while (resultSet1.next()) {
				String userUserName = resultSet1.getString("userUserName");
				Date returningDate = resultSet1.getDate("returningDate");
				Date receivingDate = resultSet1.getDate("receivingDate");
				
				String returningOffice = resultSet1.getString("returningOffice");
				String receivingOffice = resultSet1.getString("receivingOffice");
				returnStringDate = resultSet1.getString("returningDate");
				receiveStringDate = resultSet1.getString("receivingDate");
				
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
	
	public void initTotals() {
		
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
	
	public void completeTheOrder() {
		boolean check = false;
		
		if(this.namesurname.equals("") || this.namesurname == null || this.creditcardno.equals("") || this.creditcardno == null ||
			this.cvc2.equals("") || this.cvc2 == null || this.expdate == null){
			check = true;
				String warningMessage1 = "Invalid payment information.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
		}
			
	    if(this.registeredUser.getName().equals("") || this.registeredUser.getName() == null || this.registeredUser.getSurname().equals("") || 
	    		this.registeredUser.getSurname() == null || this.registeredUser.getEmail().equals("") || this.registeredUser.getEmail() == null ||
	    		this.registeredUser.getPhone().equals("") || this.registeredUser.getPhone() == null || 
	    		this.registeredUser.getCity().equals("") || this.registeredUser.getCity() == null ||
	    		this.registeredUser.getCountry().equals("") || this.registeredUser.getCountry() == null ||
	    		this.registeredUser.getAddress().equals("") || this.registeredUser.getAddress() == null ||
	    		this.scn.equals("") || this.scn == null) {
	    	check = true;
	    	
	    	String warningMessage2 = "Invalid personal information.";
			FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage2));
	    }
	    
	    if (!check) {
	    	createRenting(); // create the renting in the db
	    	setVehicleToRented(); // vehicle is rented
	    	cleanUserCart(); // clear the cart of the user
	    	directToCompleteOrderPage(); // direct the user to the order completed page
	    }
		
	}
	
	public void createRenting() {
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("INSERT INTO renting(receivingDate, returningDate, returningOffice, "
							+ "receivingOffice, rentingDate, userName, userSurname, userAddress, userUserName, userPhone, paymentType, totalPrice, "
							+ "socialSecurityNo, vehicleName, vehicleBrand, vehiclePlateNumber, status, orderNumber) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			Date returnDateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.returnStringDate);
			Date receiveDateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.receiveStringDate);
			java.sql.Timestamp receivingTimestamp = new java.sql.Timestamp(receiveDateFormatted.getTime());
			java.sql.Timestamp returningTimestamp = new java.sql.Timestamp(returnDateFormatted.getTime());
//			pstmt.setDate(1, new java.sql.Date(chart.getReceivingDate().getTime()));
//			pstmt.setDate(2, new java.sql.Date(chart.getReturningDate().getTime()));
			
			pstmt.setTimestamp(1, receivingTimestamp);
			pstmt.setTimestamp(2, returningTimestamp);
			
			pstmt.setString(3, chart.getReturningOffice());
			pstmt.setString(4, chart.getReceivingOffice());
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
			pstmt.setString(6, this.registeredUser.getName());
			pstmt.setString(7, this.registeredUser.getSurname());
			pstmt.setString(8, this.registeredUser.getAddress());
			pstmt.setString(9, this.registeredUser.getUsername());
			pstmt.setString(10, this.registeredUser.getPhone());
			pstmt.setString(11, "Credit/Debit Card");
			pstmt.setString(12, this.totalPrice);
			pstmt.setString(13, this.scn);
			pstmt.setString(14, chart.getVehicleName());
			pstmt.setString(15, chart.getVehicleBrand());
			pstmt.setString(16, chart.getVehiclePlateNumber());
			pstmt.setString(17, "Active");
			String orderNoString = findLastOrder();
			pstmt.setString(18,orderNoString);
			pstmt.executeUpdate();
			CompleteOrderBeanStatic.setOrderNoString(orderNoString);
		} catch (SQLException | ParseException e) {
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
	
	public void setVehicleToRented() {
		try {
			PreparedStatement pstmt2 = connection.prepareStatement(
					"UPDATE vehicle SET rentingStatus = ?, rentStart = ?, rentEnd = ? where plateNumber = ? ");
			pstmt2.setString(1, "Rented");
			pstmt2.setDate(2, new java.sql.Date(this.chart.getReceivingDate().getTime()));
			pstmt2.setDate(3, new java.sql.Date(this.chart.getReturningDate().getTime()));
			pstmt2.setString(4, this.chart.getVehiclePlateNumber());
			pstmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String findLastOrder() {
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT id FROM renting order by id desc limit 1");
			ResultSet rs1 = pstmt.executeQuery();
			rs1.beforeFirst();
			
			int a = 0;
			while(rs1.next()) {
				a = rs1.getInt("id");
			}
			a = a + 1 + 10000 ;
			String resultString = "ORD" + a;
			return resultString;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void directToCompleteOrderPage() {
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		try {
		    ec.redirect(ec.getRequestContextPath()
		            + "/completeorder.xhtml");
		    return;
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	public String getNamesurname() {
		return namesurname;
	}

	public void setNamesurname(String namesurname) {
		this.namesurname = namesurname;
	}

	public String getCreditcardno() {
		return creditcardno;
	}

	public void setCreditcardno(String creditcardno) {
		this.creditcardno = creditcardno;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public String getCvc2() {
		return cvc2;
	}

	public void setCvc2(String cvc2) {
		this.cvc2 = cvc2;
	}
	
	public Date getMaxdate() {
		return maxdate;
	}

	public Chart getChart() {
		return chart;
	}
	
	public String getTotalDays() {
		return totalDays;
	}
	
	public String getTotalPrice() {
		return totalPrice;
	}
	
	public String getScn() {
		return scn;
	}
	
	public void setScn(String scn) {
		this.scn = scn;
	}
	public String getReceiveStringDate() {
		return receiveStringDate;
	}
	public String getReturnStringDate() {
		return returnStringDate;
	}

}
