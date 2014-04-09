package com.coursefeedback.teacher.login;

import com.coursefeedback.teacher.Teacher;

public interface TeacherLoginManager {
	String login(Teacher teacher);

	/**
	 * The given user name exists
	 * 
	 * @param userName
	 * @return
	 */
	boolean isUserNameValid(String userName);

	boolean isPasswordValid(String userName, String password);
}
