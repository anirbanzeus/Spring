package com.fse.springassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.springassignment.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
