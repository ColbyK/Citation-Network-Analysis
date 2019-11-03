package com.cna;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract class SortCNA {
	public static List<TreeCNA> sortByYear(List<TreeCNA> list){
		return list.stream().sorted((a, b) -> Integer.compare(b.getNodeData().getInt("year"), a.getNodeData().getInt("year"))).collect(Collectors.toList());
	}
	public static List<TreeCNA> sortByTimesReferenced(List<TreeCNA> list){
		return list.stream().sorted((a, b) -> Integer.compare(b.getBranches().size() , a.getBranches().size())).collect(Collectors.toList());
	}
	private static List<TreeCNA> recCollapseTree(List<TreeCNA> nodes, List<TreeCNA> list){
		for(TreeCNA node: nodes) {
			list.add(node);
			if(!node.isLeaf()) {
				recCollapseTree(node.getBranches(), list);
			}
		}
		return list;
	}
	public static List<TreeCNA> collapseTreeToList(List<TreeCNA> roots){
		return recCollapseTree(roots, new ArrayList<TreeCNA>());
	}
}
