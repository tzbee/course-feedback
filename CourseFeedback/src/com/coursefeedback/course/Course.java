package com.coursefeedback.course;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.coursefeedback.courseitem.CourseItem;
import com.coursefeedback.student.Student;
import com.coursefeedback.teacher.Teacher;

/**
 * Java bean representing the basic structure of a course
 * 
 * @author touzbi
 * 
 */
@ManagedBean
@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "courseId")
	private int courseId;

	@Column(name = "name")
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "course_student", joinColumns = { @JoinColumn(name = "courseId") }, inverseJoinColumns = { @JoinColumn(name = "studentId") })
	private Collection<Student> students;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "teacher_course", joinColumns = { @JoinColumn(name = "courseId") }, inverseJoinColumns = { @JoinColumn(name = "userName") })
	private Collection<Teacher> teachers;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	private Collection<CourseItem> courseItems;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCourseItem(CourseItem courseItem) {
		this.courseItems.add(courseItem);
		courseItem.setCourse(this);
	}

	public Collection<CourseItem> getCourseItems() {
		return courseItems;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return getName();
	}
}
