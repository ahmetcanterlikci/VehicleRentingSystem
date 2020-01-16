package com.jsf.vehicle;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.jsf.database.DatabaseManager;
import com.jsf.entity.Vehicle;

/**
 * Controller class of the search page.
 */
@ManagedBean
@ViewScoped
public class VehicleAddBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String plateNumber;
	private Boolean isDeleted;
	private String physicalStatus;
	private String rentingStatus;
	private int dailyPrice;
	private String classes;
	private String gearType;
	private String fuelType;
	private String type;
	private int numberOfSeats;
	private String avaliableLuggage;
	private int minimumYearsOfLicense;
	private String airbags;
	private String airConditioning;
	private String currentOfficeName;
	private String name;
	private String brand;
	private int modelNumber;
	private String rentStart;
	private String rentEnd;
	private Connection connection;

	
	/**
	 * Initialize method of the class
	 */
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze();
		connection = DatabaseManager.getConnection();
	}
	
	public void addVehicles() { // load the vehicles from database
		try {
			PreparedStatement pstmt7 = connection.prepareStatement(
					"INSERT INTO vehicle (plateNumber,isDeleted,physicalStatus,rentingStatus,dailyPrice,classes,gearType,fuelType,"
					+ "type,numberOfSeats,avaliableLuggage,minimumYearsOfLicense,airbags,airConditioning,currentOfficeName,name,brand,modelNumber,"
					+ "rentStart,rentEnd) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			pstmt7.setString(1, plateNumber);
			pstmt7.setString(2, "False");
			pstmt7.setString(3, "Good");
			pstmt7.setString(4, "Not Rented");
			pstmt7.setInt(5, dailyPrice);
			pstmt7.setString(6, classes);
			pstmt7.setString(7, gearType);
			pstmt7.setString(8, fuelType);
			pstmt7.setString(9, type);
			pstmt7.setInt(10, numberOfSeats);
			pstmt7.setString(11, avaliableLuggage);
			pstmt7.setInt(12, minimumYearsOfLicense);
			pstmt7.setString(13, airbags);
			pstmt7.setString(14, airConditioning);
			pstmt7.setString(15, currentOfficeName);
			pstmt7.setString(16, name);
			pstmt7.setString(17, brand);
			pstmt7.setInt(18, modelNumber);
			pstmt7.setString(19, "null");
			pstmt7.setString(20, "null");
			pstmt7.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}
	
	/**
	 * Check whether given ResultSet is includes any row.
	 * 
	 * @return true if includes any row, else false
	 * @param resultSet java.sql.ResultSet object from validateUser()
	 */
	public boolean checkDB(ResultSet resultSet) {
		try {
			ResultSetMetaData rsMetaData = resultSet.getMetaData();
			resultSet.beforeFirst();
			while (resultSet.next()) {
				int size = rsMetaData.getColumnCount();
				if (size > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}