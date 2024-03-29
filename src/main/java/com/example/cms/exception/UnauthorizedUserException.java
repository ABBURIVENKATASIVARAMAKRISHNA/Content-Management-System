package com.example.cms.exception;

@SuppressWarnings("serial")
public class UnauthorizedUserException extends RuntimeException{

	private String message;

	public UnauthorizedUserException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
	
}
