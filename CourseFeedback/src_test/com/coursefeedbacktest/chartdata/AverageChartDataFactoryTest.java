package com.coursefeedbacktest.chartdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.coursefeedback.feedback.Feedback;
import com.coursefeedback.graph.chartdatafactory.AverageChartDataFactory;
import com.coursefeedback.graph.chartdatafactory.ContinuousChartDataFactory;

public class AverageChartDataFactoryTest {
	private static List<Feedback> FEEDBACKS;

	@BeforeClass
	public static void initFeedbacks() {
		// Init feedback list
		FEEDBACKS = new ArrayList<Feedback>();

		// Feedback values
		Collection<Integer> feedbackValues = Arrays.asList(1, 4, 2, 6);

		Feedback feedback;

		for (Integer feedbackValue : feedbackValues) {
			feedback = new Feedback();
			feedback.setValue(feedbackValue);
			FEEDBACKS.add(feedback);
		}
	}

	@Test
	public void test() {
		ContinuousChartDataFactory continuousChartDataFactory = new AverageChartDataFactory();

		System.out.println("Feedback values: " + FEEDBACKS);
		System.out.println("Chart series: "
				+ continuousChartDataFactory.getChartData(FEEDBACKS));
	}
}
