package com.coursefeedback.feedback;

import java.io.Serializable;

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

	public CartesianChartModel createCategoryModel(Feedback feedback) {
		categoryModel = new CartesianChartModel();

		ChartSeries chart = new ChartSeries();
		chart.setLabel("Lecture 1");
		chart.set("Feedback 1", feedback.getValue());
		// chart.set("Feedback 2", 1);
		// chart.set("Feedback 3", 5);
		categoryModel.addSeries(chart);

		return categoryModel;
	}
}