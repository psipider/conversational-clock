conversational-clock
====================

A Java coding exercise to develop a clock that can tell the time in plain English.

Prerequisites:

1. JDK1.6
2. Maven 2.x

To build:

1. Download all source.
2. From root folder, type:
	mvn package
3. executable jar file will be created in <root folder>/target

If you've built the binary it will be located in the target sub-folder name clock-1.0.jar. A pre-packaged binary is also available in the bin sub-folder with the same name.

To run:

1. Use the following command:
	java -jar <jar file name> [time parameter in 24hr format]
	e.g. 
	java -jar clock-1.0.jar 23:46
	
Enjoy!
