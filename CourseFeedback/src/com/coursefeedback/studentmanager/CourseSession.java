package com.coursefeedback.studentmanager;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@ManagedBean
@Entity
@Table(name = "course_session")
public class CourseSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int courseID = 0;

	@Temporal(TemporalType.DATE)
	private Date time;
	private int teacherID = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "courseID")
	public int getCourseID() {
		return this.courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	@Column(name = "time")
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "teacherID")
	public int getTeacherID() {
		return this.teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
}
