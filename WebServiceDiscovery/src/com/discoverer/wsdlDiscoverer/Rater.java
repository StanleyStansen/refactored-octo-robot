package com.discoverer.wsdlDiscoverer;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.xml.sax.SAXException;

import com.discoverer.filtering.*;

public class Rater {

	private List<FilterStrategy> filterStrategies = new LinkedList<FilterStrategy>();

	public Rater() {

	}

	public Set<WsdlResult> rate(List<String> wsdls, Map<String, Integer> bpmnKeyWords) {

		Set<WsdlResult> resultSet = new TreeSet<WsdlResult>();

		// Setup ResultSet
		for (String wsdl : wsdls) {
			resultSet.add(new WsdlResult(wsdl));
		}

		// score every wsdl-url in the resultSet using every filter
		for (FilterStrategy filter : filterStrategies) {

			// Prepare Filter for Filtering with the new bpmn keywords
			filter.deleteAllKeyWords();
			filter.addKeyWords(bpmnKeyWords);

			// score the wsdls in resultSet by using the current filter
			try {
				resultSet = filter.filterWsdls(resultSet);
			} catch (Exception e) {
				System.err.println("A wsdl file could not be loaded.");
				e.printStackTrace();
			}
		}
		return resultSet;
	}

	public Rater addFilterStrategy(FilterType type) {
		FilterStrategy strategy = Filter.createFilter(type);
		filterStrategies.add(strategy);
		return this;
	}

}
