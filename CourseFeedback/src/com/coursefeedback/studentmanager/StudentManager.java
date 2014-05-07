package com.coursefeedback.studentmanager;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.coursefeedback.courseitemmanager.CourseItem;
import com.coursefeedback.coursemanager.Course;

@ManagedBean
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

	// for temp testing
	public Collection<Course> getCoursesByStudentId(long studentID) {
		Collection<Course> col = new ArrayList<Course>();
		Course c1 = new Course(), c2 = new Course(), c3 = new Course();
		CourseItem ci1 = new CourseItem(), ci2 = new CourseItem(), ci3 = new CourseItem();
		c1.setCourseId(1);
		c2.setCourseId(2);
		c3.setCourseId(3);
		ci1.setCourseItemId(1);
		ci2.setCourseItemId(2);
		ci3.setCourseItemId(3);
		c1.setName("math (course 1)");
		c2.setName("science (course 2)");
		c3.setName("computing (course 3)");
		ci1.setCourseItemName("courseItemName a");
		ci2.setCourseItemName("courseItemName b");
		ci3.setCourseItemName("courseItemName c");
		c1.addCourseItem(ci1);
		c2.addCourseItem(ci1);
		c2.addCourseItem(ci2);
		c3.addCourseItem(ci1);
		c3.addCourseItem(ci2);
		c3.addCourseItem(ci3);

		if (studentID == 1) {
			col.add(c1);
			col.add(c2);
			col.add(c3);
			return col;
		} else if (studentID == 2) {
			col.add(c1);
			col.add(c2);
			return col;
		} else if (studentID == 3) {
			col.add(c1);
			return col;
		} else {
			col.clear();
			return col;
		}
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

	/*
	 * private String generateStudentKey() { UUID randomKey; do { randomKey =
	 * UUID.randomUUID(); } while (keyIsDuplicate(randomKey.toString()));
	 * 
	 * return randomKey.toString(); }
	 */

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

	@Override
	public Collection<Student> displayStudentFile(HSSFWorkbook workbook) {
		Collection<Student> studentList = new ArrayList<Student>();

		// This workbook already has all content of uploaded file
		// ArrayList<Student> studentList=new ArrayList<Student>();
		String studentNo = null;
		String studentEmail = null;
		HSSFSheet sheet = workbook.getSheetAt(0);
		// Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.rowIterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			Student student = new Student();

			Cell cell = cellIterator.next();

			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				studentNo = String.valueOf(cell.getBooleanCellValue());
				System.out.println("cell" + cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				studentNo = String.valueOf(cell.getNumericCellValue());
				System.out.println("cell" + cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				studentNo = cell.getStringCellValue();
				System.out.println("cell" + cell.getStringCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				studentNo = "";
				System.out.println("cell" + cell.getStringCellValue());
				break;
			}

			student.setStudentNumber(studentNo);

			cell = cellIterator.next();
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BOOLEAN:
				studentEmail = String.valueOf(cell.getBooleanCellValue());
				System.out.println("cell" + cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				studentEmail = String.valueOf(cell.getNumericCellValue());
				System.out.println("cell" + cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				studentEmail = cell.getStringCellValue();
				System.out.println("cell" + cell.getStringCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				studentEmail = "";
				System.out.println("cell" + cell.getStringCellValue());
				break;

			}
			student.setStudentEmail(studentEmail);
			studentList.add(student);
		}

		return studentList;
	}

	public String saveStudentList(Collection<Student> students) {
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
