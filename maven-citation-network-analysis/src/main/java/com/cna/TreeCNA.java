package com.cna;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

// Class for tree objects where each node represents a single paper
public class TreeCNA {
	// The branches of the tree object. Branches are papers that reference this node's paper
	private List<TreeCNA> branches;
	// The paper object for this tree node
	private JSONObject paper;
	// The current depth of this tree node
	private int depth;
	public TreeCNA(int currentDepth, int maxDepth, JSONObject obj, List<JSONObject> data) {
		depth = currentDepth;
		paper = obj;
		branches = new ArrayList<TreeCNA>();
		// Make sure node does not create branches past max depth
		if(currentDepth < maxDepth) {
			makeBranches(data, maxDepth);
		}
	}
	// Creates branches by traversing through all paper references to match with this paper's id
	private void makeBranches(List<JSONObject> data, int maxDepth) {
		List<JSONObject> matches = data.stream().filter(s -> {
			if(!s.has("references")) {
				return false;
			}
			JSONArray referenceList = s.getJSONArray("references");
			for(Object item : referenceList) {
				if(paper.getString("id").equals((String)item)) {
					return true;
				}
			}
			return false;
		}).collect(Collectors.toList());
		// Once branches have been found create new nodes and do the process again until no references or max depth has been reached
		for(int i = 0; i < matches.size(); i++) {
			branches.add(new TreeCNA(depth + 1, maxDepth, matches.get(i), data));
		}
	}
	// Gets a string representation of the node with proper indentation if printing in tree format
	public String getPaperString(String indent) {
		return indent + "T" + depth + ":\t" + "Title: " + paper.getString("title") + " ||||| Year: " + paper.getInt("year");
	}
	// Gets full string representation of this node and its branches with proper indentation
	public String getString(String indent) {
		String concat = getPaperString(indent) + "\n";
		for(int i = 0; i < branches.size(); i++) {
			concat += branches.get(i).getString(indent + "\t");
		}
		return concat;
	}
	public List<TreeCNA> getBranches() {
		return branches;
	}
	public JSONObject getNodeData() {
		return paper;
	}
	public boolean isLeaf() {
		return branches.size() == 0;
	}
	public int getDepth() {
		return depth;
	}
}
