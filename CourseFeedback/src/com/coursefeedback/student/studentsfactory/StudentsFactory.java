package com.coursefeedback.student.studentsfactory;

import java.io.InputStream;
import java.util.Collection;

import com.coursefeedback.student.Student;

/**
 * Abstract factory for creating a set of students from an input stream
 * 
 * @author Amine
 * 
 */
public interface StudentsFactory {
	/**
	 * Creates a set of students from a data input stream(Console, file,
	 * string..)
	 * 
	 * @param inputStream
	 *            Inputstream containing the data to process
	 * @return The collection of students created from the input stream
	 */
	Collection<Student> getStudents(InputStream inputStream);
}
