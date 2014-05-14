package com.coursefeedbacktest.course;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.coursefeedback.course.Course;

/**
 * Test bean for a list of courses
 * 
 * @author touzbi
 */
@ManagedBean(name = "courseBeanTest")
public class CourseBeanTest {
	private List<Course> courses = new ArrayList<Course>();

	// Test values
	private static final String[] COURSE_CODES = { "H454", "S65987", "FEF",
			"FE656" };
	private static final String[] COURSE_NAMES = { "IT", "Physics", "Maths",
			"Geography" };

	@PostConstruct
	public void initCourses() {
		Course course;

		try {
			for (int i = 0; i < COURSE_CODES.length; i++) {
				course = new Course();
				course.setCourseCode(COURSE_CODES[i]);
				course.setName(COURSE_NAMES[i]);
				this.courses.add(course);
			}
		} catch (IndexOutOfBoundsException ignore) {
			// Do nothing
		}
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
