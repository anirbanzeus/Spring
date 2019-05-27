package com.fse.springassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.springassignment.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
