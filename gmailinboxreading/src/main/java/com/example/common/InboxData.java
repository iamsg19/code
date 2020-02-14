package com.example.common;

import java.util.List;

import javax.mail.Message;

public class InboxData {

	private int messageNumber;
	private String subject;
	private List<MailAddress> from;
	
	private String bodyContent;
	public int getMessageNumber() {
		return messageNumber;
	}
	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<MailAddress> getFrom() {
		return from;
	}
	public void setFrom(List<MailAddress> from) {
		this.from = from;
	}
	public String getBodyContent() {
		return bodyContent;
	}
	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}
	@Override
	public String toString() {
		return "InboxData [messageNumber=" + messageNumber + ", subject=" + subject + ", from=" + from
				+ ", bodyContent=" + bodyContent + "]";
	}
	
}
