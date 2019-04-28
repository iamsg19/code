package com.ssa.usa.federal.gov.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static com.ssa.usa.federal.gov.constants.StringConstants.*;

@Controller
@ControllerAdvice
public class ExceptionMapper {

	@ExceptionHandler(value={CouldNotSaveException.class})
	public String handleException(Model model)
	{
		model.addAttribute(ERROR_MSG , SAVE_ERROR);
		return "couldNotSave";
		
	}
}
