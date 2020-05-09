package com.herokuapp.skmtodoapp.exception.model;

public class BadArgumentException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadArgumentException() {
		super();
	}

	public BadArgumentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadArgumentException(String message) {
		super(message);
	}

	public BadArgumentException(Throwable cause) {
		super(cause);
	}

}
