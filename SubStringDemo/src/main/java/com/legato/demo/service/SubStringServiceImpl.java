package com.legato.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SubStringServiceImpl implements SubStringService{

	@Override
	public String getSubStr(String str) {
		
		char[] charStr=null;
		
		char[] ch=str.toCharArray();
			
		charStr=new char[4];
			
		for(int i=2;i<4;i++)
		{
			charStr[i]=ch[i];
		}
		str=new String(charStr);
		//String subStr=new String(charStr);
		return str;
	}
}
