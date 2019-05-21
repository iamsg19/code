package com.ssa.usa.federal.gov.controller;

import static com.ssa.usa.federal.gov.constants.StringConstants.DATA_MSG;
import static com.ssa.usa.federal.gov.constants.StringConstants.DATA_NOT_AVAILABLE;
import static com.ssa.usa.federal.gov.constants.StringConstants.ENROLL;
import static com.ssa.usa.federal.gov.constants.StringConstants.FAILURE;
import static com.ssa.usa.federal.gov.constants.StringConstants.FAILURE_MSG;
import static com.ssa.usa.federal.gov.constants.StringConstants.FEMALE;
import static com.ssa.usa.federal.gov.constants.StringConstants.GENDER;
import static com.ssa.usa.federal.gov.constants.StringConstants.HYPHEN;
import static com.ssa.usa.federal.gov.constants.StringConstants.MALE;
import static com.ssa.usa.federal.gov.constants.StringConstants.MODEL_DATA;
import static com.ssa.usa.federal.gov.constants.StringConstants.SAVE_DATA;
import static com.ssa.usa.federal.gov.constants.StringConstants.STATES;
import static com.ssa.usa.federal.gov.constants.StringConstants.SUCCESS;
import static com.ssa.usa.federal.gov.constants.StringConstants.SUCCESS_MSG;
import static com.ssa.usa.federal.gov.constants.StringConstants.VIEW_DATA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssa.usa.federal.gov.exception.CouldNotSaveException;
import com.ssa.usa.federal.gov.model.SsaModel;
import com.ssa.usa.federal.gov.properties.SsaAppProperties;
import com.ssa.usa.federal.gov.service.SsaService;
import com.ssa.usa.federal.gov.service.StateRestService;

/**
 * This class is designed to map the different
 * different request to the particular method.
 * 
 * @author Shivaji Chandra
 *
 */
@Controller
public class SsaController{

	@Autowired
	private StateRestService stateService;
	
	@Autowired
	private SsaService ssaService;
	
	@Autowired
	private SsaAppProperties prop;

	/**
	 * This method is used for launching the 
	 * form and getting the filled records in model obj
	 * @param Model model
	 * @return String ssnEnroll logicalViewName
	 */
	/*@RequestMapping(value= {"/","/ssnEnroll"},method=RequestMethod.GET)*/
	@GetMapping(value="/")
	public String ssnEnroll(Model model)
	{
		//adding empty entity to model scope for form binding
		model.addAttribute(MODEL_DATA , new SsaModel());
		
		//calling this to get 
		//gender and state details
		getFormDetails(model);
		
		return ENROLL;
	}
	
	/**
	 * This method is developed to enroll the SSN details
	 * 
	 * @param ssaModel
	 * @param model
	 * @return	String
	 * @throws CouldNotSaveException
	 */
	@PostMapping(value="saveSsn")
	public String saveSsn(@ModelAttribute(MODEL_DATA) SsaModel ssaModel,Model model)throws CouldNotSaveException
	{
		String strId=null;
		try 
			{
				//returing id is saved in id
				Long id=ssaService.saveSsn(ssaModel);
				
				//adding (-) in the id
				strId=new StringBuilder(id.toString()).insert(3, HYPHEN).insert(6, HYPHEN).toString();
				
				//sending new empty model
				model.addAttribute(MODEL_DATA , new SsaModel());
				
				Map<String,String> value=prop.getSsaProps();
				if(id!=null && id>1)
				{
					model.addAttribute(SUCCESS_MSG, value.get(SUCCESS)+strId);
				}
				else
				{
					model.addAttribute(FAILURE_MSG, value.get(FAILURE));
				}
				getFormDetails(model);
			}
			catch(Exception e)
			{
				throw new CouldNotSaveException(e.getMessage());
			}

		return SAVE_DATA;
	}
	
		//method to get gender and state details
		private void getFormDetails(Model model) {
			
			//creating gender list
			List<String> genderList=new ArrayList<String>(2);
			genderList.add(MALE);
			genderList.add(FEMALE);
			
			//adding genderList to model scope
			model.addAttribute(GENDER, genderList);
			
			//getting states from database
			List<String> states=stateService.getAllStates();
				
			//adding states to model scope
			model.addAttribute(STATES, states);
		}
		
	/**
	 * The method is developed to retrieve the SSN details
	 * and show on the page.
	 * 
	 * @param model
	 * @return	String(logical view name)
	 */
	@GetMapping("/viewData")
	public String showEnrolledData(Model model)
	{
		List<SsaModel> ssaModel=ssaService.retrieveSsnData();
		
		if(!ssaModel.isEmpty())
		{
			model.addAttribute(MODEL_DATA,ssaModel);
		}
		else
		{
			model.addAttribute(DATA_MSG , DATA_NOT_AVAILABLE);
		}
		return VIEW_DATA;
	}
}
