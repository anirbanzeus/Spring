package com.fse.springassignment.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fse.springassignment.model.User;
import com.fse.springassignment.service.UserService;

public class UserValidator implements Validator {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if(user.getUsername().length()<6 || user.getUsername().length()>32) {
			errors.reject("username","Size.userForm.username");
		}
		if(userService.findUserByName(user.getUsername()) != null) {
			errors.reject("username", "Duplicate.userForm.username");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if(user.getPassword().length()<6 || user.getPassword().length()>32) {
			errors.reject("password","Size.userForm.password");
		}
		if(!user.getPassword().equalsIgnoreCase(user.getPasswordConfirm())) {
			errors.reject("password", "Diff.userForm.passwordConfirm");
		}
	}

}
