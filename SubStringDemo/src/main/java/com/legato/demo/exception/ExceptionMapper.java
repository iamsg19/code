package com.legato.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionMapper {
	@ExceptionHandler(value={SubStringException.class})
	public ResponseEntity<String> handleException()
	{
		return new ResponseEntity<String>("Enter String length minimum 6 length",HttpStatus.BAD_REQUEST);
	}
}
