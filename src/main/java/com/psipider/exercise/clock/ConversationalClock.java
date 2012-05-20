package com.psipider.exercise.clock;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.psipider.exercise.clock.utils.ClockUtil;

/**
 * Hello world!
 *
 */
public class ConversationalClock 
{
	
	private static Date getDateFromParam(String arg) {
		if (arg != null) {
			DateFormat format = new SimpleDateFormat("HH:mm");
			try {
				Date date = format.parse(arg);
				
				return date;
			} catch (ParseException e) {
				//do some logging to hide the exception from the user but not the devs.
			}
		}
		return null;
	}
	
    public static void main( String[] args )
    {
    	Calendar cal = Calendar.getInstance();
    	if (args.length == 0) {
    		System.out.println("No time parameter supplied.");
    		System.out.println("Using system time of " + cal.getTime());
  		} else {
  			Date date = getDateFromParam(args[0]);
  			if (date != null) {
  				cal.clear();
  				cal.setTime(date);
  			} else {
  				System.out.println("Invalid parameter. Please provide a parameter in 24 hour time format (HH:mm). E.g 23:54.");
  	    		System.out.println("Using system time of " + cal.getTime());
  			}
  		}
        System.out.println("The time is " + ClockUtil.getConversationalTime(cal));
    }
    
}
