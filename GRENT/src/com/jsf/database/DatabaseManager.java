package com.jsf.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseManager {
	private static Connection connection;

	public static void initiliaze() {
		if (!isConnected()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				System.out.println("Driver loaded");
				connection = DriverManager.getConnection("jdbc:mysql://localhost/grent", "root", "Boardkastroyaka863*");
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static Connection getConnection() {
		return connection;
	}

	public static boolean isConnected() {
		if (connection != null) {
			return true;
		} else
			return false;
	}
}
