package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.User;
import com.example.cms.exception.UserAlreadyExistByEmailException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.UserRepostitory;
import com.example.cms.requestdto.UserReq;
import com.example.cms.responsesdto.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

@Service
public class UserServiceImpl  implements UserService{
	private UserRepostitory userRepostitory;

	private PasswordEncoder encryptPassword;


	private ResponseStructure<UserResponse> userStructures;
	public UserServiceImpl(UserRepostitory userRepostitory, ResponseStructure<UserResponse> userStructures,PasswordEncoder encryptPassword) {
		this.userRepostitory = userRepostitory;
		this.userStructures=userStructures;
		this.encryptPassword=encryptPassword;
	}
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserReq userRequest) {
		if(!userRepostitory.existsByEmail(userRequest.getEmail()))
			return ResponseEntity.ok(userStructures.setMessage("User Register Successfully").setStutusCode(HttpStatus.OK.value())
					.setData(mapToResponse(userRepostitory.save(mapToUser(userRequest, new User())), new UserResponse())));
		throw new UserAlreadyExistByEmailException("Failed to Register User");
	}
	public User mapToUser(UserReq userRequest,User user)
	{
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(encryptPassword.encode(userRequest.getPassword())); 
		user.setDeleted(false);
		return user;
	}
	public UserResponse mapToResponse(User user,UserResponse userRes)
	{
		userRes.setUserId(user.getUserId());
		userRes.setUserName(user.getUsername()); 
		userRes.setUserEmail(user.getEmail());
		userRes.setCreatedAt(user.getCreatedAt());
		userRes.setLastModifiedAt(user.getLastModifiedAt());
		return userRes; 
	}
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserByUserId(int userId) {
		return userRepostitory.findById(userId).map(user->{
			if(!user.isDeleted())
				return ResponseEntity.ok(userStructures.setMessage("Found Success").setStutusCode(HttpStatus.OK.value()).setData(mapToResponse(user, new UserResponse())));

			throw new UserNotFoundByIdException("User Id Already Deleted");
		}
				).orElseThrow(()-> new UserNotFoundByIdException("User Not Found By Id"));


	}
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> softDeleteUserByUserId(int userId) {
		return userRepostitory.findById(userId).map(user->{
			return ResponseEntity.ok(userStructures.setMessage("User Deleted Success").setStutusCode(HttpStatus.OK.value()).setData(mapToResponse(userRepostitory.save(user.setDeleted(true)), new UserResponse())));
		}).orElseThrow(()->new UserNotFoundByIdException("User Id Not Found"));
	} 

	}
