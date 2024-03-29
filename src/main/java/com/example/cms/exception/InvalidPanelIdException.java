package com.example.cms.exception;

@SuppressWarnings("serial")
public class InvalidPanelIdException extends RuntimeException {

	private String message;
	

	public InvalidPanelIdException(String message) {
		this.message = message;
	}


	public String getMessage() {
		return message;
	}
	
	
}
