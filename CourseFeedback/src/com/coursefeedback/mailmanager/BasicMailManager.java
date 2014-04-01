package com.coursefeedback.mailmanager;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BasicMailManager implements MailManager {

	@Override
	public void sendMail(String from, String to, String message) {
		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage mimeMessage = new MimeMessage(session);

			// Set From: header field of the header.
			mimeMessage.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			mimeMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			mimeMessage.setSubject("This is the Subject Line!");

			// Now set the actual message
			mimeMessage.setText(message);

			// Send message
			Transport.send(mimeMessage);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
