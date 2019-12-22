package com.jsf.main;

import java.io.Serializable;

/**
 * Object to store navigation bar contents
 */
public class NavigationBarContent implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String path;
	
	public NavigationBarContent() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param name the String which will be displayed in the navigation bar drop down
	 * @param path the path of the given content. Syntax: pathName.xhtml
	 */
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
