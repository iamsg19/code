package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nt.entity.Employee;
import com.nt.service.EmployeeServiceImpl;
import com.nt.service.IEmployeeService;

@SpringBootApplication
public class SpringBootJpaCrud5Application {

	public static void main(String[] args) {
		ApplicationContext ac=SpringApplication.run(SpringBootJpaCrud5Application.class, args);
		
		IEmployeeService service=ac.getBean(EmployeeServiceImpl.class);
		/*Employee emp=new Employee();
		
		
		emp.setEmpName("Bala Karikalan");
		emp.setEmpSal(29000.00);
		
		if(service.saveEmployee(emp))
			System.out.println("Data Inserted");
		else
			System.out.println("Not Inserted");*/
		System.out.println(service.findById(89));
	}

}
