package com.coursefeedback.student;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.coursefeedback.course.Course;

/**
 * Java bean containing the basic student information
 * 
 * @author touzbi
 */
@ManagedBean
@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "studentId")
	private int id;

	@Column(name = "studentkey")
	private String studentKey = "0";

	@Column(name = "studentnumber")
	private String studentNumber = "";

	@Column(name = "studentemail")
	private String studentEmail = "";

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "course_student", joinColumns = { @JoinColumn(name = "studentId") }, inverseJoinColumns = { @JoinColumn(name = "courseId") })
	private Collection<Course> courses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentKey() {
		return this.studentKey;
	}

	public void setStudentKey(String studentKey) {
		this.studentKey = studentKey;
	}

	public String getStudentNumber() {
		return this.studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentEmail() {
		return this.studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	@Override
	public String toString() {
		return getId() + " " + getStudentNumber() + " " + getStudentEmail()
				+ " " + getStudentKey();
	}
}
