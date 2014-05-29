package com.coursefeedback.teacher.registration;

import com.coursefeedback.teacher.Teacher;

/**
 * Teacher Registration interface.
 * 
 * Handles all teacher registration operations.
 */
public interface TeacherRegistrationManager {
	/**
	 * Register a teacher in the system
	 * 
	 * @param teacher
	 *            the teacher object to be registered
	 * @return The next page this operation leads to
	 */
	String register(Teacher teacher);
}
