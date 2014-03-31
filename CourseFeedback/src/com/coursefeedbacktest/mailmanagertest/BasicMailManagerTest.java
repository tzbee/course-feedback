package com.coursefeedbacktest.mailmanagertest;

import org.junit.Test;

import com.coursefeedback.mailmanager.BasicMailManager;
import com.coursefeedback.mailmanager.MailManager;

public class BasicMailManagerTest {

	@Test
	public void test() {
		MailManager mailManager = new BasicMailManager();

		mailManager.sendMail("test@test.com", "touzbi@msn.com", "prouuuuuuut");
	}
}
