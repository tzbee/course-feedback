package com.coursefeedback.course;

import java.util.Collection;

public interface CourseManager {

	/**
	 * Get all registered courses
	 * 
	 * @return
	 */
	Collection<Course> getCourses();

	/**
	 * Get a course by its unique id
	 * 
	 * @param courseId
	 * @return
	 */
	Course getCourseById(int courseId);

	/**
	 * Add a course to the system
	 * 
	 * @param course
	 */
	void addCourse(Course course);

	/**
	 * Get all courses taken by a student identified by its student key
	 * 
	 * @param studentKey
	 * @return
	 */

	Collection<Course> getCoursesByStudentKey(String studentKey);
}
