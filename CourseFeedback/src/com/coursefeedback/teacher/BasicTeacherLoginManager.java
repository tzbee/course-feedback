package com.coursefeedback.teacher;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean
public class BasicTeacherLoginManager implements TeacherLoginManager {
	private EntityManager em = Persistence.createEntityManagerFactory(
			"CourseFeedback").createEntityManager();

	@Override
	public String login(Teacher teacher) {
		String userName = teacher.getUserName();
		boolean isUserNameValid = isUserNameValid(userName);

		// Check user name
		System.out.println("User name " + userName + " "
				+ (isUserNameValid ? "valid!" : "not valid!"));

		return "login-page";
	}

	@Override
	public boolean isUserNameValid(String userName) {
		Query query = this.em
				.createQuery("SELECT t from Teacher t WHERE t.userName='"
						+ userName + "'");

		return !query.getResultList().isEmpty();
	}
}
