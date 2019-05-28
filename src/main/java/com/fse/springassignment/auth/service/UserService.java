package com.fse.springassignment.auth.service;

import com.fse.springassignment.model.User;

public interface UserService {
	
	void saveUser(User user);
	
	User findUserByName(String name);

}
