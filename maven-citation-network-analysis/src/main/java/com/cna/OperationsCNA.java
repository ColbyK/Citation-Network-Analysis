package com.cna;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
// Class for running operations for the reference trees
public class OperationsCNA {
	// The roots of the initial matches of the keyword, branches reference the root
	private List<TreeCNA> roots;
	public OperationsCNA(String key, ReaderCNA read, int sizeFromReader, int mDepth, long startTime) {
		roots = new ArrayList<TreeCNA>();
		List<JSONObject> instances = read.getNextNumberOfInstances(sizeFromReader);
		// Prints the Reading time
		MainCNA.printProgramTime(startTime, "Reading");
		List<JSONObject> rootMatches = getInstancesWithKeyword(key, instances);
		// Adds all matches to roots, then finds reference matches
		for(int i = 0; i < rootMatches.size(); i++) {
			roots.add(new TreeCNA(0, mDepth, rootMatches.get(i), instances));
		}
		
	}
	// Uses stream to get all instances that contain the keyword
	public List<JSONObject> getInstancesWithKeyword(String key, List<JSONObject> instances){
		return instances.stream().filter(s -> s.has("title") && s.getString("title").contains(key)).collect(Collectors.toList());
	}
	// Prints the results of the matched and referenced instances in tree format by order (default, no sorting)
	public void printResultsTreeByOrder() {
		System.out.println("========== Results Ordered By Order (Tree) ==========");
		roots.stream().forEach(a -> System.out.println(a.getString("")));
		System.out.println("=====================================================");
	}
	// Prints the results of the matched and referenced instances all as a list by year
	public void printResultsAllByYear() {
		System.out.println("========== Results Ordered By Year (List) ==========");
		List<TreeCNA> sortedList = SortCNA.sortByYear(SortCNA.collapseTreeToList(roots));
		sortedList.stream().forEach(a -> System.out.println(a.getPaperString("")));
		System.out.println("====================================================");
	}
	// Prints the results of the matched and referenced instances all as a list by number the times the paper has been referenced
	public void printResultsAllByReferenced() {
		System.out.println("========== Results Ordered By Number Of Times Referenced (List) ==========");
		List<TreeCNA> sortedList = SortCNA.sortByTimesReferenced(SortCNA.collapseTreeToList(roots));
		sortedList.stream().forEach(a -> System.out.println(a.getPaperString("")));
		System.out.println("==========================================================================");
	}
	// Prints the results of the matches and referenced instances in tree format by year
	public void printResultsTreeByYear() {
		System.out.println("========== Results Ordered By Year (Tree) ==========");
		traversePrintTreeByYear(roots, "");
		System.out.println("====================================================");
	}
	// Recursive helper method for printResultsTreeByYear()
	private void traversePrintTreeByYear(List<TreeCNA> nodes, String indent) {
		List<TreeCNA> sortedRoots = SortCNA.sortByYear(nodes);
		sortedRoots.stream().forEach(a -> {
			System.out.println(a.getPaperString(indent));
			if(!a.isLeaf()) {
				traversePrintTreeByYear(a.getBranches(), indent+"\t");
			}
		});
	}
	// Prints the results of the matched and referenced instances in tree format by number the times the paper has been referenced
	public void printResultsTreeByReferenced() {
		System.out.println("========== Results Ordered By Number Of Times Referenced (Tree) ==========");
		traversePrintTreeByReference(roots, "");
		System.out.println("==========================================================================");
	}
	//Recursive helper method for PrintResultsTreeByReferenced()
	private void traversePrintTreeByReference(List<TreeCNA> nodes, String indent) {
		List<TreeCNA> sortedRoots = SortCNA.sortByTimesReferenced(nodes);
		sortedRoots.stream().forEach(a -> {
			System.out.println(a.getPaperString(indent));
			if(!a.isLeaf()) {
				traversePrintTreeByReference(a.getBranches(), indent+"\t");
			}
		});
	}
}
