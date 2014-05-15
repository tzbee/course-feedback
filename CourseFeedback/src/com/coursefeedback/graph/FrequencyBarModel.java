package com.coursefeedback.graph;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.coursefeedback.feedback.Feedback;

@ManagedBean
public class FrequencyBarModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private CartesianChartModel categoryModel;

	public FrequencyBarModel() {
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public CartesianChartModel getFeedbackChartModel(
			Collection<Feedback> feedbacks) {
		categoryModel = new CartesianChartModel();

		ChartSeries chart = new ChartSeries();
		chart.setLabel("Lecture 1");

		int badCounter = 0;
		int goodCounter = 0;
		for (Feedback feedback : feedbacks) {
			if (feedback.getValue() == 1)
				badCounter++;
			if (feedback.getValue() == 2)
				goodCounter++;
		}
		System.out.println("goodCounter" + goodCounter);

		System.out.println("badCounter" + badCounter);
		chart.set("bad  ", badCounter);
		chart.set("good ", goodCounter);

		categoryModel.addSeries(chart);

		return categoryModel;
	}
}