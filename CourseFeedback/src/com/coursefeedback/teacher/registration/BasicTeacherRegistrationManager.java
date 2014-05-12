package com.coursefeedback.teacher.registration;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.coursefeedback.teacher.Teacher;

/**
 * Basic implementation of teacher registration .
 * 
 * Handles all teacher registration operations.
 */
@ManagedBean(name = "teacherRegistrationManager")
public class BasicTeacherRegistrationManager implements
		TeacherRegistrationManager {
	private static final String TEACHER_HOME = "teacherHome";

	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	@Override
	public String register(Teacher teacher) {
		try {
			this.utx.begin();
			this.em.persist(teacher);
			this.utx.commit();
		} catch (Exception e) {
			try {
				this.utx.rollback();
			} catch (Exception ignore) {
			}
		}

		return TEACHER_HOME;
	}
}
