package com.shivatube.generator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.shivatube.document.DocumentGenerator;
import com.shivatube.mapper.DataMapper;
import com.shivatube.model.Employee;

@RestController
public class DemoDocument {

	@Autowired
	private DocumentGenerator documentGenerator;
	
	@Autowired
	private SpringTemplateEngine springTemplateEngine;
	
	@Autowired
	private DataMapper dataMapper;
	
	@PostMapping(value = "/generate/document")
	public String generateDocument(@RequestBody List<Employee> employeeList) {
		
		String finalHtml = null;
		
		Context dataContext = dataMapper.setData(employeeList);
		
		finalHtml = springTemplateEngine.process("template", dataContext);
		
		documentGenerator.htmlToPdf(finalHtml);
		
		return "Success";
	}
}
