package com.springsecurity.demo.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.demo.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByEmailIdIgnoreCase(String partnerEmailId);

	User findByEmailId(String emailId);
	
}
