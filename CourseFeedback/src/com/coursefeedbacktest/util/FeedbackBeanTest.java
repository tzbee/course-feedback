package com.coursefeedbacktest.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.coursefeedback.feedback.Feedback;

@ManagedBean(name = "feedbackBeanTest")
public class FeedbackBeanTest {
	private List<Feedback> feedbacks = new ArrayList<Feedback>();
	private static final Collection<Integer> FEEDBACK_VALUES = Arrays.asList(2,
			53, 2);

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
