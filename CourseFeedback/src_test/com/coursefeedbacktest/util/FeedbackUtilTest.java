package com.coursefeedbacktest.util;

import static com.coursefeedback.util.FeedbackUtil.createAverageValueSet;
import static com.coursefeedback.util.FeedbackUtil.getSubSet;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.coursefeedback.util.FeedbackUtil;

public class FeedbackUtilTest {

	// Test list
	private static final List<Integer> NUMBERS = Arrays.asList(3, 5, 2, 6, 324,
			6);

	@Test
	public void testGetAverageValueSet() {
		System.out.println("Numbers: " + NUMBERS);
		System.out.println("Average set: " + createAverageValueSet(NUMBERS));
	}

	@Test
	public void testGetSubset() {
		int index = NUMBERS.size() - 1;

		System.out.println("Numbers: " + NUMBERS + " - index: " + index);
		System.out.println("Subset: " + getSubSet(NUMBERS, index));
	}

	@Test
	public void testSum() {
		System.out.println("Numbers: " + NUMBERS);
		System.out.println("Sum: " + FeedbackUtil.sum(NUMBERS));

	}

	@Test
	public void testAverage() {
		System.out.println("Numbers: " + NUMBERS);
		System.out.println("Average: " + FeedbackUtil.average(NUMBERS));
	}
}
