package com.coursefeedback.feedbackmanager;

import java.util.Collection;

import com.coursefeedback.feedback.Feedback;

public interface FeedbackManager {
	String saveFeedback(Feedback feedback);

	Collection<Feedback> getAllFeedbacks();
}
