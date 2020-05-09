package com.herokuapp.skmtodoapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.herokuapp.skmtodoapp.entity.UserDetailsImpl;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String showLogin(Model model, HttpSession session) {

		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (object instanceof UserDetailsImpl) {
			session.setAttribute("warning", "You are already logged in!");
			return "redirect:/todo";
		}
		model.addAttribute("error", session.getAttribute("error"));
		model.addAttribute("warning", session.getAttribute("warning"));
		model.addAttribute("success", session.getAttribute("success"));
		session.removeAttribute("error");
		session.removeAttribute("warning");
		session.removeAttribute("success");
		return "login";
	}

}
