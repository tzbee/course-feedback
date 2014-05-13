package com.coursefeedback.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.coursefeedback.feedback.Feedback;

/**
 * Utility methods for feedback management
 * 
 * @author touzbi
 */

public class FeedbackUtil {

	/**
	 * Calculate the average of a collection of numbers
	 * 
	 * @param numbers
	 *            the collection of numbers to calculate the average from
	 * @return The average of the given collection of numbers
	 */

	public static double average(Collection<Integer> numbers) {
		return (double) sum(numbers) / numbers.size();
	}

	/**
	 * Calculate the sum of a collection of numbers
	 * 
	 * @param numbers
	 *            the collection of numbers to calculate the sum from
	 * @return The sum of the given collection of numbers
	 */

	public static int sum(Collection<Integer> numbers) {
		int sum = 0;

		for (Integer number : numbers) {
			sum += number;
		}

		return sum;
	}

	/**
	 * Get a subset of a generic list from 0 to a specified index, inclusive
	 * 
	 * @param elements
	 * @param index
	 * @return
	 */

	public static <E> List<E> getSubSet(List<E> elements, int index) {
		List<E> subSet = new ArrayList<E>();

		for (int i = 0; i <= index; i++) {
			subSet.add(elements.get(i));
		}

		return subSet;
	}

	/**
	 * Get a list of average values form all previous elements for each index of
	 * the given collection
	 * 
	 * Example: {1,4,7} -> {1, 2.5, 4} because
	 * 
	 * average of 1 -> 1
	 * 
	 * average of {1, 4} -> 2.5
	 * 
	 * average of {1, 4, 7} -> 4
	 * 
	 * @param input
	 *            values
	 * @return the list of average values
	 */

	public static List<Double> createAverageValueSet(List<Integer> values) {
		List<Double> averageValueSet = new ArrayList<Double>();

		for (int i = 0; i < values.size(); i++) {
			averageValueSet.add(average(getSubSet(values, i)));
		}

		return averageValueSet;
	}

	/**
	 * Get a list of all feedback values given a feedback collection
	 * 
	 * @param feedbacks
	 * @return
	 */

	public static List<Integer> getFeedbackValues(List<Feedback> feedbacks) {
		List<Integer> feedbackValues = new ArrayList<Integer>();

		for (Feedback feedback : feedbacks) {
			feedbackValues.add(feedback.getValue());
		}

		return feedbackValues;
	}
}
