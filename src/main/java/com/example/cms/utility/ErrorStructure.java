package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.exception.BlogAlreadyExitByTitleException;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.TopicsNotSpecifiedException;
import com.example.cms.exception.UserAlreadyExistByEmailException;
import com.example.cms.exception.UserNotFoundByIdException;

@RestControllerAdvice
@Component
public class ErrorStructure {

	@ExceptionHandler
	public ResponseEntity<ErrorHandler<String>> handleUserNotFoundById(UserNotFoundByIdException ex)
	{
		return new ResponseEntity<ErrorHandler<String>>(new ErrorHandler<String>()
				.setMessage("Not Found").setStatuscode(HttpStatus.NOT_FOUND.value())
				.setData(ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorHandler<String>> handleUserAlreadyExistByEmail(UserAlreadyExistByEmailException ex)
	{
		return new ResponseEntity<ErrorHandler<String>>(new ErrorHandler<String>()
				.setMessage("Email Already Exist").setStatuscode(HttpStatus.BAD_REQUEST.value())
				.setData(ex.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler 
	public ResponseEntity<ErrorHandler<String>> handleBlogAlreadyExistByTitle(BlogAlreadyExitByTitleException ex)
	{
		return new ResponseEntity<ErrorHandler<String>>(new ErrorHandler<String>()
				.setMessage("Title Already Exist").setStatuscode(HttpStatus.BAD_REQUEST.value())
				.setData(ex.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorHandler<String>> handleBlogNotFoundById(BlogNotFoundByIdException ex)
	{
		return new ResponseEntity<ErrorHandler<String>>(new ErrorHandler<String>()
				.setMessage("BlogId Not  Exist").setStatuscode(HttpStatus.NOT_FOUND.value())
				.setData(ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorHandler<String>> handleTopicsNotSpecified(TopicsNotSpecifiedException ex)
	{
		return new ResponseEntity<ErrorHandler<String>>(new ErrorHandler<String>()
				.setMessage("Failed").setStatuscode(HttpStatus.NOT_FOUND.value())
				.setData(ex.getMessage()),HttpStatus.NOT_FOUND);
	}
}
