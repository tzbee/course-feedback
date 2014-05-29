package com.coursefeedback.graph.coursechartmodelfactory;

import org.primefaces.model.chart.CartesianChartModel;

import com.coursefeedback.course.Course;

/**
 * Abstract factory for Creating a chart model based on a given course object.
 * 
 */
public interface CourseChartModelFactory {
	/**
	 * Creates a chart model based on the given course object.
	 * 
	 * @param course
	 *            The course object to create the chart model from
	 * 
	 * @return The chart model created from the course object
	 */

	CartesianChartModel createCourseChartModel(Course course);
}
