package com.example.exception;

import javax.mail.AuthenticationFailedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Shivaji Chandra
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(InvalidUserNamePasswordExcpeption.class)
	public ResponseEntity<ExceptionMessage> invalidUserNamePasswordException(Exception ex, WebRequest request){
		
		ExceptionMessage response = new ExceptionMessage();
		response.setMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AuthenticationFailedException.class)
	public ResponseEntity<ExceptionMessage> authFailedException(Exception ex, WebRequest request){
		
		ExceptionMessage response = new ExceptionMessage();
		response.setMessage(ex.getMessage());
		response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
