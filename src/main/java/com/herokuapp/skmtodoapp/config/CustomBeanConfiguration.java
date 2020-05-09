package com.herokuapp.skmtodoapp.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.entity.UserDetailsImpl;

@Configuration
public class CustomBeanConfiguration {

	@Bean
	public SimpleDateFormat getDateFormatter() {
		return new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Scope("prototype")
	@Lazy
	public User getCurrentLoggedInUser() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUser();
	}

}
