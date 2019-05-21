package com.usa.nj.ed.gov.response;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import static com.usa.nj.ed.gov.constants.StringConstants.PLAN_INFO;

import lombok.Data;
@Data
@XmlRootElement(name =PLAN_INFO)
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanInfoResponse {

	private Integer planId;
	private String planName;
	private String planStatus;
	private Date planStartDate;
	private Date planEndDate;
	private Double benefitAmnt;
	private String denialReason;
}
