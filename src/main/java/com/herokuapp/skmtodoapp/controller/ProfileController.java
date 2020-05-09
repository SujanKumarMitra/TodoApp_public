package com.herokuapp.skmtodoapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.exception.model.UserAlreadyExistsException;
import com.herokuapp.skmtodoapp.exception.model.UserNotFoundException;
import com.herokuapp.skmtodoapp.service.AccountService;
import com.herokuapp.skmtodoapp.service.ProfileService;
import com.herokuapp.skmtodoapp.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping
	public String showProfile(HttpSession session, Model model) throws Exception {

		User user = userService.getUserBean();
		model.addAttribute("user", user);
		return "profile";
	}

	@RequestMapping("/update")
	public String showUpdateProfilePage(HttpSession session, Model model) throws Exception {

		User user = userService.getUserBean();

		model.addAttribute("user", user);
		return "update-profile";

	}

	@RequestMapping("/changePassword")
	public String changePassword(Model model) throws UserNotFoundException {

		User user = userService.getUserBean();
		model.addAttribute("user", user);

		return "update-password";
	}

	@PostMapping("/updatePassword")
	public String updatePassword(@ModelAttribute User user, @RequestParam("oldPassword") String oldPassoword,
			HttpSession session, Model model) throws UserNotFoundException {
		user.setPassword(encoder.encode(user.getPassword()));
		User sessionUser = userService.getUserBean();
		if (!encoder.matches(oldPassoword, sessionUser.getPassword())) {
			model.addAttribute("error", "Old password didn't matched!");
			return "update-password";
		}
		accountService.changePassword(user);
		SecurityContextHolder.clearContext();
		session.setAttribute("success", "You have updated your password! Please login again");
		return "redirect:/login";
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute User user, HttpSession session, Model model) throws Exception {
		try {
			profileService.updateProfile(user);
			session.setAttribute("success", "You have updated your profile details! Please login again");
			SecurityContextHolder.clearContext();
			return "redirect:/login";
		} catch (UserAlreadyExistsException exception) {
			model.addAttribute("error", "This email is already taken.");
			return "update-profile";
		}

	}

	@RequestMapping("/delete")
	public String showDeleteProfilePage(HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("message", "We have received your request. Please check your email.");
		accountService.sendDeleteLink(userService.getUserBean(), request);
		return "message";
	}
}
