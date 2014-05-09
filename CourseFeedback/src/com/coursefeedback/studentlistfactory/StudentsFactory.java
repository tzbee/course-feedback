package com.coursefeedback.studentlistfactory;

import java.io.InputStream;
import java.util.Collection;

import com.coursefeedback.student.Student;

/**
 * Defines ways of creating or retriving a set of students
 * 
 * @author touzbi
 * 
 */
public interface StudentsFactory {
	Collection<Student> getStudents(InputStream inputStream);
}
