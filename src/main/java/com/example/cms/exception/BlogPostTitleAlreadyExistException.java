package com.example.cms.exception;

@SuppressWarnings("serial")
public class BlogPostTitleAlreadyExistException extends RuntimeException {

	private String message;

	public BlogPostTitleAlreadyExistException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
	
}
