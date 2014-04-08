package com.coursefeedback.studentmanager;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface AbstractStudentManager {
	/*
	 * Add student to the system
	 * 
	 * @param Student student
	 * 
	 * @return next page
	 */
	public String saveStudent(Student student);

	/*
	 * Add student to the system
	 * 
	 * @param String studentNumber, String studentEmail
	 * 
	 * @return next page
	 */
	public String addStudentToSystem(String studentNumber, String studentEmail);

	/*
	 * Check if student is already registered to system
	 * 
	 * @param String Student student
	 * 
	 * @return boolean
	 */
	public boolean isStudentRegisteredToSystem(Student student);

	/*
	 * Get all students registered to system
	 * 
	 * @return Collection<Student>
	 */
	public Collection<Student> getAllStudents();

	ArrayList<Student> displayStudentFile(HSSFWorkbook workbook);

	String saveStudentList(Collection<Student> students);
}
