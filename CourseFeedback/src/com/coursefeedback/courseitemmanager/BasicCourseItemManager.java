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
	public String addCourseItemToCourse(CourseItem courseItem, int courseId) {
		System.out.println(courseItem);

		try {
			this.utx.begin();
			Course course = this.em.find(Course.class, courseId);
			course.addCourseItem(courseItem);
			this.utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "course-page";
	}

	@Override
	public CourseItem getCourseItemById(int courseItemId) {
		return this.em.find(CourseItem.class, courseItemId);
	}

	@Override
	public String getCourseNameById(int courseItemId) {
		return (String) this.em
				.createQuery(
						"SELECT ci.courseItemName FROM CourseItem ci WHERE ci.courseItemId= :courseItemId")
				.setParameter("courseItemId", courseItemId).getSingleResult();
	}
}
