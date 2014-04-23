package com.coursefeedback.feedback;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.coursefeedback.courseitemmanager.CourseItem;
import com.coursefeedback.feedback.metadata.MetaData;

@ManagedBean
@Entity
@Table(name = "feedback")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int feedbackId;

	private int value;

	@ManyToOne
	@JoinColumn(name = "courseItemId", nullable = false)
	private CourseItem courseItem;

	@OneToOne
	private MetaData metaData;

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

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
}
