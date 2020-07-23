package com.springsecurity.demo.service.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springsecurity.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Serializable> {

	@Query(value = "select * from CM.Customer where approved='Approved By Role3' or approved='recomendedByRole2' or approved='Recomended by Role2 for Role3' or interestRate<12 or interestRate>=12 and interestRate<13 ", nativeQuery = true)
	List<Customer> findDataForRole3();

	@Query(value = "select * from CM.Customer where approved='Recomended by Role3' or approved='Approved by Role4'", nativeQuery = true)
	List<Customer> findDataForRole4();
}
