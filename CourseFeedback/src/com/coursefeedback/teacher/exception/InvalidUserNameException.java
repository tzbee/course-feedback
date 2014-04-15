package com.coursefeedback.teacher.exception;

public class InvalidUserNameException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidUserNameException(String userName) {
		super("Invalid user name: " + userName);
	}
}
