package com.jsf.office;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.jsf.database.DatabaseManager;
import com.jsf.entity.Office;

@ManagedBean
@ViewScoped
public class OfficeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> nameSelections;

	private ArrayList<String> selectedNames;
	private ArrayList<String> selectedCities;
	private ArrayList<String> countries;
	private ArrayList<String> selectedcountry;

	private ArrayList<String> cities;

	private ArrayList<Office> Offices;

	public ArrayList<String> getCities() {
		return cities;
	}

	public void setCities(ArrayList<String> cities) {
		this.cities = cities;
	}

	private ArrayList<Office> selectedOffices;
	private int officeCount;
	private Connection connection;

	@PostConstruct
	public void init() {
		DatabaseManager.initiliaze();
		connection = DatabaseManager.getConnection();
		selectedcountry = new ArrayList<String>();
		countries = new ArrayList<String>();
		selectedNames = new ArrayList<String>(); // prepare the attributes
		selectedCities = new ArrayList<String>();

		cities = new ArrayList<String>();

		Offices = new ArrayList<Office>();

		nameSelections = new ArrayList<String>();

		receiveOffices(); // prepare the vehicles
		receiveCountries(); // select countries in that are in the database
		receiveCities();
		receiveNames();
		officeCount = Offices.size();
		selectedOffices = Offices;

	}

	public void receiveOffices() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM office ");
			ResultSet resultSet1 = pstmt.executeQuery();
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
				String country = resultSet1.getString("country");
				Office office = new Office(name, isDeleted, email, address, phone, fax, workingDays, workingHours, city,
						country);
				Offices.add(office);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void receiveCountries() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT country FROM office");
			ResultSet resultSet1 = pstmt.executeQuery();

			resultSet1.beforeFirst();
			while (resultSet1.next()) {
				String countryname = resultSet1.getString("country");
				countries.add(countryname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void receiveCities() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT city FROM office");
			ResultSet resultSet1 = pstmt.executeQuery();

			resultSet1.beforeFirst();
			while (resultSet1.next()) {
				String cityname = resultSet1.getString("city");
				cities.add(cityname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void receiveNames() {
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT name FROM office");
			ResultSet resultSet1 = pstmt.executeQuery();

			resultSet1.beforeFirst();
			while (resultSet1.next()) {
				String name = resultSet1.getString("name");
				nameSelections.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void filter() { // filter action
		System.out.println("filter");
		boolean c = checkForFilter();

		if (c == false) {
			selectedOffices = Offices;
		} else {
			selectedOffices = new ArrayList<Office>();

			for (int i = 0; i < Offices.size(); i++) {
				Office v = Offices.get(i);

				boolean control = true;

				if (!selectedCities.isEmpty()) {
					if (!checkCities(v.getCity())) {
						control = false;
					}
				}

				if (!selectedNames.isEmpty()) {
					if (!checkname(v.getName())) {
						control = false;
					}
				}
				if (!selectedcountry.isEmpty()) {
					if (!checkCountry(v.getCountry())) {
						control = false;
					}
				}
				if (control == true) {
					selectedOffices.add(v);
				}
			}
		}

		officeCount = selectedOffices.size();

	}

	public boolean checkCities(String OfficeCity) {
		if (selectedCities.contains(OfficeCity)) {
			return true;
		} else
			return false;
	}

	public boolean checkname(String name) {
		if (selectedNames.contains(name)) {
			return true;
		} else
			return false;
	}

	public boolean checkCountry(String country) {
		if (selectedcountry.contains(country)) {
			return true;
		} else
			return false;
	}

	public boolean checkForFilter() {

		if (!selectedCities.isEmpty()) {
			return true;
		} else if (!selectedNames.isEmpty()) {
			return true;
		} else if (!selectedcountry.isEmpty()) {
			return true;
		} else
			return false; // there is no filter selection
	}

	public ArrayList<String> getNameSelections() {
		return nameSelections;
	}

	public void setNameSelections(ArrayList<String> nameSelections) {
		this.nameSelections = nameSelections;
	}

	public ArrayList<String> getSelectedNames() {
		return selectedNames;
	}

	public void setSelectedNames(ArrayList<String> selectedNames) {
		this.selectedNames = selectedNames;
	}

	public ArrayList<String> getSelectedCities() {
		return selectedCities;
	}

	public void setSelectedCities(ArrayList<String> selectedCities) {
		this.selectedCities = selectedCities;
	}

	public ArrayList<String> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<String> countries) {
		this.countries = countries;
	}

	public ArrayList<String> getSelectedcountry() {
		return selectedcountry;
	}

	public void setSelectedcountry(ArrayList<String> selectedcountry) {
		this.selectedcountry = selectedcountry;
	}

	public ArrayList<Office> getOffices() {
		return Offices;
	}

	public void setOffices(ArrayList<Office> offices) {
		Offices = offices;
	}

	public ArrayList<Office> getSelectedOffices() {
		return selectedOffices;
	}

	public void setSelectedOffices(ArrayList<Office> selectedOffices) {
		this.selectedOffices = selectedOffices;
	}

	public int getOfficeCount() {
		return officeCount;
	}

	public void setOfficeCount(int officeCount) {
		this.officeCount = officeCount;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

//getter setters

}
