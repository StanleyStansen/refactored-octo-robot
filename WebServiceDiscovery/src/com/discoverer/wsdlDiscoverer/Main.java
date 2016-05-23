package com.discoverer.wsdlDiscoverer;

import java.util.List;
import java.util.Set;
import java.util.Collections;
import java.util.LinkedList;
import com.discoverer.bpmnInput.BpmnStrategyType;
import com.discoverer.filtering.FilterType;

/**
 * Die Klasse Main implementiert eine beispielhafte Nutzung des Frameworks zur
 * Bewertung von Wsdl-xml-Dateien hinsichtlich eines gegebenen Bpmn-Modells.
 * 
 * @author igt
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		// Setup List of URIs of WSDLs
		List<String> uris = WsdlDiscoverer.getWsdlUrlsFromFile("urls.txt");

		// Setup Filters and BpmnInputStrategies and rate wsdl uris
		Set<WsdlResult> resultSet = new Rater().setBpmnStrategy(BpmnStrategyType.BpmnNamesOfAllTags)
				.addFilterStrategy(FilterType.FilterByIncludedWords).rate(uris, "BpmnTest.xml");

		// Sorting resultSet
		List<WsdlResult> list = new LinkedList<WsdlResult>(resultSet);
		Collections.sort(list);

		// Printing list
		for (WsdlResult wsdlResult : list) {
			System.out.println(wsdlResult);
		}
	}
}
