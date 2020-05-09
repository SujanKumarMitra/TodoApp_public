package com.herokuapp.skmtodoapp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.herokuapp.skmtodoapp.exception.model.UserNotFoundException;

@ControllerAdvice
public class LoginExceptionHandler {

	@ExceptionHandler
	public ModelAndView userNotFoundException(UserNotFoundException exc) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("error", exc.getMessage());
		return mv;

	}

}
