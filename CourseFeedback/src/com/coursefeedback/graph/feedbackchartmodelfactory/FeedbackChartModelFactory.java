package com.coursefeedback.graph.feedbackchartmodelfactory;

import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;

import com.coursefeedback.feedback.Feedback;

/**
 * Abstract factory for Creating a chart model based on an ordered list of
 * feedbacks
 * 
 */
public interface FeedbackChartModelFactory {
	/**
	 * Creates a chart model based on the given ordered list of feedbacks.
	 * 
	 * @param feedbacks
	 *            The list of feedbacks to create the chart model from
	 * 
	 * @return The chart model created from the list of feedbacks.
	 */
	CartesianChartModel createFeedbackChartModel(List<Feedback> feedbacks);
}
