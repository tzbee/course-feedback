package com.coursefeedback.teacher.login;

import com.coursefeedback.teacher.Teacher;
import com.coursefeedback.teacher.exception.InvalidPasswordException;
import com.coursefeedback.teacher.exception.InvalidUserNameException;

/**
 * Teacher Login interface.
 * 
 * Handles all teacher login operations.
 */
public interface TeacherLoginManager {
	/**
	 * Log in a teacher account using the teacher user name and password
	 * 
	 * @param userName
	 *            User name identifying the teacher account
	 * @param password
	 *            Password of the teacher account
	 * @return next page this operation leads to
	 */

	String login(String userName, String password);

	/**
	 * Logout from the teacher account
	 * 
	 * @return next page this operation leads to
	 */
	String logout();

	/**
	 * Retrieve a teacher from its user name and password
	 * 
	 * @param userName
	 *            User name identifying the teacher account
	 * @param password
	 *            Password of the teacher account
	 * 
	 * @return The teacher found by its credentials
	 * 
	 * @throws InvalidUserNameException
	 *             The user name does not exist
	 * @throws InvalidPasswordException
	 *             The password associated to this user name is wrong
	 */
	Teacher getTeacher(String userName, String password)
			throws InvalidUserNameException, InvalidPasswordException;
}
