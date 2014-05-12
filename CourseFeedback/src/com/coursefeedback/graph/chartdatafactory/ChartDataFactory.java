package com.coursefeedback.graph.chartdatafactory;

import java.util.List;
import java.util.Map;

import com.coursefeedback.feedback.Feedback;

/**
 * Create chart data (x & y axis) based on a list of feedback
 * 
 * @author touzbi
 * 
 * @param <E>
 */
public interface ChartDataFactory<E> {
	Map<Integer, E> getChartData(List<Feedback> feedbacks);
}
