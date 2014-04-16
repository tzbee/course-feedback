package com.coursefeedback.studentmanager;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

//import org.apache.poi.ss.usermodel.Cell;
//import HSSFSheet;
//import HSSFWorkbook;
//import Row;
//import Student;

@ManagedBean
public class StudentManager implements AbstractStudentManager {
	@PersistenceContext(name = "CourseFeedbaack")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	private String loginReply = "";

	@SuppressWarnings("unchecked")
	private Student getStudentByStudentNumber(String studentNumber) {
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
	private Student getStudentByStudentKey(int studentKey) {
		Query query = this.em.createQuery(
				"SELECT f FROM Student f WHERE f.studentKey LIKE :stuKey")
				.setParameter("stuKey", studentKey);
		List<Student> students = query.getResultList();

		if (students.size() == 1)
			return students.get(0);
		else
			return null;
	}

	public String getParameter() {
		String parameter = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("key");
		if (parameter != null) {
			int studentKey = Integer.parseInt(parameter);
			Student student = getStudentByStudentKey(studentKey);
			if (student != null)
				return "Student ( number: " + student.getStudentNumber()
						+ " email: " + student.getStudentEmail() + " key: "
						+ student.getStudentKey() + " ) has logged in.";
			else
				return "error";
		} else
			return "error";
	}

	private int generateStudentKey() {
		int MIN = 100000000;
		int MAX = 999999999;
		long fraction;
		int randomKey;

		SecureRandom random = new SecureRandom();
		long range = (long) MAX - (long) MIN + 1;
		do {
			fraction = (long) (range * random.nextDouble());
			randomKey = (int) (fraction + MIN);
		} while (keyIsDuplicate(randomKey));

		return randomKey;
	}

	@SuppressWarnings("unchecked")
	private boolean keyIsDuplicate(int key) {
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
			student.setStudentKey(generateStudentKey());
			updateStudentKeyToDatabase(student);
			loginReply = "Login link has been sent to email: "
					+ student.getStudentEmail();
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
			return "http://localhost:8080/CourseFeedback/studentLogin.xhtml"
					+ "?key=" + students.get(0).getStudentKey();
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

	private static final long serialVersionUID = 1L;
	public ArrayList<Student> studentList = new ArrayList<Student>();

	@Override
	public ArrayList<Student> displayStudentFile(HSSFWorkbook workbook) {
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
			this.studentList.add(student);
		}

		return this.studentList;
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		System.out.println("studentList1 size" + studentList.size());

		this.studentList = studentList;
		System.out.println("this.studentList size" + this.studentList.size());
	}

	public String saveStudentList(Collection<Student> students) {
		for (Student student : students) {
			saveStudent(student);
		}
		return "teacher-index";
	}

	// @Override
	// public ArrayList<Student> displayStudentFile(HSSFWorkbook workbook) {
	// // TODO Auto-generated method stub
	// return null;
	// }
}
