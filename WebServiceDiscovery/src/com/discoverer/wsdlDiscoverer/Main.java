package com.discoverer.wsdlDiscoverer;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.discoverer.filtering.FilterByIncludedWords;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// Setup List of URIs of WSDLs
		List<String> uris = new LinkedList<String>();
		uris.add("http://www.webservicex.net/stockquote.asmx?WSDL");
		
		// Create Filter with keywords (in this case "stock" for one search
		FilterByIncludedWords filter = new FilterByIncludedWords();
		filter.addFilterString("Stock");
		
		// Search all WSDL Files 
		Set<WsdlResult> resultSet = filter.filterWsdls(uris);
		
		//Print ResultSet (sorted by number of Hits)
		for (WsdlResult wsdl : resultSet) {
			System.out.println("Keywords: " + wsdl.getNumberOfHits() + ", URI: " + wsdl.getUri());
		}
	}
}
