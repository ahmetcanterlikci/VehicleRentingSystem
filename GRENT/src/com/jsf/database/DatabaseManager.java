package com.jsf.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database manager of the system. Creates or returns the database connection.
 */
public abstract class DatabaseManager {
	private static Connection connection;

	/**
	 * Create a connection with given database
	 */
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

	/**
	 * @return a Connection object for database connection in the system
	 */
	public static Connection getConnection() {
		return connection;
	}

	/**
	 * @return true if a connection created before, else false
	 */
	public static boolean isConnected() {
		if (connection != null) {
			return true;
		} else
			return false;
	}
}
