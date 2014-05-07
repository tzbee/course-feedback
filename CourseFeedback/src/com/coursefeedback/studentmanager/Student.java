package com.coursefeedback.studentmanager;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ManagedBean
@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "studentId")
	private long id;

	private String studentKey = "0";
	private String studentNumber = "";
	private String studentEmail = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// public int getStudentKey() {
	@Column(name = "studentkey")
	public String getStudentKey() {
		return this.studentKey;
	}

	// public void setStudentKey(int studentKey) {
	public void setStudentKey(String studentKey) {
		this.studentKey = studentKey;
	}

	@Column(name = "studentnumber")
	public String getStudentNumber() {
		return this.studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	@Column(name = "studentemail")
	public String getStudentEmail() {
		return this.studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
}
