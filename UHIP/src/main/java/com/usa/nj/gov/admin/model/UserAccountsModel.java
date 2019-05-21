package com.usa.nj.gov.admin.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserAccountsModel {

	private String userId;
	private String firstName;

	private String lastName;

	private String userEmail;

	@DateTimeFormat(pattern="dd/M/yyyy")
	private Date userDob;

	private String gender;

	private Long ssn;

	private String password;
	
	private String userRole;

	private String createdBy;

	private String updatedBy;

	private String activeSwitch;
}
