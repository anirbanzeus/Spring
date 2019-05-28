package com.fse.springassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.fse.springassignment.auth.service.*;
import com.fse.springassignment.auth.validator.UserValidator;

public class UserController { 
	
	@Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
}
