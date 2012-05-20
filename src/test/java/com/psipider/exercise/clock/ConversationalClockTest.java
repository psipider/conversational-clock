package com.psipider.exercise.clock;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.psipider.exercise.clock.ConversationalClock;

/**
 * Unit test .
 */
public class ConversationalClockTest 
{
    /**
     * Primarily test the param parsing.
     */
    @Test
    public void testConversationalClock()
    {
    	//Set up so we can capture System.outs
    	OutputStream out = new ByteArrayOutputStream();
    	final PrintStream oldOut = System.out;
    	System.setOut(new PrintStream(out));
    	
    	//Now do run our tests and check the outputs
    	String[] time = new String[0];
    	ConversationalClock.main(time);
    	String testString = "No time parameter supplied.";
    	assertTrue("String not found: \"" + testString + "\"", out.toString().contains(testString));
    	time = new String[1];
    	
    	time[0] = "20:03";
    	ConversationalClock.main(time);
    	testString = "just after eight in the evening";
    	assertTrue("String not found: \"" + testString + "\"", out.toString().contains(testString));
    	
    	time[0] = "23:59";
    	ConversationalClock.main(time);
    	testString = "just before midnight";
    	assertTrue("String not found: \"" + testString + "\"", out.toString().contains(testString));
    	
    	time[0] = "26.23";
    	ConversationalClock.main(time);
    	testString = "Invalid parameter";
    	assertTrue("String not found: \"" + testString + "\"", out.toString().contains(testString));
    	
    	// Now reset the System.out
    	System.setOut(oldOut);
    	System.out.println("Recorded output:\n" + out.toString());
    }
	
}
