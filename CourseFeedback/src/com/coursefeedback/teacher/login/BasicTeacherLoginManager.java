package com.coursefeedback.teacher.login;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import com.coursefeedback.teacher.Teacher;
import com.coursefeedback.teacher.exception.InvalidPasswordException;
import com.coursefeedback.teacher.exception.InvalidUserNameException;

@ManagedBean
public class BasicTeacherLoginManager implements TeacherLoginManager {
	private static final String TEACHER_SESSION_ATTRIBUTE = "teacherSession";

	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	private String message = "";

	@Override
	public String login(String userName, String password) {
		Teacher teacher = null;

		try {
			teacher = getTeacher(userName, password);
		} catch (InvalidUserNameException | InvalidPasswordException e) {
			this.message = e.getMessage();
			return "login-page";
		}

		// Get session
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);

		httpSession.setAttribute(TEACHER_SESSION_ATTRIBUTE, teacher);

		return "teacher-home";
	}

	@Override
	public Teacher getTeacher(String userName, String password)
			throws InvalidUserNameException, InvalidPasswordException {

		Teacher teacher = this.em.find(Teacher.class, userName);

		if (teacher == null)
			throw new InvalidUserNameException(userName);

		if (!teacher.getPassword().equals(password))
			throw new InvalidPasswordException();

		return teacher;
	}

	@Override
	public String logout() {
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(false);

		httpSession.invalidate();

		return "teacher-home";
	}

	public String getMessage() {
		return message;
	}
}
