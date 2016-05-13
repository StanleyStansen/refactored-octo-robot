package com.discoverer.bpmnInput;

import java.util.Map;

public interface BpmnStrategy {

	public Map<String, Integer> getKeywords(String filePath);
	
}
