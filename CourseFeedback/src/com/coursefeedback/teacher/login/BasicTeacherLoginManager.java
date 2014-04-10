package com.coursefeedback.teacher.login;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import com.coursefeedback.teacher.Teacher;

@ManagedBean
public class BasicTeacherLoginManager implements TeacherLoginManager {
	private static final String TEACHER_ATTRIBUTE = "teacherSession";

	private EntityManager em = Persistence.createEntityManagerFactory(
			"CourseFeedback").createEntityManager();

	@Override
	public String login(Teacher teacher) {

		if (isUserNameValid(teacher.getUserName())
				&& isPasswordValid(teacher.getUserName(), teacher.getPassword())) {

			System.out.println("Good!!");
			// Login

			// Get session
			HttpSession httpSession = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);

			httpSession.setAttribute(TEACHER_ATTRIBUTE, teacher);

			return "teacher-index";
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

	@Override
	public Teacher getTeacherByUserName(String name) {
		Query query = this.em
				.createQuery("SELECT t from Teacher t WHERE t.userName='" + name
						+ "'");

		try {
			return (Teacher) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Teacher getSessionTeacher() {
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);

		Teacher teacher = (Teacher) httpSession.getAttribute(TEACHER_ATTRIBUTE);

		return teacher;
	}

	@Override
	public String logout() {
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);

		httpSession.invalidate();
		return "teacher-index";
	}

}
