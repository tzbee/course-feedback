package com.coursefeedback.courseitem;

public interface CourseItemManager {
	String addCourseItemToCourse(CourseItem courseItem, int courseId);

	CourseItem getCourseItemById(int courseItemId);

	String getCourseNameById(int courseItemId);
}
