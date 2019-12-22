package com.jsf.authentication;

/**
 * Login manager of the system. Stores some information of the logged users.
 */
public abstract class LoginManager {

	private static String username;
	private static boolean isLoggedIn;
	private static String role;
	
	/**
	 * @return the role of the current logged user.
	 */
	public static String getRole() {
		return role;
	}
	
	/**
	 * @return the username of the current logged user.
	 */
	public static String getUsername() {
		return username;
	}
	
	/**
	 * @return true if user logged in with his/her corresponding client, else false.
	 */
	public static boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public static void setLoggedIn(boolean isLoggedIn) {
		LoginManager.isLoggedIn = isLoggedIn;
	}
	
	public static void setRole(String role) {
		LoginManager.role = role;
	}
	
	public static void setUsername(String username) {
		LoginManager.username = username;
	}
}
