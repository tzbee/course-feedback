package com.coursefeedback.feedback;

import java.util.Collection;

/**
 * Feedback Manager class Handles all feedback operations
 * 
 * @author Amine
 */
public interface FeedbackManager {
	/**
	 * Get all feedbacks registered in the system
	 * 
	 * @return all registered feedbacks
	 */
	Collection<Feedback> getAllFeedbacks();

	/**
	 * Adds feedback object to a course item identified by its id
	 * 
	 * @param feedback
	 *            Feedback object to add
	 * @param courseItemId
	 *            Id identifying the course item to give feedback to
	 * @return The next navigation rule (Page this action leads to)
	 */
	String addFeedbackToCourseItem(Feedback feedback, int courseItemId);

	/**
	 * Adds feedback to the system
	 * 
	 * @param feedback
	 *            The feedback object to add to the system
	 * @return The next navigation rule (Page this action leads to)
	 */
	String addFeedback(Feedback feedback);

	/**
	 * Get all feedbacks for a specific course item given its id
	 * 
	 * @param courseItemId
	 *            The id identifying the course item to get feedbacks from
	 * @return The collection of feedbacks the identified course item possesses
	 */
	Collection<Feedback> getFeedbacksByCourseItemId(int courseItemId);
}
