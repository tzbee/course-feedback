package com.coursefeedback.graph.coursechartmodelfactory;

import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.coursefeedback.course.Course;
import com.coursefeedback.courseitem.CourseItem;
import com.coursefeedback.feedback.Feedback;

/**
 * This class creates feedback summary model based on the given course object.
 * Displays every course item of the given course and their average value.
 * 
 * @author
 * 
 */
@ManagedBean
public class FeedbackSummary implements CourseChartModelFactory {
	private CartesianChartModel categoryModel;

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	@Override
	public CartesianChartModel createCourseChartModel(Course course) {
		System.out.print("createFeedbackSummaryModel(Course course) reached");
		this.categoryModel = new CartesianChartModel();
		ChartSeries chart = new ChartSeries();

		System.out.print("course: " + course); // for testing
		if (course == null) {
			return this.categoryModel;
		}
		chart.setLabel(course.getName());

		Collection<CourseItem> cItems = course.getCourseItems();

		System.out.print("cItems: " + cItems); // for testing
		if (cItems == null) {
			return this.categoryModel;
		}

		System.out.print("cItems.isEmpty: " + cItems.isEmpty()); // for testing
		if (cItems.isEmpty()) {
			chart.set("No course items", 0);
			this.categoryModel.addSeries(chart);
			return this.categoryModel;
		}

		List<Feedback> feedbacks;
		int average = 0;
		int counter = 0;
		for (CourseItem cItem : cItems) {
			feedbacks = cItem.getFeedbacks();
			if (feedbacks != null && !feedbacks.isEmpty()) {
				for (Feedback feedback : feedbacks) {
					average = average + feedback.getValue();
					counter++;
				}
				chart.set(cItem.getCourseItemName(), (float) average / counter);
				System.out.print("set to graph: " + cItem.getCourseItemName()
						+ " " + (float) average / (float) counter); // for
																	// testing
				average = 0;
				counter = 0;
			} else {
				chart.set(cItem.getCourseItemName(), 0);
				System.out.print("set to graph: " + cItem.getCourseItemName()
						+ " " + 0); // for testing
			}

		}

		this.categoryModel.addSeries(chart);
		return this.categoryModel;
	}

}
