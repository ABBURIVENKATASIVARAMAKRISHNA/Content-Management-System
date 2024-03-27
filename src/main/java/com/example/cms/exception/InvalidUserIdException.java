package com.example.cms.exception;

@SuppressWarnings("serial")
public class InvalidUserIdException extends RuntimeException{

	private String message;

	public InvalidUserIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
	
}
