package com.cna;

import org.json.*;

public class MainCNA {
	public static void main(String[] args) {
		ReaderCNA reader = new ReaderCNA("dblp_papers_v11_first_100_lines.txt");
		JSONObject obj = reader.getNextJSONLine();
		
		System.out.println(obj);
	}
}
