package com.coursefeedback.courseitem;

/**
 * Course Item Manager
 * 
 * Handles all course item (Lectures, exercises, course material) related
 * operations
 */
public interface CourseItemManager {
	/**
	 * Add a course item object to a course identified by its id
	 * 
	 * @param courseItem
	 *            The course item object to be added
	 * @param courseId
	 *            The id identifying the course to add a course item to
	 * 
	 * @return The next navigation rule (Page this action leads to)
	 */
	String addCourseItemToCourse(CourseItem courseItem, int courseId);

	/**
	 * Fin a course item object identified by its id
	 * 
	 * @param courseItemId
	 *            The id identifying the course item to find
	 * @return The course item object to find
	 */
	CourseItem getCourseItemById(int courseItemId);

	/**
	 * Find a course item identified by its id
	 * 
	 * @param courseItemId
	 *            the id identifying the course item to find
	 * @return The next navigation rule (Page this action leads to)
	 */
	String getCourseItemNameById(int courseItemId);
}
