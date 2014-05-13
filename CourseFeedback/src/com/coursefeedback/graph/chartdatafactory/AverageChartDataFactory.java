package com.coursefeedback.graph.chartdatafactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import com.coursefeedback.feedback.Feedback;
import com.coursefeedback.util.FeedbackUtil;

/**
 * Create chart data (x & y axis) based on a list of feedback with:
 * 
 * x axis -> constant iteration (1,2,3..)
 * 
 * y axis -> average of all previous feedback values
 * 
 * @author touzbi
 */

@ManagedBean(name = "chartDataFactory")
public class AverageChartDataFactory implements ContinuousChartDataFactory {

	@Override
	public Map<Integer, Double> getChartData(List<Feedback> feedbacks) {
		Map<Integer, Double> chartData = new HashMap<Integer, Double>();

		// Get a list of all feedback values
		List<Integer> feedbackValues = FeedbackUtil
				.getFeedbackValues(feedbacks);

		// Get a list of all average values
		List<Double> averageValueSet = FeedbackUtil
				.createAverageValueSet(feedbackValues);

		// Create the chart series
		for (int i = 0; i < averageValueSet.size(); i++) {
			chartData.put(i, averageValueSet.get(i));
		}

		return chartData;
	}
}
