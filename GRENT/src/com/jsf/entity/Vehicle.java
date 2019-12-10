package com.jsf.entity;

public class Vehicle {
	private String plateNumber;
	private String physicalStatus;
	private String rentingStatus;
	private int dailyprice;
	private String vehicleClass;
	private String gearType;
	private String fuelType;
	private String type;
	private String numberOfSeats;
	private String avaliableLuggage;
	private String minimumYearsOfLicense;
	private String airbags;
	private String airConditioning;
	private String currentOfficeName;
	private String name;
	private String brand;

	public Vehicle() {

	}

	public Vehicle(String plateNumber, String physicalStatus, String rentingStatus, int dailyprice,
			String vehicleClass, String gearType, String fuelType, String type, String numberOfSeats,
			String avaliableLuggage, String minimumYearsOfLicense, String airbags, String airConditioning,
			String currentOfficeName, String name, String brand) {
		this.plateNumber = plateNumber;
		this.physicalStatus = physicalStatus;
		this.rentingStatus = rentingStatus;
		this.dailyprice = dailyprice;
		this.vehicleClass = vehicleClass;
		this.gearType = gearType;
		this.fuelType = fuelType;
		this.type = type;
		this.numberOfSeats = numberOfSeats;
		this.avaliableLuggage = avaliableLuggage;
		this.minimumYearsOfLicense = minimumYearsOfLicense;
		this.airbags = airbags;
		this.airConditioning = airConditioning;
		this.currentOfficeName = currentOfficeName;
		this.name = name;
		this.brand = brand;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getPhysicalStatus() {
		return physicalStatus;
	}

	public void setPhysicalStatus(String physicalStatus) {
		this.physicalStatus = physicalStatus;
	}

	public String getRentingStatus() {
		return rentingStatus;
	}

	public void setRentingStatus(String rentingStatus) {
		this.rentingStatus = rentingStatus;
	}

	public int getDailyprice() {
		return dailyprice;
	}

	public void setDailyprice(int dailyprice) {
		this.dailyprice = dailyprice;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getGearType() {
		return gearType;
	}

	public void setGearType(String gearType) {
		this.gearType = gearType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(String numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getAvaliableLuggage() {
		return avaliableLuggage;
	}

	public void setAvaliableLuggage(String avaliableLuggage) {
		this.avaliableLuggage = avaliableLuggage;
	}

	public String getMinimumYearsOfLicense() {
		return minimumYearsOfLicense;
	}

	public void setMinimumYearsOfLicense(String minimumYearsOfLicense) {
		this.minimumYearsOfLicense = minimumYearsOfLicense;
	}

	public String getAirbags() {
		return airbags;
	}

	public void setAirbags(String airbags) {
		this.airbags = airbags;
	}

	public String getAirConditioning() {
		return airConditioning;
	}

	public void setAirConditioning(String airConditioning) {
		this.airConditioning = airConditioning;
	}

	public String getCurrentOfficeName() {
		return currentOfficeName;
	}

	public void setCurrentOfficeName(String currentOfficeName) {
		this.currentOfficeName = currentOfficeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	

}