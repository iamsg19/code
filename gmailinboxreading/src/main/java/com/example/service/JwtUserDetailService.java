package com.example.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.exception.UserNameNotFoundException;

@Service
public class JwtUserDetailService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if("shivatube".equals(username)) {
			return new User("shivatube", "$2y$12$76zC2qVyFce9NAH7KwnOrul9ljY6iklGW0pJckZRO6bR7GMilHbm6", new ArrayList<>());
		}else {
			throw new UserNameNotFoundException("User not found with the given name");
		}
	}

	 
}
