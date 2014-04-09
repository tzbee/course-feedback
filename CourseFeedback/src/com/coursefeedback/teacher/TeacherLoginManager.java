package com.coursefeedback.teacher;

public interface TeacherLoginManager {
	String login(Teacher teacher);

	boolean isUserNameValid(String userName);
}
