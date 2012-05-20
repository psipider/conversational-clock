package com.psipider.exercise.clock.utils;

import java.util.Calendar;

/**
 * Utility class for decoding a time into plain language strings representing 
 * each portion of the time.
 * 
 * @author gavyn
 */
public class ClockUtil {
	
	public static final String AM = "in the morning";
	public static final String PM = "in the afternoon";
	public static final String EVE = "in the evening";
	
	public static final String QUARTER_PAST = "a quarter past";
	public static final String QUARTER_TO = "a quarter to";
	public static final String HALF_PAST = "half past";
	public static final String JUST_AFTER = "just after";
	public static final String JUST_BEFORE = "just before";
	
	public static final String[] HOURS = {"midnight", "one", "two", "three", "four", "five",
		"six", "seven", "eight", "nine", "ten", "eleven", "noon"};
	
	/**
	 * Decodes the hour of a supplied time into a plain language representation. 
	 * Special cases are midnight and noon. Other times are one, two, three etc.  
	 * 
	 * @param time the time to decode.
	 * @return String representing the hour.
	 */
	public static String decodeHours(final Calendar time) {
		String decodedHour = null;
		int hour = time.get(Calendar.HOUR_OF_DAY);
		// If we are in the range of quarter to the hour we will be talking about the next hour
		if (time.get(Calendar.MINUTE) > 37) {
			hour = hour + 1;
			if (hour == 24) {
				hour = 0;
			}
		}
		// Now convert from 24hr hours > 12 e.g. 13:00 is 1pm so the same as 1 for our purposes
		if (hour > 12) {
			hour = hour - 12;
		}
		// Now return the value
		decodedHour = HOURS[hour];
		return decodedHour;
	}

	/**
	 * Determines if we're in the morning or afternoon
	 * 
	 * @param time the time to decode.
	 * @return String representing the period of the day.
	 */
	public static String decodePeriod(final Calendar time) {
		String decodedPeriod = null;
		int hour = time.get(Calendar.HOUR_OF_DAY);
		if (hour >= 18) {
			decodedPeriod = EVE;
		} else if (hour >= 12) {
			decodedPeriod = PM;
		} else {
			decodedPeriod = AM;
		}
		return decodedPeriod;
	}
	
	/**
	 * Decodes the minutes into plain language. Rules: 7 minutes after each 15 
	 * is just after, 6 minutes before each 15 is just before, 15 is quarter past, 
	 * 30 is half past, 45 is quarter to.
	 * 
	 * E.g. 8:43 = just before a quarter to nine.
	 * 
	 * @param time the time to decode.
	 * @return String representing the position in the hour.
	 */
	public static String decodeMinutes(final Calendar time) {
		int minute = time.get(Calendar.MINUTE);
		String decodedMinutes = decodeQuarterHour(minute);
		for (int i=0; i <= 45; i+=15) {
			if (minute > i && minute <= i+7) {
				decodedMinutes = JUST_AFTER + " " + decodedMinutes;
			}
			if (minute > i+7 && minute < i+15) {
				decodedMinutes = JUST_BEFORE + " " + decodedMinutes;
			}			
		}
		return decodedMinutes;
	}

	/**
	 * Determines which quarter of the hour the supplied minute is closest to. 
	 * Returns an empty string if the answer is the hour. Otherwise it returns
	 * half past, a quarter past or a quarter to.
	 * 
	 * @param minute the minute of the hour.
	 * @return String based on the section of the hour the minute is in or empty.
	 */
	private static String decodeQuarterHour(int minute) {
		String decodedMinutes = "";
		if (minute > 7 && minute <= 22) {
			decodedMinutes = QUARTER_PAST + " ";
		}
		if (minute > 22 && minute <= 37) {
			decodedMinutes = HALF_PAST + " ";
		}
		if (minute > 37 && minute <= 52) {
			decodedMinutes = QUARTER_TO + " ";
		}
		return decodedMinutes;
	}

	/**
	 * Builds up a full plain language string telling the time based on the Calendar input.
	 * 
	 * @param time the time to convert.
	 * @return String representing a plain language time.
	 */
	public static String getConversationalTime(Calendar time) {
		String conversationalTime = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(time.getTime());
		String minutes = decodeMinutes(cal);
		String hours = decodeHours(cal);
		String period = "";
		if (!"noon".equals(hours) && !"midnight".equals(hours)) {
			period = " " + decodePeriod(cal);
		}
		conversationalTime = minutes + hours + period;
		return conversationalTime;
	}
	
}
