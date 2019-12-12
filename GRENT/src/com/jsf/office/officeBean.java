package com.jsf.office;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.jsf.entity.Office;

@ManagedBean
@ViewScoped
public class officeBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<String> nameSelections;
	private ArrayList<String> classSelections;
	private ArrayList<String> vehicleTypeSelections;
	private ArrayList<String> gearTypeSelections;
	private ArrayList<String> fuelTypeSelections;
	
	private String selectedPriceRange;
	private ArrayList<String> selectedNames;
	private ArrayList<String> selectedCities;
	private ArrayList<String> selectedVehicleTypes;
	private ArrayList<String> selectedGearTypes;
	private ArrayList<String> selectedFuelTypes;
	private ArrayList<Office> Offices;
	private ArrayList<Office> selectedOffices;
	private int officeCount;       
	
	@PostConstruct
	public void init() {
		selectedNames = new ArrayList<String>(); // prepare the attributes
		selectedCities = new ArrayList<String>();
		selectedVehicleTypes = new ArrayList<String>();
		selectedGearTypes = new ArrayList<String>();
		selectedFuelTypes = new ArrayList<String>();
		Offices = new ArrayList<Office>();
		selectedOffices = Offices;
		
		nameSelections = new ArrayList<String>(); 
		classSelections = new ArrayList<String>(); 
		vehicleTypeSelections = new ArrayList<String>(); 
		gearTypeSelections = new ArrayList<String>(); 
		fuelTypeSelections = new ArrayList<String>(); 
		
		receiveOffices(); // prepare the vehicles
		
		officeCount = Offices.size();
		
	}
	
	public void receiveOffices() { // load the vehicles from database
		Office v1 = new Office("Bursa Office", "bursa.grent@hotmail.com", "Keles", "04582979375","0850852917",
			"Pazartesi-Cuma", "7.30-23.45", "Bursa","Turkey");
		
		Offices.add(v1);
		
		
		//setFilters();
		
	}
	
	public void filter() { // filter action
		System.out.println("filter");
		boolean c = checkForFilter();
		
		if (c == false) {
			selectedOffices = Offices;
		}else {
			selectedOffices = new ArrayList<Office>();
			
			for (int i = 0; i < Offices.size(); i++) {
				Office v = Offices.get(i);
				
				boolean control = true;
			/*	if(selectedPriceRange != null && !selectedPriceRange.equals("")) {
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
				*/
				if(control == true) {
					selectedOffices.add(v);
				}
			}
		}
		
		officeCount = selectedOffices.size();
		
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
		}
		else if(!classSelections.isEmpty()) {
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
	/*
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
	}*/

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


	public ArrayList<String> getSelectedCities() {
		return selectedCities;
	}

	public void setSelectedCities(ArrayList<String> selectedCities) {
		this.selectedCities = selectedCities;
	}

	public void setSelectedClasses(ArrayList<String> selectedClasses) {
		this.selectedCities = selectedClasses;
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

	public ArrayList<Office> getOffices() {
		return Offices;
	}

	public void setOffices(ArrayList<Office> offices) {
		Offices = offices;
	}

	public ArrayList<Office> getSelectedOffices() {
		return selectedOffices;
	}

	public void setSelectedOffices(ArrayList<Office> selectedOffices) {
		this.selectedOffices = selectedOffices;
	}

	public int getOfficeCount() {
		return officeCount;
	}

	public void setOfficeCount(int officeCount) {
		this.officeCount = officeCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
//getter setters

}
