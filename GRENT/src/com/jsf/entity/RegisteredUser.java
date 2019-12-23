package com.jsf.entity;

import java.util.Date;

/**
 * Entity class of the RegisteredUser 
 */
public class RegisteredUser {

    private String username;
    private String isDeleted;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Date birthdate;
    private String phone;
    private String gender;
    private String address;
    private String city;
    private String country;
    private Date driverLicenseDate;
    private String discountId;

    public RegisteredUser() {

    }

    /**
	 * @param isDeleted the boolean flag which checks the corresponding element is deleted or not in the database
	 * @param driverLicenseDate the year which corresponding RegisteredUser took his driver license
	 * @param discountId a key which represents the discount id of that RegisteredUser in the Discounts table
	 */
    public RegisteredUser(String username, String isDeleted, String email, String password,
            String name, String surname, Date birthdate, String phone, String gender,
            String address, String city, String country, Date driverLicenseDate, String discountId) {
        this.username = username;
        this.isDeleted = isDeleted;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.country = country;
        this.driverLicenseDate = driverLicenseDate;
        this.discountId = discountId;

    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public Date getDriverLicenseDate() {
		return driverLicenseDate;
	}

	public void setDriverLicenseDate(Date driverLicenseDate) {
		this.driverLicenseDate = driverLicenseDate;
	}

	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}
    
    
    
}
