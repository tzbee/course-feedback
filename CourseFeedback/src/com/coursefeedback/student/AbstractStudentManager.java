package com.coursefeedback.student;

import java.util.Collection;

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
	 * @return boolean; true = student is registered to system, false = student
	 *         is not registered to system
	 */
	public boolean isStudentRegisteredToSystem(Student student);

	/**
	 * Add an existing student to a course
	 * 
	 * @param student
	 * @param courseId
	 * @return
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
	 * @return
	 */

	String saveStudents(Collection<Student> students);

	/**
	 * Get a student based on his studentNumber
	 * 
	 * @return Collection<Student>
	 */

	public Student getStudentByStudentNumber(String studentNumber);
}
