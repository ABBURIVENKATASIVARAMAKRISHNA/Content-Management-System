package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestdto.UserReq;
import com.example.cms.responsesdto.UserResponse;
import com.example.cms.utility.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>>	registerUser(UserReq user);
	
	ResponseEntity<ResponseStructure<UserResponse>> findUserByUserId(int userId);
	
	ResponseEntity<ResponseStructure<UserResponse>> softDeleteUserByUserId(int userId);
	
	
	
}
  