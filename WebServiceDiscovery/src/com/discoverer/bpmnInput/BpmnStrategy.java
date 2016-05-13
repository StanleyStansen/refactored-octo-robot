package com.discoverer.bpmnInput;

import java.io.IOException;
import java.util.Map;

import org.xml.sax.SAXException;

public interface BpmnStrategy {

	public Map<String, Integer> getKeywords(String filePath) throws SAXException, IOException;
	
}
