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

	/**
	 * Get all courses associated with one student
	 * 
	 * @param studentID
	 *            of the student
	 * @return
	 */

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Course> getCoursesByStudentId(int studentID) {
		return this.em
				.createQuery(
						"SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentID")
				.setParameter("studentID", studentID).getResultList();
	}

	/**
	 * Get all courses associated with one teacher
	 * 
	 * @param userName
	 *            user name of the teacher
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Course> getCoursesByTeacherId(String userName) {
		return this.em
				.createQuery(
						"SELECT c FROM Course c JOIN c.teachers t WHERE t.userName = :userName")
				.setParameter("userName", userName).getResultList();
	}
}
