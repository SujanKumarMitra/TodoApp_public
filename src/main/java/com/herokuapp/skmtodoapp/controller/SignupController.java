package com.herokuapp.skmtodoapp.controller;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.herokuapp.skmtodoapp.entity.Role;
import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.entity.UserDetailsImpl;
import com.herokuapp.skmtodoapp.exception.model.UserAlreadyExistsException;
import com.herokuapp.skmtodoapp.service.SignupService;

@Controller
public class SignupController {

	@Autowired
	private SignupService signupService;
	
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/signup")
	public String showSignup(Model model, HttpSession session) {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (object instanceof UserDetailsImpl) {
			session.setAttribute("error", "Please logout to signup!");
			return "redirect:/todo";
		}
		return "signup";
	}

	@PostMapping("/processSignup")
	public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception {
		try {
			user.setId(0);
			user.setPassword(encoder.encode(user.getPassword()));
			
			UserDetailsImpl userDetails = new UserDetailsImpl();
			userDetails.setId(0);
			userDetails.setEnabled(true);
			userDetails.setExpired(false);
			userDetails.setExpiredCredentials(false);
			userDetails.setLocked(false);
			userDetails.setRoles(Arrays.asList(Role.USER));
			
			userDetails.setUser(user);
			user.setUserDetails(userDetails);
			
			signupService.signUp(user);
			session.setAttribute("success", "Account Successfully Created!");
			return "redirect:/login";
		} catch (UserAlreadyExistsException e) {
			throw new UserAlreadyExistsException(
					"This email address is already taken! Please try with different email address");
		}

	}

}
