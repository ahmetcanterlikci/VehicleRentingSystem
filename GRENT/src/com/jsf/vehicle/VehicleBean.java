package com.jsf.vehicle;

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

import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;
import com.jsf.entity.Vehicle;

/**
 * Controller class of the search page.
 */
@ManagedBean
@ViewScoped
public class VehicleBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<String> nameSelections;
	private ArrayList<String> classSelections;
	private ArrayList<String> vehicleTypeSelections;
	private ArrayList<String> gearTypeSelections;
	private ArrayList<String> fuelTypeSelections;
	
	private String selectedPriceRange;
	private ArrayList<String> selectedNames;
	private ArrayList<String> selectedClasses;
	private ArrayList<String> selectedVehicleTypes;
	private ArrayList<String> selectedGearTypes;
	private ArrayList<String> selectedFuelTypes;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Vehicle> selectedVehicles;
	private int vehicleCount;
	private Connection connection;
	private boolean adminCheck;
	
	
	
	
	/**
	 * Initialize method of the class.
	 */
	@PostConstruct
	public void init() {
		selectedNames = new ArrayList<String>(); // prepare the attributes
		selectedClasses = new ArrayList<String>();
		selectedVehicleTypes = new ArrayList<String>();
		selectedGearTypes = new ArrayList<String>();
		selectedFuelTypes = new ArrayList<String>();
		vehicles = new ArrayList<Vehicle>();
		
		nameSelections = new ArrayList<String>(); 
		classSelections = new ArrayList<String>(); 
		vehicleTypeSelections = new ArrayList<String>(); 
		gearTypeSelections = new ArrayList<String>(); 
		fuelTypeSelections = new ArrayList<String>(); 
		
		DatabaseManager.initiliaze(); // init Database
		connection = DatabaseManager.getConnection();
		
	
		
		receiveVehicles(); // prepare the vehicles
		
		vehicleCount = vehicles.size();
		selectedVehicles = vehicles;
		initAdminControl();
		
	}
	
	public void initAdminControl() {
		if(LoginManager.isLoggedIn() && LoginManager.getRole().equals("Admin")) {
			adminCheck = true;
		}
	}
	
	/**
	 * Receive all vehicles in the system from database
	 */
	public void receiveVehicles() { // load the vehicles from database
		try {
			PreparedStatement pstmt = connection.prepareStatement(
				        "SELECT * FROM vehicle ");
	        ResultSet resultSet1 = pstmt.executeQuery();
	        
	 //       ResultSetMetaData rsMetaData = resultSet1.getMetaData();
			resultSet1.beforeFirst();
	        while (resultSet1.next()) {
	        	String plateNumber = resultSet1.getString("plateNumber");
	            String physicalStatus = resultSet1.getString("physicalStatus");
	        	String rentingStatus = resultSet1.getString("rentingStatus");
	        	int dailyprice  = resultSet1.getInt("dailyPrice");
	        	String vehicleClass = resultSet1.getString("class");
	        	String gearType = resultSet1.getString("gearType");
	        	String fuelType = resultSet1.getString("fuelType");
	        	String type = resultSet1.getString("type");
	        	String numberOfSeats = resultSet1.getString("numberOfSeats");
	        	String avaliableLuggage = resultSet1.getString("avaliableLuggage");
	            String minimumYearsOfLicense = resultSet1.getString("minimumYearsOfLicense");
	        	String airbags = resultSet1.getString("airbags");
	        	String airConditioning = resultSet1.getString("airConditioning");
	        	String currentOfficeName = resultSet1.getString("currentOfficeName");
	        	String name = resultSet1.getString("name");
	        	String brand = resultSet1.getString("brand");
	        	String modelNumber = resultSet1.getString("modelNumber");
	        	Date rentStart = resultSet1.getDate("rentStart");
	        	Date rentEnd = resultSet1.getDate("rentEnd");
	        	
	        	
	        	Vehicle newVehicle = new Vehicle(plateNumber, physicalStatus, rentingStatus, dailyprice,vehicleClass, gearType, fuelType,  type, numberOfSeats,
	        			avaliableLuggage, minimumYearsOfLicense,airbags, airConditioning,
	        			currentOfficeName, name,  brand, modelNumber, rentStart, rentEnd);
	        	
	        	vehicles.add(newVehicle);
	        	
	        }
	        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
		
		setFilters();
		
	}

	/**
	 * Action of the search button on the homepage. Filters the cars second times according to given data.
	 */
	
	/**
	 * Action of the search button on the search page. Filters the cars corresponding to the given options.
	 */
	public void filter() { // filter action
		System.out.println("filter");
		boolean c = checkForFilter();
		
		if (c == false) {
			selectedVehicles = vehicles;
		}else {
			selectedVehicles = new ArrayList<Vehicle>();
			
			for (int i = 0; i < vehicles.size(); i++) {
				Vehicle v = vehicles.get(i);
				
				boolean control = true;
				if(selectedPriceRange != null && !selectedPriceRange.equals("")) {
					if(!checkPriceRange(String.valueOf(v.getDailyprice()))) {
						control = false;
					}
				}
				if(!classSelections.isEmpty()) {
					if(!checkClasses(v.getVehicleClass())) {
						control = false;
					}
				}
				if(!fuelTypeSelections.isEmpty()) {
					if(!checkFuelTypes(v.getFuelType())) {
						control = false;
					}
				}
				if(!nameSelections.isEmpty()) {
					if(!checkNames(v.getName())) {
						control = false;
					}
				}
				if(!vehicleTypeSelections.isEmpty()) {
					if(!checkVehicleTypes(v.getType())) {
						control = false;
					}
				}
				if(!gearTypeSelections.isEmpty()) {
					if(!checkGearTypes(v.getGearType())) {
						control = false;
					}
				}
				
				if(control == true) {
					selectedVehicles.add(v);
				}
			}
		}
		
		vehicleCount = selectedVehicles.size();
		
	}
	
	/**
	 * Check whether given price is in the selected price range.
	 * @param price daily price of the vehicle
	 */
	public boolean checkPriceRange(String price) {
		
		int count = 0;
		for (int i = 0; i < selectedPriceRange.length(); i++) {
			String check = selectedPriceRange.charAt(i) + "";
			if(check.equals("-")) {
				count = i;
			}
		}

		int iprice = Integer.parseInt(price);
		
		if(count==0) {
			int temp = Integer.parseInt(selectedPriceRange);
			if(iprice >= temp) {
				return true;
			}
		}
		
		String selected1 = selectedPriceRange.substring(0,count);
		String selected2 = selectedPriceRange.substring(count+1, selectedPriceRange.length());
		
		int iselected1 = Integer.parseInt(selected1);
		int iselected2 = Integer.parseInt(selected2);
		
	
		if(iprice >= iselected1 && iprice <= iselected2) {
			return true;
		}else
			return false;
	}
	
	/**
	 * Check whether given vehicle class is in the selected vehicle classes.
	 * @param vehicleClass vehicle class of the vehicle
	 */
	public boolean checkClasses(String vehicleClass) {
		if(classSelections.contains(vehicleClass)) {
			return true;
		}else
			return false;
	}
	
	/**
	 * Check whether given fuel type is in the selected fuel types.
	 * @param fuelType fuel type of the vehicle
	 */
	public boolean checkFuelTypes(String fuelType) {
		if(fuelTypeSelections.contains(fuelType)) {
			return true;
		}else
			return false;
	}
	
	/**
	 * Check whether given vehicle name is in the selected vehicle names.
	 * @param name name of the vehicle
	 */
	public boolean checkNames(String name) {
		if(nameSelections.contains(name)) {
			return true;
		}else 
			return false;
	}
	
	/**
	 * Check whether given vehicle type is in the selected vehicle types.
	 * @param vehicleType vehicle type of the vehicle
	 */
	public boolean checkVehicleTypes(String vehicleType) {
		if(vehicleTypeSelections.contains(vehicleType)) {
			return true;
		}else
			return false;
	}
	
	/**
	 * Check whether given gear type is in the selected gear types.
	 * @param gearType gear type of the vehicle
	 */
	public boolean checkGearTypes(String gearType) {
		if(gearTypeSelections.contains(gearType)) {
			return true;
		}else
			return false;
	}
	
	/**
	 * Check whether user selected any filter options
	 */
	public boolean checkForFilter() {
		if(selectedPriceRange != null) {
			return true;
		}else if(!classSelections.isEmpty()) {
			return true;
		}else if (!fuelTypeSelections.isEmpty()) {
			return true;
		}else if (!nameSelections.isEmpty()) {
			return true;
		}else if (!vehicleTypeSelections.isEmpty()) {
			return true;
		}else if (!gearTypeSelections.isEmpty()) {
			return true;
		}else
			return false; // there is no filter selection
	}
	
	/**
	 * Initialize the filters in the filter panel 
	 */
	public void setFilters() { // find the existing filter options for the filter panel
		filterNames();
		filterClasses();
	    filterFuelType();
	    filterGearType();
	    filterVehicleTypes();
	}

	/**
	 * Initialize the filter options for the vehicle name
	 */
	public void filterNames() {
		for (int i = 0; i < vehicles.size(); i++) {
			boolean c = false;
			for (int j = 0; j < selectedNames.size(); j++) {
				if(vehicles.get(i).getName().equals(selectedNames.get(j))) {
					c = true;
				}
			}
			if (c == false ) {
				selectedNames.add(vehicles.get(i).getName());
			}
		}
	}
	
	/**
	 * Initialize the filter options for the vehicle type
	 */
	public void filterVehicleTypes() {
		for (int i = 0; i < vehicles.size(); i++) {
			boolean c = false;
			for (int j = 0; j < selectedVehicleTypes.size(); j++) {
				if(vehicles.get(i).getType().equals(selectedVehicleTypes.get(j))) {
					c = true;
				}
			}
			if (c == false ) {
				selectedVehicleTypes.add(vehicles.get(i).getType());
			}
		}
	}
	
	/**
	 * Initialize the filter options for the vehicle class
	 */
	public void filterClasses() {
		for (int i = 0; i < vehicles.size(); i++) {
			boolean c = false;
			for (int j = 0; j < selectedClasses.size(); j++) {
				if(vehicles.get(i).getVehicleClass().equals(selectedClasses.get(j))) {
					c = true;
				}
			}
			if (c == false ) {
				selectedClasses.add(vehicles.get(i).getVehicleClass());
			}
		}
	}
	
	/**
	 * Initialize the filter options for the gear type of the vehicle
	 */
	public void filterGearType() {
		for (int i = 0; i < vehicles.size(); i++) {
			boolean c = false;
			for (int j = 0; j < selectedGearTypes.size(); j++) {
				if(vehicles.get(i).getGearType().equals(selectedGearTypes.get(j))) {
					c = true;
				}
			}
			if (c == false ) {
				selectedGearTypes.add(vehicles.get(i).getGearType());
			}
		}
	}
	
	/**
	 * Initialize the filter options for the fuel type of the vehicle
	 */
	public void filterFuelType() {
		for (int i = 0; i < vehicles.size(); i++) {
			boolean c = false;
			for (int j = 0; j < selectedFuelTypes.size(); j++) {
				if(vehicles.get(i).getFuelType().equals(selectedFuelTypes.get(j))) {
					c = true;
				}
			}
			if (c == false ) {
				selectedFuelTypes.add(vehicles.get(i).getFuelType());
			}
		}
	}
	
	public ArrayList<String> getNameSelections() {
		return nameSelections;
	}

	public void setNameSelections(ArrayList<String> nameSelections) {
		this.nameSelections = nameSelections;
	}

	public ArrayList<String> getClassSelections() {
		return classSelections;
	}

	public void setClassSelections(ArrayList<String> classSelections) {
		this.classSelections = classSelections;
	}

	public ArrayList<String> getVehicleTypeSelections() {
		return vehicleTypeSelections;
	}

	public void setVehicleTypeSelections(ArrayList<String> vehicleTypeSelections) {
		this.vehicleTypeSelections = vehicleTypeSelections;
	}

	public ArrayList<String> getGearTypeSelections() {
		return gearTypeSelections;
	}

	public void setGearTypeSelections(ArrayList<String> gearTypeSelections) {
		this.gearTypeSelections = gearTypeSelections;
	}

	public ArrayList<String> getFuelTypeSelections() {
		return fuelTypeSelections;
	}

	public void setFuelTypeSelections(ArrayList<String> fuelTypeSelections) {
		this.fuelTypeSelections = fuelTypeSelections;
	}

	public String getSelectedPriceRange() {
		return selectedPriceRange;
	}

	public void setSelectedPriceRange(String selectedPriceRange) {
		this.selectedPriceRange = selectedPriceRange;
	}

	public ArrayList<String> getSelectedNames() {
		return selectedNames;
	}

	public void setSelectedNames(ArrayList<String> selectedNames) {
		this.selectedNames = selectedNames;
	}

	public ArrayList<String> getSelectedClasses() {
		return selectedClasses;
	}

	public void setSelectedClasses(ArrayList<String> selectedClasses) {
		this.selectedClasses = selectedClasses;
	}

	public ArrayList<String> getSelectedVehicleTypes() {
		return selectedVehicleTypes;
	}

	public void setSelectedVehicleTypes(ArrayList<String> selectedVehicleTypes) {
		this.selectedVehicleTypes = selectedVehicleTypes;
	}

	public ArrayList<String> getSelectedGearTypes() {
		return selectedGearTypes;
	}

	public void setSelectedGearTypes(ArrayList<String> selectedGearTypes) {
		this.selectedGearTypes = selectedGearTypes;
	}

	public ArrayList<String> getSelectedFuelTypes() {
		return selectedFuelTypes;
	}

	public void setSelectedFuelTypes(ArrayList<String> selectedFuelTypes) {
		this.selectedFuelTypes = selectedFuelTypes;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<Vehicle> getSelectedVehicles() {
		return selectedVehicles;
	}

	public void setSelectedVehicles(ArrayList<Vehicle> selectedVehicles) {
		this.selectedVehicles = selectedVehicles;
	}
	
	public int getVehicleCount() {
		return vehicleCount;
	}
	
	public void setVehicleCount(int vehicleCount) {
		this.vehicleCount = vehicleCount;
	}
	
	public boolean isAdminCheck() {
		return adminCheck;
	}

}
