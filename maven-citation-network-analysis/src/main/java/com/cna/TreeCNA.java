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
			getBranches(data, maxDepth);
		}
	}
	public void getBranches(List<JSONObject> data, int maxDepth) {
		//data.stream().forEach(s -> System.out.println(s.getJSONArray("references")));
		//List<JSONObject> matches = data.stream().filter(s -> s.has("references") && s.getJSONArray("references").toList().contains(paper.getLong("id"))).collect(Collectors.toList());
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
		//System.out.println(matches.size());
		for(int i = 0; i < matches.size(); i++) {
			//System.out.println(matches.get(i));
			branches.add(new TreeCNA(depth + 1, maxDepth, matches.get(i), data));
		}
	}
	public String getString(String indent) {
		String concat = indent + "T" + depth + ":\t" + paper + "\n";
		for(int i = 0; i < branches.size(); i++) {
			concat += branches.get(i).getString(indent + "\t");
		}
		return concat;
	}
	public int getDepth() {
		return depth;
	}
}
