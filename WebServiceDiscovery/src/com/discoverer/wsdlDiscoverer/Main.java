package com.discoverer.wsdlDiscoverer;

import java.util.List;
import java.util.Set;
import java.util.Collection;
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
		List<WsdlResult> list = new Rater()
				.setBpmnStrategy(BpmnStrategyType.BpmnNamesOfAllTags)
				.addFilterStrategy(FilterType.FilterByIncludedWords)
				.addFilterStrategy(FilterType.FilterReduceResultSet, 10)
				.rate(uris, "BpmnTest.xml");

		// Printing list
		System.out.println("\n");
		for (WsdlResult wsdlResult : list) {
			System.out.println(wsdlResult);
		}
	}
}
