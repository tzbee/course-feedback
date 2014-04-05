package com.coursefeedback.feedback;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coursefeedback.course.Course;

@ManagedBean
@Entity
@Table(name = "feedback")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long feedbackId;

	private int value = 0;

	@ManyToOne
	private Course course;

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long id) {
		this.feedbackId = id;
	}

	@Column(name = "value")
	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
