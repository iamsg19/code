package com.springsecurity.demo.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return true;
	}

}
