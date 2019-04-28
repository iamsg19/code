package com.ssa.usa.federal.gov.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SsaModel {

	private String ssn;

	private String firstName;

	private String lastName;
	
	private String dob;

	private String gender;
	
	private String phoneNo;
	
	private String state;
	
	private MultipartFile photo;
	
	private String createdBy;
	
	private String updatedBy;
	
	private String createdTime;
	
	private String updatedTime;
}
