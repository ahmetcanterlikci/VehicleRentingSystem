package com.jsf.userprofile;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;

@ManagedBean
@ViewScoped
public class ChangePasswordBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String oldPassword;
	private String newPassword;
	private String newPassword2;
	private String warningMessage;
	private Connection connection;

	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze();
		connection = DatabaseManager.getConnection();

		if (LoginManager.isLoggedIn()) {
			userName = LoginManager.getUsername();
		}
	}

	public void checkPassword() {

		if (newPassword.equals(newPassword2)) {
			try {
				PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM registereduser WHERE username = ? and password = SHA1(?)");
				pstmt.setString(1, userName);
				pstmt.setString(2, oldPassword);

				ResultSet resultSet1 = pstmt.executeQuery();

				int count = 0;

				resultSet1.beforeFirst();
				while (resultSet1.next()) {

					count++;

				}
 
				if (count != 0) {
                    if((newPassword.equals("") || newPassword == null) || (newPassword2.equals("") || newPassword2 == null)) {
                    	warningMessage = "New Password can not be null";
    					FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage));
                    }
                    else {
                    	PreparedStatement pstmt1 = connection
    							.prepareStatement("UPDATE registereduser SET password = SHA1(?) where username = ?");
    					pstmt1.setString(1, newPassword);
    					pstmt1.setString(2, userName);
    					pstmt1.executeUpdate();
    					warningMessage = "Saved Successfully";
    					FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage));
                    }
					
				} else {
					warningMessage = "Old password is not valid";
					FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			warningMessage = "Passwords does not match";
			FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage));
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
