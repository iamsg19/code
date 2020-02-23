package com.innocore.gmail;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Thread;

/**
 * @author Shivaji Chandra
 *
 */
@RestController
public class GmailApp {

	/**
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@GetMapping("/read-mail")
	public List<Thread> mailLabels() throws GeneralSecurityException, IOException, ClassNotFoundException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, GmailQuickStart.JSON_FACTORY,
				GmailQuickStart.getCredentials(HTTP_TRANSPORT)).setApplicationName(GmailQuickStart.APPLICATION_NAME)
						.build();
		
		// Print the labels in the user's account.
		// ListLabelsResponse listResponse =
		// service.users().labels().list(user).execute();
		ListThreadsResponse response = service.users().threads().list("email id").execute();

		List<Thread> thread = new ArrayList<>();
		thread = response.getThreads();
		return thread;
	}
}
