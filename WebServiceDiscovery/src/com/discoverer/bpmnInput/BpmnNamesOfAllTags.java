package com.discoverer.bpmnInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class BpmnNamesOfAllTags extends BpmnInput {
	
	@Override
	public Map<String, Integer> getKeywords(String filePath) throws SAXException, IOException {
		Map<String, Integer> keyWords = new HashMap<String, Integer>();
		
		Document doc = getBpmnDocument(filePath);
		
		return keyWords;
	}
	
	/*
	@Override
	public Map<String, Integer> getKeywords(String filePath) {
		Map<String, Integer> keyWords = new HashMap<String, Integer>();
		keyWords.put("Stock", 4);
		return keyWords;
	}*/
	
}
