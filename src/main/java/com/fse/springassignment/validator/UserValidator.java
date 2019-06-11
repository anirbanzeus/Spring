package com.fse.springassignment.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.fse.springassignment.model.User;
import com.fse.springassignment.service.UserService;

@Component
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
		
		//Validate name
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
		if(!isValidName(user.getName())) {
			errors.reject("name","Type.userForm.name");
		}
		
		//Validate email
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		if(!isValidEmail(user.getEmail())) {
			errors.reject("email","Size.userForm.email.invalid");
		}
		
		//Validate username
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if(user.getUsername().length()<6 || user.getUsername().length()>32) {
			errors.reject("username","Size.userForm.username");
		}
		if(userService.findUserByName(user.getUsername()) != null) {
			errors.reject("username", "Duplicate.userForm.username");
		}
		
		//validate password
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if(user.getPassword().length()<6 || user.getPassword().length()>32) {
			errors.reject("password","Size.userForm.password");
		}
		if(!user.getPassword().equalsIgnoreCase(user.getPasswordConfirm())) {
			errors.reject("password", "Diff.userForm.passwordConfirm");
		}
	}
	
	static boolean isValidEmail(String email) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	   }
	
	static boolean isValidName(String name) {
		Pattern p = Pattern.compile("[^A-Za-z]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(name);
		boolean b = m.find();
	    return !b;
	   }

}
