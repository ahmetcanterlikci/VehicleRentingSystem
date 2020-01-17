package com.jsf.renting;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;
import com.jsf.entity.Renting;

@ManagedBean
@ViewScoped
public class RentingDetailsBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Renting renting;
	private Connection connection;
	private boolean officeUserCheck;
	private boolean adminCheck;
	private Date maxdate;
	private Date receiveStringDate;
	private Date returnStringDate;
	private Date rentingStringDate;
	
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze(); // init Database
		connection = DatabaseManager.getConnection();
		
		receiveRenting();
		initAuthentication();
		this.maxdate = new Date();
	}
	
	
	public void initAuthentication() {
	
		if(LoginManager.isLoggedIn()) {
			if(LoginManager.getRole().equals("OfficeUser")) {
				officeUserCheck = true;
			}else if(LoginManager.getRole().equals("Admin")) {
				adminCheck = true;
			}
		}
	}
	
	public void receiveRenting() {
		this.renting = RentingDetailsBeanStatic.getRenting();
		try {
			this.receiveStringDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(renting.getReceiveStringDate());
			this.returnStringDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(renting.getRentingStringDate());
			this.rentingStringDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(renting.getRentingStringDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveOfficeUser() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("UPDATE renting SET status = ? where id = ? ");
			pstmt.setString(1, renting.getStatus());
			pstmt.setString(2, renting.getId());
			pstmt.executeUpdate();
			
			String warningMessage = "Saved succesfully.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(warningMessage));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void saveAdmin() {
		try {
			
			PreparedStatement pstmt = connection.prepareStatement("UPDATE renting SET receivingDate = ?, returningDate = ?, returningOffice = ? ,receivingOffice = ?, rentingDate = ?, userName = ?, userSurname = ?, userAddress = ?, userUserName = ?, "
					+ "userPhone = ?, paymentType = ?, totalPrice = ?, socialSecurityNo = ?, vehicleName = ?, vehicleBrand = ?, vehiclePlateNumber = ?, status = ?, orderNumber = ? where id = ? ");
			
			java.sql.Timestamp receivingTimestamp = new java.sql.Timestamp(receiveStringDate.getTime());
			java.sql.Timestamp returningTimestamp = new java.sql.Timestamp(returnStringDate.getTime());
			java.sql.Timestamp rentingTimestamp = new java.sql.Timestamp(rentingStringDate.getTime());
			
			pstmt.setTimestamp(1, receivingTimestamp);
			pstmt.setTimestamp(2, returningTimestamp);
			pstmt.setString(3, renting.getReturningOffice());
			pstmt.setString(4, renting.getReceivingOffice());
			pstmt.setTimestamp(5, rentingTimestamp);
			pstmt.setString(6, renting.getUserName());
			pstmt.setString(7, renting.getUserSurname());
			pstmt.setString(8, renting.getUserAddress());
			pstmt.setString(9, renting.getUserUserName());
			pstmt.setString(10, renting.getUserPhone());
			pstmt.setString(11, renting.getPaymentType());
			pstmt.setString(12, renting.getTotalPrice());
			pstmt.setString(13, renting.getSocialSecurityNo());
			pstmt.setString(14, renting.getVehicleName());
			pstmt.setString(15, renting.getVehicleBrand());
			pstmt.setString(16, renting.getVehiclePlateNumber());
			pstmt.setString(17, renting.getStatus());
			pstmt.setString(18, renting.getOrderNumber());
			pstmt.setString(19, renting.getId());
			pstmt.executeUpdate();
			
			String warningMessage = "Saved succesfully.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(warningMessage));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Renting getRenting() {
		return renting;
	}
	
	public void setRenting(Renting renting) {
		this.renting = renting;
	}
	
	public boolean isAdminCheck() {
		return adminCheck;
	}
	
	public boolean isOfficeUserCheck() {
		return officeUserCheck;
	}
	
	public Date getMaxdate() {
		return maxdate;
	}
	
	public Date getReceiveStringDate() {
		return receiveStringDate;
	}
	
	public void setReceiveStringDate(Date receiveStringDate) {
		this.receiveStringDate = receiveStringDate;
	}
	
	public Date getRentingStringDate() {
		return rentingStringDate;
	}
	
	public void setRentingStringDate(Date rentingStringDate) {
		this.rentingStringDate = rentingStringDate;
	}
	
	public Date getReturnStringDate() {
		return returnStringDate;
	}
	
	public void setReturnStringDate(Date returnStringDate) {
		this.returnStringDate = returnStringDate;
	}
	

}
