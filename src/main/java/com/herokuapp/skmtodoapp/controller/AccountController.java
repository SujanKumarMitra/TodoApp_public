package com.herokuapp.skmtodoapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.herokuapp.skmtodoapp.entity.User;
import com.herokuapp.skmtodoapp.exception.model.UserAlreadyActivatedException;
import com.herokuapp.skmtodoapp.exception.model.UserNotFoundException;
import com.herokuapp.skmtodoapp.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder encoder;

	@Value("${account.activate.success.message}")
	private String successMessage;

	@RequestMapping("/activate")
	public String activateAccount(@RequestParam("code") String code, HttpSession session,Model model)
			throws UserAlreadyActivatedException {
		try {
			accountService.activateAccount(code);
			session.setAttribute("success", successMessage);
			return "redirect:/login";
		} catch (UserAlreadyActivatedException e) {
			model.addAttribute("message", e.getMessage());
			return "message";
		} catch (UserNotFoundException e) {
			model.addAttribute("message", e.getMessage());
			return "message";
		}
	}
	
	@RequestMapping("/delete")
	public String deleteAccount(@RequestParam("code") String code,Model model) {
		try {
			accountService.deleteAccount(code);
			model.addAttribute("message", "Your account has been deleted");
			SecurityContextHolder.clearContext();
		} catch (UserNotFoundException e) {
			model.addAttribute("message", e.getMessage());
		}
		return "message";
	}
	
	@RequestMapping("/forgotPassword")
	public String forgotPassword() {
		return "password-forgot";
	}
	
	@PostMapping("/processForgotPassword")
	public String processForgotPassword(@RequestParam("email") String email,HttpServletRequest request,Model model) {
		
		try {
			accountService.sendPasswordResetLink(email,request);
			model.addAttribute("message", "You will receive an email shorly with link to reset your password.");
			return "message";
		} catch (UserNotFoundException e) {
			model.addAttribute("error", "No user found with email: "+email);
			return "password-forgot";
		}
	}
	
	@RequestMapping("/reset")
	public String resetPassword(@RequestParam("code") String code,Model model) {
		
		try {
			User user = accountService.getUserFromCode(code);
			model.addAttribute("user", user);
			return "reset-password";
		} catch (UserNotFoundException e) {
			model.addAttribute("message", "Your code is not valid!");
			return "message";
		}
		
		
	}
	
	@RequestMapping("/resetPassword")
	public String resetPassord(@ModelAttribute User user,HttpSession session) {
		user.setPassword(encoder.encode(user.getPassword()));
		accountService.changePassword(user);
		session.setAttribute("success", "Your password has been reset!");
		return "redirect:/login";
		
	}
	
	@ExceptionHandler
	public String handleException(MissingServletRequestParameterException exception,Model model) {
		model.addAttribute("message", "Code Missing!");
		return "message";
		
	}

}
