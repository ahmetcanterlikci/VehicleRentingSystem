package com.jsf.register;

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
import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;

/**
 * Controller of the Register page.
 */
@ManagedBean
@ViewScoped
public class RegisterBean implements Serializable {
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordagain() {
		return passwordagain;
	}

	public void setPasswordagain(String passwordagain) {
		this.passwordagain = passwordagain;
	}

	private static final long serialVersionUID = 1L;
	private String username;
	private String email;
	private String password;
	private String passwordagain;
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
	 * Checks whether given user information in the register page is valid.If valid,
	 * register the user and redirects it to the mainpage
	 */
	public void validateUser() {
		if (!password.isEmpty() && !passwordagain.isEmpty() && password.equals(passwordagain)) {

			boolean usernametaken = false;
			boolean emailtaken = false;
			if (this.password != null && !this.password.equals("")) {
				try {
					PreparedStatement pstmt = connection
							.prepareStatement("SELECT * FROM registereduser WHERE username = ?");
					pstmt.setString(1, username);

					ResultSet resultSet1 = pstmt.executeQuery();
					boolean registeredUser = checkDB(resultSet1);
					if (registeredUser) {
						usernametaken = true;
					}
					PreparedStatement pstmt2 = connection
							.prepareStatement("SELECT * FROM administrator WHERE username = ?");
					pstmt2.setString(1, username);
					ResultSet resultSet2 = pstmt2.executeQuery();
					boolean admin = checkDB(resultSet2);
					if (admin) {
						usernametaken = true;
					}

					PreparedStatement pstmt3 = connection
							.prepareStatement("SELECT * FROM officeuser WHERE username = ?");
					pstmt3.setString(1, username);
					ResultSet resultSet3 = pstmt3.executeQuery();
					boolean officeuser = checkDB(resultSet3);
					if (officeuser) {
						usernametaken = true;
					}
					PreparedStatement pstmt4 = connection.prepareStatement("SELECT * FROM officeuser WHERE email = ?");
					pstmt4.setString(1, email);
					ResultSet resultSet4 = pstmt4.executeQuery();
					boolean officeuseremail = checkDB(resultSet4);
					if (officeuseremail) {
						emailtaken = true;
					}
					PreparedStatement pstmt5 = connection
							.prepareStatement("SELECT * FROM registereduser WHERE email = ?");
					pstmt5.setString(1, email);
					ResultSet resultSet5 = pstmt5.executeQuery();
					boolean registereduseremail = checkDB(resultSet5);
					if (registereduseremail) {
						emailtaken = true;
					}
					PreparedStatement pstmt6 = connection
							.prepareStatement("SELECT * FROM administrator WHERE email = ?");
					pstmt6.setString(1, email);
					ResultSet resultSet6 = pstmt6.executeQuery();
					boolean administratoremail = checkDB(resultSet6);
					if (officeuseremail) {
						emailtaken = true;
					}
					if (!usernametaken && !emailtaken) {
						PreparedStatement pstmt7 = connection.prepareStatement(
								"INSERT INTO registereduser (username,email,password) VALUES (?,?,SHA1(?)) ");
						pstmt7.setString(1, username);
						pstmt7.setString(2, email);
						pstmt7.setString(3, password);
						pstmt7.executeUpdate();
						directToLoginPage();
					} else {
						if (usernametaken) {
							if (emailtaken) {
								this.warningMessage = "Username and email already taken";
								FacesContext.getCurrentInstance().addMessage("warningMessage",
										new FacesMessage(this.warningMessage));
							} else {
								this.warningMessage = "Username already taken";
								FacesContext.getCurrentInstance().addMessage("warningMessage",
										new FacesMessage(this.warningMessage));
							}
						} else {
							this.warningMessage = "email already taken";
							FacesContext.getCurrentInstance().addMessage("warningMessage",
									new FacesMessage(this.warningMessage));
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			this.warningMessage = "Password field cannot be empty or passwords didnt match";
			FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(this.warningMessage));
		}
	}

	/**
	 * Logs in the user to the system.
	 * 
	 * @param username username of the user
	 * @param role     role of the user
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
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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

}
