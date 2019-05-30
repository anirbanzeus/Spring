package com.fse.springassignment.service;

import com.fse.springassignment.model.User;

public interface UserService {
	
	void saveUser(User user);
	
	User findUserByName(String name);

}
