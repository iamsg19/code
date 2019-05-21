package com.usa.nj.gov.admin.controller;

import static com.usa.nj.gov.util.UserAccountsConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usa.nj.gov.admin.exception.AccountActDectException;
import com.usa.nj.gov.admin.model.UserAccountsModel;
import com.usa.nj.gov.admin.properties.UhipAppProperties;
import com.usa.nj.gov.admin.service.IUserAccountsService;



/**
 * This is UserAccountsController
 * It is designed to perform different-different operations 
 * on User's accounts- like creating user, updating user etc..
 * 
 * @author Shivaji
 *
 */
@Controller
public class UserAccountsController {

	//Logger 
	private static final Logger logger = LoggerFactory.getLogger(UserAccountsController.class);

	@Autowired
	private IUserAccountsService userAccService;

	@Autowired
	private UhipAppProperties uhipAppProps;

	/**
	 * This is createUserAccount method
	 * It is designed to perform the creating user 
	 * and loading the form page.
	 * 
	 * @param model
	 * @return String(logicalViewName)
	 */
	@GetMapping(value= {"/","/loadform"})
	public String loadUserForm(Model model)
	{
		logger.debug(LOAD_USER_FORM_METHOD_STARTED);

		//storing the new UserAccountsModel() to save the form details
		model.addAttribute(NEW_USER_ACC_MODEL,new UserAccountsModel());
		//getting the gender and caseworker
		getFormDetails(model);

		logger.debug(LOAD_USER_FORM_METHOD_ENDED);
		logger.info(LOAD_USER_FORM_METHOD_COMPLETED_SUCCESSFULLY);

		return LOAD_FORM_PAGE;
	}

	/**
	 * This method is used to 
	 * load the gender and case_worker
	 * to the form
	 * 
	 * @param model
	 */
	private void getFormDetails(Model model)
	{
		logger.debug(GET_FORM_DETAILS_METHOD_STARTED);
		//creating the gender list
		ArrayList<String> gender=new ArrayList<String>(2);
		gender.add(MALE);
		gender.add(FEMALE);

		//setting the gender to form
		model.addAttribute(GENDER,gender);

		//creating the caseworker list
		ArrayList<String> role=new ArrayList<String>(2);
		role.add(ADMIN);
		role.add(CASE_WORKER);

		//setting the role to form
		model.addAttribute(ROLE,role);
		logger.debug(GET_FORM_DETAILS_METHOD_ENDED);
		logger.debug(GET_FORM_DETAILS_METHOD_COMPLETED_SUCCESSFULLY);
	}

	/**
	 * This is createUser() method
	 * This method is used to create an user in database
	 * 
	 * @param userAccModel
	 * @param model
	 * @return String(logicalViewName)
	 * @throws MessagingException 
	 */
	@PostMapping(value="/createuser")
	public String createUser(@ModelAttribute(USER_MODEL_DATA) UserAccountsModel userAccModel,Model model,RedirectAttributes redirect)
	{
		logger.debug(CREATE_USER_METHOD_STARTED);

		Map<String,String> props=uhipAppProps.getUhipProps();

		boolean status=userAccService.createUserAccount(userAccModel);

		model.addAttribute(NEW_USER_ACC_MODEL,new UserAccountsModel());

		getFormDetails(model);

		if(status)
		{
			redirect.addFlashAttribute(SUCCESS,props.get(SUCCESS));
		}
		else
			redirect.addFlashAttribute(FAILURE,props.get(FAILURE));

		logger.debug(CREATE_USER_METHOD_ENDED);
		logger.debug(CREATE_USER_METHOD_COMPLETED_SUCCESSFULLY);

		return "redirect:/"+LOAD_FORM_PAGE;
	}

	/**
	 * This method is used to checking 
	 * the entered email is unique or not.
	 * 
	 * @param req
	 * @return String as ResponseBody
	 */
	@GetMapping(value="/checkEmail")
	public @ResponseBody String checkEmail(HttpServletRequest req)
	{
		logger.debug(CHECK_EMAIL_METHOD_STARTED);

		//entered email is getting from request scope
		String email=req.getParameter("userEmail");

		//passing email to service layer for checking
		String status=userAccService.checkEmail(email);

		logger.debug(CHECK_EMAIL_METHOD_ENDED);
		logger.info(CHECK_EMAIL_METHOD_COMPLETED_SUCCESSFULLY);

		return status;
	}

	/**
	 * This method is used to map the request comming from 
	 * ui page for getting all the accounts details.
	 * 
	 * @param model
	 * @return String(logical view name)
	 */
	@GetMapping(value="/getAllAccounts")
	public String getAllAccounts(Model model)
	{
		logger.debug(GET_ALL_ACCOUNTS_METHOD_STARTED);
		//retrieving all the accounts and keeping into
		//the listModel obj
		List<UserAccountsModel> listModel=userAccService.retrieveAllAccounts();

		//checking listModel is empty or not
		if(!listModel.isEmpty())
		{
			//setting listModel to model
			model.addAttribute(LIST_MODEL,listModel);
		}
		else
		{
			//setting No data available to model.
			model.addAttribute(NO_DATA , NO_DATA_IN_TABLE);
		} 
		logger.debug(GET_ALL_ACCOUNTS_METHOD_ENDED);

		logger.info(GET_ALL_ACCOUNTS_METHOD_SUCCESSFULLY_COMPLETED);

		return "accounts";
	}

	/**
	 * This method is used to map the request coming to 
	 * activate or deactivate the user accounts.
	 * 
	 * @param 	userId
	 * @param 	activeSwitch
	 * @param 	redirect
	 * @return	String
	 */
	@GetMapping(value="/activateDeactivate/{userId}/{activeSwitch}")
	public String actDeactSwtch(@PathVariable(USER_ID)Integer userId,@PathVariable(ACTIVE_SWITCH)String activeSwitch,RedirectAttributes redirect)
	{
		logger.debug(ACT_DEACT_SWTCH_METHOD_STARTED);
		String status=null;

		//calling the service method to activate/deactivate account
		status=userAccService.actvtDeactvt(userId,activeSwitch);

		//adding status of account activation/deactivation
		redirect.addFlashAttribute(STATUS,status);
		logger.debug(ACT_DEACT_SWTCH_METHOD_ENDED);
		logger.info(ACT_DEACT_SWTCH_METHOD_COMPLETED_SUCCESSFULLY);
		return "redirect:/getAllAccounts";
	}		
}
