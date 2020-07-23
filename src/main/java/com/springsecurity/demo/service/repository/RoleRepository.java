package com.springsecurity.demo.service.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Serializable>{

}
