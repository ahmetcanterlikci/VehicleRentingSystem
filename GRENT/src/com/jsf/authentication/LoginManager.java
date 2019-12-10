package com.jsf.authentication;

public abstract class LoginManager {

	private static String username;
	private static boolean isLoggedIn;
	private static String role;
	
	public static String getRole() {
		return role;
	}
	
	public static String getUsername() {
		return username;
	}
	
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
