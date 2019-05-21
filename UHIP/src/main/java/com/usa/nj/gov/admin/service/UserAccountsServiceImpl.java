package com.usa.nj.gov.admin.service;

import static com.usa.nj.gov.util.UserAccountsConstants.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.nj.gov.admin.entity.UserAccountsEntity;
import com.usa.nj.gov.admin.exception.AccountActDectException;
import com.usa.nj.gov.admin.exception.CreateUserAccountException;
import com.usa.nj.gov.admin.exception.EmailException;
import com.usa.nj.gov.admin.model.UserAccountsModel;
import com.usa.nj.gov.admin.properties.UhipAppProperties;
import com.usa.nj.gov.admin.repository.UserAccountsRepository;
import com.usa.nj.gov.util.Email;

/**
 * This is UserAccountServiceImpl class.
 * This class is developed to perform different-different
 * operation related to User.
 * 
 * @author Shivaji
 *
 */
@Service
public class UserAccountsServiceImpl implements IUserAccountsService{
	
	//UHIP properties
	@Autowired 
	private UhipAppProperties uhipAppProps;
	//Email
	@Autowired
	private Email email;
	//Logger 
	private static final Logger logger = LoggerFactory.getLogger(UserAccountsServiceImpl.class);
	//UserAccountsRepository
	@Autowired
	private UserAccountsRepository userAccountRepo;
	
	/**
	 * This method is developed to create the user in Database
	 * 
	 * @param	UserAccountsModel userAccountModel
	 * @return	boolean
	 */
	@Override
	public boolean createUserAccount(UserAccountsModel userAccountModel) {
		
		logger.debug(CREATE_USER_ACC_METHOD_STARTED); 

		boolean status=false;
		UserAccountsEntity userAccountsEntity=new UserAccountsEntity();
		try
		{
			logger.debug(CHECKING_EXCEPTION_IN_TRY);
			
			//copying userAccountModel to userAccountEntity
			BeanUtils.copyProperties(userAccountModel, userAccountsEntity);
			//adding account active switch
			userAccountsEntity.setActiveSwitch(ACTIVE);
			
			//saving userAccountEntity to database
			userAccountsEntity=userAccountRepo.save(userAccountsEntity);

			//checking userAccountEntity is saved or not
			if(userAccountsEntity.getUserId()>0)
			{
				logger.info(USER_ACCOUNT_CREATED_SUCCESSFULLY);
				
				//loading yml properties to Map object
				Map<String,String> props=uhipAppProps.getUhipProps();	
				
				//sending mail
				email.sendMail(userAccountModel.getUserEmail(),props.get(SUBJECT),getMailBody(userAccountModel));
				status=true;
			}
		}
		catch(Exception e)
		{
			logger.error(EXCEPTION_GENERATED+e.getMessage());
			throw new CreateUserAccountException(e.getMessage());
		}
		
		logger.debug(CREATE_USER_ACC_METHOD_ENDED);
		
		logger.info(CREATE_USER_ACC_METHOD_COMPLETED_SUCCESSFULLY);
		
		return status;
	}
	
	/**
	 * This method is designed to check the 
	 * email is in database or not.
	 * 
	 * @param	String(email)
	 * @return	String(status)
	 */
	@Override
	public String checkEmail(String email)
	{
		logger.debug(CHECK_EMAIL_METHOD_STARTED);
		String flag=UNIQUE;
		Integer status=null;
		try
		{
			logger.debug(CHECKING_ENTERED_EMAIL);
			status=userAccountRepo.checkEmail(email);
		}
		catch(EmailException e)
		{
			logger.error(EXCEPTION_GENERATED_INTO_CHECK_EMAIL_METHOD);
			throw new EmailException(SOMETHING_WENT_WRONG+e.getMessage());
		}
		if(status>0)
			flag=DUPLICATE;
		
		logger.debug(CHECK_EMAIL_METHOD_ENDED);
		logger.info(CHECK_EMAIL_METHOD_COMPLETED_SUCCESSFULLY);
		return flag;
	}
	
