package com.coursefeedback.courseitem;

/**
 * Course Item Manager
 * 
 * Handles all course item related operations
 * 
 */
public interface CourseItemManager {
	String addCourseItemToCourse(CourseItem courseItem, int courseId);

	CourseItem getCourseItemById(int courseItemId);

	String getCourseItemNameById(int courseItemId);
}
