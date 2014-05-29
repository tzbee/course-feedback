package com.coursefeedback.course;

import java.util.Collection;

/**
 * Course Manager
 * 
 * Handles all course related operations
 */
public interface CourseManager {

	/**
	 * Get all registered courses
	 * 
	 * @return a collection of all courses registered in the system
	 */
	Collection<Course> getCourses();

	/**
	 * Get a course by its unique id
	 * 
	 * @param courseId
	 * @return The course object selected by its id
	 */
	Course getCourseById(int courseId);

	/**
	 * Add a course to the system
	 * 
	 * @param course
	 *            the course object to add to the system
	 */
	void addCourse(Course course);

	/**
	 * Get all courses taken by a student identified by its student key
	 * 
	 * @param studentKey
	 *            The student key identifying the student
	 * @return A collection of course the student is taking
	 */

	Collection<Course> getCoursesByStudentKey(String studentKey);

	/**
	 * Get all courses taken by a student identified by its student id
	 * 
	 * @param studentKey
	 *            The student id identifying the student
	 * @return A collection of course the student is taking
	 */
	Collection<Course> getCoursesByStudentId(int studentID);

	/**
	 * Get all courses given by a teacher identified by its user name
	 * 
	 * @param userName
	 *            User of the teacher
	 * @return A collection of course the teacher is giving
	 */
	Collection<Course> getCoursesByTeacherId(String userName);
}
