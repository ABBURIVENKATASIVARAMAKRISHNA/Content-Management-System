package com.example.cms.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistByEmailException extends RuntimeException {
	
	private String message;

	public UserAlreadyExistByEmailException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
	
	
	
}
