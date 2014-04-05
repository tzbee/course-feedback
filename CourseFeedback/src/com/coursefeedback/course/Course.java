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
import javax.persistence.Table;

import com.coursefeedback.teacher.Teacher;

@ManagedBean
@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "courseId")
	private int courseId;

	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "course_teacher", joinColumns = { @JoinColumn(name = "courseId") }, inverseJoinColumns = { @JoinColumn(name = "teacherId") })
	private Collection<Teacher> teachers;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int id) {
		this.courseId = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers;
	}

	@Override
	public String toString() {
		return "Id: "
				+ getCourseId()
				+ ", name: "
				+ getName()
				+ ", teachers: "
				+ (getTeachers() == null || getTeachers().isEmpty() ? "No teachers assigned"
						: getTeachers());
	}
}
