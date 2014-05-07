package com.coursefeedback.coursemanager.coursepage;

import javax.faces.bean.ManagedBean;

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
