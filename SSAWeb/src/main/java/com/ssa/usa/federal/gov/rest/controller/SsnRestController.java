package com.ssa.usa.federal.gov.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.usa.federal.gov.model.SsaModel;
import com.ssa.usa.federal.gov.service.SsaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



/**
 * This class is designed for performing different different restful operations.
 * This class is a RestController.
 * @author Shivaji Chandra
 *
 */
@RestController
@Api(value="/get",description="SsnRestController",produces="application/json")
@RequestMapping("/get")
public class SsnRestController {

	//declaring Logger for performing log operations
	public static final Logger logger = LoggerFactory.getLogger(SsnRestController.class);
	
	//SsaService object reference
	@Autowired
	private SsaService ssaService;
	
	
	/**
	 * @name	getStateBySsn
	 * @param	ssn
	 * @return	here this method is returning 
	 * 			the ResponseEntity<String> with state name and HttpStatus.
	 */
	@ApiOperation(value="getStateBySsn",response=String.class)
	@ApiResponses(value={
		    @ApiResponse(code=200,message="Customer Details Retrieved",response=String.class),
		   @ApiResponse(code=500,message="Internal Server Error"),
		   @ApiResponse(code=404,message="Customer not found")
		})
	@GetMapping(value="/state/{ssn}")
	public ResponseEntity<?> getStateBySsn(@PathVariable("ssn") Long ssn)
	{
		logger.debug("getState() started");
		
		//declaring SsaModel obj
		SsaModel ssaModel=null;
		
		//getting state by passing ssn to findStateBySsn method
		ssaModel=ssaService.findStateBySsn(ssn);
		
		logger.debug("getState() ended"); 
		
		return new ResponseEntity<>(ssaModel.getState(),HttpStatus.OK);
	}
}
