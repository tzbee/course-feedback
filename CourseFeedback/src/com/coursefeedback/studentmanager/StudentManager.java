package com.coursefeedback.studentmanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@ManagedBean
public class StudentManager implements AbstractStudentManager {
	private EntityManager em = Persistence.createEntityManagerFactory(
			"CourseFeedback").createEntityManager();

	public String saveStudent(Student student) {

		if (!isStudentRegisteredToSystem(student)) {
			this.em.getTransaction().begin();
			this.em.persist(student);
			this.em.getTransaction().commit();
		}

		return "input-student";
	}

	public String addStudentToSystem(String studentNumber, String studentEmail) {
		Student stu = new Student();
		stu.setStudentNumber(studentNumber);
		stu.setStudentEmail(studentEmail);

		if (!isStudentRegisteredToSystem(stu)) {
			this.em.getTransaction().begin();
			this.em.persist(stu);
			this.em.getTransaction().commit();
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

		return students;
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

	@Override
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
