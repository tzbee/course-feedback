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

	private String searchTerm = "Search by teacher";

	@Override
	public String addCourse(Course course, Teacher teacher) {
		// Add teacher to the course
		course.setTeachers(Arrays.asList(teacher));

		this.em.getTransaction().begin();
		this.em.persist(course);
		this.em.getTransaction().commit();

		return "get-courses";
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

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}
