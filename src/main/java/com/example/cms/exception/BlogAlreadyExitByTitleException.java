package com.example.cms.exception;

@SuppressWarnings("serial")
public class BlogAlreadyExitByTitleException extends RuntimeException {
	
	private String message;

	public BlogAlreadyExitByTitleException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
