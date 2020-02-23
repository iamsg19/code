package com.example.common;

import static com.example.constants.ProjectConstants.HOST;
import static com.example.constants.ProjectConstants.MAIL_STORE_PROTOCOL;
import static com.example.constants.ProjectConstants.PROTOCOL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

/**
 * @author Shivaji Chandra
 *
 */
public class CommonCode {

	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws MessagingException
	 */
	public static Store setPropertyAndAuthenticateUser(String userName, String password) throws MessagingException {

		// adding properties related to mail server
		Properties properties = new Properties();
		properties.setProperty(MAIL_STORE_PROTOCOL, PROTOCOL);

		// creating session for email
		Session emailSession = Session.getDefaultInstance(properties);

		// getting the email store from the specified protocol
		Store emailStore = null;
		emailStore = emailSession.getStore(PROTOCOL);
		emailStore.connect(HOST, userName, password);
		return emailStore;
	}

	/**
	 * @param messages
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static List<InboxData> inboxData(Message[] messages) throws MessagingException, IOException {

		List<InboxData> inboxDataList = new ArrayList<>();
		for (int i = 0; i < messages.length; i++) {

			List<MailAddress> mailAddressList = new ArrayList<>();
			Message message = messages[i];
			InboxData inboxData = new InboxData();

			inboxData.setMessageNumber(message.getMessageNumber());
			inboxData.setSubject(message.getSubject());
			Address[] address = message.getFrom();
			for (Address addr : address) {
				MailAddress mailAddress = new MailAddress();
				mailAddress.setFromMail(addr.toString());
				mailAddressList.add(mailAddress);
			}
			inboxData.setFrom(mailAddressList);
			inboxData.setBodyContent(getTextFromMessage(message));

			inboxDataList.add(inboxData);
		}

		return inboxDataList;
	}

	/**
	 * @param message
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	/**
	 * @param mimeMultipart
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

	/**
	 * @param messages
	 * @param emailId
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static List<InboxData> inboxDataBySearch(Message[] messages, String emailId)
			throws MessagingException, IOException {

		Address searchFrom = new InternetAddress(emailId);

		List<InboxData> inboxDataList = new ArrayList<>();
		for (int i = 0; i < messages.length; i++) {
			List<MailAddress> mailAddressList = new ArrayList<>();
			Message message = messages[i];
			if (Arrays.asList(message.getFrom()).contains(searchFrom)) {
				InboxData inboxData = new InboxData();

				inboxData.setMessageNumber(message.getMessageNumber());
				inboxData.setSubject(message.getSubject());
				Address[] address = message.getFrom();
				for (Address addr : address) {
					MailAddress mailAddress = new MailAddress();
					mailAddress.setFromMail(addr.toString());
					mailAddressList.add(mailAddress);
				}
				inboxData.setFrom(mailAddressList);
				inboxData.setBodyContent(getTextFromMessage(message));

				inboxDataList.add(inboxData);
			}
		}

		return inboxDataList;
	}
}
