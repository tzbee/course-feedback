package com.coursefeedback.graph.chartseriesfactory;

import java.util.List;
import java.util.Map;

import com.coursefeedback.feedback.Feedback;

/**
 * Creates a set of data (Int -> E)based on a feedback list
 * 
 * @author touzbi
 * 
 * @param <E>
 */
public interface ChartSeriesFactory<E> {
	Map<Integer, E> getChartSeries(List<Feedback> feedbacks);
}
