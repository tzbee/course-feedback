package com.coursefeedback.coursemanager;

import java.util.Collection;

import com.coursefeedback.course.Course;

public interface CourseManager {

	/**
	 * Get all registered courses
	 * 
	 * @return
	 */

	Collection<Course> getCourses();

	/**
	 * Get all courses associated with the teacher given his name
	 * 
	 * @param teacherName
	 * @return
	 */
	Collection<Course> getCoursesByTeacherName(String teacherName);

	Course getCourseById(int courseId);
}
