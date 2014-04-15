package com.coursefeedback.feedback;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coursefeedback.courseitemmanager.CourseItem;

@ManagedBean
@Entity
@Table(name = "feedback")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int feedbackId;

	private int value;

	@ManyToOne
	@JoinColumn(name = "CourseItemId", nullable = false)
	private CourseItem courseItem;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
