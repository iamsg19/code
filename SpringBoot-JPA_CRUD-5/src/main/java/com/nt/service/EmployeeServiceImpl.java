package com.nt.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.exception.DataNotFoundException;
import com.nt.model.EmployeeModel;
import com.nt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public boolean saveEmployee(Employee e) {
		
		Employee emp=repository.save(e);
		return (emp!=null)?true:false;
	}

	@Override
	public void deleteById(int empId) {
		
		try
		{
			repository.deleteById(empId);
		}
		catch(Exception e)
		{
			System.out.println("Give correct ID!!!");
		}
	}

	@Override
	public EmployeeModel findById(int empId) {
		
		//Empty EmployeeModel obj
		EmployeeModel empModel=new EmployeeModel();
		
		//empty EmployeeEntity obj
		Employee empEntity=null;
		
		//finding data by id
		Optional<Employee> optionalEmp=repository.findById(empId);
		
		//checking data is present in optionalEmp or not
		if(optionalEmp.isPresent())
		{
			//getting data from optionalEmp to employeeEntity
			empEntity=optionalEmp.get();
			
			//copying data from employeeEntity to employeeModel obj
			BeanUtils.copyProperties(empEntity, empModel);
		}
		else
		{
			throw new DataNotFoundException("Data not found for given ID :"+empId);
		}
		return empModel;
	}
}
