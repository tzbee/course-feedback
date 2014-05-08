package com.coursefeedback.courseitem;

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

import com.coursefeedback.course.Course;
import com.coursefeedback.feedback.Feedback;

@ManagedBean
@Entity
@Table(name = "courseItem")
public class CourseItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "courseItemId")
	private int courseItemId;

	@Column(name = "name")
	private String courseItemName;

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

	public void setCourseItemId(int courseItemId) {
		this.courseItemId = courseItemId;
	}

	public String getCourseItemName() {
		return courseItemName;
	}

	public void setCourseItemName(String courseItemName) {
		this.courseItemName = courseItemName;
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

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void addFeedback(Feedback feedback) {
		this.feedbacks.add(feedback);
		feedback.setCourseItem(this);
	}
}
