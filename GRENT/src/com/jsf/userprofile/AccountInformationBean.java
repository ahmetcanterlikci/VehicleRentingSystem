package com.jsf.userprofile;

import java.sql.Connection;
import java.util.regex.Pattern;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;
import com.jsf.entity.RegisteredUser;

/**
 * Controller class of the personal and account information pages
 */
@ManagedBean
@ViewScoped
public class AccountInformationBean {
	private Connection connection;
	private RegisteredUser registeredUser;
	private boolean usernameFlag;
	private boolean emailFlag;
	

	/**
	 * Initialize method of the class
	 */
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze();
		connection = DatabaseManager.getConnection();
		receiveAccountInfo();
	}

	/**
	 * A listener method which activated from view. Specifies that username field is changed by user.
	 */
	public void usernameChange() {
		usernameFlag = true;
	}

	/**
	 * A listener method which activated from view. Specifies that email field is changed by user.
	 */
	public void emailChange() {
		emailFlag = true;
	}

	/**
	 * Receives the data of the corresponding RegisteredUser
	 */
	public void receiveAccountInfo() {
		if (LoginManager.isLoggedIn()) {
			try {
				PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM registereduser WHERE username = ?  ");
				pstmt.setString(1, LoginManager.getUsername());
				ResultSet resultSet1 = pstmt.executeQuery();

				resultSet1.beforeFirst();
				if (resultSet1.next()) {
					String username = resultSet1.getString("username");
					String isDeleted = resultSet1.getString("isDeleted");
					String email = resultSet1.getString("email");
					String password = resultSet1.getString("password");
					String name = resultSet1.getString("name");
					String surname = resultSet1.getString("surname");
					Date birthdate = resultSet1.getDate("birthdate");
					String phone = resultSet1.getString("phone");
					String gender = resultSet1.getString("gender");
					String address = resultSet1.getString("address");
					String city = resultSet1.getString("city");
					String country = resultSet1.getString("country");
					Date driverLicenseDate = resultSet1.getDate("driverLicenseDate");
					String discountId = resultSet1.getString("discountId");
					RegisteredUser newRegisteredUser = new RegisteredUser(username, isDeleted, email, password, name,
							surname, birthdate, phone, gender, address, city, country, driverLicenseDate, discountId);
					this.registeredUser = newRegisteredUser;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Saves the information on the account page. Activated from the save button on the account information page.
	 */
	public void saveAccountInfo() {

		try {

			if (searchUsername(registeredUser.getUsername())) {
				String warningMessage1 = "Username is already taken.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
				System.out.println(registeredUser.getUsername());
			} else if (searchEmail(registeredUser.getEmail())) {
				String warningMessage1 = "Email is already taken.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (registeredUser.getName().equals("") || registeredUser.getName() == null ){
				String warningMessage1 = "Name cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if(registeredUser.getSurname().equals("") || registeredUser.getSurname() == null ) {
				String warningMessage1 = "Surname cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if(registeredUser.getEmail().equals("") || registeredUser.getEmail() == null) {
				String warningMessage1 = "Email cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if(registeredUser.getUsername().equals("") ||  registeredUser.getUsername() == null) {
				String warningMessage1 = "Username cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}else {
				PreparedStatement pstmt = connection.prepareStatement(
						"UPDATE registereduser SET name=?, surname=?, email=? WHERE username = ?  ");
				pstmt.setString(1, registeredUser.getName());
				pstmt.setString(2, registeredUser.getSurname());
				pstmt.setString(3, registeredUser.getEmail());
				pstmt.setString(4, LoginManager.getUsername());
				pstmt.executeUpdate();

				String warningMessage1 = "Saved succesfully.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	/**
	 * Saves the information on the personal information page. Activated from the save button on the personal information page.
	 */
	public void savePersonalInfo() {
		try {
				
				if (!registeredUser.getPhone().matches("^[0-9]*$") ) {
					
					String warningMessage1 = "Phone field should only contain numbers!";
					FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
					
				}else if(registeredUser.getBirthdate().after(new Date())) {
					String warningMessage1 = "Your birthdate is invalid!";
					FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
					
				}	else if(registeredUser.getDriverLicenseDate().after(new Date())) {
					String warningMessage1 = "Your driver licence date is invalid!";
					FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
					
				}
				else {
				
				PreparedStatement pstmt = connection.prepareStatement(
						"UPDATE registereduser SET birthdate=?, phone=?, city=?, country=?, address=?, gender=?, driverLicenseDate=? WHERE username = ?  ");
				pstmt.setDate(1, new java.sql.Date(registeredUser.getBirthdate().getTime()));
				pstmt.setString(2, registeredUser.getPhone());
				pstmt.setString(3, registeredUser.getCity());
				pstmt.setString(4, registeredUser.getCountry());
				pstmt.setString(5, registeredUser.getAddress());
				pstmt.setString(6, registeredUser.getGender());
				pstmt.setDate(7, new java.sql.Date(registeredUser.getDriverLicenseDate().getTime()));
				pstmt.setString(8, LoginManager.getUsername());
				pstmt.executeUpdate();

				String warningMessage1 = "Saved succesfully.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	/**
	 * Checks whether given username is already taken or not.
	 * @param username username of a RegisteredUser
	 */
	public boolean searchUsername(String username) {
		if (usernameFlag) {
			try {
				PreparedStatement pstmt = connection
						.prepareStatement("Select * From registereduser where username = ? ");
				pstmt.setString(1, username);
				ResultSet resultSet1 = pstmt.executeQuery();

				resultSet1.beforeFirst();
				if (resultSet1.next()) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}
	

		

	/**
	 * Checks whether given email is already taken or not.
	 * @param email email of a RegisteredUser
	 */
	public boolean searchEmail(String email) {
		if (emailFlag) {
			try {
				PreparedStatement pstmt = connection.prepareStatement("Select * From registereduser where email = ? ");
				pstmt.setString(1, email);
				ResultSet resultSet1 = pstmt.executeQuery();

				resultSet1.beforeFirst();
				if (resultSet1.next()) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}
	
}
