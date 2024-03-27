package com.example.cms.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cms.repository.UserRepostitory;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private UserRepostitory userRepo;
	
	

	public CustomUserDetailsService(UserRepostitory userRepo) {
		this.userRepo = userRepo;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByEmail(username).map(user->
			 new CustomUserDetails(user)
		).orElseThrow(()-> new UsernameNotFoundException("Not Found"));
	}

}
