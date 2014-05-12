package com.coursefeedback.student;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.coursefeedback.course.Course;

@ManagedBean(name = "studentManager")
@SessionScoped
public class StudentManager implements AbstractStudentManager {
	@PersistenceContext(name = "CourseFeedback")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	private String loginReply = "";

	private long currentStudentID = -1;
	private Course currentCourse = null;

	public Course getCurrentCourse() {
		return this.currentCourse;
	}

	public String setCurrentCourse(Course selectedCourse) {
		this.currentCourse = selectedCourse;
		return "courseItemSelection.xhtml";
	}

	public String setCurrentStudentID(long studentID) {
		this.currentStudentID = studentID;
		return "courseSelection.xhtml";
	}

	public long getCurrentStudentID() {
		return this.currentStudentID;
	}

	@SuppressWarnings("unchecked")
	public Student getStudentByStudentNumber(String studentNumber) {
		Query query = this.em
				.createQuery(
						"SELECT f FROM Student f WHERE f.studentNumber LIKE :stuNumber")
				.setParameter("stuNumber", studentNumber);
		List<Student> students = query.getResultList();

		if (students.size() == 1)
			return students.get(0);
		else
			return null;
	}

	public String getLoginReply() {
		return loginReply;
	}

	@SuppressWarnings("unchecked")
	private Student getStudentByStudentKey(String studentKey) {
		Query query = this.em.createQuery(
				"SELECT f FROM Student f WHERE f.studentKey LIKE :stuKey")
				.setParameter("stuKey", studentKey);
		List<Student> students = query.getResultList();

		if (students.size() == 1)
			return students.get(0);
		else
			return null;
	}

	public boolean getParameter() {
		String parameter = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("key");
		System.out.print("Paramater: " + parameter); // for testing
		if (parameter != null) {
			String studentKey = parameter;
			Student student = getStudentByStudentKey(studentKey);
			if (student != null) {
				this.setCurrentStudentID(student.getId());
				System.out
						.print("Current Student ID: " + getCurrentStudentID()); // for
																				// testing
				return true;
			}
		}
		return false;
	}

	private String generateStudentKey() {
		int MIN = 10000000;
		int MAX = 99999999;
		long fraction;
		String randomKey;

		SecureRandom random = new SecureRandom();
		long range = (long) MAX - (long) MIN + 1;
		do {
			fraction = (long) (range * random.nextDouble());
			randomKey = Integer.toString((int) (fraction + MIN));
		} while (keyIsDuplicate(randomKey));

		return randomKey;
	}

	@SuppressWarnings("unchecked")
	private boolean keyIsDuplicate(String key) {
		Query query = this.em.createQuery(
				"SELECT f FROM Student f WHERE f.studentKey LIKE :stuKey")
				.setParameter("stuKey", key);
		Collection<Student> students = query.getResultList();

		if (students.isEmpty())
			return false;
		else
			return true;
	}

	public String createKeyForStudent(String studentNumber) {
		Student student = getStudentByStudentNumber(studentNumber);
		if (student != null) {
			if (student.getStudentKey().equals("0")) {
				student.setStudentKey(generateStudentKey());
				updateStudentKeyToDatabase(student);
				loginReply = "Login link has been sent to email: "
						+ student.getStudentEmail();
			} else
				loginReply = "Key already generated";
		} else
			loginReply = "student number not in system";
		return "studentRegister.xhtml";
	}

	private boolean updateStudentKeyToDatabase(Student student) {
		Query query;
		try {
			this.utx.begin();
			query = this.em
					.createQuery(
							"UPDATE Student f SET f.studentKey=:stuKey WHERE f.studentNumber=:stuNumber")
					.setParameter("stuKey", student.getStudentKey())
					.setParameter("stuNumber", student.getStudentNumber());
			query.executeUpdate();
			this.utx.commit();
		} catch (IllegalArgumentException e) {
			System.out.println("Query invalid");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public String getStudentKeyLink(Student student) {
		Query query = this.em
				.createQuery(
						"SELECT f FROM Student f WHERE f.studentNumber LIKE :stuNumber AND f.studentEmail LIKE :stuEmail AND f.studentKey NOT LIKE '0'")
				.setParameter("stuNumber", student.getStudentNumber())
				.setParameter("stuEmail", student.getStudentEmail());
		List<Student> students = query.getResultList();

		if (students.size() == 1)
			return "http://localhost:8080/CourseFeedback/courseSelection.xhtml"
					+ "?key=" + students.get(0).getStudentKey();
		// return "http://localhost:8080/CourseFeedback/studentLogin.xhtml" +
		// "?key=" + students.get(0).getStudentKey();
		else
			return "";
	}

	public String saveStudent(Student student) {

		if (!isStudentRegisteredToSystem(student)) {
			try {
				this.utx.begin();
				this.em.persist(student);
				this.utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "input-student";
	}

	public String addStudentToSystem(String studentNumber, String studentEmail) {
		Student stu = new Student();
		stu.setStudentNumber(studentNumber);
		stu.setStudentEmail(studentEmail);

		if (!isStudentRegisteredToSystem(stu)) {
			try {
				this.utx.begin();
				this.em.persist(stu);
				this.utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return "input-student";
	}

	@SuppressWarnings("unchecked")
	public boolean isStudentRegisteredToSystem(Student student) {
		Query query = this.em
				.createQuery(
						"SELECT f FROM Student f WHERE f.studentNumber LIKE :stuNumber OR f.studentEmail LIKE :stuEmail")
				.setParameter("stuNumber", student.getStudentNumber())
				.setParameter("stuEmail", student.getStudentEmail());
		Collection<Student> students = query.getResultList();

		if (students.isEmpty())
			return false;
		else
			return true;
	}

	@SuppressWarnings("unchecked")
	public Collection<Student> getAllStudents() {
		Query query = this.em.createQuery("SELECT f FROM Student f");
		Collection<Student> students = query.getResultList();
		if (!students.isEmpty())
			return students;
		else
			return null;
	}

	public String saveStudents(Collection<Student> students) {
		for (Student student : students) {
			saveStudent(student);
		}
		return "teacher-index";
	}

	@Override
	public String addStudentToCourse(String studentNumber, int courseId) {
		try {
			this.utx.begin();
			Student student = getStudentByStudentNumber(studentNumber);
			Course course = this.em.find(Course.class, courseId);
			course.addStudent(student);
			this.utx.commit();
		} catch (Exception e) {
			// XXX To do better
			e.printStackTrace();
		}

		return "teacher-home";
	}
}
