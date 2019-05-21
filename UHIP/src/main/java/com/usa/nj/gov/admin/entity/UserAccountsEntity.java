package com.usa.nj.gov.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="USER_ACCOUNTS")
@Data
public class UserAccountsEntity {

	@Id
	@GeneratedValue(generator="user_acc_seq")
	@SequenceGenerator(
			name="user_acc_seq_gen",
			sequenceName="user_acc_seq",
			initialValue=1,
			allocationSize=1
			)
	@Column(name="USER_ID",length=6)
	private Integer userId;
	
	@Column(name="FIRST_NAME",length=15)
	private String firstName;
	
	@Column(name="LAST_NAME",length=15)
	private String lastName;
	
	@Column(name="USER_EMAIL",length=35)
	private String userEmail;
	@Temporal(TemporalType.DATE)
	@Column(name="USER_DOB")
	private Date userDob;
	
	@Column(name="GENDER",length=6)
	private String gender;
	
	@Column(name="USER_SSN")
	private Long ssn;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="USER_ROLE",length=15)
	private String userRole;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private	Date updatedDate;
	
	@Column(name="CREATED_BY",length=35)
	private String createdBy;
	
	@Column(name="UPDATED_BY",length=35)
	private String updatedBy;
	
	@Column(name="ACTIVE_DEACTIVE",length=2)
	private String activeSwitch;
}
