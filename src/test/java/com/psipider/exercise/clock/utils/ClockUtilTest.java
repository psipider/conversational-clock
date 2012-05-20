package com.psipider.exercise.clock.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import com.psipider.exercise.clock.utils.ClockUtil;


public class ClockUtilTest {

	@Test
	public void testDecodeHours() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.HOUR, 0);
		String hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "midnight", hour);
		cal.set(Calendar.HOUR, 12);
		hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "noon", hour);
		//Test each end
		cal.set(Calendar.HOUR_OF_DAY, 1);
		hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "one", hour);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "eleven", hour);
		//Test either side of noon
		cal.set(Calendar.HOUR_OF_DAY, 11);
		hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "eleven", hour);
		cal.set(Calendar.HOUR_OF_DAY, 13);
		hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "one", hour);
		//Test one other value
		cal.set(Calendar.HOUR_OF_DAY, 20);
		hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "eight", hour);
	}
	
	@Test
	public void testDecodeHourIncrement() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.MINUTE, 37);
		String hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "eleven", hour);
		cal.set(Calendar.MINUTE, 38);
		hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "noon", hour);
		cal.set(Calendar.MINUTE, 59);
		hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "noon", hour);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		hour = ClockUtil.decodeHours(cal);
		assertEquals("Hour incorrect.", "midnight", hour);
	}
	
	@Test
	public void testDecodePeriod() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2012, 5, 5, 0, 0, 0);
		//Test boundaries
		cal.set(Calendar.HOUR_OF_DAY, 0);
		String period = ClockUtil.decodePeriod(cal);
		assertEquals("Period incorrect.", "in the morning", period);
		cal.set(Calendar.HOUR_OF_DAY, 11);
		period = ClockUtil.decodePeriod(cal);
		assertEquals("Period incorrect.", "in the morning", period);
		cal.set(Calendar.HOUR_OF_DAY, 17);
		period = ClockUtil.decodePeriod(cal);
		assertEquals("Period incorrect.", "in the afternoon", period);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		period = ClockUtil.decodePeriod(cal);
		assertEquals("Period incorrect.", "in the afternoon", period);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		period = ClockUtil.decodePeriod(cal);
		assertEquals("Period incorrect.", "in the evening", period);
		cal.set(Calendar.HOUR_OF_DAY, 18);
		period = ClockUtil.decodePeriod(cal);
		assertEquals("Period incorrect.", "in the evening", period);
	}

	@Test
	public void testDecodeMinutesJustAfter() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2012, 5, 5, 0, 0, 0);
		String minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Not expecting a result", "", minutes);
		// Test a minute period in each area plus each quarter.
		// Test all just afters, in each case test the edge cases
		cal.set(Calendar.MINUTE, 1);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just after ", minutes);
		cal.set(Calendar.MINUTE, 7);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just after ", minutes);
		cal.set(Calendar.MINUTE, 16);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just after a quarter past ", minutes);
		cal.set(Calendar.MINUTE, 22);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just after a quarter past ", minutes);
		cal.set(Calendar.MINUTE, 36);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just after half past ", minutes);
		cal.set(Calendar.MINUTE, 37);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just after half past ", minutes);
		cal.set(Calendar.MINUTE, 46);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just after a quarter to ", minutes);
		cal.set(Calendar.MINUTE, 52);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just after a quarter to ", minutes);
	}

	@Test
	public void testDecodeMinutesJustBefore() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2012, 5, 5, 0, 0, 0);
		String minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Not expecting a result", "", minutes);
		// Test a minute period in each area plus each quarter.
		// Test all just afters, in each case test the edge cases
		cal.set(Calendar.MINUTE, 8);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just before a quarter past ", minutes);
		cal.set(Calendar.MINUTE, 14);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just before a quarter past ", minutes);
		cal.set(Calendar.MINUTE, 23);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just before half past ", minutes);
		cal.set(Calendar.MINUTE, 29);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just before half past ", minutes);
		cal.set(Calendar.MINUTE, 38);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just before a quarter to ", minutes);
		cal.set(Calendar.MINUTE, 44);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just before a quarter to ", minutes);
		cal.set(Calendar.MINUTE, 53);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just before ", minutes);
		cal.set(Calendar.MINUTE, 59);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "just before ", minutes);
	}
	
	@Test
	public void testDecodeMinutesQuarters() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2012, 5, 5, 0, 0, 0);
		String minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Not expecting a result", "", minutes);
		cal.set(Calendar.MINUTE, 15);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "a quarter past ", minutes);
		cal.set(Calendar.MINUTE, 30);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "half past ", minutes);
		cal.set(Calendar.MINUTE, 45);
		minutes = ClockUtil.decodeMinutes(cal);
		assertEquals("Minutes string incorrect.", "a quarter to ", minutes);
	}

	@Test
	public void testGetConversationalTime() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2012, 5, 5, 23, 59, 0);
		String time = ClockUtil.getConversationalTime(cal);
		String testString = "just before midnight";
		assertTrue("String not found: \"" + testString + "\"", time.contains(testString));
	}
}
