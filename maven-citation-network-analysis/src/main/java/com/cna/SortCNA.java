package com.cna;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Abstract class for sorting methods 
abstract class SortCNA {
	// Sorts a list by year from newest to oldest
	public static List<TreeCNA> sortByYear(List<TreeCNA> list){
		return list.stream().sorted((a, b) -> Integer.compare(b.getNodeData().getInt("year"), a.getNodeData().getInt("year"))).collect(Collectors.toList());
	}
	// Sorts a list by number of times referenced from most times referenced to least
	public static List<TreeCNA> sortByTimesReferenced(List<TreeCNA> list){
		return list.stream().sorted((a, b) -> Integer.compare(b.getBranches().size() , a.getBranches().size())).collect(Collectors.toList());
	}
	// Recursive helper method for collapseTreeToList()
	private static List<TreeCNA> recCollapseTree(List<TreeCNA> nodes, List<TreeCNA> list){
		for(TreeCNA node: nodes) {
			list.add(node);
			if(!node.isLeaf()) {
				recCollapseTree(node.getBranches(), list);
			}
		}
		return list;
	}
	// Collapses an entire tree into to a list
	public static List<TreeCNA> collapseTreeToList(List<TreeCNA> roots){
		return recCollapseTree(roots, new ArrayList<TreeCNA>());
	}
}
