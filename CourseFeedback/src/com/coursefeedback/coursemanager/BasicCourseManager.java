package com.coursefeedback.coursemanager;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.coursefeedback.course.Course;

@ManagedBean
public class BasicCourseManager implements CourseManager {
	private EntityManager em = Persistence.createEntityManagerFactory(
			"CourseFeedback").createEntityManager();

	private String searchTerm;

	@Override
	public String addCourse(Course course) {
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

	@Override
	public Course getCourseByTeacherName(String teacherName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}
