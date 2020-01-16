package com.jsf.entity;

import java.util.Date;

public class Renting {
	private String id;
	private String userUserName;
    private Date returningDate;
    private Date receivingDate;
    private Date rentingDate;
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
    private String status;
    private String orderNumber;
    private String socialSecurityNo;
    private String paymentType;
    
    private String returnStringDate;
    private String receiveStringDate;
    private String rentingStringDate;
    private String totalDays;
    
    
	public Renting(String id, String userUserName, Date returningDate, Date receivingDate, Date rentingDate,
			String returningOffice, String receivingOffice, String userName, String userSurname, String userAddress,
			String userPhone, String totalPrice, String vehicleName, String vehicleBrand, String vehiclePlateNumber,
			String status, String orderNumber, String socialSecurityNo, String paymentType) {
		this.id = id;
		this.userUserName = userUserName;
		this.returningDate = returningDate;
		this.receivingDate = receivingDate;
		this.rentingDate = rentingDate;
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
		this.status = status;
		this.orderNumber = orderNumber;
		this.socialSecurityNo = socialSecurityNo;
		this.paymentType = paymentType;
	}
	
	public Renting(String id, String userUserName, Date returningDate, Date receivingDate, Date rentingDate,
			String returningOffice, String receivingOffice, String userName, String userSurname, String userAddress,
			String userPhone, String totalPrice, String vehicleName, String vehicleBrand, String vehiclePlateNumber,
			String status, String orderNumber, String socialSecurityNo, String paymentType, String receiveStringDate, String returnStringDate, String rentingStringDate, String totalDays) {
		this.id = id;
		this.userUserName = userUserName;
		this.returningDate = returningDate;
		this.receivingDate = receivingDate;
		this.rentingDate = rentingDate;
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
		this.status = status;
		this.orderNumber = orderNumber;
		this.socialSecurityNo = socialSecurityNo;
		this.paymentType = paymentType;
		this.receiveStringDate = receiveStringDate;
		this.returnStringDate = returnStringDate;
		this.rentingStringDate = rentingStringDate;
		this.totalDays = totalDays;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getRentingDate() {
		return rentingDate;
	}

	public void setRentingDate(Date rentingDate) {
		this.rentingDate = rentingDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getSocialSecurityNo() {
		return socialSecurityNo;
	}

	public void setSocialSecurityNo(String socialSecurityNo) {
		this.socialSecurityNo = socialSecurityNo;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public String getReceiveStringDate() {
		return receiveStringDate;
	}
	
	public void setReceiveStringDate(String receiveStringDate) {
		this.receiveStringDate = receiveStringDate;
	}
	
	public String getReturnStringDate() {
		return returnStringDate;
	}
	
	public void setReturnStringDate(String returnStringDate) {
		this.returnStringDate = returnStringDate;
	}
	
	public String getRentingStringDate() {
		return rentingStringDate;
	}
	
	public void setRentingStringDate(String rentingStringDate) {
		this.rentingStringDate = rentingStringDate;
	}
    
	public String getTotalDays() {
		return totalDays;
	}
	
	public void setTotalDays(String totalDays) {
		this.totalDays = totalDays;
	}

}
