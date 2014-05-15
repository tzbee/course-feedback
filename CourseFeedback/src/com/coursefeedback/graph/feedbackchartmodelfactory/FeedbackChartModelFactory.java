package com.coursefeedback.graph.feedbackchartmodelfactory;

import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;

import com.coursefeedback.feedback.Feedback;

/**
 * Interface for creating a cartesian chart model based on an ordered list of
 * feedbacks
 * 
 * @author touzbi
 * 
 */
public interface FeedbackChartModelFactory {
	CartesianChartModel createFeedbackChartModel(List<Feedback> feedbacks);
}