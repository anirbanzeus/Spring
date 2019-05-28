package com.fse.springassignment.auth.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fse.springassignment.model.User;
import com.fse.springassignment.repository.RoleRepository;
import com.fse.springassignment.repository.UserRepository;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
		
	}

	@Override
	public User findUserByName(String name) {
		return userRepository.findByUsername(name);
	}

}
