package com.jsf.userprofile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;
import com.jsf.entity.Renting;

@ManagedBean
@ViewScoped
public class MyRentingsBean {
	private Renting renting;
	private Connection connection;
	private ArrayList<Renting> completedRentings;
	private ArrayList<Renting> activeRentings;
	
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze(); // init Database
		connection = DatabaseManager.getConnection();
		completedRentings = new ArrayList<Renting>();
		activeRentings = new ArrayList<Renting>();
		
		receiveRentings();
	}
	
	public String initTotals(Date returning, Date receiving) {
			long diff = returning.getTime() - receiving.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			String totalDays="";
			if (diffDays == 0 && diffHours != 0) {
				totalDays = "1";
			} else if (diffDays != 0) {
				totalDays = String.valueOf(diffDays);
			}
			return totalDays;
	}
	
	public void receiveRentings() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM renting WHERE userUserName = ? ");
			pstmt.setString(1, LoginManager.getUsername());
			
			ResultSet rs1 = pstmt.executeQuery();
			
			while(rs1.next()) {
				String id = rs1.getString("id");
				 String userUserName = rs1.getString("userUserName");
			     Date returningDate = rs1.getDate("returningDate");
			     Date receivingDate = rs1.getDate("receivingDate");
			     Date rentingDate = rs1.getDate("rentingDate");
			     
			     String returnStringDate = rs1.getString("returningDate");
				 String receiveStringDate = rs1.getString("receivingDate");
				 String rentingStringDate = rs1.getString("rentingDate");
				 String totalDays = initTotals(returningDate, receivingDate);
				 
			     String returningOffice = rs1.getString("returningOffice");
			     String receivingOffice = rs1.getString("receivingOffice");
			     String userName = rs1.getString("userName");
			     String userSurname = rs1.getString("userSurname");
			     String userAddress = rs1.getString("userAddress");
			     String userPhone = rs1.getString("userPhone");
			     String totalPrice = rs1.getString("totalPrice");
			     String vehicleName = rs1.getString("vehicleName");
			     String vehicleBrand = rs1.getString("vehicleBrand");
			     String vehiclePlateNumber = rs1.getString("vehiclePlateNumber");
			     String status = rs1.getString("status");
			     String orderNumber = rs1.getString("orderNumber");
			     String socialSecurityNo = rs1.getString("socialSecurityNo");
			     String paymentType = rs1.getString("paymentType");
			     
			     Renting newRenting = new Renting( id,  userUserName,  returningDate,  receivingDate,  rentingDate,
			 returningOffice,  receivingOffice,  userName,  userSurname,  userAddress,
			 userPhone,  totalPrice,  vehicleName,  vehicleBrand,  vehiclePlateNumber,
			 status,  orderNumber,  socialSecurityNo,  paymentType, receiveStringDate, returnStringDate, rentingStringDate, totalDays);
			     
			     this.renting = newRenting;
			     if(status.equals("Active")) {
			    	 activeRentings.add(newRenting);
			     }else {
					completedRentings.add(newRenting);
				}
			}
			
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

	public ArrayList<Renting> getCompletedRentings() {
		return completedRentings;
	}

	public void setCompletedRentings(ArrayList<Renting> completedRentings) {
		this.completedRentings = completedRentings;
	}

	public ArrayList<Renting> getActiveRentings() {
		return activeRentings;
	}

	public void setActiveRentings(ArrayList<Renting> activeRentings) {
		this.activeRentings = activeRentings;
	}
	

}
