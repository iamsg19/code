package com.nt.service;

import com.nt.entity.Employee;
import com.nt.model.EmployeeModel;

public interface IEmployeeService {
	public boolean saveEmployee(Employee e);
	public void deleteById(int empId);
	public EmployeeModel findById(int empId);
}
