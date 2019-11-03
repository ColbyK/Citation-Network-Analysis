package com.cna;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class ReaderCNA {
	BufferedReader br;
	public ReaderCNA(String fileName) {
		readTXT(fileName);
	}
	private void readTXT(String fileName) {
		try {
			br = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(fileName + " Not found...");
		}
	}
	private JSONObject getNextJSONLine() {
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error reading next line...");
		}
		if(line == null) {
			return null;
		}
		return new JSONObject(line);
	}
	public List<JSONObject> getNextNumberOfInstances(int size){
		List<JSONObject> instances = new ArrayList<JSONObject>();
		JSONObject current = getNextJSONLine();
		if(size < -1 || current == null) {
			return null;
		}
		if(size == -1) {
			do {
				instances.add(current);
				current = getNextJSONLine();
			}while(current != null);
			return instances;
		}
		else {
			for(int i = 0; i < size; i++) {
				instances.add(current);
				current = getNextJSONLine();
				if(current == null) {
					break;
				}
			}
			return instances;
		}
	}
	public void finalize() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error closing file...");
		}
	}
}
