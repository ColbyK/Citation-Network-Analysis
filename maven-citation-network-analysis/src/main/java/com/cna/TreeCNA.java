package com.cna;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

public class TreeCNA {
	private List<TreeCNA> branches;
	private JSONObject paper;
	private int depth;
	public TreeCNA(int currentDepth, int maxDepth, JSONObject obj, List<JSONObject> data) {
		depth = currentDepth;
		paper = obj;
		branches = new ArrayList<TreeCNA>();
		if(currentDepth < maxDepth) {
			makeBranches(data, maxDepth);
		}
	}
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
		for(int i = 0; i < matches.size(); i++) {
			branches.add(new TreeCNA(depth + 1, maxDepth, matches.get(i), data));
		}
	}
	public String getPaperString(String indent) {
		return indent + "T" + depth + ":\t" + "Title: " + paper.getString("title") + " ||||| Year: " + paper.getInt("year");
	}
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
