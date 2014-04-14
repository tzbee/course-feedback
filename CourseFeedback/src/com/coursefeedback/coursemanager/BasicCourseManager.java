package com.coursefeedback.coursemanager;

import java.util.Arrays;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.coursefeedback.course.Course;
import com.coursefeedback.teacher.Teacher;

/**
 * Basic implementation of a course manager
 * 
 * @author touzbi
 */
@ManagedBean
public class BasicCourseManager implements CourseManager {
	// XXX To do better
	private EntityManager em = Persistence.createEntityManagerFactory(
			"CourseFeedback").createEntityManager();

	@Override
	public void addCourse(Course course) {
		this.em.getTransaction().begin();
		this.em.persist(course);
		this.em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Course> getCourses() {
		Query query = this.em.createQuery("SELECT c FROM Course c");
		return (Collection<Course>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Course> getCoursesByTeacherName(String teacherName) {
		Query query = this.em
				.createQuery("SELECT c FROM Course c LEFT OUTER JOIN c.teachers t WHERE t.name = '"
						+ teacherName + "'");
		return (Collection<Course>) query.getResultList();
	}

	@Override
	public Course getCourseById(int courseId) {
		Query query = this.em
				.createQuery("SELECT c FROM Course c WHERE c.courseId = "
						+ courseId);
		return (Course) query.getSingleResult();
	}
}
