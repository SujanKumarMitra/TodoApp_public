package com.herokuapp.skmtodoapp.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.herokuapp.skmtodoapp.service.AccountService;
import com.herokuapp.skmtodoapp.service.SignupService;

@Controller
public class SignupController {

	@Autowired
	private SignupService signupService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AccountService activationService;

	@Value("${account.create.success.message}")
	private String successMessage;

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
	public String login(@ModelAttribute("user") User user, Model model, HttpServletRequest request) throws Exception {
		try {
			user.setId(0);
			user.setPassword(encoder.encode(user.getPassword()));

			UserDetailsImpl userDetails = new UserDetailsImpl();
			userDetails.setId(0);
			userDetails.setEnabled(false);
			userDetails.setExpired(false);
			userDetails.setExpiredCredentials(false);
			userDetails.setLocked(false);
			userDetails.setRoles(Arrays.asList(Role.USER));

			userDetails.setUser(user);
			user.setUserDetails(userDetails);

			user = signupService.signUp(user);
			activationService.sendActivationLink(user, request);

			model.addAttribute("message", successMessage);
			return "message";
		} catch (UserAlreadyExistsException e) {
			throw new UserAlreadyExistsException(
					"This email address is already taken! Please try with different email address");
		}

	}

}
