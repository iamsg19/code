package com.usa.nj.gov.util;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
public class Email {

	@Autowired
	private JavaMailSender javaMailSender;

	//Logger 
	private static final Logger logger = LoggerFactory.getLogger(Email.class);

	public  String sendMail(String to, String subject,String body)throws MessagingException,IOException
	{
		MimeMessage msg=javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body,true);
		javaMailSender.send(msg);


		return "Email sent successfully to ";
	}
}
