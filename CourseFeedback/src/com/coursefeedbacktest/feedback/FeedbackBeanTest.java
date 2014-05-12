package com.coursefeedbacktest.feedback;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.coursefeedback.feedback.Feedback;

@ManagedBean(name = "feedbackBeanTest")
public class FeedbackBeanTest {
	private List<Feedback> feedbacks = new ArrayList<Feedback>();

	// Test values
	private static final Integer[] FEEDBACK_VALUES = { 2, 3, 4, 1, 6, 3, 5, 1,
			6, 7, 3, 0, 1, 9, 4, 2, 3, 6, 1, 6, 9, 8, 9, 1, 8 };

	@PostConstruct
	public void initFeedbacks() {
		Feedback feedback;

		for (Integer feedbackValue : FEEDBACK_VALUES) {
			feedback = new Feedback();
			feedback.setValue(feedbackValue);
			this.feedbacks.add(feedback);
		}
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
}
