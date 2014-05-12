package com.coursefeedback.feedback;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coursefeedback.courseitem.CourseItem;

/**
 * Java bean representing the basic structure of a feedback
 * 
 * @author touzbi
 * 
 */
@ManagedBean
@Entity
@Table(name = "feedback")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "feedbackId")
	private int feedbackId;

	@Column(name = "value")
	private int value = 1;

	@ManyToOne
	@JoinColumn(name = "courseItemId")
	private CourseItem courseItem;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public CourseItem getCourseItem() {
		return courseItem;
	}

	public void setCourseItem(CourseItem courseItem) {
		this.courseItem = courseItem;
	}

	@Override
	public String toString() {
		return String.valueOf(getValue());
	}
}
