package com.bookmark.restservices.model;

public class EditInput 
{
	private String name;
	private String type;
	private String newpath;
	private String oldpath;
	private String url;
	private String description;
	private String operation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNewpath() {
		return newpath;
	}
	public void setNewpath(String newpath) {
		this.newpath = newpath;
	}
	public String getOldpath() {
		return oldpath;
	}
	public void setOldpath(String oldpath) {
		this.oldpath = oldpath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
