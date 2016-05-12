package com.discoverer.wsdlDiscoverer;


import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.discoverer.filtering.FilterType;

public class Main {

	public static void main(String[] args) throws Exception {
		// Setup List of URIs of WSDLs
		List<String> uris = WsdlDiscoverer.getWsdlUrlsFromFile("urls.txt");
		
		// Create Filter with keywords (in this case "stock" for one search
		Map<String, Integer> bpmnKeyWords = new HashMap<String, Integer>();
		bpmnKeyWords.put("Stock", 4);
		
		// Rate WSDLs according to BPMN keywords
		Rater r = new Rater();
		r.addFilterStrategy(FilterType.FilterByIncludedWords);
				
		Set<WsdlResult> resultSet = r.rate(uris, bpmnKeyWords);

		//Sorting resultSet
		List<WsdlResult> list = new LinkedList<WsdlResult>(resultSet);
		Collections.sort(list);
		
		//Printing list
		for (WsdlResult wsdlResult : list) {
			System.out.println(wsdlResult);
		}
	}
}
