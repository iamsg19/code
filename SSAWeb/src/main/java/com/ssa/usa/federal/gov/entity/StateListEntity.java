package com.ssa.usa.federal.gov.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="USA_STATES")
@Data
public class StateListEntity {
	
	@Id
	@Column(name="STATE_ID",length=2)
	private Integer stateId;
	
	@Column(name="STATE_CODE",length=2)
	private String stateCode;
	
	@Column(name="STATE_NAME",length=25)
	private String stateName;
}
