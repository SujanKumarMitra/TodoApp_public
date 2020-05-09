package com.herokuapp.skmtodoapp.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.entity.UserDetailsImpl;
import com.herokuapp.skmtodoapp.exception.model.UserNotFoundException;

@Service
public class UserService {

	public User getUserBean() throws UserNotFoundException {
		Object principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof String) {
			throw new UserNotFoundException();
		}
		UserDetailsImpl userDetails =  (UserDetailsImpl) principal;
		return userDetails.getUser();
	}
	
}