	/**
	 * This method is designed to define the body of Email and
	 * replace the placeholeder of the body.
	 * 
	 * @param userAccountModel
	 * @return	String
	 */
	private String getMailBody(UserAccountsModel userAccountModel)
	{
		logger.debug(GET_MAIL_BODY_METHOD_STARTED);
		String line=null;
		StringBuilder sb=new StringBuilder();
		try 
		{
			//loading file
			File file=new File(EMAIL_BODY_FILE);
			
			//adding file to FileReader
			FileReader fr=new FileReader(file);
			
			//because of FileReader read the file character by character
			//thats why we linked FileReader to BufferedReader
			BufferedReader br=new BufferedReader(fr);
			logger.debug(FILE_LOADED_SUCCESSFULLY_AND_LOADED_TO_BUFFERED_READER);
			//reading one line from file
			line=br.readLine();
			//condition to replace placeholders
			while(line!=null)
			{
				logger.debug(ENTERED_INTO_WHILE_LOOP);
				//if the line is there and $ is not then
				//this will execute to skip that line
				if(line.equals(DOUBLE_QUOTE) || !line.contains(DOLLOR_SYMBOL))
				{
					sb.append(line);
					line=br.readLine();
					continue;
				}
				//replacing the ${FIRST_NAME} and ${LAST_NAme} to user's name
				if(line.contains(FIRST_NAME)||line.contains(LAST_NAME))
				{
					line=line.replace(FIRST_NAME, userAccountModel.getFirstName());
					
					line=line.replace(LAST_NAME, userAccountModel.getLastName());
					sb.append(line);
					
				}
				/*if(line.contains("${LAST_NAME}"))
				{
					line=line.replace("${LAST_NAME}", userAccountModel.getLastName());
					sb.append(line);
				}*/
				//replacing ${URL} to WEBSITE
				if(line.contains(URL))
				{
					line=line.replace(URL, WEBSITE);
					sb.append(line);
				}
				//replacing ${EMAIL} to user's Email
				if(line.contains(EMAIL))
				{
					line=line.replace(EMAIL, userAccountModel.getUserEmail());
					sb.append(line);
				}
				//replacing ${PASSWORD} to user's given Password
				if(line.contains(PASSWORD))
				{
					line=line.replace(PASSWORD, userAccountModel.getPassword());
					sb.append(line);
				}
				//replacing ${ROLE} to user's Role
				if(line.contains(USER_ROLE))
				{
					line=line.replace(USER_ROLE, userAccountModel.getUserRole());
					sb.append(line);
				}
				//replacing ${PHONE_NO} to UHIP Admin's number
				if(line.contains(PHONE_NO))
				{
					line=line.replace(PHONE_NO, NUMBER);
					sb.append(line);
				}
				//reading next line from file
				line=br.readLine();
			}
			logger.debug(EXITED_FROM_WHILE_LOOP);
		} 
		catch (Exception e) 
		{
			logger.error(EXCEPTION_GENERATED_IN_GET_MAIL_BODY_METHOD);
		}
		logger.debug(GET_MAIL_BODY_METHOD_ENDED);
		logger.info(GET_MAIL_BODY_METHOD_COMPLETED_SUCCESSFULLY);
		
		return	sb.toString();
	}

	/**
	 * This method is designed to retrieve all the 
	 * accounts in database.
	 * 
	 * @return	List<UserAccountsModel>
	 */
	@Override
	public List<UserAccountsModel> retrieveAllAccounts() {
		
		logger.debug(RETRIEVE_ALL_ACCOUNTS_METHOD_STARTED);
		//declaring UserAccountsModel
		List<UserAccountsModel> listModel=null;
		
		//declaring UserAccountsEntity
		List<UserAccountsEntity> listEntity=null;
		
		//creating empty listModel obj
		listModel=new ArrayList<UserAccountsModel>();
		
		//retrieving all the records to listEntity obj
		logger.debug(RETRIEVING_ACCOUNTS);
		listEntity=userAccountRepo.findAll();
		logger.info(RETRIEVING_ACCOUNTS_EXECUTED_SUCCESSFULLY);
		
		//checking listEntity is empty or not
		if(!listEntity.isEmpty())
		{
			logger.debug(COPYING_DATA_FROM_ENTITY_TO_MODEL);
			for(UserAccountsEntity entity:listEntity)
			{
				//creating empty model obj to store single UserAccountsModel obj
				UserAccountsModel model=new UserAccountsModel();
				
				//copying entity obj to model obj
				BeanUtils.copyProperties(entity, model);
				
				//setting the userId to the model obj
				model.setUserId(entity.getUserId().toString());
				
				//adding model to listModel
				listModel.add(model);
			}
			logger.debug(COPIED_SUCCESSFULLY);
		}
		logger.info(RETRIEVING_ALL_ACCOUNTS_ENDED);
		logger.debug(RETRIEVING_ALL_ACCOUNTS_COMPLETED_SUCCESSFULLY);
		return listModel;
	}

	/**
	 * This method is used to activate/deactivate
	 * the user accounts
	 * 
	 * @param	userId
	 * @param	activeSwitch
	 * @return	String
	 */
	@Override
	public String actvtDeactvt(Integer userId,String activeSwitch)
	{
		logger.debug(ACTVT_DEACTVT_METHOD_STARTED);
		String accStatus=null;
		
		//logic to deactivate the account
		if(activeSwitch.equals(Y))
		{
				activeSwitch=N;
				accStatus=DEACTIVATED;
		}
		//logic to activate the account
		else if(activeSwitch.equals(N))
		{
			activeSwitch=Y;
			accStatus=ACTIVATED;
		}
		//updating account active/inactive in database
		Integer status=userAccountRepo.updateActiveSwitch(activeSwitch,userId);
		
		logger.debug(ACTVT_DEACTVT_METHOD_ENDED);
		logger.info(ACTVT_DEACTVT_METHOD__COMPLETED_SUCCESSFULLY);
		
		return accStatus;
	}
	
}