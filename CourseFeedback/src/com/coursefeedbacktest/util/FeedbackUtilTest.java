package com.coursefeedbacktest.util;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import com.coursefeedback.util.FeedbackUtil;

public class FeedbackUtilTest {

	@Test
	public void test() {
		Collection<Integer> numbers = Arrays.asList(3, 5, 2, 6, 324, 6);

		System.out.println("Numbers: " + numbers);

		System.out.println("Sum: " + FeedbackUtil.sum(numbers));

		System.out.println("Average: " + FeedbackUtil.average(numbers));
	}
}
