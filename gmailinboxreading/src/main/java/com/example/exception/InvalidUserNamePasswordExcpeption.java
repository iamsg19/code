package com.example.exception;

public class InvalidUserNamePasswordExcpeption extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6554235444536576887L;

	public InvalidUserNamePasswordExcpeption(String message) {
		super(message);
	}
	public InvalidUserNamePasswordExcpeption(String message, Throwable th) {
		super(message, th);
	}
}
