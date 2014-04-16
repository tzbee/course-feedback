package com.coursefeedback.courseitemmanager;

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

import com.coursefeedback.coursemanager.Course;
import com.coursefeedback.feedback.Feedback;

@ManagedBean
@Entity
@Table(name = "courseItem")
public class CourseItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "courseItemId")
	private int courseItemId;

	private String courseItemName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "courseItem")
	private List<Feedback> feedbacks;

	@ManyToOne
	@JoinColumn(name = "courseId", nullable = false)
	private Course course;

	public int getCourseItemId() {
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

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void addFeedback(Feedback feedback) {
		this.feedbacks.add(feedback);
	}
}
