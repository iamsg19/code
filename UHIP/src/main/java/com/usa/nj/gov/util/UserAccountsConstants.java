package com.usa.nj.gov.util;

public class UserAccountsConstants {

	//ExceptionMapper class Constants
	public static final String SAVE_ERROR="Something went wrong!!!<br> Please try later!!!";
	public static final String ERROR_MSG="errMsg";

	//UserAccountsServiceImpl class constants
	public static final String EXCEPTION_GENERATED="Something went worng...!!!";
	public static final String CREATE_USER_ACC_METHOD_STARTED="createUserAccount() method started...";
	public static final String CHECKING_EXCEPTION_IN_TRY="Checking Exception in try block";
	public static final String CREATE_USER_ACC_METHOD_ENDED="createUserAccount() method ended...";
	public static final String CREATE_USER_ACC_METHOD_COMPLETED_SUCCESSFULLY="createUserAccount() method completed successfully";
	public static final String ACTIVE="Y";
	public static final String DE_ACTIVE="N";
	
		//actvtDeactvt() method constants
		public static final String Y="Y";
		public static final String N="N";
		public static final String ACTIVATED="Activated";
		public static final String DEACTIVATED="Deactivated";
		public static final String ACTVT_DEACTVT_METHOD_STARTED="actvtDeactvt() method started";
		public static final String ACTVT_DEACTVT_METHOD_ENDED="actvtDeactvt() method ended";
		public static final String ACTVT_DEACTVT_METHOD__COMPLETED_SUCCESSFULLY="actvtDeactvt() method completed successfully";
	
	//UserAccountsController class constants
	
		//getFormDetails() method constants
		public static final String MALE="Male";
		public static final String FEMALE="Female";
		public static final String GENDER="gender";
		public static final String ADMIN="Admin";
		public static final String CASE_WORKER="Case Worker";
		public static final String ROLE="role";
		public static final String GET_FORM_DETAILS_METHOD_STARTED="getFormDetails() method started";
		public static final String GET_FORM_DETAILS_METHOD_ENDED="getFormDetails() method ended";
		public static final String GET_FORM_DETAILS_METHOD_COMPLETED_SUCCESSFULLY="getFormDetails() method completed successfully";
		
		//createUser() method constants
		public static final String SUCCESS="success";
		public static final String FAILURE="failure";
		public static final String USER_MODEL_DATA="userModelData";
		public static final String CREATE_USER_METHOD_STARTED="createUser() method started";
		public static final String USER_ACCOUNT_CREATED_SUCCESSFULLY="User Account created successfully";
		public static final String CREATE_USER_METHOD_ENDED="createUser() method ended";
		public static final String CREATE_USER_METHOD_COMPLETED_SUCCESSFULLY="createUser() method completed successfully";
		public static final String SUBJECT="subject";
		
		//loadUserForm() method constants
		public static final String NEW_USER_ACC_MODEL="userAccModel";
		public static final String LOAD_FORM="form";
		public static final String LOAD_FORM_PAGE="loadform";
		public static final String LOAD_USER_FORM_METHOD_STARTED="loadUserForm() method started";
		public static final String LOAD_USER_FORM_METHOD_ENDED="loadUserForm() method ended";
		public static final String LOAD_USER_FORM_METHOD_COMPLETED_SUCCESSFULLY="loadUserForm() completed successfully";
	
		//checkEmail() method constants
		public static final String UNIQUE="Unique";
		public static final String DUPLICATE="Duplicate";
		public static final String CHECK_EMAIL_METHOD_STARTED="checkEmail() method Started";
		public static final String CHECKING_ENTERED_EMAIL="Checking Entered Email";
		public static final String EXCEPTION_GENERATED_INTO_CHECK_EMAIL_METHOD="Exception generated into checkEmail() method";
		public static final String CHECK_EMAIL_METHOD_ENDED="checkEmail() method ended";
		public static final String CHECK_EMAIL_METHOD_COMPLETED_SUCCESSFULLY="checkEmail() method completed successfully";
		public static final String SOMETHING_WENT_WRONG="Something went Wrong... Check your entered Email!!";
		
		//getMailBody() method constants
		public static final String EMAIL_BODY_FILE="src//main//resources//MAIL_BODY_TEMPLATE.txt";
		public static final String DOLLOR_SYMBOL="$";
		public static final String DOUBLE_QUOTE="";
		public static final String FIRST_NAME="${FIRST_NAME}";
		public static final String LAST_NAME="${LAST_NAME}";
		public static final String URL="${URL}";
		public static final String EMAIL="${EMAIL}";
		public static final String PASSWORD="${PASSWORD}";
		public static final String PHONE_NO="${PHONE_NO}";
		public static final String USER_ROLE="${ROLE}";
		public static final String NUMBER="+91-7735136806";
		public static final String WEBSITE="https://www.uhip.com";
		public static final String GET_MAIL_BODY_METHOD_STARTED="getMailBody() method started";
		public static final String FILE_LOADED_SUCCESSFULLY_AND_LOADED_TO_BUFFERED_READER="File loaded successfully and loaded to Buffered reader";
		public static final String ENTERED_INTO_WHILE_LOOP="Entered into while loop";
		public static final String EXITED_FROM_WHILE_LOOP="Exited from while loop";
		public static final String EXCEPTION_GENERATED_IN_GET_MAIL_BODY_METHOD="Exception generated in getMailBody() method";
		public static final String GET_MAIL_BODY_METHOD_ENDED="getMailBody() method ended";
		public static final String GET_MAIL_BODY_METHOD_COMPLETED_SUCCESSFULLY="getMailBody() method completed successfully";
		
		//getAllAccounts() method constants
		public static final String LIST_MODEL="listModel";
		public static final String NO_DATA="noData";
		public static final String NO_DATA_IN_TABLE="!!!No Data Available in table!!!";
		public static final String GET_ALL_ACCOUNTS_METHOD_STARTED="getAllAccounts() method started";
		public static final String GET_ALL_ACCOUNTS_METHOD_ENDED="getAllAccounts() method ended";
		public static final String GET_ALL_ACCOUNTS_METHOD_SUCCESSFULLY_COMPLETED="getAllAccounts() method completed successfully";

		//retrieveAllAccounts() method constants
		public static final String RETRIEVE_ALL_ACCOUNTS_METHOD_STARTED="retrieveAllAccounts() method started";
		public static final String RETRIEVING_ACCOUNTS="Retrieving accounts from database";
		public static final String RETRIEVING_ACCOUNTS_EXECUTED_SUCCESSFULLY="Retrieving accounts from database completed";
		public static final String COPYING_DATA_FROM_ENTITY_TO_MODEL="Copying data from entity to model";
		public static final String COPIED_SUCCESSFULLY="Copying data from entity to model completed";
		public static final String RETRIEVING_ALL_ACCOUNTS_ENDED="retrievingAllAccounts() method ended";
		public static final String RETRIEVING_ALL_ACCOUNTS_COMPLETED_SUCCESSFULLY="retrivingAllAccounts() method completed successfully";

		//actDeactSwitch() method constants
		public static final String STATUS="status";
		public static final String ACTIVE_SWITCH="activeSwitch";
		public static final String USER_ID="userId";
		public static final String ACT_DEACT_SWTCH_METHOD_STARTED="actDeactSwtch() method started";
		public static final String ACT_DEACT_SWTCH_METHOD_ENDED="actDeactSwtch() method ended";
		public static final String ACT_DEACT_SWTCH_METHOD_COMPLETED_SUCCESSFULLY="actDeactSwtch() method completed Successfully";
}
