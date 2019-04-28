package com.ssa.usa.federal.gov.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="SSA_ENTITY")
public class SsaEntity {

	@Id
	@GeneratedValue(generator="ssn_seq_gen")
	@SequenceGenerator(
			name="ssn_seq_gen",
			sequenceName="ssn_seq",
			initialValue=214326453,
			allocationSize=1
			)
	@Column(name="SSN",length=9)
	private Long ssn;
	
	@Column(name="FIRST_NAME",length=15)
	private String firstName;
	
	@Column(name="LAST_NAME",length=15)
	private String lastName;
	
	@Column(name="DOB")
	@DateTimeFormat(style="dd/MM/yyyy")
	private String dob;
	
	@Column(name="GENDER",length=6)
	private String gender;
	
	@Column(name="PHONE_NO",length=15)
	private String phoneNo;
	
	@Column(name="STATE",length=20)
	private String state;
	
	@Column(name="PHOTO")
	@Lob
	private byte[] photo;
	
	@Column(name="CREATED_BY",length=30)
	private String createdBy;
	
	@Column(name="UPDATED_BY",length=30)
	
	private String updatedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE",length=15)
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE",length=15)
	private Date updatedDate;
}
