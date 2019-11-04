package com.cna;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.concurrent.TimeUnit;
// Main file for the project with time and memory recording
public class MainCNA {
	// Configurations for the project
	// The file to read data from, in JSON format
	private static final String fileName = "dblp_papers_v11.txt";
	// The keyword to search for creating reference trees
	private static final String keyword = "Middleware";
	// The number of instances to read and test from the data file
	private static final int instancesToRead = 100000;
	// The maximum depth the tree can reference from the root
	private static final int maxDepth = 3;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ReaderCNA reader = new ReaderCNA(fileName);
		OperationsCNA oper = new OperationsCNA(keyword, reader, instancesToRead, maxDepth, startTime);
		oper.printResultsTreeByYear();
		//oper.printResultsTreeByOrder();
		//oper.printResultsAllByYear();
		//oper.printResultsTreeByReferenced();
		//oper.printResultsAllByReferenced();
		printMemoryUsage();
		printProgramTime(startTime, "Program");
		
	}
	// Prints the difference in time from the start time
	public static void printProgramTime(long startTime, String identifier) {
		long difference = System.currentTimeMillis() - startTime;
		String time = String.format("%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours(difference),
				TimeUnit.MILLISECONDS.toMinutes(difference) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(difference)),
			    TimeUnit.MILLISECONDS.toSeconds(difference) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference)));
		System.out.println(identifier + " Time : " + time);
	}
	// Prints the memory usage of the program at the current time
	private static void printMemoryUsage() {
		MemoryUsage heapMemUse = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		System.out.println("Memory Used in Bytes : " + heapMemUse.getUsed());
	}
}
