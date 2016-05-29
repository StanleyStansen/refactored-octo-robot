package com.discoverer.filtering;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.xml.sax.SAXException;

import com.discoverer.wsdlDiscoverer.WsdlResult;

public class FilterReduceResultSet extends Filter {

	private int numberOfResulsts;
	
	public FilterReduceResultSet(int numberOfResults) {
		this.numberOfResulsts = numberOfResults;
	}
	
	@Override
	public Collection<WsdlResult> filterWsdls(Collection<WsdlResult> resultSet) throws SAXException, IOException {
		
		// Delete all wsdl results with score 0
		Set<WsdlResult> markedForDelete = new TreeSet<WsdlResult>();
		for (WsdlResult wsdlResult : resultSet) {
			if (wsdlResult.getScore() == 0) {
				markedForDelete.add(wsdlResult);
			}
		}
		resultSet.removeAll(markedForDelete); // Bug: Entfernt 3 webservices nicht: geoipservice.qsmx, NAICS.asmx und LondonGoldFix.asmx 
		
		// Sort Results as List
		List<WsdlResult> list = new LinkedList<WsdlResult>(resultSet);
		Collections.sort(list);
		
		// Remove all except the topmost ones according to numberOfResults
		markedForDelete = new TreeSet<WsdlResult>();
		int i = numberOfResulsts;
		for (WsdlResult wsdlResult : list) {
			if (i == 0) {
				markedForDelete.add(wsdlResult);
			} else {
				i--;
			}
		}
		list.removeAll(markedForDelete);
		return list;
	}

	@Override
	public void addKeyWords(Map<String, Integer> keyWords) {
		// empty implementation
	}

	@Override
	public void deleteAllKeyWords() {
		// empty implementation
	}
	
	

}
