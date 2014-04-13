package com.coursefeedback.teacher.login;

import com.coursefeedback.teacher.Teacher;
import com.coursefeedback.teacher.exception.InvalidPasswordException;
import com.coursefeedback.teacher.exception.InvalidUserNameException;

public interface TeacherLoginManager {
	/**
	 * Teacher login
	 * 
	 * @param teacher
	 * @return next page
	 */

	String login(String userName, String password);

	/**
	 * Teacher logout
	 * 
	 * @return next page
	 */
	String logout();

	Teacher getTeacher(String userName, String password)
			throws InvalidUserNameException, InvalidPasswordException;

}
