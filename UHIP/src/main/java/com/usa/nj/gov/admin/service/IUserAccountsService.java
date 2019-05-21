package com.usa.nj.gov.admin.service;

import java.util.List;

import com.usa.nj.gov.admin.model.UserAccountsModel;

public interface IUserAccountsService{
	boolean createUserAccount(UserAccountsModel userAccountModel);
	String checkEmail(String email);
	List<UserAccountsModel> retrieveAllAccounts();
	String actvtDeactvt(Integer userId,String activeSwitch);
}
