package com.cna;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.util.concurrent.TimeUnit;

public class MainCNA {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ReaderCNA reader = new ReaderCNA("dblp_papers_v11.txt");
		OperationsCNA oper = new OperationsCNA("Middleware", reader, 10000, 3, startTime);
		oper.printResultsTreeByYear();
		//oper.printResultsTreeByOrder();
		//oper.printResultsAllByYear();
		//oper.printResultsTreeByReferenced();
		//oper.printResultsAllByReferenced();
		printMemoryUsage();
		printProgramTime(startTime, "Program");
		
	}
	public static void printProgramTime(long startTime, String identifier) {
		long difference = System.currentTimeMillis() - startTime;
		String time = String.format("%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours(difference),
				TimeUnit.MILLISECONDS.toMinutes(difference) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(difference)),
			    TimeUnit.MILLISECONDS.toSeconds(difference) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference)));
		System.out.println(identifier + " Time : " + time);
	}
	private static void printMemoryUsage() {
		MemoryUsage heapMemUse = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
		System.out.println("Memory Used in Bytes : " + heapMemUse.getUsed());
	}
}
