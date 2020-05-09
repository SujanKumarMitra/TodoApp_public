package com.herokuapp.skmtodoapp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.herokuapp.skmtodoapp.exception.model.UserAlreadyExistsException;

@ControllerAdvice
public class SignupExceptionHandler {

	@ExceptionHandler
	public ModelAndView userAlreadyExists(UserAlreadyExistsException exception) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup");
		mv.addObject("error", exception.getMessage());
		return mv;

	}

}
