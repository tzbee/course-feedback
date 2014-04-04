package com.coursefeedback.coursemanager;

import java.util.Collection;

import com.coursefeedback.course.Course;

public interface CourseManager {
	/**
	 * Add a course to the system
	 * 
	 * @param course
	 * @return the next page
	 */
	String addCourse(Course course);

	/**
	 * Get all registered courses
	 * 
	 * @return
	 */
	Collection<Course> getCourses();

	Course getCourseByTeacherName(String teacherName);
}
