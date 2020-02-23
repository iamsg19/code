package com.example.exception;

import java.security.Timestamp;

/**
 * @author Shivaji Chandra
 *
 */
public class ExceptionMessage {

	private int httpStatus;
	private String message;
	private String country;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
