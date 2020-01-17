package com.jsf.office;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.jsf.authentication.LoginManager;
import com.jsf.database.DatabaseManager;
import com.jsf.entity.Office;
import com.jsf.entity.RegisteredUser;

/**
 * Controller of the Office object.
 */
@ManagedBean
@ViewScoped
public class AddOfficeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Office office;
	private boolean officenameFlag;
	private boolean officeDeletedFlag;
	private boolean emailFlag;
	

	
	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze();
		connection = DatabaseManager.getConnection();
		
	}

	/**
	 * A listener method which activated from view. Specifies that username field is changed by user.
	 */
	public void officenameChange() {
		officenameFlag = true;
	}

	/**
	 * A listener method which activated from view. Specifies that email field is changed by user.
	 */
	public void emailChange() {
		emailFlag = true;
	}

	

	/**
	 * Saves the office on the page. Activated from the save button on the add office page.
	 */
	public void addOffice() {

		try {

			if (doesOfficeExists(office.getName())) {
				String warningMessage1 = "The office already exists!.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
				System.out.println(office.getName());
			} else if (!office.getName().matches("^[a-zA-Z]*$")) {
				String warningMessage1 = "Office name cannot contain any number.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if (searchEmail(office.getEmail())){
				String warningMessage1 = "The email is already taken.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if(office.getName().equals("") || office.getName() == null ) {
				String warningMessage1 = "Office name cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if(office.getEmail().equals("") || office.getEmail() == null) {
				String warningMessage1 = "Email cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if(office.getAddress().equals("") ||  office.getAddress() == null) {
				String warningMessage1 = "Address cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}
			else if(!office.getAddress().matches("^[a-zA-Z]*$")) {
				String warningMessage1 = "Address cannot contain numbers .";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if(!office.getCity().matches("^[a-zA-Z]*$")) {
				String warningMessage1 = "City cannot contain numbers .";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}else if(!office.getCountry().matches("^[a-zA-Z]*$")) {
				String warningMessage1 = "Country cannot contain numbers .";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			} else if(office.getCity().equals("") ||  office.getCity() == null) {
				String warningMessage1 = "City cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}else if(office.getCountry().equals("") ||  office.getCountry() == null) {
				String warningMessage1 = "Country cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}else if(office.getPhone().equals("") ||  office.getPhone() == null) {
				String warningMessage1 = "Phone cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}else if(office.getFax().equals("") ||  office.getFax() == null) {
				String warningMessage1 = "Fax cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}else if(office.getWorkingDays().equals("") ||  office.getWorkingDays() == null) {
				String warningMessage1 = "Working days cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}else if(office.getWorkingHours().equals("") ||  office.getWorkingHours() == null) {
				String warningMessage1 = "Working hours cannot be empty.";
				FacesContext.getCurrentInstance().addMessage("warningMessage", new FacesMessage(warningMessage1));
			}else {
				PreparedStatement pstmt = connection.prepareStatement(
						"INSERT INTO Office (name, isDeleted, email, address, phone, fax, workingDays, workingHours, city, country)"
						+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ? ");
				pstmt.setString(1, office.getName());
				pstmt.setString(2, "False");
				pstmt.setString(3, office.getEmail());
				pstmt.setString(4, office.getAddress());
				pstmt.setString(5, office.getPhone());
				pstmt.setString(6, office.getFax());
				pstmt.setString(7, office.getWorkingDays());
				pstmt.setString(8, office.getWorkingHours());
				pstmt.setString(9, office.getCity());
				pstmt.setString(10, office.getCountry());
				
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
	 * Checks whether given office name is already taken or not.
	 * @param officename, name of a Office
	 */
	public boolean searchOfficeName(String officename) {
		if (officenameFlag) {
			try {
				PreparedStatement pstmt = connection
						.prepareStatement("Select * From Office where name = ? ");
				pstmt.setString(1,officename);
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
	public boolean doesOfficeExists(String officename) {
		if (officeDeletedFlag) {
			try {
				PreparedStatement pstmt = connection
						.prepareStatement("Select * From Office where name = ? AND isDeleted=?");
				pstmt.setString(1,officename);
				pstmt.setString(2, "False");
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
				PreparedStatement pstmt = connection.prepareStatement("Select * From Office where email = ? ");
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
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office=office;
				
	}

	
}

