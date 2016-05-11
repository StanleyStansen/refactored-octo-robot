package com.discoverer.wsdlDiscoverer;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.discoverer.filtering.FilterByIncludedWords;
import com.discoverer.filtering.FilterType;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// Setup List of URIs of WSDLs
		List<String> uris = new LinkedList<String>();
		uris.add("http://www.webservicex.net/stockquote.asmx?WSDL");
		
		// Create Filter with keywords (in this case "stock" for one search
		List<String> bpmnKeyWords = new LinkedList<String>();
		bpmnKeyWords.add("Stock");
		
		Rater r = new Rater();
		r.addFilterStrategy(FilterType.FilterByIncludedWords);
		Set<WsdlResult> resultSet = r.rate(uris, bpmnKeyWords);

		for (WsdlResult wsdl : resultSet) {
			System.out.println("Keywords: " + wsdl.getNumberOfHits() + ", URI: " + wsdl.getUri());
		}
	}
}
