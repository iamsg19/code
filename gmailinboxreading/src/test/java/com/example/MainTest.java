package com.example;

import com.example.common.InboxData;

public class MainTest {
	public static void main(String[] args) {
		
		InboxData inboxData = new InboxData();
		inboxData.getMailAddress().getFromMail().equals("");
	}

}
