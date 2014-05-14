package com.coursefeedbacktest.courseitembeantest;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.coursefeedback.courseitem.CourseItem;

@ManagedBean(name = "courseItemBeanTest")
public class CourseItemBeanTest {
	private static String[] NAMES = { "Lecture 1", "Lecture 2", "Exercise 1" };

	private Collection<CourseItem> courseItems;

	@PostConstruct
	private void createCourseItems() {
		this.courseItems = new ArrayList<CourseItem>();
		CourseItem courseItem;

		for (String name : NAMES) {
			courseItem = new CourseItem();
			courseItem.setCourseItemName(name);
			this.courseItems.add(courseItem);
		}
	}

	public Collection<CourseItem> getCourseItems() {
		return courseItems;
	}

	public void setCourseItems(Collection<CourseItem> courseItems) {
		this.courseItems = courseItems;
	}
}
