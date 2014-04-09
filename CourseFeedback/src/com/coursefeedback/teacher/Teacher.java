package com.coursefeedback.teacher;

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

@ManagedBean
@Entity
@Table(name = "teacher")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "teacherId")
	private int teacherId;
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "course_teacher", joinColumns = { @JoinColumn(name = "teacherId") }, inverseJoinColumns = { @JoinColumn(name = "courseId") })
	private Collection<Course> courses;

	// Teacher account

	private String userName;
	private String password;

	//

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int id) {
		this.teacherId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Teacher account
	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return getName();
	}
}