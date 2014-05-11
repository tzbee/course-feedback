package com.coursefeedback.util;

import java.util.Collection;

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
}
