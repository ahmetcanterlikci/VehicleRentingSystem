package com.jsf.entity;

import java.util.Date;

/**
 * Entity class of the Chart object
 */
public class Chart {

    private String userUserName;
    private Date returningDate;
    private Date receivingDate;
    private String returningOffice;
    private String receivingOffice;
    private String userName;
    private String userSurname;
    private String userAddress;
    private String userPhone;
    private String totalPrice;
    private String vehicleName;
    private String vehicleBrand;
    private String vehiclePlateNumber;
    private String discountId;
    private String dailyPrice;

    public Chart() {

    }

    /**
	 * @param userUserName username of the corresponding user
	 * @param returningDate returning date of the renting
	 * @param receivingDate receiving date of the renting
	 * @param returningOffice returning office of the renting
	 * @param receivingOffice receiving office of the renting
	 * @param dailyPrice daily price of the selected car to rent
	 * @param vehiclePlateNumber plate number of the selected car to rent
	 * @param vehicleName name of the selected car to rent
	 */
    public Chart(String userUserName, Date returningDate, Date receivingDate, String returningOffice, String receivingOffice,
            String userName, String userSurname, String userAddress, String userPhone, String totalPrice, String vehicleName,
            String vehicleBrand, String vehiclePlateNumber, String discountId, String dailyPrice) {
        this.userUserName = userUserName;
        this.returningDate = returningDate;
        this.receivingDate = receivingDate;
        this.returningOffice = returningOffice;
        this.receivingOffice = receivingOffice;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.totalPrice = totalPrice;
        this.vehicleName = vehicleName;
        this.vehicleBrand = vehicleBrand;
        this.vehiclePlateNumber = vehiclePlateNumber;
        this.discountId = discountId;
        this.dailyPrice=dailyPrice;

    }
    
    public String getUserUserName() {
		return userUserName;
    }

    public void setUserUserName(String userUserName) {
		this.userUserName = userUserName;
    }
    
    public Date getReturningDate() {
		return returningDate;
    }

    public void setReturningDate(Date returningDate) {
		this.returningDate = returningDate;
    }
    
    public Date getReceivingDate() {
		return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
    }
    
    public String getReturningOffice() {
		return returningOffice;
    }

    public void setReturningOffice(String returningOffice) {
		this.returningOffice = returningOffice;
    }
    
    public String getReceivingOffice() {
		return receivingOffice;
    }

    public void setReceivingOffice(String receivingOffice) {
		this.receivingOffice = receivingOffice;
    }
    
    public String getUserName() {
		return userName;
    }

    public void setUserName(String userName) {
		this.userName = userName;
    }
    
    public String getUserSurname() {
		return userSurname;
    }

    public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
    }
    
    public String getUserAddress() {
		return userAddress;
    }

    public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
    }
    
    public String getUserPhone() {
		return userPhone;
    }

    public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
    }

    public String getTotalPrice() {
		return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
    }
    
    public String getVehicleName() {
		return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
    }
    
    public String getVehicleBrand() {
		return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
    }
    
    public String getVehiclePlateNumber() {
		return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
		this.vehiclePlateNumber = vehiclePlateNumber;
    }
    
    public String getDiscountId() {
		return discountId;
    }

    public void setDiscountId(String discountId) {
		this.discountId = discountId;
    }
    public String getDailyPrice() {
		return dailyPrice;
	}
    public void setDailyPrice(String dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
    
}
