package com.herokuapp.skmtodoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.entity.UserDetailsImpl;
import com.herokuapp.skmtodoapp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
		
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(null);
		}
		UserDetailsImpl userDetails = user.getUserDetails();
		return (UserDetails) userDetails;
	}
}
