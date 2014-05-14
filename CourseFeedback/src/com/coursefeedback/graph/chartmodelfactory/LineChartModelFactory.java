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
 * Creates a line chart model with the series of x,y values taken from the chart
 * data factory
 * 
 * @author touzbi
 */

@ManagedBean(name = "chartModelFactory")
public class LineChartModelFactory implements ChartModelFactory {
	private static final String SERIES_LABEL = "Average";

	@ManagedProperty("#{chartDataFactory}")
	private ContinuousChartDataFactory chartDataFactory;

	@Override
	public CartesianChartModel getFeedbackChartModel(List<Feedback> feedbacks) {

		// Create the model
		CartesianChartModel cartesianChartModel = new CartesianChartModel();

		// Create the series
		LineChartSeries series = new LineChartSeries();

		// Label the series
		series.setLabel(SERIES_LABEL);

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
