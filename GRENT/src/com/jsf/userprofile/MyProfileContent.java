package com.jsf.userprofile;

import java.io.Serializable;

/**
 * Stores the my profile contents
 */
public class MyProfileContent implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String path;
	private String pathId;

	public MyProfileContent() {

	}

	/**
	 * @param name content name
	 * @param path content path. Syntax: pathName.xhtml
	 * @param pathId corresponding fontawesome class name for content icon
	 */
	public MyProfileContent(String name, String path, String pathId) {
		this.name = name;
		this.path=path;
		this.pathId = pathId;
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
	
	public String getPathId() {
		return pathId;
	}
	public void setPathId(String pathId) {
		this.pathId = pathId;
	}
}
