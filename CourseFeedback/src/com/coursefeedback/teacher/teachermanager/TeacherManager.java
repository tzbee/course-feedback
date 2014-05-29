package com.coursefeedback.teacher.teachermanager;

import com.coursefeedback.course.Course;
import com.coursefeedback.teacher.Teacher;

/**
 * Teacher Manager class
 * 
 * Handles all teacher related operations
 */
public interface TeacherManager {
	/**
	 * Add a course to a teacher identified by its user name
	 * 
	 * @param course
	 *            Course object to add to the teacher
	 * @param teacherUserName
	 *            The id identifying the teacher
	 * @return The next navigation rule
	 */
	String addCourseToTeacher(Course course, String teacherUserName);

	/**
	 * Find a teacher identified by its user name
	 * 
	 * @param userName
	 *            The user name used to identify the teacher
	 * @return The teacher object found using the user name
	 */
	Teacher getTeacherByUserName(String userName);
}
