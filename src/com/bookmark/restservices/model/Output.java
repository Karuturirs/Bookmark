package com.bookmark.restservices.model;

/*
 * O/p parameters  values
 * -------------------------------------------------------------------------------------------------------------
 * | 		 status        ||     messages   						||    description							|
 * -------------------------------------------------------------------------------------------------------------
 * |		  200          ||       OK		 						||		Successful     						|
 * |		  800          ||       Unsuccessful DB operation		||		UnSuccessful						|
 * |		  401          ||       Invalid input					||		input attribute value details		|
 * -------------------------------------------------------------------------------------------------------------
 */


public class Output {
	
	int status;
	String message;
	String description;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
