package com.herokuapp.skmtodoapp.exception;

import java.text.ParseException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GenericWebApplicationExceptionHandler {

	@ExceptionHandler
	public ModelAndView textParseError(ParseException exception) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add-todo");
		mv.addObject("error", exception.getMessage());
		return mv;

	}

	@ExceptionHandler
	public ModelAndView nullPointerException(NullPointerException exception) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("error", "Something went wrong! Please login again");
		return mv;

	}

}
