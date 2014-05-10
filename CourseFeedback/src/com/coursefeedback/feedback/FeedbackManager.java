package com.coursefeedback.feedback;

import java.util.Collection;

/**
 * Feedback Manager class Handles all feedback operations
 * 
 * @author Amine
 * 
 */
public interface FeedbackManager {
	/**
	 * Get all feedbacks registered in the system
	 * 
	 * @return all registered feedbacks
	 */
	Collection<Feedback> getAllFeedbacks();

	/**
	 * Adds feedback to a course item
	 * 
	 * @param feedback
	 * @param courseId
	 * @return
	 */
	String addFeedbackToCourseItem(Feedback feedback, int courseId);

	/**
	 * Adds feedback to the system
	 * 
	 * @param feedback
	 * @return
	 */
	String addFeedback(Feedback feedback);

	/**
	 * Get all feedbacks for a specific course item given its id
	 * 
	 * @param courseItemId
	 * @return The feedbacks
	 */
	Collection<Feedback> getFeedbacksByCourseItemId(int courseItemId);
}
