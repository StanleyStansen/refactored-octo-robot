package com.discoverer.tests;

import org.junit.Test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.xml.sax.SAXException;

import com.discoverer.bpmnInput.BpmnInput;
import com.discoverer.bpmnInput.BpmnStrategy;
import com.discoverer.bpmnInput.BpmnStrategyType;

public class bpmnTests {

	@Rule
	public TestName testName = new TestName();

	@Test
	public void createBpmnStrategy() {
		System.out.println("\n***** Test " + testName.getMethodName() + " *****\n");
		BpmnStrategy bpmnStrategy = BpmnInput.create(BpmnStrategyType.BpmnNamesOfAllTags);
		assertNotNull(bpmnStrategy);
	}

	@Test
	public void getKeyWordsFromBpmn() throws SAXException, IOException {
		System.out.println("\n***** Test " + testName.getMethodName() + " *****\n");

		BpmnStrategy bpmnStrategy = BpmnInput.create(BpmnStrategyType.BpmnNamesOfAllTags);
		Map<String, Integer> keyWords = bpmnStrategy.getKeywords("BpmnTest.xml");

		Map<String, Integer> expected = setUpExpectedKeywordsForBpmn();
		System.out.println("KeyWords from Bpmn:");

		assertNotNull(keyWords);
		assertEquals(expected.size(), keyWords.size());

		for (Entry keyWord : keyWords.entrySet()) {
			String key = (String) keyWord.getKey();
			System.out.println("Value: " + keyWord.getValue() + ", Key: " + key);

			assertTrue(expected.containsKey(key));

			if (expected.containsKey(key)) {
				int actualValue = (Integer) keyWord.getValue();
				int expectedValue = expected.get(key);
				assertEquals(expectedValue, actualValue);
			}
		}
	}

	private Map setUpExpectedKeywordsForBpmn() {
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
