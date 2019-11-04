package com.cna;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

// Class for reading the data file
public class ReaderCNA {
	// Reader object
	BufferedReader br;
	public ReaderCNA(String fileName) {
		readTXT(fileName);
	}
	// Reads the text file
	private void readTXT(String fileName) {
		try {
			br = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(fileName + " Not found...");
		}
	}
	// Helper method for getNextNumberOfInstances()
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
	// Reads the number of instances based on size and converts to JSONObject ArrayList
	public List<JSONObject> getNextNumberOfInstances(int size){
		ArrayList<JSONObject> instances = new ArrayList<JSONObject>();
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
			JSONObject[] arr = new JSONObject[size];
			for(int i = 0; i < size; i++) {
				arr[i] = current;
				//instances.add(current);
				current = getNextJSONLine();
				if(current == null) {
					break;
				}
			}
			instances = new ArrayList<>(Arrays.asList(arr));
			instances.removeAll(Collections.singleton(null));
			return instances;
		}
	}
	// Close file when program finishes
	public void finalize() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error closing file...");
		}
	}
}
