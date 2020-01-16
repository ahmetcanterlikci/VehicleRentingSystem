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
import com.jsf.entity.Admin;
import com.jsf.entity.OfficeUser;
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
	private Date maxdate;
	private Admin admin;
	private OfficeUser officeUser;
	private boolean adminCheck;
	private boolean officeUserCheck;
	private boolean registeredUserCheck;

	/**
	 * Initialize method of the class
	 */
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze();
		connection = DatabaseManager.getConnection();
		receiveAccountInfo();
		this.maxdate = new Date();
		initUser();
	}
	
	public void initUser() {
		if(LoginManager.getRole().equals("RegisteredUser")) {
			registeredUserCheck = true;
		}else if (LoginManager.getRole().equals("OfficeUser")) {
			officeUserCheck = true;
		}else if (LoginManager.getRole().equals("Admin")){
			adminCheck = true;
		}
	}

	/**
	 * A listener method which activated from view. Specifies that username field is
	 * changed by user.
	 */
	public void usernameChange() {
		usernameFlag = true;
	}

	/**
	 * A listener method which activated from view. Specifies that email field is
	 * changed by user.
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
				if (LoginManager.getRole().equals("RegisteredUser")) {
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
						RegisteredUser newRegisteredUser = new RegisteredUser(username, isDeleted, email, password,
								name, surname, birthdate, phone, gender, address, city, country, driverLicenseDate,
								discountId);
						this.registeredUser = newRegisteredUser;
					}
				} else if (LoginManager.getRole().equals("OfficeUser")) {
					PreparedStatement pstmt2 = connection
							.prepareStatement("SELECT * FROM officeuser WHERE username = ?  ");
					pstmt2.setString(1, LoginManager.getUsername());
					ResultSet resultSet2 = pstmt2.executeQuery();

					resultSet2.beforeFirst();
					if (resultSet2.next()) {
						String username = resultSet2.getString("username");
						String isDeleted = resultSet2.getString("isDeleted");
						String email = resultSet2.getString("email");
						String password = resultSet2.getString("password");
						String name = resultSet2.getString("name");
						String surname = resultSet2.getString("surname");
						Date birthdate = resultSet2.getDate("birthdate");
						String phone = resultSet2.getString("phone");
						String gender = resultSet2.getString("gender");
						String address = resultSet2.getString("address");
						String city = resultSet2.getString("city");
						String country = resultSet2.getString("country");
						String officeName = resultSet2.getString("officeName");
						OfficeUser newOfficeUser = new OfficeUser(username, isDeleted, email, password,
								name, surname, birthdate, phone, gender, address, city, country, officeName);
						this.officeUser = newOfficeUser;
						
					}

				} else if (LoginManager.getRole().equals("Admin")) {
					PreparedStatement pstmt3 = connection
							.prepareStatement("SELECT * FROM Administrator WHERE username = ?  ");
					pstmt3.setString(1, LoginManager.getUsername());
					ResultSet resultSet3 = pstmt3.executeQuery();

					resultSet3.beforeFirst();
					if (resultSet3.next()) {
						String username = resultSet3.getString("username");
						String isDeleted = resultSet3.getString("isDeleted");
						String email = resultSet3.getString("email");
						String password = resultSet3.getString("password");
						String name = resultSet3.getString("name");
						String surname = resultSet3.getString("surname");
						Admin newAdmin = new Admin(username, isDeleted, email, password,
								name, surname);
						this.admin = newAdmin;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Saves the information on the account page. Activated from the save button on
	 * the account information page.
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
			} else if (registeredUser.getName().equals("") || registeredUser.getName() == null) {
				String warningMessage1 = "Name cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (registeredUser.getSurname().equals("") || registeredUser.getSurname() == null) {
				String warningMessage1 = "Surname cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (registeredUser.getEmail().equals("") || registeredUser.getEmail() == null) {
				String warningMessage1 = "Email cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (registeredUser.getUsername().equals("") || registeredUser.getUsername() == null) {
				String warningMessage1 = "Username cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (registeredUser.getName().matches(".*\\d.*")) {
				String warningMessage1 = "Name cannot contain numbers .";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (registeredUser.getSurname().matches(".*\\d.*")) {
				String warningMessage1 = "surname cannot contain numbers .";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else {
				PreparedStatement pstmt = connection
						.prepareStatement("UPDATE registereduser SET name=?, surname=?, email=? WHERE username = ?  ");
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
	
	public void saveAccountInfoOfficeUser() {

		try {

			if (searchUsername(officeUser.getUsername())) {
				String warningMessage1 = "Username is already taken.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
				System.out.println(registeredUser.getUsername());
			} else if (searchEmail(officeUser.getEmail())) {
				String warningMessage1 = "Email is already taken.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (officeUser.getName().equals("") || officeUser.getName() == null) {
				String warningMessage1 = "Name cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (officeUser.getSurname().equals("") || officeUser.getSurname() == null) {
				String warningMessage1 = "Surname cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (officeUser.getEmail().equals("") || officeUser.getEmail() == null) {
				String warningMessage1 = "Email cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (officeUser.getUsername().equals("") || officeUser.getUsername() == null) {
				String warningMessage1 = "Username cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (officeUser.getName().matches(".*\\d.*")) {
				String warningMessage1 = "Name cannot contain numbers .";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (officeUser.getSurname().matches(".*\\d.*")) {
				String warningMessage1 = "surname cannot contain numbers .";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else {
				PreparedStatement pstmt = connection
						.prepareStatement("UPDATE officeuser SET name=?, surname=?, email=? WHERE username = ?  ");
				pstmt.setString(1, officeUser.getName());
				pstmt.setString(2, officeUser.getSurname());
				pstmt.setString(3, officeUser.getEmail());
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
	
	public void saveAccountInfoAdmin() {

		try {

			if (searchUsername(admin.getUsername())) {
				String warningMessage1 = "Username is already taken.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
				System.out.println(admin.getUsername());
			} else if (searchEmail(admin.getEmail())) {
				String warningMessage1 = "Email is already taken.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (admin.getName().equals("") || admin.getName() == null) {
				String warningMessage1 = "Name cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (admin.getSurname().equals("") || admin.getSurname() == null) {
				String warningMessage1 = "Surname cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (admin.getEmail().equals("") || admin.getEmail() == null) {
				String warningMessage1 = "Email cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (admin.getUsername().equals("") || admin.getUsername() == null) {
				String warningMessage1 = "Username cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (admin.getName().matches(".*\\d.*")) {
				String warningMessage1 = "Name cannot contain numbers .";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (admin.getSurname().matches(".*\\d.*")) {
				String warningMessage1 = "surname cannot contain numbers .";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else {
				PreparedStatement pstmt = connection
						.prepareStatement("UPDATE Administrator SET name=?, surname=?, email=? WHERE username = ?  ");
				pstmt.setString(1, admin.getName());
				pstmt.setString(2, admin.getSurname());
				pstmt.setString(3, admin.getEmail());
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
	 * Saves the information on the personal information page. Activated from the
	 * save button on the personal information page.
	 */
	public void savePersonalInfo() {
		try {

			if (!registeredUser.getPhone().matches("^[0-9]*$")) {

				String warningMessage1 = "Phone field should only contain numbers!";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));

			} else if (registeredUser.getBirthdate().after(new Date())) {
				String warningMessage1 = "Your birthdate is invalid!";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));

			} else if (registeredUser.getDriverLicenseDate().after(new Date())) {
				String warningMessage1 = "Your driver licence date is invalid!";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));

			} else {

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
	
	public void savePersonalInfoOfficeUser() {
		try {

			if (!officeUser.getPhone().matches("^[0-9]*$")) {

				String warningMessage1 = "Phone field should only contain numbers!";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(warningMessage1));

			} else if (officeUser.getBirthdate().after(new Date())) {
				String warningMessage1 = "Your birthdate is invalid!";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(warningMessage1));

			} else {

				PreparedStatement pstmt = connection.prepareStatement(
						"UPDATE officeuser SET birthdate=?, phone=?, city=?, country=?, address=?, gender=? WHERE username = ?  ");
				pstmt.setDate(1, new java.sql.Date(officeUser.getBirthdate().getTime()));
				pstmt.setString(2, officeUser.getPhone());
				pstmt.setString(3, officeUser.getCity());
				pstmt.setString(4, officeUser.getCountry());
				pstmt.setString(5, officeUser.getAddress());
				pstmt.setString(6, officeUser.getGender());
				pstmt.setString(7, LoginManager.getUsername());
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
	 * 
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
	 * 
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

	public Date getMaxdate() {
		return maxdate;
	}
	
	public Admin getAdmin() {
		return admin;
	}
	
	public OfficeUser getOfficeUser() {
		return officeUser;
	}
	
	public boolean isAdminCheck() {
		return adminCheck;
	}
	
	public boolean isRegisteredUserCheck() {
		return registeredUserCheck;
	}
	
	public boolean isOfficeUserCheck() {
		return officeUserCheck;
	}

}
