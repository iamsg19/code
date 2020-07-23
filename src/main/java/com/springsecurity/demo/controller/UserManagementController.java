package com.springsecurity.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springsecurity.demo.model.Customer;
import com.springsecurity.demo.model.User;
import com.springsecurity.demo.service.UserManagementService;

@RestController
@RequestMapping("/api")
public class UserManagementController {

	@Autowired
	private UserManagementService userService;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@GetMapping("/get-encrypted-password")
	public String getEncryptedPass(@RequestParam("password") String value) {

		return passwordEncoder.encode(value);
	}

	/**
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/authFailure")
	public ModelAndView authFailure(ModelAndView modelAndView, User user) {
		modelAndView.setViewName("authFailure");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/accessDenied")
	public ModelAndView accessDenied(ModelAndView modelAndView, User user) {
		modelAndView.setViewName("accessDenied");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/login")
	public ModelAndView displayLogin(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/register")
	public ModelAndView register(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param customer
	 * @return
	 */
	@PostMapping(value = "/register")
	public ModelAndView registerData(ModelAndView modelAndView, Customer customer) {

		String message = userService.createData(customer);
		modelAndView.addObject("message", message);

		modelAndView.setViewName("success");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param customer
	 * @return
	 */
	@GetMapping(value = "/approve/{id}")
	public ModelAndView approval(ModelAndView modelAndView, @PathVariable("id") Integer id) {

		List<Customer> customer = userService.update(id);
		modelAndView.addObject("customers", customer);

		modelAndView.setViewName("viewData");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @return
	 */
	@GetMapping(value = "/viewData")
	public ModelAndView viewData(ModelAndView modelAndView) {

		List<Customer> customers = userService.findAllData();

		modelAndView.addObject("customers", customers);

		modelAndView.setViewName("viewData");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param customer
	 * @return
	 */
	@GetMapping(value = "/approve-by-role3/{id}")
	public ModelAndView role3Approval(ModelAndView modelAndView, @PathVariable("id") Integer id) {

		List<Customer> customers = userService.updateByRole3(id);
		modelAndView.addObject("customers", customers);

		modelAndView.setViewName("role3data");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @return
	 */
	@GetMapping(value = "/recomended/data")
	public ModelAndView viewDataForRole3(ModelAndView modelAndView) {

		List<Customer> customers = userService.findDataForRole3();
		ArrayList<Customer> c = new ArrayList<Customer>();
		customers.forEach(cust -> {
			if (!cust.getApproved().equals("Approved by Role4")) {
				Customer cs = new Customer();
				cs.setApproved(cust.getApproved());
				cs.setCustomerName(cust.getCustomerName());
				cs.setInterestRate(cust.getInterestRate());
				cs.setJobType(cust.getJobType());
				cs.setLoanAmount(cust.getLoanAmount());
				cs.setEnquiryId(cust.getEnquiryId());

				c.add(cs);
			}
		});
		modelAndView.addObject("customers", c);

		modelAndView.setViewName("role3data");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @return
	 */
	@GetMapping(value = "/final/data")
	public ModelAndView finalData(ModelAndView modelAndView) {

		List<Customer> customers = userService.findDataForRole4();

		modelAndView.addObject("customers", customers);

		modelAndView.setViewName("role4data");
		return modelAndView;
	}

	/**
	 * @param modelAndView
	 * @param customer
	 * @return
	 */
	@GetMapping(value = "/approve-by-role4/{id}")
	public ModelAndView role4Approval(ModelAndView modelAndView, @PathVariable("id") Integer id) {

		List<Customer> customers = userService.updateByRole4(id);
		modelAndView.addObject("customers", customers);

		modelAndView.setViewName("role4data");
		return modelAndView;
	}
}
