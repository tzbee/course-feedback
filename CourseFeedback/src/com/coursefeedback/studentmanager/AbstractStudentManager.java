package com.coursefeedback.studentmanager;

import java.util.Collection;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.coursefeedback.coursemanager.Course;

public interface AbstractStudentManager {

	/**
	 * Retrieves login reply for the studentRegister.xhtml page
	 * 
	 * @return String representing login reply
	 */
	public String getLoginReply();

	/**
	 * Retrieves student key parameter from the page address
	 * 
	 * @return Boolean representing whether or not parameter was read
	 *         successfully
	 */
	public boolean getParameter();

	/**
	 * Creates key for a student IF: teacher has input the student into the
	 * system AND student does not already have a key
	 * 
	 * @param String
	 *            studentNumber
	 * 
	 * @return String representing next page
	 */
	public String createKeyForStudent(String studentNumber);

	/**
	 * Retrieves student key link for the given student
	 * 
	 * @param Student
	 *            student
	 * 
	 * @return String representing student key link
	 */
	public String getStudentKeyLink(Student student);

	/**
	 * Add student to the system
	 * 
	 * @param Student
	 *            student
	 * 
	 * @return String representing next page
	 */
	public String saveStudent(Student student);

	/**
	 * Add student to the system
	 * 
	 * @param String
	 *            studentNumber, String studentEmail
	 * 
	 * @return String representing next page
	 */
	public String addStudentToSystem(String studentNumber, String studentEmail);

	/**
	 * Check if student is already registered to system
	 * 
	 * @param String
	 *            Student student
	 * 
	 * @return boolean; true = student is registered to system, false =s tudent
	 *         is not registered to system
	 */
	public boolean isStudentRegisteredToSystem(Student student);

	/**
	 * Get all students registered to system
	 * 
	 * @return Collection<Student>
	 */

	/**
	 * Add an existing student to a course
	 * 
	 * @param student
	 * @param courseId
	 * @return
	 */
	String addStudentToCourse(String studentNumber, int courseId);

	public Student getStudentByStudentNumber(String studentNumber);

	public Collection<Student> getAllStudents();

	Collection<Student> displayStudentFile(HSSFWorkbook workbook);

	String saveStudentList(Collection<Student> students);

	/**
	 * Get all courses associated with one student
	 * 
	 * @param studentID
	 *            of the student
	 * @return
	 */
	Collection<Course> getCoursesByStudentId(int studentID);
}
