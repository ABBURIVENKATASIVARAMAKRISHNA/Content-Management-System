package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestdto.UserReq;
import com.example.cms.responsesdto.UserResponse;

import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Operation(description = "The endpoint is used to the add new user to the database", responses = {
			@ApiResponse(responseCode = "200", description = "user Data Submitted success"),
			@ApiResponse(responseCode = "400", description = "Invalid Input") })
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserReq user) {
		
		return userService.registerUser(user);
	}

	@GetMapping("/test")
	public String test() {
		return "Hello from CMS";
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserByUserId(@PathVariable int userId)
	{
		return userService.findUserByUserId(userId);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId)
	{
		return userService.softDeleteUserByUserId(userId);
	}
}
