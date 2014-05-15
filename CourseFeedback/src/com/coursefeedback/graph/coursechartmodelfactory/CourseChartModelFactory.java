package com.coursefeedback.graph.coursechartmodelfactory;

import org.primefaces.model.chart.CartesianChartModel;

import com.coursefeedback.course.Course;

/**
 * Creates chart model based on the given course object.
 * 
 * @author
 */
public interface CourseChartModelFactory {
	CartesianChartModel createCourseChartModel(Course course);
}
