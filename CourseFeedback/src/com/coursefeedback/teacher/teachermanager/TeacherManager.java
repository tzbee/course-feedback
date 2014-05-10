package com.coursefeedback.teacher.teachermanager;

import com.coursefeedback.course.Course;
import com.coursefeedback.teacher.Teacher;

/**
 * Teacher Manager class Handles all teacher related operations
 * 
 */
public interface TeacherManager {
	/**
	 * Add a course to a teacher identified by its username
	 * 
	 * @param course
	 * @param teacherUserName
	 * @return The next navigation rule
	 */
	String addCourseToTeacher(Course course, String teacherUserName);

	/**
	 * Get teacher's information using its username
	 * 
	 * @param userName
	 * @return
	 */
	Teacher getTeacherByUserName(String userName);
}
