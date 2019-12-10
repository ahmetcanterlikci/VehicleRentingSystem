package com.jsf.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.jsf.database.DatabaseManager;


@ManagedBean
@ViewScoped
public class LoginBean {
	private String username;
	private String password;
	private String warningMessage;
	private Connection connection;

	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze();
		connection = DatabaseManager.getConnection();
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getWarningMessage() {
		return warningMessage;
	}
	
	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}
	
	public void validateUser() {
		if(this.password!=null && !this.password.equals("")) {
			try {
			PreparedStatement pstmt = connection.prepareStatement(
				        "SELECT * FROM registereduser WHERE username = ? and password = ? ");
			pstmt.setString(1,username);
			pstmt.setString(2, password);
	        ResultSet resultSet1 = pstmt.executeQuery();
	        boolean registeredUser = checkDB(resultSet1);
	        if(registeredUser) {
	        	return;
	        }
	         
	        PreparedStatement pstmt2 = connection.prepareStatement(
			        "SELECT * FROM administrator WHERE username = ? and password = ? ");
	        pstmt2.setString(1,username);
			pstmt2.setString(2, password);
	        ResultSet resultSet2 = pstmt2.executeQuery();
	        boolean admin = checkDB(resultSet2);
	        if(admin) {
	        	return;
	        }
	        
	        PreparedStatement pstmt3 = connection.prepareStatement(
			        "SELECT * FROM officeuser WHERE username = ? and password = ? ");
	        pstmt3.setString(1,username);
			pstmt3.setString(2, password);
	        ResultSet resultSet3 = pstmt3.executeQuery();
	        boolean officeuser = checkDB(resultSet3);
	        if(officeuser) {
	        	return;
	        }
	        
	        this.warningMessage="Wrong username or password.";
	        FacesContext.getCurrentInstance().addMessage("loginPanel:warningMessage",
	                new FacesMessage(this.warningMessage));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
	}
	
	public void updateLoginManager() {
		
	}
	
	public boolean checkDB(ResultSet resultSet) {
		try {
			ResultSetMetaData rsMetaData = resultSet.getMetaData();
			resultSet.beforeFirst();
	        while (resultSet.next()) {
	            int size = rsMetaData.getColumnCount();
	            if(size > 0) {
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
