package com.coursefeedback.coursemanager.coursepage;

import javax.faces.bean.ManagedBean;

import com.coursefeedback.coursemanager.Course;

@ManagedBean(name = "coursePageBean")
public class CoursePageBean {
	private String currentCourseId;

	public String getCurrentCourseId() {
		return currentCourseId;
	}

	public void setCurrentCourseId(String currentCourseId) {
		this.currentCourseId = currentCourseId;
	}
}
