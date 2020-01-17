package com.jsf.renting;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.jsf.database.DatabaseManager;
import com.jsf.entity.Renting;

@ManagedBean
@ViewScoped
public class RentingsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Renting renting;
	private Connection connection;
	private ArrayList<Renting> customerRentings;
	private String searchText;

	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze(); // init Database
		connection = DatabaseManager.getConnection();
		customerRentings = new ArrayList<Renting>();

		receiveRentings();
	}

	public String initTotals(Date returning, Date receiving) {
		long diff = returning.getTime() - receiving.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		String totalDays = "";
		if (diffDays == 0 && diffHours != 0) {
			totalDays = "1";
		} else if (diffDays != 0) {
			totalDays = String.valueOf(diffDays);
		}
		return totalDays;
	}

	public void receiveRentings() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM renting ORDER BY rentingDate desc ");
			ResultSet rs1 = pstmt.executeQuery();

			while (rs1.next()) {
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

				Renting newRenting = new Renting(id, userUserName, returningDate, receivingDate, rentingDate,
						returningOffice, receivingOffice, userName, userSurname, userAddress, userPhone, totalPrice,
						vehicleName, vehicleBrand, vehiclePlateNumber, status, orderNumber, socialSecurityNo,
						paymentType, receiveStringDate, returnStringDate, rentingStringDate, totalDays);

				customerRentings.add(newRenting);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void search() {

		if(!searchText.equals("") && searchText != null ) {
			try {
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM registereduser where email = ? ");
				pstmt.setString(1, searchText);
				ResultSet rs1 = pstmt.executeQuery();
				
				String username = "";
				while(rs1.next()) {
					username = rs1.getString("username");
				}
				
				
				PreparedStatement pstmt2 = connection.prepareStatement("SELECT * FROM renting WHERE userUserName = ? ORDER BY rentingDate desc ");
				pstmt2.setString(1, username);
				ResultSet rs2 = pstmt2.executeQuery();

				customerRentings.clear();
				while (rs2.next()) {
					String id = rs2.getString("id");
					String userUserName = rs2.getString("userUserName");
					Date returningDate = rs2.getDate("returningDate");
					Date receivingDate = rs2.getDate("receivingDate");
					Date rentingDate = rs2.getDate("rentingDate");

					String returnStringDate = rs2.getString("returningDate");
					String receiveStringDate = rs2.getString("receivingDate");
					String rentingStringDate = rs2.getString("rentingDate");
					String totalDays = initTotals(returningDate, receivingDate);

					String returningOffice = rs2.getString("returningOffice");
					String receivingOffice = rs2.getString("receivingOffice");
					String userName = rs2.getString("userName");
					String userSurname = rs2.getString("userSurname");
					String userAddress = rs2.getString("userAddress");
					String userPhone = rs2.getString("userPhone");
					String totalPrice = rs2.getString("totalPrice");
					String vehicleName = rs2.getString("vehicleName");
					String vehicleBrand = rs2.getString("vehicleBrand");
					String vehiclePlateNumber = rs2.getString("vehiclePlateNumber");
					String status = rs2.getString("status");
					String orderNumber = rs2.getString("orderNumber");
					String socialSecurityNo = rs2.getString("socialSecurityNo");
					String paymentType = rs2.getString("paymentType");

					Renting newRenting = new Renting(id, userUserName, returningDate, receivingDate, rentingDate,
							returningOffice, receivingOffice, userName, userSurname, userAddress, userPhone, totalPrice,
							vehicleName, vehicleBrand, vehiclePlateNumber, status, orderNumber, socialSecurityNo,
							paymentType, receiveStringDate, returnStringDate, rentingStringDate, totalDays);

					customerRentings.add(newRenting);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void update(Renting renting) {

		RentingDetailsBeanStatic.setRenting(renting);
		directToRentingDetailsPage();
	}
	
	public void directToRentingDetailsPage() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/rentingdetails.xhtml");
		} catch (IOException e) {
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

	public ArrayList<Renting> getCustomerRentings() {
		return customerRentings;
	}

	public void setCustomerRentings(ArrayList<Renting> customerRentings) {
		this.customerRentings = customerRentings;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	

}
