package com.example.email;

import java.util.List;

import javax.mail.AuthenticationFailedException;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.AuthenticationData;
import com.example.common.CommonCode;
import com.example.common.InboxData;
import com.example.exception.InvalidUserNamePasswordExcpeption;

/**
 * @author Shivaji Chandra
 *
 */
@RestController
public class EmailReading {

	
	@Value("${folder}")
	private String inbox;
	
	/**
	 * @param data
	 * @return List<InboxData>
	 * @throws AuthenticationFailedException
	 * @throws FolderNotFoundException
	 * @throws IllegalStateException
	 * @throws NoSuchProviderException
	 * @throws Exception
	 */
	@PostMapping(value = "/read-messages")
	public List<InboxData> receiveMail(@RequestBody AuthenticationData data) throws AuthenticationFailedException, FolderNotFoundException,
			IllegalStateException, NoSuchProviderException, Exception {
		
		//validating username and password
		validateUser(data);
		
		Store emailStore = CommonCode.setPropertyAndAuthenticateUser(data.getUsername(), data.getPassword());

		// getting the email folder by folder name
		Folder emailFolder = emailStore.getFolder(inbox);

		// setting the accessed folder as READ_ONLY
		emailFolder.open(Folder.READ_ONLY);

		// getting all the messages from the particular folder name
		Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), true));
		//Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.USER), true));
		//Message[] messages = emailFolder.getMessages();
		
		// returning all the inbox data
		return CommonCode.inboxData(messages);
		
	}
	
	/**
	 * @param data
	 * @param emailId
	 * @return List<InboxData>
	 * @throws AuthenticationFailedException
	 * @throws FolderNotFoundException
	 * @throws IllegalStateException
	 * @throws NoSuchProviderException
	 * @throws Exception
	 */
	@PostMapping(value = "/search-messages-by-email")
	public List<InboxData> receiveMail(@RequestBody AuthenticationData data, @RequestParam("emailId") String emailId) throws AuthenticationFailedException, FolderNotFoundException,
			IllegalStateException, NoSuchProviderException, Exception {

		//validating username and passwords
		validateUser(data);
		
		//getting emailStore after authentication
		Store emailStore = CommonCode.setPropertyAndAuthenticateUser(data.getUsername(), data.getPassword());

		// getting the email folder by folder name
		Folder emailFolder = emailStore.getFolder(inbox);

		// setting the accessed folder as READ_ONLY
		emailFolder.open(Folder.READ_ONLY);
		
		//getting all the messages from the emailFolder
		Message[] messages = emailFolder.getMessages();
		
		// returning all the inbox data
		return CommonCode.inboxDataBySearch(messages, emailId);
		
	}
	
	/**
	 * @param data
	 * @return List<InboxData>
	 * @throws AuthenticationFailedException
	 * @throws FolderNotFoundException
	 * @throws IllegalStateException
	 * @throws NoSuchProviderException
	 * @throws Exception
	 */
	@PostMapping("/unread-messages")
	public List<InboxData> unreadMails(@RequestBody AuthenticationData data) throws AuthenticationFailedException, FolderNotFoundException,
	IllegalStateException, NoSuchProviderException, Exception{
		
		//validating username and password
		validateUser(data);
	
		Store emailStore = CommonCode.setPropertyAndAuthenticateUser(data.getUsername(), data.getPassword());

		// getting the email folder by folder name
		Folder emailFolder = emailStore.getFolder(inbox);

		// setting the accessed folder as READ_ONLY
		emailFolder.open(Folder.READ_ONLY);

		// getting all the messages from the particular folder name
		Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

		// returning all the inbox data
		return CommonCode.inboxData(messages);
	}

	
	/**
	 * @param data
	 */
	private void validateUser(AuthenticationData data) {

		if (data.getUsername() == null || data.getPassword() == null) {
			throwException();
		} else if (StringUtil.isBlank(data.getUsername()) || StringUtil.isBlank(data.getPassword())) {
			throwException();
		}
	}

	private void throwException() {
		throw new InvalidUserNamePasswordExcpeption("Required username and password");
	}
	
	
}
