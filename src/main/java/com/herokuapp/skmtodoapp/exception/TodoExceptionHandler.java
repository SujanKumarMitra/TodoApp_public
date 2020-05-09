package com.herokuapp.skmtodoapp.exception;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.herokuapp.skmtodoapp.exception.model.BadArgumentException;

@ControllerAdvice
public class TodoExceptionHandler {

	@ExceptionHandler
	public void badArgument(NumberFormatException exception, HttpSession session, HttpServletResponse response)
			throws Exception {
		session.setAttribute("error", "Bad Argument " + exception.getMessage());
		response.sendRedirect("/todo");

	}

	@ExceptionHandler
	public void invalidArgument(BadArgumentException exception, HttpSession session, HttpServletResponse response)
			throws Exception {
		session.setAttribute("error", exception.getMessage());
		response.sendRedirect("/todo");

	}

	@ExceptionHandler
	public void invalidArgument(MissingServletRequestParameterException exception, HttpSession session,
			HttpServletResponse response) throws Exception {
		session.setAttribute("error", "Missing Argument Parameter(s)");
		response.sendRedirect("/todo");

	}

}
