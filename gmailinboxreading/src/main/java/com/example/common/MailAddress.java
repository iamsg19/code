package com.example.common;

public class MailAddress {

	private String fromMail;

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String addr) {
		this.fromMail = addr;
	}

	@Override
	public String toString() {
		return "MailAddress [fromMail=" + fromMail + "]";
	}
	
}
