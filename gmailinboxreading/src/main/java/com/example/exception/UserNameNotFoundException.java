package com.example.exception;

public class UserNameNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6427083368162436460L;

	public UserNameNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNameNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserNameNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNameNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNameNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
