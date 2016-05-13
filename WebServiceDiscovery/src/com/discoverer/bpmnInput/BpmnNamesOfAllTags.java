package com.discoverer.bpmnInput;

import java.util.HashMap;
import java.util.Map;

public class BpmnNamesOfAllTags extends BpmnInput {
	
	@Override
	public Map<String, Integer> getKeywords(String filePath) {
		Map<String, Integer> keyWords = new HashMap<String, Integer>();
		keyWords.put("Stock", 4);
		return keyWords;
	}
	
	

}
