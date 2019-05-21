package com.usa.nj.ed.gov.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import static com.usa.nj.ed.gov.constants.StringConstants.INDV_INFO;

import lombok.Data;

@Data
@XmlRootElement(name=INDV_INFO)
@XmlAccessorType(XmlAccessType.FIELD)
public class IndvInfoRequest {

	private Integer indvId;
	private Long ssn;
	private String firstName;
	private String lastName;
	private String planName;
	private Integer age;
	private String gender;
	private Double monthlyIncome;
	private Integer noOfKids;
	private String maritalStatus;
	private boolean qhpPurchased;
}
