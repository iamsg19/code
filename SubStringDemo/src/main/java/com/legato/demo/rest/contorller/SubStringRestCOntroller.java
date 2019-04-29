package com.legato.demo.rest.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legato.demo.exception.ExceptionMapper;
import com.legato.demo.exception.SubStringException;
import com.legato.demo.service.SubStringService;

@RestController
@RequestMapping("/get")
public class SubStringRestCOntroller {

	@Autowired
	private SubStringService subStrService;
	@GetMapping("/substr/{str}")
	public ResponseEntity<String> getSubStr(@PathVariable() String str)throws SubStringException
	{
		String subStr=null;
		//exception handling
		try
		{
			subStr=subStrService.getSubStr(str);
		}
		catch(Exception e)
		{
			throw new SubStringException("Something Went Wrong");
		}
		return new ResponseEntity<String>(subStr,HttpStatus.OK);
	}
}
