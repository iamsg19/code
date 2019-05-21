package com.usa.nj.gov.admin.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.usa.nj.gov.admin.entity.UserAccountsEntity;

@Repository
public interface UserAccountsRepository extends JpaRepository<UserAccountsEntity, Serializable>{

	@Query("select count(userEmail) from UserAccountsEntity where userEmail=:email")
	public Integer checkEmail(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE UserAccountsEntity c SET c.activeSwitch=:switch where c.userId=:id")
	public Integer updateActiveSwitch(@Param("switch")String activeSwitch,@Param("id")Integer id);
	
	@Query("select activeSwitch from UserAccountsEntity where userId=:userId")
	public String getActiveSwitch(Integer userId);
}
