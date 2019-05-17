package com.ssa.usa.federal.gov.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.usa.federal.gov.entity.StateListEntity;
import com.ssa.usa.federal.gov.repository.StateListRepository;

@Service
public class StateServiceImpl implements StateRestService{

	@Autowired
	StateListRepository stateRepo;
	
	@Override
	public List<String> getAllStates() {
		
		//new empty list
		List<String> stateList=new ArrayList<String>(60);
		//retreiving all states
		List<StateListEntity> stateEntity=stateRepo.findAll();
		
		//checking stateEntity is empty or not
		if(!stateEntity.isEmpty())
		{
			for(StateListEntity entity:stateEntity)
			{
				//adding state names to stateList
				stateList.add(entity.getStateName());
			}
		}
		return stateList;
	}
}
