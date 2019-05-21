package com.usa.nj.ed.gov.service;

import com.usa.nj.ed.gov.request.IndvInfoRequest;
import com.usa.nj.ed.gov.response.PlanInfoResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static com.usa.nj.ed.gov.constants.StringConstants.DENIED;
import static com.usa.nj.ed.gov.constants.StringConstants.APPROVED;
import static com.usa.nj.ed.gov.constants.StringConstants.HIGH_INCOME_OR_NO_CHILD;;

public class CcapRulesService implements IRuleService{

	@Override
	public PlanInfoResponse executeRules(IndvInfoRequest request) {
		
		PlanInfoResponse planInfoResponse=new PlanInfoResponse();
		Double income=request.getMonthlyIncome();
		Integer noOfKids=request.getNoOfKids();
		
		if(income>2000 && noOfKids<1)
		{
			planInfoResponse.setPlanStatus(DENIED);
			planInfoResponse.setDenialReason(HIGH_INCOME_OR_NO_CHILD);
		}
		else
		{
			planInfoResponse.setPlanName(request.getPlanName());
			planInfoResponse.setPlanStatus(APPROVED);
			planInfoResponse.setBenefitAmnt(400.00);
			planInfoResponse.setPlanStartDate(new Date());
			
			Calendar myCalendar = new GregorianCalendar(2020, 2, 11);
			Date endDate = myCalendar.getTime();
			planInfoResponse.setPlanEndDate(endDate);
		}
		
		return planInfoResponse;
	}

}
