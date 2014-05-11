package com.coursefeedback.course;

import java.util.Collection;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 * Basic implementation of a course manager
 * 
 * @author touzbi
 */
@ManagedBean(name = "courseManager")
public class BasicCourseManager implements CourseManager {
	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public void addCourse(Course course) {
		try {
			this.utx.begin();
			this.em.persist(course);
			this.utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Course> getCourses() {
		Query query = this.em.createQuery("SELECT c FROM Course c");
		return (Collection<Course>) query.getResultList();
	}

	@Override
	public Course getCourseById(int courseId) {
		return (Course) this.em.find(Course.class, courseId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Course> getCoursesByStudentKey(String studentKey) {
		return this.em
				.createQuery(
						"SELECT c FROM Course c JOIN c.students s WHERE s.studentKey = :studentKey")
				.setParameter("studentKey", studentKey).getResultList();
	}
}
