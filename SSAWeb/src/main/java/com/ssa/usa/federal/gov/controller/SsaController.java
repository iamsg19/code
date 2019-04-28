package com.ssa.usa.federal.gov.controller;

import static com.ssa.usa.federal.gov.constants.StringConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.ssa.usa.federal.gov.service.StateService;
@Controller
public class SsaController {


	@Autowired
	private StateService stateService;
	
	@Autowired
	private SsaService ssaService;
	
	@Autowired
	SsaAppProperties prop;
	/**
	 * This method is used for launching the 
	 * form and getting the filled records in model
	 * @param model
	 * @return ssnEnroll
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
