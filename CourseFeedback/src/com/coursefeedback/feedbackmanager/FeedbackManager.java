package com.coursefeedback.feedbackmanager;

import java.util.Collection;

import com.coursefeedback.feedback.Feedback;

public interface FeedbackManager {
	Collection<Feedback> getAllFeedbacks();

	String addFeedbackToCourseItem(Feedback feedback, int courseId);

	String addFeedback(Feedback feedback);

	/**
	 * Get all feedbacks for a specific course item given its id
	 * @param courseItemId
	 * @return The feedbacks
	 */
	Collection<Feedback> getFeedbacksByCourseItemId(int courseItemId);
}
