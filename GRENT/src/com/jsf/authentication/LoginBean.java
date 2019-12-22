package com.jsf.authentication;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.jsf.database.DatabaseManager;

/**
 * Controller of the Login page.
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String warningMessage;
	private Connection connection;

	/**
	 * Initialize method of the class
	 */
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
	
	/**
	 * Checks whether given user information in the login page is valid.
	 */
	public void validateUser() {
		if(this.password!=null && !this.password.equals("")) {
			try {
			PreparedStatement pstmt = connection.prepareStatement(
				        "SELECT * FROM registereduser WHERE username = ? and password = SHA1(?) ");
			pstmt.setString(1,username);
			pstmt.setString(2, password);
	        ResultSet resultSet1 = pstmt.executeQuery();
	        boolean registeredUser = checkDB(resultSet1);
	        if(registeredUser) {
	        	updateLoginManager(username, "RegisteredUser");
	        	directToHomePage();
	        	return;
	        }
	         
	        PreparedStatement pstmt2 = connection.prepareStatement(
			        "SELECT * FROM administrator WHERE username = ? and password = SHA1(?) ");
	        pstmt2.setString(1,username);
			pstmt2.setString(2, password);
	        ResultSet resultSet2 = pstmt2.executeQuery();
	        boolean admin = checkDB(resultSet2);
	        if(admin) {
	        	updateLoginManager(username, "Admin");
	        	directToHomePage();
	        	return;
	        }
	        
	        PreparedStatement pstmt3 = connection.prepareStatement(
			        "SELECT * FROM officeuser WHERE username = ? and password = SHA1(?) ");
	        pstmt3.setString(1,username);
			pstmt3.setString(2, password);
	        ResultSet resultSet3 = pstmt3.executeQuery();
	        boolean officeuser = checkDB(resultSet3);
	        if(officeuser) {
	        	updateLoginManager(username, "OfficeUser");
	        	directToHomePage();
	        	return;
	        }
	        
	        this.warningMessage="Wrong username or password.";
	        FacesContext.getCurrentInstance().addMessage("warningMessage",
	                new FacesMessage(this.warningMessage));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
	}
	
	/**
	 * Logs in the user to the system.
	 * @param username username of the user
	 * @param role role of the user
	 */
	public void updateLoginManager(String username, String role) {
		LoginManager.setUsername(username);
		LoginManager.setRole(role);
		LoginManager.setLoggedIn(true);
	}
	
	
	/**
	 * Directs the user to the homepage.
	 */
	public void directToHomePage() {
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		try {
		    ec.redirect(ec.getRequestContextPath()
		            + "/index.xhtml");
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
	
	/**
	 * Check whether given ResultSet is includes any row.
	 * @return true if includes any row, else false
	 * @param resultSet java.sql.ResultSet object from validateUser()
	 */
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
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	
}
