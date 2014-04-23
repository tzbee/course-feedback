package com.coursefeedback.teacher.teachermanager;

import com.coursefeedback.coursemanager.Course;
import com.coursefeedback.teacher.Teacher;

public interface TeacherManager {
	String addCourseToTeacher(Course course, String teacherUserName);

	Teacher getTeacherByUserName(String userName);
}
