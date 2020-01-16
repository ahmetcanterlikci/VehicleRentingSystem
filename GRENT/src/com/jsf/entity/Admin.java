package com.jsf.entity;

public class Admin {
	private String username;
    private String isDeleted;
    private String email;
    private String password;
    private String name;
    private String surname;
    
    public Admin(String username, String isDeleted, String email, String password,
            String name, String surname) {
        this.username = username;
        this.isDeleted = isDeleted;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
    
    

}
