package com.coursefeedback.graph.chartdatafactory;

import java.util.List;
import java.util.Map;

import com.coursefeedback.feedback.Feedback;

/**
 * Abstract factory or creating chart data (x & y axis) based on a list of
 * feedbacks
 * 
 * @author Amine
 * 
 * @param <E>
 *            The type of the data series at the y axis
 */
public interface ChartDataFactory<E> {
	/**
	 * Create a chart series(x & y axis data) from an ordered list of feedbacks
	 * 
	 * @param feedbacks
	 *            the ordered list of feedbacks to process the data from
	 * 
	 * @return a Map representing the data: The Map keys are X axis values, and
	 *         the Map values are Y axis values
	 *
	 */
	Map<Integer, E> getChartData(List<Feedback> feedbacks);
}
