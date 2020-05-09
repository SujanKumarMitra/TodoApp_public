package com.herokuapp.skmtodoapp.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.exception.model.UserAlreadyExistsException;
import com.herokuapp.skmtodoapp.exception.model.UserNotFoundException;
import com.herokuapp.skmtodoapp.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@Autowired
	private ApplicationContext context;

	@RequestMapping
	public String showProfile(HttpSession session, Model model) throws Exception {

		User user = context.getBean(User.class);
		model.addAttribute("user", user);
		return "profile";

	}

	@RequestMapping("/update")
	public String showUpdateProfilePage(HttpSession session, Model model) throws Exception {

		User user = context.getBean(User.class);

		model.addAttribute("user", user);
		return "update-profile";

	}

	@RequestMapping("/updateProfile")
	public String updateProfile(@ModelAttribute User user, HttpSession session, Model model) throws Exception {
		try {
			profileService.updateProfile(user);
			session.setAttribute("success", "You have updated your credentials! Please login again!");
			session.removeAttribute("user");
			return "redirect:/logout";
		} catch (UserAlreadyExistsException exception) {
			model.addAttribute("error", "This email is already taken.");
			return "update-profile";
		}

	}

	@RequestMapping("/delete")
	public String showDeleteProfilePage(HttpSession session, Model model) throws Exception {
		model.addAttribute("warning", "Sign in to confirm Account Deletion");
		return "delete-confirm";
	}

	@RequestMapping("/processDelete")
	public String deleteProfile(HttpSession session, Model model, @ModelAttribute User user)
			throws NoSuchAlgorithmException {

		try {
			profileService.deleteProfile(user);
			session.removeAttribute("user");
			return "redirect:/";
		} catch (UserNotFoundException e) {
			model.addAttribute("error", e.getMessage());
			return "delete-confirm";
		}
	}
}
