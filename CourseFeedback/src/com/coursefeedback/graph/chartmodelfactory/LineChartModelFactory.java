package com.coursefeedback.graph.chartmodelfactory;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.coursefeedback.feedback.Feedback;
import com.coursefeedback.graph.chartseriesfactory.ContinuousChartSeriesFactory;

/**
 * Creates a line chart model with:
 * 
 * x axis -> constant iteration (1,2,3..).
 * 
 * y axis -> series of values taken from the series factory
 * 
 * @author touzbi
 * 
 */

@ManagedBean(name = "chartModelFactory")
public class LineChartModelFactory implements ChartModelFactory {

	@ManagedProperty("#{chartSeriesFactory}")
	private ContinuousChartSeriesFactory chartSeriesFactory;

	@Override
	public CartesianChartModel getFeedbackChartModel(List<Feedback> feedbacks) {

		CartesianChartModel cartesianChartModel = new CartesianChartModel();

		LineChartSeries series = new LineChartSeries();

		series.setLabel("Average");

		Map<Integer, Double> chartSeries = this.chartSeriesFactory
				.getChartSeries(feedbacks);

		for (Integer xAxisValue : chartSeries.keySet()) {
			series.set(xAxisValue, chartSeries.get(xAxisValue));
		}

		cartesianChartModel.addSeries(series);

		return cartesianChartModel;
	}

	public ContinuousChartSeriesFactory getChartSeriesFactory() {
		return chartSeriesFactory;
	}

	public void setChartSeriesFactory(
			ContinuousChartSeriesFactory chartSeriesFactory) {
		this.chartSeriesFactory = chartSeriesFactory;
	}
}
