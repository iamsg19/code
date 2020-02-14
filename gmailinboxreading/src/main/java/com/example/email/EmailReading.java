package com.example.email;

import java.util.List;

import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.AuthenticationData;
import com.example.common.CommonCode;
import com.example.common.InboxData;

/**
 * @author Shivaji Chandra
 *
 */
@RestController
public class EmailReading {

	/**
	 * @param userName
	 * @param password
	 */

	@PostMapping(value = "/read-messages")
	public List<InboxData> receiveMail(@RequestBody AuthenticationData data) throws AuthenticationFailedException, FolderNotFoundException,
			IllegalStateException, NoSuchProviderException, Exception {

		if(data.getUsername() == null || data.getPassword() == null) {
			throw new Exception("Required username and password");
		}
		
		Store emailStore = CommonCode.setPropertyAndAuthenticateUser(data.getUsername(), data.getPassword());

		// getting the email folder by folder name
		Folder emailFolder = emailStore.getFolder("INBOX");

		// setting the accessed folder as READ_ONLY
		emailFolder.open(Folder.READ_ONLY);

		// getting all the messages from the particular folder name
		//Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), true));
		//Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.USER), true));
		Message[] messages = emailFolder.getMessages();
		
		// returning all the inbox data
		return CommonCode.inboxData(messages);
		
	}
	
	/**
	 * @param data
	 * @param emailId
	 * @return
	 * @throws AuthenticationFailedException
	 * @throws FolderNotFoundException
	 * @throws IllegalStateException
	 * @throws NoSuchProviderException
	 * @throws Exception
	 */
	@PostMapping(value = "/search-messages-by-email")
	public List<InboxData> receiveMail(@RequestBody AuthenticationData data, @RequestParam("emailId") String emailId) throws AuthenticationFailedException, FolderNotFoundException,
			IllegalStateException, NoSuchProviderException, Exception {

		if(data.getUsername() == null || data.getPassword() == null) {
			throw new Exception("Required username and password");
		}
		
		Store emailStore = CommonCode.setPropertyAndAuthenticateUser(data.getUsername(), data.getPassword());

		// getting the email folder by folder name
		Folder emailFolder = emailStore.getFolder("INBOX");

		// setting the accessed folder as READ_ONLY
		emailFolder.open(Folder.READ_ONLY);

		// getting all the messages from the particular folder name
		//Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), true));
		//Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.USER), true));
		Message[] messages = emailFolder.getMessages();
		
		// returning all the inbox data
		return CommonCode.inboxDataBySearch(messages, emailId);
		
	}
}
