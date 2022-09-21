package com.example.exceptions;

/*
 *  This is a checked Exception since its extending the Exception class
 *  
 */
public class ElementNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorCode;

	
	public ElementNotFoundException(String errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage() {
		return this.errorCode+":"+super.getMessage();
	}
	
	
}
