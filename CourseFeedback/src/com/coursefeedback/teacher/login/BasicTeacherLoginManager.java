package com.coursefeedback.teacher.login;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.coursefeedback.teacher.Teacher;

@ManagedBean
public class BasicTeacherLoginManager implements TeacherLoginManager {
	private EntityManager em = Persistence.createEntityManagerFactory(
			"CourseFeedback").createEntityManager();

	@Override
	public String login(Teacher teacher) {

		if (isUserNameValid(teacher.getUserName())
				&& isPasswordValid(teacher.getUserName(), teacher.getPassword())) {

			System.out.println("Good!!");
			// Login
		}

		return "login-page";
	}

	@Override
	public boolean isUserNameValid(String userName) {
		return getTeacherByUserName(userName) != null;
	}

	@Override
	public boolean isPasswordValid(String userName, String password) {
		Teacher teacher = getTeacherByUserName(userName);
		return teacher.getPassword().equals(password);
	}

	public Teacher getTeacherByUserName(String userName) {
		Query query = this.em
				.createQuery("SELECT t from Teacher t WHERE t.userName='"
						+ userName + "'");

		try {
			return (Teacher) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
