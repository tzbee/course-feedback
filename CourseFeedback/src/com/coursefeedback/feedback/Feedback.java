package com.coursefeedback.feedback;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ManagedBean
@Entity
@Table(name = "feedback")
public class Feedback {
	private long feedbackId;
	private int value = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
