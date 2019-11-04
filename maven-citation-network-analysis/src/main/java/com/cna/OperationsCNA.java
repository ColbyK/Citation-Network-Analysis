package com.cna;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class OperationsCNA {
	private String keyword;
	private int maxDepth;
	private List<TreeCNA> roots;
	public OperationsCNA(String key, ReaderCNA read, int sizeFromReader, int mDepth, long startTime) {
		keyword = key;
		maxDepth = mDepth;
		roots = new ArrayList<TreeCNA>();
		List<JSONObject> instances = read.getNextNumberOfInstances(sizeFromReader);
		MainCNA.printProgramTime(startTime, "Reading");
		List<JSONObject> rootMatches = getInstancesWithKeyword(keyword, instances);
		for(int i = 0; i < rootMatches.size(); i++) {
			roots.add(new TreeCNA(0, maxDepth, rootMatches.get(i), instances));
		}
		
	}
	public List<JSONObject> getInstancesWithKeyword(String key, List<JSONObject> instances){
		return instances.stream().filter(s -> s.has("title") && s.getString("title").contains(key)).collect(Collectors.toList());
	}
	public void printResultsTreeByOrder() {
		System.out.println("========== Results Ordered By Order (Tree) ==========");
		roots.stream().forEach(a -> System.out.println(a.getString("")));
		System.out.println("=====================================================");
	}
	public void printResultsAllByYear() {
		System.out.println("========== Results Ordered By Year (List) ==========");
		List<TreeCNA> sortedList = SortCNA.sortByYear(SortCNA.collapseTreeToList(roots));
		sortedList.stream().forEach(a -> System.out.println(a.getPaperString("")));
		System.out.println("====================================================");
	}
	public void printResultsAllByReferenced() {
		System.out.println("========== Results Ordered By Number Of Times Referenced (List) ==========");
		List<TreeCNA> sortedList = SortCNA.sortByTimesReferenced(SortCNA.collapseTreeToList(roots));
		sortedList.stream().forEach(a -> System.out.println(a.getPaperString("")));
		System.out.println("==========================================================================");
	}
	public void printResultsTreeByYear() {
		System.out.println("========== Results Ordered By Year (Tree) ==========");
		traversePrintTreeByYear(roots, "");
		System.out.println("====================================================");
	}
	private void traversePrintTreeByYear(List<TreeCNA> nodes, String indent) {
		List<TreeCNA> sortedRoots = SortCNA.sortByYear(nodes);
		sortedRoots.stream().forEach(a -> {
			System.out.println(a.getPaperString(indent));
			if(!a.isLeaf()) {
				traversePrintTreeByYear(a.getBranches(), indent+"\t");
			}
		});
	}
	public void printResultsTreeByReferenced() {
		System.out.println("========== Results Ordered By Number Of Times Referenced (Tree) ==========");
		traversePrintTreeByReference(roots, "");
		System.out.println("==========================================================================");
	}
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
