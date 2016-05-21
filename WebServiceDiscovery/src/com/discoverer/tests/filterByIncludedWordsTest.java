package com.discoverer.tests;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.xml.sax.SAXException;

import com.discoverer.filtering.Filter;
import com.discoverer.filtering.FilterStrategy;
import com.discoverer.filtering.FilterType;
import com.discoverer.wsdlDiscoverer.WsdlResult;

import static org.junit.Assert.*;

public class filterByIncludedWordsTest {

	private Map<String, Integer> keyWords = setUpExampleKeywords();
	private String testWsdlUri = "http://www.webservicex.net/airport.asmx?WSDL";
	
	@Rule
	public TestName testName = new TestName();
	
	@Test
	public void createFilter() {
		System.out.println("\n***** Test " + testName.getMethodName() + " *****\n");
		FilterStrategy filterStrategy = Filter.createFilter(FilterType.FilterByIncludedWords);
		assertNotNull(filterStrategy);
	}
	
	@Test
	public void filterTest() throws SAXException, IOException {
		System.out.println("\n***** Test " + testName.getMethodName() + " *****\n");
		
		FilterStrategy filterStrategy = Filter.createFilter(FilterType.FilterByIncludedWords);
		filterStrategy.addKeyWords(keyWords);
		
		Set<WsdlResult> resultSet = new TreeSet<WsdlResult>();
		resultSet.add(new WsdlResult(testWsdlUri));
		
		resultSet = filterStrategy.filterWsdls(resultSet);
		
		List<WsdlResult> list = new LinkedList<WsdlResult>(resultSet);
		WsdlResult result = list.get(0);
		assertEquals(testWsdlUri, result.getUri());
		assertEquals(51, result.getScore());
		
		System.out.println("Score: " + result.getScore() + ", WSDL: "+ result.getUri());
		
	}
			
	private Map setUpExampleKeywords() {
		Map<String, Integer> expected = new HashMap<String, Integer>();
		expected.put("date", 8);
		expected.put("booking", 2);
		expected.put("city", 8);
		expected.put("Customer", 2);
		expected.put("enters", 2);
		expected.put("content", 8);
		expected.put("miles", 2);
		expected.put("than", 2);
		expected.put("weather", 8);
		expected.put("Directory", 4);
		expected.put("Directory", 4);
		expected.put("length", 8);
		expected.put("runway", 8);
		expected.put("forecast", 8);
		expected.put("completed", 2);
		expected.put("airport", 8);
		expected.put("Weather", 4);
		expected.put("Sunset", 4);
		expected.put("Travel", 4);
		expected.put("longer", 2);
		expected.put("service", 4);
		expected.put("sunset", 8);
		expected.put("Airport", 4);
		expected.put("name", 8);
		expected.put("Fax", 8);
		expected.put("rise", 8);
		expected.put("travel", 2);
		expected.put("agency", 4);
		
		return expected;
	}
}
