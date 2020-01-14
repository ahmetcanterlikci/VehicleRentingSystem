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
public class ForgetpBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private String password;
	private String password2;
	private String warningMessage;
	private String warningMessage2;
	private Connection connection;

	/**
	 * Initialize method of the class
	 */
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze();
		connection = DatabaseManager.getConnection();
	}

	/**
	 * Checks whether given user information in the login page is valid.
	 */
	public void validatePassword() {
		if (!password.equals(password2)) {
			this.warningMessage2 = "Passwords are not match.";
			FacesContext.getCurrentInstance().addMessage("warningMessage2",
					new FacesMessage(this.warningMessage2));
		}
		else if (this.password != null && !this.password.equals("") && this.password2 != null
				&& !this.password2.equals("")) {
			try {
				PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM registereduser WHERE username = ? and email = ? ");
				pstmt.setString(1, username);
				pstmt.setString(2, email);
				ResultSet resultSet1 = pstmt.executeQuery();
				boolean registeredUser = checkDB(resultSet1);
				if (registeredUser) {
					changePassword();
					directToLoginPage();
					return;
				}

				PreparedStatement pstmt2 = connection
						.prepareStatement("SELECT * FROM administrator WHERE username = ? and email = ? ");
				pstmt2.setString(1, username);
				pstmt2.setString(2, email);
				ResultSet resultSet2 = pstmt2.executeQuery();
				boolean admin = checkDB(resultSet2);
				if (admin) {
					changePassword();
					directToLoginPage();
					return;
				}

				PreparedStatement pstmt3 = connection
						.prepareStatement("SELECT * FROM officeuser WHERE username = ? and email = ? ");
				pstmt3.setString(1, username);
				pstmt3.setString(2, email);
				ResultSet resultSet3 = pstmt3.executeQuery();
				boolean officeuser = checkDB(resultSet3);
				if (officeuser) {
					changePassword();
					directToLoginPage();
					return;
				}

				this.warningMessage = "Wrong username or email.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(this.warningMessage));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			this.warningMessage = "Password field(s) cannot be blank.";
			FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(this.warningMessage));
		}
	}
	
	public void changePassword() {
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("UPDATE registereduser SET password = SHA1(?) WHERE username = ? ");		
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Directs the user to the loginpage.
	 */
	public void directToLoginPage() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Check whether given ResultSet is includes any row.
	 * 
	 * @return true if includes any row, else false
	 * @param resultSet java.sql.ResultSet object from validateUser()
	 */
	public boolean checkDB(ResultSet resultSet) {
		try {
			ResultSetMetaData rsMetaData = resultSet.getMetaData();
			resultSet.beforeFirst();
			while (resultSet.next()) {
				int size = rsMetaData.getColumnCount();
				if (size > 0) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
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

}
