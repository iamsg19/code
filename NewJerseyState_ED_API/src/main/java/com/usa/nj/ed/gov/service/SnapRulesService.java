package com.usa.nj.ed.gov.service;

import com.usa.nj.ed.gov.request.IndvInfoRequest;
import com.usa.nj.ed.gov.response.PlanInfoResponse;
import static com.usa.nj.ed.gov.constants.StringConstants.DENIED;
import static com.usa.nj.ed.gov.constants.StringConstants.HIGH_INCOME;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.usa.nj.ed.gov.constants.StringConstants.APPROVED;
import static com.usa.nj.ed.gov.constants.LoggerConstants.EXECUTE_RULES_STARTED;
import static com.usa.nj.ed.gov.constants.LoggerConstants.EXECUTE_RULES_ENDED;
import static com.usa.nj.ed.gov.constants.LoggerConstants.EXECUTE_RULES_COMPLETED_SUCCESSFULLY;;

/**
 * This class is designed for performing the operation 
 * related to SNAP plan service.
 * 
 * @author Shivaji Chandra
 *
 */
public class SnapRulesService implements IRuleService{

	public static Logger logger=LoggerFactory.getLogger(SnapRulesService.class);
	
	/**
	 * This method is designed to perform executeRules for 
	 * the SNAP plan
	 * 
	 * @param	IndvInfoRequest request
	 * @return	PlanInfoResponse response
	 */
	@Override
	public PlanInfoResponse executeRules(IndvInfoRequest request) 
	{
		logger.debug(EXECUTE_RULES_STARTED);
		
			PlanInfoResponse planInfoResponse=new PlanInfoResponse();
			Double income=request.getMonthlyIncome();
			planInfoResponse.setPlanName(request.getPlanName());
			
			if(income>1000)
			{
				//Plan Denied logic
				planInfoResponse.setPlanStatus(DENIED);
				planInfoResponse.setDenialReason(HIGH_INCOME);
			}
			else
			{
				//Plan Approved Logic
				planInfoResponse.setPlanStatus(APPROVED);
				planInfoResponse.setBenefitAmnt(300.00);
				planInfoResponse.setPlanStartDate(new Date());
				
				Calendar myCalendar = new GregorianCalendar(2020, 2, 11);
				Date endDate = myCalendar.getTime();
				planInfoResponse.setPlanEndDate(endDate);
			}
		
		logger.debug(EXECUTE_RULES_ENDED);
		logger.info(EXECUTE_RULES_COMPLETED_SUCCESSFULLY);
		
		return planInfoResponse;
	}

}
