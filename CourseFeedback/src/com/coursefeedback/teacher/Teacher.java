package com.coursefeedback.teacher;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.coursefeedback.course.Course;

/**
 * Java bean containing the basic teacher information
 * 
 * @author touzbi
 * 
 */
@ManagedBean(name = "teacher")
@Entity
@Table(name = "teacher")
@RequestScoped
public class Teacher {
	@Id
	@Column(name = "userName")
	private String userName;

	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "teacher_course", joinColumns = { @JoinColumn(name = "userName") }, inverseJoinColumns = { @JoinColumn(name = "courseId") })
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

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	@Override
	public String toString() {
		return getUserName() + " " + getCourses();
	}
}