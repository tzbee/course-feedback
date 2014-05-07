package com.coursefeedback.courseitemmanager;

public interface CourseItemManager {
	String addCourseItemToCourse(CourseItem courseItem, String courseId);

	CourseItem getCourseItemById(int courseItemId);

	String getCourseNameById(int courseItemId);
}
