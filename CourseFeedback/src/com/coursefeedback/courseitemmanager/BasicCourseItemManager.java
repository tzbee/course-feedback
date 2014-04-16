package com.coursefeedback.courseitemmanager;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.coursefeedback.coursemanager.Course;

@ManagedBean
public class BasicCourseItemManager implements CourseItemManager {

	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public String addCourseItemToCourse(CourseItem courseItem, Course course) {

		try {
			System.out.println(courseItem + " " + course);
			this.utx.begin();
			this.em.persist(courseItem);
			course.addCourseItem(courseItem);
			this.em.merge(course);
			this.utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// XXX To do better
		return "course-page";
	}
}
