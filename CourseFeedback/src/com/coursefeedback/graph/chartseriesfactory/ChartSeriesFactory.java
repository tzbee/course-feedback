package com.coursefeedback.graph.chartseriesfactory;

import java.util.List;
import java.util.Map;

import com.coursefeedback.feedback.Feedback;

/**
 * Create chart data (x & y axis)based on a list of feedback
 * 
 * @author touzbi
 * 
 * @param <E>
 */
public interface ChartSeriesFactory<E> {
	Map<Integer, E> getChartSeries(List<Feedback> feedbacks);
}
