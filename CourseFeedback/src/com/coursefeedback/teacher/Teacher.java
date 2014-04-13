package com.coursefeedback.teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "userName")
	private String userName;

	private String password;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "course_teacher", joinColumns = { @JoinColumn(name = "userName") }, inverseJoinColumns = { @JoinColumn(name = "courseId") })
	private Collection<Course> courses;

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

	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
		for (Course course : courses) {
			course.setTeachers(Arrays.asList(this));
		}
	}

	@Override
	public String toString() {
		return getUserName() + ", Courses: " + getCourses();
	}
}