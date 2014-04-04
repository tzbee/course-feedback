package com.coursefeedback.coursemanager;

import java.util.Collection;

import com.coursefeedback.course.Course;
import com.coursefeedback.teacher.Teacher;

public interface CourseManager {
	/**
	 * Add a course to the system
	 * 
	 * @param course
	 * @return the next page
	 */
	String addCourse(Course course, Teacher teacher);

	/**
	 * Get all registered courses
	 * 
	 * @return
	 */
	Collection<Course> getCourses();

	/**
	 * 
	 * @param teacherName
	 * @return
	 */
	Collection<Course> getCoursesByTeacherName(String teacherName);
}
