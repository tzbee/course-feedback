package com.coursefeedback.courseitemmanager;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.coursefeedback.coursemanager.Course;
import com.coursefeedback.feedback.Feedback;

@ManagedBean
@Entity
@Table(name = "courseItem")
public class CourseItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "courseItemId")
	private long courseItemId;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "courseId")
	private Course course;

	@Temporal(TemporalType.DATE)
	@Column(name = "time")
	private Date time;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "courseItem")
	private List<Feedback> feedbacks;

	public long getCourseItemId() {
		return courseItemId;
	}

	public void setCourseItemId(long courseItemId) {
		this.courseItemId = courseItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
