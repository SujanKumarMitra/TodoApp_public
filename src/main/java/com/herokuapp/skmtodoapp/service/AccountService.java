package com.herokuapp.skmtodoapp.service;

import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.exception.model.UserAlreadyActivatedException;
import com.herokuapp.skmtodoapp.exception.model.UserNotFoundException;
import com.herokuapp.skmtodoapp.repository.UserRepository;

@Service
public class AccountService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;
	

	@Value("${account.create.message}")
	private String createMessage;
	
	@Value("${account.delete.message}")
	private String deleteMessage;
	
	@Value("${account.password.reset.message}")
	private String resetMessage;

	public void sendActivationLink(User user ,HttpServletRequest request) {
		String pathToActivate = request.getRequestURL()
								.toString()
								.replace(request.getServletPath(), "/account/activate?code=");
		Executors.newWorkStealingPool().execute(new Runnable() {

			@Override
			public void run() {
				emailService.sendMail(user.getEmail(), "Account Creation",
						createMessage.replace("__LINK__", pathToActivate.concat(user.getPassword())));
			}
		});
		System.out.println(pathToActivate.concat(user.getPassword()));
	}
	
	public void sendDeleteLink(User user ,HttpServletRequest request) {
		
		String pathToActivate = request.getRequestURL()
								.toString()
								.replace(request.getServletPath(), "/account/delete?code=");
		Executors.newWorkStealingPool().execute(new Runnable() {

			@Override
			public void run() {
				emailService.sendMail(user.getEmail(), "Account Delete Request",
						deleteMessage.replace("__LINK__", pathToActivate.concat(user.getPassword())));
			}
		});
		System.out.println(pathToActivate.concat(user.getPassword()));
	}

	public void activateAccount(String code) throws UserAlreadyActivatedException, UserNotFoundException {
		User user = userRepository.findByPassword(code);
		if(user == null) {
			throw new UserNotFoundException("Your code is not valid");
		}
		if(user.getUserDetails().isEnabled()) {
			throw new UserAlreadyActivatedException("Your account is already activated");			
		}
		user.getUserDetails().setEnabled(true);
		userRepository.save(user);
	}
	
	public void deleteAccount(String code) throws UserNotFoundException {
		User user = userRepository.findByPassword(code);
		if(user == null) {
			throw new UserNotFoundException("Your code is not valid");
		}
		
		userRepository.delete(user);
	}

	public void sendPasswordResetLink(String email,HttpServletRequest request) throws UserNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UserNotFoundException();
		}
		
		String pathToActivate = request.getRequestURL().toString().replace(request.getServletPath(),
				"/account/reset?code=");
		Executors.newWorkStealingPool().execute(new Runnable() {

			@Override
			public void run() {
				emailService.sendMail(user.getEmail(), "Password Reset",
						resetMessage.replace("__LINK__", pathToActivate.concat(user.getPassword())));
			}
		});
	}

	public User getUserFromCode(String code) throws UserNotFoundException {
		User user = userRepository.findByPassword(code);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}
	
	public void changePassword(User user) {
		User dbUser = userRepository.findByEmail(user.getEmail());
		dbUser.setPassword(user.getPassword());
		userRepository.save(dbUser);
	}

}
