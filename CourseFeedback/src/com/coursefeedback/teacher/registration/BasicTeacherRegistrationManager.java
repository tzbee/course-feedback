package com.coursefeedback.teacher.registration;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.coursefeedback.teacher.Teacher;

@ManagedBean
public class BasicTeacherRegistrationManager implements
		TeacherRegistrationManager {
	private EntityManager em = Persistence.createEntityManagerFactory(
			"CourseFeedback").createEntityManager();

	@Override
	public String register(Teacher teacher) {
		this.em.getTransaction().begin();
		this.em.persist(teacher);
		this.em.getTransaction().commit();

		return "teacher-home";
	}
}
