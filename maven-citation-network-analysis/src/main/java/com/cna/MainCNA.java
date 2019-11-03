package com.cna;

public class MainCNA {
	public static void main(String[] args) {
		ReaderCNA reader = new ReaderCNA("dblp_papers_v11_first_100_lines.txt");
		//ReaderCNA reader = new ReaderCNA("dblp_papers_v11.txt", false);
		//List<JSONObject> objs = reader.getNextNumberOfInstances(-1);
		OperationsCNA oper = new OperationsCNA("Middleware", reader, 100000, 3);
		oper.printResultsTreeByYear();
		//oper.printResultsTreeByOrder();
		//oper.printResultsAllByYear();
		oper.printResultsTreeByReferenced();
		//oper.printResultsAllByReferenced();
	}
}
