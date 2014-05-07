package com.coursefeedback.coursemanager;

import java.util.Collection;

public interface CourseManager {

	/**
	 * Get all registered courses
	 * 
	 * @return
	 */

	Collection<Course> getCourses();

	Course getCourseById(int courseId);

	void addCourse(Course course);
}
