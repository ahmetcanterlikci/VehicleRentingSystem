package com.jsf.main;

import java.io.Serializable;

public class NavigationBarContent implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String path;
	
	public NavigationBarContent() {
		// TODO Auto-generated constructor stub
	}
	
	public NavigationBarContent(String name, String path) {
		this.name=name;
		this.path=path;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
