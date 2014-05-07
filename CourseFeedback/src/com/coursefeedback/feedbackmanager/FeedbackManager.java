package com.coursefeedback.feedbackmanager;

import java.util.Collection;

import com.coursefeedback.feedback.Feedback;

public interface FeedbackManager {
	Collection<Feedback> getAllFeedbacks();

	String addFeedbackToCourseItem(Feedback feedback, int courseId);

	String addFeedback(Feedback feedback);

	Collection<Feedback> getFeedbacksByCourseItemId(int courseItemId);
}
