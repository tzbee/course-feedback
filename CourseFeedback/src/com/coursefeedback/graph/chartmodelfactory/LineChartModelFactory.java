package com.coursefeedback.graph.chartmodelfactory;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.coursefeedback.feedback.Feedback;
import com.coursefeedback.graph.chartdatafactory.ContinuousChartDataFactory;

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

	@ManagedProperty("#{chartDataFactory}")
	private ContinuousChartDataFactory chartDataFactory;

	@Override
	public CartesianChartModel getFeedbackChartModel(List<Feedback> feedbacks) {

		// Create the model
		CartesianChartModel cartesianChartModel = new CartesianChartModel();

		// Create the series
		LineChartSeries series = new LineChartSeries();

		// Label the series
		series.setLabel("Average");

		// Translate the data
		Map<Integer, Double> chartData = this.chartDataFactory
				.getChartData(feedbacks);

		// Add the data to the chart series
		for (Integer xAxisValue : chartData.keySet()) {
			series.set(xAxisValue, chartData.get(xAxisValue));
		}

		// Add the series to the model
		cartesianChartModel.addSeries(series);

		return cartesianChartModel;
	}

	public ContinuousChartDataFactory getChartDataFactory() {
		return chartDataFactory;
	}

	public void setChartDataFactory(ContinuousChartDataFactory chartDataFactory) {
		this.chartDataFactory = chartDataFactory;
	}
}
