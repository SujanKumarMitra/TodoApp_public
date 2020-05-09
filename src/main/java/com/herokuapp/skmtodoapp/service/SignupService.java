package com.herokuapp.skmtodoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.exception.model.UserAlreadyExistsException;
import com.herokuapp.skmtodoapp.repository.UserRepository;

@Service
public class SignupService {

	@Autowired
	public UserRepository repository;

	public User signUp(User user) throws UserAlreadyExistsException {

		if (userExists(user)) {
			throw new UserAlreadyExistsException();
		}

		User savedUser = repository.save(user);
		return savedUser;
	}

	public boolean userExists(User user) {

		return repository.findByEmail(user.getEmail()) != null;
	}
}
