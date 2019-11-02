package com.cna;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

public class ReaderCNA {
	BufferedReader br;
	public ReaderCNA(String fileName) {
		try {
			br = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(fileName + " Not found...");
		}
	}
	public JSONObject getNextJSONLine() {
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
	public void finalize() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error closing file...");
		}
	}
}
