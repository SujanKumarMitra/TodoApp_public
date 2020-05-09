package com.herokuapp.skmtodoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.entity.UserDetailsImpl;
import com.herokuapp.skmtodoapp.exception.model.UserAlreadyExistsException;
import com.herokuapp.skmtodoapp.exception.model.UserNotFoundException;
import com.herokuapp.skmtodoapp.repository.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	public void updateProfile(User user) throws Exception {

		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		User sessionUser = userDetails.getUser();

		if (!user.getPassword().equals(sessionUser.getPassword())) {
			user.setPassword(encoder.encode(user.getPassword()));
		}

		if (!user.getEmail().equals(sessionUser.getEmail())) {
			User dbUser = userRepository.findByEmail(user.getEmail());
			if (dbUser != null) {
				throw new UserAlreadyExistsException();
			}
		}

		userRepository.save(user);
	}

	public void deleteProfile(User user) throws UserNotFoundException {
		user.setPassword(encoder.encode(user.getPassword()));
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		User dbUser = userDetails.getUser();
		if (!isUserCredentialsMatched(user, dbUser)) {
			throw new UserNotFoundException("Credentials doesn't match");
		}
		userRepository.delete(dbUser);

	}

	public boolean isUserCredentialsMatched(User user1, User user2) {
		return user1.getPassword().equals(user2.getPassword()) && user1.getEmail().equals(user2.getEmail()) ? true
				: false;
	}
}
