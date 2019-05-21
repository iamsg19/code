package com.usa.nj.ed.gov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.usa.nj.ed.gov.request.IndvInfoRequest;
import com.usa.nj.ed.gov.response.PlanInfoResponse;
import com.usa.nj.ed.gov.service.EdRulesService;

@RestController
public class EdRulesRestController {

	@Autowired
	private EdRulesService service;
	
	@PostMapping(value = "/ed", consumes = { "application/xml", "application/json" }, produces = {
			"application/xml", "application/json" })
	public @ResponseBody PlanInfoResponse checkEligibility(@RequestBody IndvInfoRequest request) {
		PlanInfoResponse response = service.findEligibility(request);
		return response;
	}
}
