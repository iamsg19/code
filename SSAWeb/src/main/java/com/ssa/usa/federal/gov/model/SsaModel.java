package com.ssa.usa.federal.gov.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SsaModel {

	private String ssn;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;
	
	@NotNull
	@DateTimeFormat(pattern="dd/M/yyyy")
	private Date dob;

	@NotNull
	private String gender;
	
	@NotNull
	private String phoneNo;
	
	@NotNull
	private String state;
	
	private MultipartFile photo;
	
	private String createdBy;
	
	private String updatedBy;
	
	private String createdTime;
	
	private String updatedTime;
}
