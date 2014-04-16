package com.coursefeedback.coursemanager;

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

import com.coursefeedback.courseitemmanager.CourseItem;
import com.coursefeedback.teacher.Teacher;

@ManagedBean
@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "courseId")
	private int courseId;

	@Column(name = "name")
	private String name;

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
	}

	public Collection<CourseItem> getCourseItems() {
		return courseItems;
	}

	@Override
	public String toString() {
		return getName();
	}
}
