package com.herokuapp.skmtodoapp.exception.model;

public class UserAlreadyActivatedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserAlreadyActivatedException() {
		super();
	}

	public UserAlreadyActivatedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAlreadyActivatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlreadyActivatedException(String message) {
		super(message);
	}

	public UserAlreadyActivatedException(Throwable cause) {
		super(cause);
	}

}
