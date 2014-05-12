package com.coursefeedbacktest.feedback;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.coursefeedback.course.Course;

@ManagedBean(name = "courseBeanTest")
public class CourseBeanTest {
	private List<Course> courses = new ArrayList<Course>();

	// Test values
	private static final String[] COURSE_NAMES = { "IT", "Physics", "Maths",
			"Geography" };

	@PostConstruct
	public void initCourses() {
		Course course;

		for (String courseName : COURSE_NAMES) {
			course = new Course();
			course.setName(courseName);
			this.courses.add(course);
		}
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
