package com.springsecurity.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.demo.model.Customer;
import com.springsecurity.demo.service.repository.CustomerRepository;

@Service
public class UserManagementService {

	@Autowired
	private CustomerRepository customerRepository;

	public String createData(Customer customer) {

		setApproval(customer);
	
		Customer cus = customerRepository.save(customer);
		
		return "Successfully Created";
	}

	
	
	private void setApproval(Customer customer) {

		if (customer.getInterestRate() > 14) {

			customer.setApproved("By default Approved");
		} else {
			customer.setApproved("No");
		}
	}

	public List<Customer> findAllData() {

		return customerRepository.findAll();
	}

	public List<Customer> update(Integer id) {

		Customer customer = customerRepository.findById(id).get();
		if (customer != null) {

			role2Approval(customer);

			customerRepository.save(customer);

			return customerRepository.findAll();
		} else {
			return customerRepository.findAll();
		}

	}

	private void role2Approval(Customer customer) {

		if (customer.getInterestRate() >= 13 && customer.getInterestRate() < 14) {

			customer.setApproved("Approved By Role2");

		} else if (customer.getInterestRate() >= 12 && customer.getInterestRate() < 13) {

			customer.setApproved("recomendedByRole2");

		} else if (customer.getInterestRate() < 12) {

			customer.setApproved("Recomended by Role2 for Role3");
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public List<Customer> updateByRole3(Integer id) {

		Customer customer = customerRepository.findById(id).get();
		if (customer != null) {
			setApprovalByRole3(customer);
			customerRepository.save(customer);

			return customerRepository.findDataForRole3();
		} else {
			return customerRepository.findDataForRole3();
		}
	}

	private void setApprovalByRole3(Customer customer) {

		if (customer.getInterestRate() >= 12 && customer.getInterestRate() < 13) {

			customer.setApproved("Approved By Role3");
		} else if (customer.getApproved().equals("Recomended by Role2 for Role3")) {

			customer.setApproved("Recomended by Role3");
		}
	}

	/**
	 * Method to
	 * 
	 * @return
	 */
	public List<Customer> findDataForRole3() {

		return customerRepository.findDataForRole3();
	}

	public List<Customer> findDataForRole4() {

		return customerRepository.findDataForRole4();
	}

	/**
	 * @param id
	 * @return
	 */
	public List<Customer> updateByRole4(Integer id) {
		Customer customer = customerRepository.findById(id).get();
		if (customer != null) {
			customer.setApproved("Approved by Role4");
			customerRepository.save(customer);

			return customerRepository.findDataForRole4();
		} else {
			return customerRepository.findDataForRole4();
		}
	}
}
