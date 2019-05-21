package com.usa.nj.ed.gov.service;

import com.usa.nj.ed.gov.request.IndvInfoRequest;
import com.usa.nj.ed.gov.response.PlanInfoResponse;

public interface EdRulesService {

	public PlanInfoResponse findEligibility(IndvInfoRequest request);
}
