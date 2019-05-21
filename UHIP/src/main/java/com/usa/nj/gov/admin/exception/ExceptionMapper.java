package com.usa.nj.gov.admin.exception;

import static com.usa.nj.gov.util.UserAccountsConstants.ERROR_MSG;
import static com.usa.nj.gov.util.UserAccountsConstants.SAVE_ERROR;

import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.mail.util.MailConnectException;

/**
 * This class is developed to map the different 
 * types of exception generated in this application
 * 
 * @author Shviaji
 *
 */
@Controller
@ControllerAdvice
public class ExceptionMapper {

	/**
	 * This method is developed to map exception with
	 * CreateUserAccountException
	 * 
	 * @param model
	 * @return String
	 */
	@ExceptionHandler(value={CreateUserAccountException.class})
	public String handleException(Model model)
	{
		model.addAttribute(ERROR_MSG , SAVE_ERROR);
		return "couldNotSave";

	}
	
	/**
	 * This method is developed to map exception with
	 * Exception(Any type of exception)
	 * 
	 * @param model
	 * @return String
	 */
	@ExceptionHandler(value={Exception.class})
	public String anyException(Model model)
	{
		model.addAttribute(ERROR_MSG , "Something went wrong");
		return "couldNotSave";

	}
	
	@ExceptionHandler(value= {EmailException.class})
	public String emailExcepiton(Model model)
	{
		return "emailexception";
	}
	
	@ExceptionHandler(value= {MailConnectException.class})
	public String mailConnectException(Model model)
	{
		return "connectException";
	}
}
