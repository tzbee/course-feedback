package com.coursefeedback.feedback;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
public class FeedbackBar implements Serializable {
	private static final long serialVersionUID = 1L;

	private CartesianChartModel categoryModel;

	public FeedbackBar() {
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public CartesianChartModel createCategoryModel(
			Collection<Feedback> feedbacks) {
		categoryModel = new CartesianChartModel();

		ChartSeries chart = new ChartSeries();
		chart.setLabel("Lecture 1");

		int counter = 0;
		for (Feedback feedback : feedbacks) {
			chart.set("Feedback " + counter++, feedback.getValue());
		}

		categoryModel.addSeries(chart);

		return categoryModel;
	}
}