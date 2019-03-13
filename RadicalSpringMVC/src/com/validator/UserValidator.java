package com.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.radical.entity.UserEntity;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return UserEntity.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required");
		
		UserEntity user = (UserEntity)obj;
		if(user.getfName().length() > 10){
			errors.rejectValue("fName", "size.invalid", new Object[]{"first Name"}, "{0} can't be more than {1}");
		}
	}

	

}
