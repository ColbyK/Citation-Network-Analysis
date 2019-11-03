package com.cna;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class OperationsCNA {
	private String keyword;
	private int maxDepth;
	private List<JSONObject> instances;
	private List<JSONObject> rootMatches;
	private List<TreeCNA> roots;
	public OperationsCNA(String key, ReaderCNA read, int sizeFromReader, int mDepth) {
		keyword = key;
		maxDepth = mDepth;
		roots = new ArrayList<TreeCNA>();
		instances = read.getNextNumberOfInstances(sizeFromReader);
		rootMatches = getInstancesWithKeyword(keyword);
		//new Garbage(instances);
		//System.out.println(rootMatches.size());
		//System.out.println(rootMatches.get(1).getJSONArray("references"));
		for(int i = 0; i < rootMatches.size(); i++) {
			//System.out.println(rootMatches.get(i).get("title"));
			roots.add(new TreeCNA(0, maxDepth, rootMatches.get(i), instances));
		}
		
	}
	public List<JSONObject> getInstancesWithKeyword(String key){
		return instances.stream().filter(s -> s.has("title") && s.getString("title").contains(key)).collect(Collectors.toList());
	}
	public void printFindings() {
		for(int i = 0; i < roots.size(); i++) {
			System.out.println(roots.get(i).getString(""));
		}
	}
}
