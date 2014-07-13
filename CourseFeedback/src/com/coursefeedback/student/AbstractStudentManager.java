package com.coursefeedback.student;

import java.util.Collection;

/**
 * Student Manager
 * 
 * Handles all student related operations
 */
public interface AbstractStudentManager {

	/**
	 * Retrieves login reply for the studentRegister.xhtml page
	 * 
	 * @return string representing login reply
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
	 * @param studentNumber
	 *            String studentNumber
	 * 
	 * @return String representing next page
	 */
	public String createKeyForStudent(String studentNumber);

	/**
	 * Retrieves student key link for the given student
	 * 
	 * @param student
	 *            student
	 * 
	 * @return String representing student key link
	 */
	public String getStudentKeyLink(Student student);

	/**
	 * Add student to the system
	 * 
	 * @param student
	 *            student
	 * 
	 * @return String representing next page
	 */
	public String saveStudent(Student student);

	/**
	 * Add student to the system
	 * 
	 * @param studentNumber
	 *            student number
	 * @param studentEmail
	 *            student email
	 * 
	 * 
	 * @return String representing next page
	 */
	public String addStudentToSystem(String studentNumber, String studentEmail);

	/**
	 * Check if student is already registered to system
	 * 
	 * @param student
	 *            student
	 * 
	 * @return boolean; true = student is registered to system, false = student
	 *         is not registered to system
	 */
	public boolean isStudentRegisteredToSystem(Student student);

	/**
	 * Add an existing student to a course
	 * 
	 * @param studentNumber
	 * @param courseId
	 * @return representing next page
	 */
	String addStudentToCourse(String studentNumber, int courseId);

	/**
	 * Retrieves all students from the system.
	 * 
	 * @return all the students within the system in a Collection
	 */
	public Collection<Student> getAllStudents();

	/**
	 * Saves a collection of students to the system
	 * 
	 * @param students
	 * @return representing next page
	 */

	String saveStudents(Collection<Student> students);

	/**
	 * Get a student by his studentNumber
	 * 
	 * @param studentNumber
	 * 
	 * @return The student identified by the student number
	 */

	public Student getStudentByStudentNumber(String studentNumber);
}
