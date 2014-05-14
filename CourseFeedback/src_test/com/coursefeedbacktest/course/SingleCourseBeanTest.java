package com.coursefeedbacktest.course;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.coursefeedback.course.Course;

/**
 * Test bean for a single course
 * 
 * @author touzbi
 */
@ManagedBean(name = "singleCourseBeanTest")
public class SingleCourseBeanTest {
	private static final String COURSE_NAME = "IT";
	private static final String COURSE_CODE = "S123";

	private Course course;

	@PostConstruct
	private void initCourse() {
		this.course = new Course();
		this.course.setName(COURSE_NAME);
		this.course.setCourseCode(COURSE_CODE);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
