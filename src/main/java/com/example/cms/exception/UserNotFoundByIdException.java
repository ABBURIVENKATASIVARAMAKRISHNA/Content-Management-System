package com.example.cms.exception;

@SuppressWarnings("serial")
public class UserNotFoundByIdException extends RuntimeException {
	
	private String message;

	public UserNotFoundByIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
	
	
	

}
