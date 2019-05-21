package com.usa.nj.ed.gov.service;

import com.usa.nj.ed.gov.request.IndvInfoRequest;
import com.usa.nj.ed.gov.response.PlanInfoResponse;

public interface IRuleService {
	
	public PlanInfoResponse executeRules(IndvInfoRequest request);
}
