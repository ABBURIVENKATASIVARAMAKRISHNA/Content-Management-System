package com.example.cms.exception;

@SuppressWarnings("serial")
public class TopicsNotSpecifiedException extends RuntimeException {

	private String message;

	public TopicsNotSpecifiedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}

