package com.jsf.search;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.timeline.DefaultTimelineUpdater;

import com.jsf.database.DatabaseManager;
import com.jsf.entity.Office;
import com.jsf.entity.Vehicle;
import com.sun.org.apache.bcel.internal.generic.NEW;

@ManagedBean
@ViewScoped
public class SearchBean implements Serializable{
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
	
	private String receivingOffice;
	private String returningOffice;
	private Date receivingDate;
	private Date returningDate;
	private boolean staticCheck;
	
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
		
		receiveStaticData(); // check the static data
		
		receiveVehicles(); // prepare the vehicles
		
		vehicleCount = vehicles.size();
		selectedVehicles = vehicles;
		
	}
	
	public void receiveStaticData() {
		this.receivingOffice=SearchBeanStatic.getReceivingOffice();
		this.returningOffice=SearchBeanStatic.getReturningOffice();
		this.receivingDate = SearchBeanStatic.getReceivingDate();
		this.returningDate = SearchBeanStatic.getReturningDate();
		
		if(receivingDate!=null && receivingOffice!=null && returningDate !=null && receivingDate!=null) {
			this.staticCheck = true;
		}
	}
	
	public void receiveVehicles() { // load the vehicles from database
		try {
			PreparedStatement pstmt = connection.prepareStatement(
				        "SELECT * FROM vehicle ");
	        ResultSet resultSet1 = pstmt.executeQuery();
	        
	        ResultSetMetaData rsMetaData = resultSet1.getMetaData();
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
		
		if(this.staticCheck) {
			searchAction();
		}
		
		setFilters();
		
	}

	public void searchAction() {
		
	    ArrayList<Vehicle> newvehiclesArrayList = new ArrayList<Vehicle>();
	    
	    for (int i = 0; i < vehicles.size(); i++) {
	    	Vehicle vehicle = vehicles.get(i);
			if(vehicle.getRentingStatus().equalsIgnoreCase("Not Rented")) {
				if(vehicle.getCurrentOfficeName().equals(receivingOffice)) {
					newvehiclesArrayList.add(vehicle);
				}
			}else {
				if(vehicle.getRentEnd().before(receivingDate) && vehicle.getRentStart().after(returningDate) && 
						vehicle.getCurrentOfficeName().equals(receivingOffice)) {
					newvehiclesArrayList.add(vehicle);
				}
			}
		}
	    
	    vehicles = newvehiclesArrayList;
	    
	}
	
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
	
	
	public boolean checkClasses(String vehicleClass) {
		if(classSelections.contains(vehicleClass)) {
			return true;
		}else
			return false;
	}
	
	public boolean checkFuelTypes(String fuelType) {
		if(fuelTypeSelections.contains(fuelType)) {
			return true;
		}else
			return false;
	}
	
	public boolean checkNames(String name) {
		if(nameSelections.contains(name)) {
			return true;
		}else 
			return false;
	}
	
	public boolean checkVehicleTypes(String vehicleType) {
		if(vehicleTypeSelections.contains(vehicleType)) {
			return true;
		}else
			return false;
	}
	
	public boolean checkGearTypes(String gearType) {
		if(gearTypeSelections.contains(gearType)) {
			return true;
		}else
			return false;
	}
	
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
	
	public void setFilters() { // find the existing filter options for the filter panel
		filterNames();
		filterClasses();
	    filterFuelType();
	    filterGearType();
	    filterVehicleTypes();
	}

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

}
