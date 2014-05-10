package com.coursefeedback.teacher.registration;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.coursefeedback.teacher.Teacher;

@ManagedBean(name = "teacherRegistrationManager")
public class BasicTeacherRegistrationManager implements
		TeacherRegistrationManager {

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

		return "teacher-home";
	}
}
