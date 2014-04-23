package com.coursefeedback.courseitemmanager;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.coursefeedback.coursemanager.Course;

@ManagedBean(name = "courseItemManager")
public class BasicCourseItemManager implements CourseItemManager {
	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public String addCourseItemToCourse(CourseItem courseItem, String courseId) {
		try {
			Integer courseIdNb = Integer.valueOf(courseId);

			this.utx.begin();
			Course course = this.em.find(Course.class, courseIdNb);
			course.addCourseItem(courseItem);
			this.utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// XXX TODO better
		return "course-page";
	}
}
