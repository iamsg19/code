package com.usa.nj.ed.gov.service;

import java.lang.reflect.Method;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.usa.nj.ed.gov.request.IndvInfoRequest;
import com.usa.nj.ed.gov.response.PlanInfoResponse;
import static com.usa.nj.ed.gov.constants.StringConstants.METHOD_NAME;
import static com.usa.nj.ed.gov.constants.StringConstants.SERVICE_PACKAGE;
import static com.usa.nj.ed.gov.constants.StringConstants.RULES_SERVICE;
import static com.usa.nj.ed.gov.constants.LoggerConstants.FIND_ELIG_STARTED;
import static com.usa.nj.ed.gov.constants.LoggerConstants.EXCEPTION_OCCURED_IN_FIND_ELIG_METHOD;
import static com.usa.nj.ed.gov.constants.LoggerConstants.FIND_ELIG_COMPLETED_SUCCESSFULLY;
import static com.usa.nj.ed.gov.constants.LoggerConstants.FIND_ELIG_ENDED;

/**
 * This Service Class is designed for determining the eligibility of the New
 * Jersey's Citizens. It determines the eligibility based on the citizen's
 * selected plans.
 * 
 * 
 * @author Shivaji Chandra
 *
 */
@Service
public class EdRulesServiceImpl implements EdRulesService {

	public static Logger logger = LoggerFactory.getLogger(EdRulesServiceImpl.class);

	/**
	 * This method is designed to find eligibility of a person based on the selected
	 * plans
	 * 
	 * @param request IndvInfoRequest
	 * @return PlanInfoResponse
	 */
	@Override
	public PlanInfoResponse findEligibility(IndvInfoRequest request) {
		logger.debug(FIND_ELIG_STARTED);
		String planName = request.getPlanName();
		PlanInfoResponse response = null;

		try {
			// Reflection API is used to load the classes based on the Plan's name.
			Class<?> clazz = Class.forName(SERVICE_PACKAGE + planName + RULES_SERVICE);

			// creating object of loaded class
			Object obj = clazz.newInstance();

			// getting the method of loaded class by method name
			Method method = clazz.getDeclaredMethod(METHOD_NAME, IndvInfoRequest.class);

			// invoking the method of loaded class
			Object retVal = method.invoke(obj, request);

			// getting the returns value to the response
			response = (PlanInfoResponse) retVal;

			logger.debug(FIND_ELIG_ENDED);
			logger.info(FIND_ELIG_COMPLETED_SUCCESSFULLY);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(EXCEPTION_OCCURED_IN_FIND_ELIG_METHOD);
		}
		return response;
	}

}
