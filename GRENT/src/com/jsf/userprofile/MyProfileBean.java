package com.jsf.userprofile;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.jsf.authentication.LoginManager;

/**
 * Controller class of the my profile page
 */
@ManagedBean
@ViewScoped
public class MyProfileBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<MyProfileContent> profilecontens;

	/**
	 * Initialize method of the class
	 */
	@PostConstruct
	public void init() {
		this.profilecontens = new ArrayList<MyProfileContent>();
		initMyProfileContents();
	}

	/**
	 * Initialize the contents of my profile page 
	 */
	public void initMyProfileContents() {

		if(LoginManager.isLoggedIn()) {
			String roleString = LoginManager.getRole();
			
			if(roleString.equalsIgnoreCase("RegisteredUser")) {
				profilecontens.add(new MyProfileContent("Account Information", "accountinfo.xhtml","fas fa-id-card"));
				profilecontens.add(new MyProfileContent("Personal Information", "personalinfo.xhtml", "fas fa-user-edit"));
				profilecontens.add(new MyProfileContent("Change Password", "beta.xhtml", "fas fa-lock"));
				profilecontens.add(new MyProfileContent("Discounts", "beta.xhtml", "fas fa-tags"));
				profilecontens.add(new MyProfileContent("My Rentings", "beta.xhtml", "fas fa-car-alt"));
			}else if (roleString.equalsIgnoreCase("OfficeUser")) {
				profilecontens.add(new MyProfileContent("Account Information", "accountinfo.xhtml","fas fa-id-card"));
				profilecontens.add(new MyProfileContent("Personal Information", "personalinfo.xhtml", "fas fa-user-edit"));
				profilecontens.add(new MyProfileContent("Change Password", "changepassword.xhtml", "fas fa-lock"));
				profilecontens.add(new MyProfileContent("Rentings", "rentings.xhtml", "fas fa-car-alt"));
			}else if (roleString.equalsIgnoreCase("Admin")) {
				profilecontens.add(new MyProfileContent("Account Information", "accountinfo.xhtml","fas fa-id-card"));
				profilecontens.add(new MyProfileContent("Change Password", "changepassword.xhtml", "fas fa-lock"));
				profilecontens.add(new MyProfileContent("Rentings", "rentings.xhtml", "fas fa-car-alt"));
				profilecontens.add(new MyProfileContent("Users", "users.xhtml", "fas fa-user"));
			}
		}
		
	}
	
	public ArrayList<MyProfileContent> getProfilecontens() {
		return profilecontens;
	}
	public void setProfilecontens(ArrayList<MyProfileContent> profilecontens) {
		this.profilecontens = profilecontens;
	}
}
