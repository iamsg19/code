package com.ssa.usa.federal.gov.exception;

import static com.ssa.usa.federal.gov.constants.StringConstants.ERROR_MSG;
import static com.ssa.usa.federal.gov.constants.StringConstants.SAVE_ERROR;
import static com.ssa.usa.federal.gov.constants.StringConstants.PROVIDE_VALID_SSN;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ssa.usa.federal.gov.apierror.SsaApiError;
import com.ssa.usa.federal.gov.rest.exception.StateNotFoundException;

/**
 * This class is developed to handle the exception
 * 
 * @author Shivaji
 *
 */
@Controller
@ControllerAdvice
public class ExceptionMapper {

	/**
	 * This method is developed to map exception with
	 * CouldNotSaveException
	 * 
	 * @param model
	 * @return String
	 */
	@ExceptionHandler(value={CouldNotSaveException.class})
	public String handleException(Model model)
	{
		model.addAttribute(ERROR_MSG , SAVE_ERROR);
		return "couldNotSave";
		
	}
	
	/**
	 * This method is developed to map exception with 
	 * StateNotFoundException
	 * 
	 * @return ResponseEntity<SsaApiError>
	 */
	@ExceptionHandler(value= {StateNotFoundException.class})
	public ResponseEntity<SsaApiError> handelStateNotFoundException()
	{
		SsaApiError error=new SsaApiError();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setExcepitonName(PROVIDE_VALID_SSN);
		error.setDate(new Date());
		
		return new ResponseEntity<SsaApiError>(error,HttpStatus.BAD_REQUEST);
	}
}
