package com.herokuapp.skmtodoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.repository.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private UserRepository userRepository;

	public void updateProfile(User user) throws Exception {

		User dbUser = userRepository.getOne(user.getId());
		
		dbUser.setFirstName(user.getFirstName());
		dbUser.setLastName(user.getLastName());

		userRepository.save(dbUser);
	}

}
