package com.coursefeedback.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.coursefeedback.feedback.Feedback;

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
	 * Get a list of all feedback average values
	 * 
	 * @param feedbackValues
	 * @return
	 */
	public static List<Double> createAverageValueSet(
			List<Integer> feedbackValues) {
		List<Double> averageValueSet = new ArrayList<Double>();

		for (int i = 0; i < feedbackValues.size(); i++) {
			averageValueSet.add(average(getSubSet(feedbackValues, i)));
		}

		return averageValueSet;
	}

	/**
	 * Get a list of all feedback values
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
