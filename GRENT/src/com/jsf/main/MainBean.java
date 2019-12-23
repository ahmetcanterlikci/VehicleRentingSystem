package com.jsf.main;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;
import com.jsf.entity.Office;
import com.jsf.search.SearchBeanStatic;

/**
 * Controller class of the homepage.
 */
@ManagedBean
@ViewScoped
public class MainBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<NavigationBarContent> navigationBarContents;
	private String navigationBarName;
	private Connection connection;
	private ArrayList<Office> offices;
	private String receivingOfficeName;
	private String returningOfficeName;
	private Date receivingDate;
	private Date returningDate;
	private boolean loginControl;

	@PostConstruct
	public void init() {
		offices = new ArrayList<Office>();
		this.navigationBarContents = new ArrayList<NavigationBarContent>();
		initNavigationBarContents();
		initNavigationBarName();
		
		if(isMainPage()) {
			DatabaseManager.initiliaze();
			connection = DatabaseManager.getConnection();
			receiveOffices();
		}
		
		loginControl();
	}
	
	/**
	 * Check whether user request is come from main page or not
	 */
	public boolean isMainPage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String viewId = facesContext.getViewRoot().getViewId();
		if(viewId.equals("/index.xhtml")) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Check whether user logged in.
	 */
	public void loginControl() {
		if(LoginManager.isLoggedIn()){
			this.loginControl = true;
		}else {
			this.loginControl = false;
		}
	}
	
	/**
	 * Receive offices in the system
	 */
	public void receiveOffices() {
		try {
			PreparedStatement pstmt = connection.prepareStatement(
				        "SELECT * FROM office ");
	        ResultSet resultSet1 = pstmt.executeQuery();
	        
	        ResultSetMetaData rsMetaData = resultSet1.getMetaData();
			resultSet1.beforeFirst();
	        while (resultSet1.next()) {
	        	String name = resultSet1.getString("name");
	        	String isDeleted = resultSet1.getString("isDeleted");
	        	String email = resultSet1.getString("email");
	        	String address = resultSet1.getString("address");
	        	String phone = resultSet1.getString("phone");
	        	String fax = resultSet1.getString("fax");
	        	String workingDays = resultSet1.getString("workingDays");
	        	String workingHours = resultSet1.getString("workingHours");
	        	String city = resultSet1.getString("city");
	        	String country  = resultSet1.getString("country");
	            Office office = new Office(name, isDeleted, email, address,phone, fax, workingDays, workingHours, city, country);
	            offices.add(office);
	        }
	        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * Store the search information entered from the user
	 * @param returningDate returning date of the renting
	 * @param receivingDate receiving date of the renting
	 * @param returningOffice returning office of the renting
	 * @param receivingOffice receiving office of the renting
	 */
	public void searchAction(String receivingOffice, String returningOffice, Date returningDate, Date receivingDate) {
		SearchBeanStatic.setReceivingOffice(receivingOffice);
		SearchBeanStatic.setReturningOffice(returningOffice);
		SearchBeanStatic.setReturningDate(returningDate);
		SearchBeanStatic.setReceivingDate(receivingDate);
		directToSearchPage();
	}
	
	/**
	 * Direct user to the vehicle search page
	 */
	public void directToSearchPage() {
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		try {
		    ec.redirect(ec.getRequestContextPath()
		            + "/search.xhtml");
		    return;
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

	/**
	 * Initialize the name of the drop down menu in the navigation bar
	 */
	public void initNavigationBarName() {
		boolean temp0 = LoginManager.isLoggedIn();
		if (temp0 == true) {
			this.navigationBarName = LoginManager.getUsername();
		} else
			this.navigationBarName = "Login";
	}
	
	/**
	 * Initialize the contents of the drop down menu in the navigation bar
	 */
	public void initNavigationBarContents() {

		if (LoginManager.isLoggedIn()) {
			String userRole = LoginManager.getRole();

			if (userRole.equals("RegisteredUser") || userRole.equals("Admin") || userRole.equals("OfficeUser")) {
				this.navigationBarContents.add(new NavigationBarContent("My Profile", "myprofile.xhtml"));
				this.navigationBarContents.add(new NavigationBarContent("Exit", "index.xhtml"));
			}
		} else {
			this.navigationBarContents.add(new NavigationBarContent("Login", "login.xhtml"));
			this.navigationBarContents.add(new NavigationBarContent("Register", "beta.xhtml"));
		}
	}
	
	/**
	 * Action of the exit button. Logs out the user and directs to the given path from param.
	 */
	public void navigationAction() {
		Map<String,String> params = 
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	  String path = params.get("path");
	  String exitControl = params.get("exit");
	  if(exitControl.equals("Exit")) {
		  userExit();
	  }
	  directToPage(path);
	}
	
	/**
	 * Log out the user
	 */
	public void userExit() {
		LoginManager.setLoggedIn(false);
		LoginManager.setRole(null);
		LoginManager.setUsername(null);
	}
	
	/**
	 * Direct to the given path.
	 * @param path name of the page. Syntax: pathName.xhtml
	 */
	public void directToPage(String path) {
		ExternalContext ec = FacesContext.getCurrentInstance()
		        .getExternalContext();
		try {
		    ec.redirect(ec.getRequestContextPath()
		            + "/" + path);
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

	public ArrayList<NavigationBarContent> getNavigationBarContents() {
		return navigationBarContents;
	}

	public String getNavigationBarName() {
		return navigationBarName;
	}
	
	public ArrayList<Office> getOffices() {
		return offices;
	}
	
	public String getReceivingOfficeName() {
		return receivingOfficeName;
	}
	
	public String getReturningOfficeName() {
		return returningOfficeName;
	}
	
	public void setReceivingOfficeName(String receivingOfficeName) {
		this.receivingOfficeName = receivingOfficeName;
	}
	
	public void setReturningOfficeName(String returningOfficeName) {
		this.returningOfficeName = returningOfficeName;
	}
	
	public Date getReceivingDate() {
		return receivingDate;
	}
	
	public Date getReturningDate() {
		return returningDate;
	}
	
	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}
	
	public void setReturningDate(Date returningDate) {
		this.returningDate = returningDate;
	}
	public boolean isLoginControl() {
		return loginControl;
	}

}
