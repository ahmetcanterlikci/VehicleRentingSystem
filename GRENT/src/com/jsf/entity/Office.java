package com.jsf.entity;

import java.sql.Connection;

public class Office {
	private String name;
	private int isDeleted;
	private String email;
	private String address;
	private String phone;
	private String fax;
	private String workingDays;
	private String workingHours;
	private String city;
	private String country;
	private Connection connection;
	public Office() {
		
	}
	public Office(String name,String email,
			String address,String phone,String fax,String workingDays,
			String workingHours,String city,String country) {
		this.name=name;
		this.email=email;
		this.address=address;
		this.phone=phone;
		this.fax=fax;
		this.workingDays=workingDays;
		this.workingHours=workingHours;
		this.city=city;
		this.country=country;
		
		
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	
}
