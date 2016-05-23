package com.discoverer.wsdlDiscoverer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.xml.sax.SAXException;

import com.discoverer.bpmnInput.BpmnInput;
import com.discoverer.bpmnInput.BpmnStrategy;
import com.discoverer.bpmnInput.BpmnStrategyType;
import com.discoverer.filtering.*;

/**
 * Ein Rater kann zur Bewertung mehrerer Wsdl-xml-Dateien hinsichtlich einer
 * gegebenen Bpmn-xml-Datei genutzt werden. Eine Instanz dieser Klasse können
 * ein oder mehrere Filter- und eine zu untersuchende BpmnStrategie zugewiesen
 * werden. Mit Hilfe der Filter werden dann die einzelnen Wsdl-xml-Dateien
 * bewertet.
 * 
 * @author igt
 *
 */
public class Rater {

	private List<FilterStrategy> filterStrategies = new LinkedList<FilterStrategy>();
	private BpmnStrategy bpmnInputStrategy;

	public Rater() {
	}

	/**
	 * Die Methode bewertet die übergebenen Urls der Wsdl-xml-Dateien
	 * hinsichtlich einer übergebenen Bpmn-xml-Datei. Dazu werden die dem Rater
	 * übergebenen Filter- und BpmnStrategien genutzt. Sind dem Rater mehrere
	 * FilterStrategien zugewiesen, so wird nacheinander jede der Strategien zur
	 * Bewertung ein und desselben resultSets genutzt.
	 * 
	 * @param wsdls
	 *            - Eine Liste an URLs von Wsdl-Schnittstellen
	 * @param bpmnFilePath
	 *            - Der Dateipfad zur Bpmn-xml-Datei, welche zur Bewertung der
	 *            Wsdls herangezogen werden soll.
	 * @return
	 */
	public Set<WsdlResult> rate(List<String> wsdls, String bpmnFilePath) {

		// get Keywords
		Map<String, Integer> keyWords = null;
		try {
			keyWords = bpmnInputStrategy.getKeywords(bpmnFilePath);
		} catch (SAXException | IOException e1) {
			System.err.println("Bpmn model could not be loaded.");
			e1.printStackTrace();
		}

		// Setup ResultSet
		Set<WsdlResult> resultSet = new TreeSet<WsdlResult>();
		for (String wsdl : wsdls) {
			resultSet.add(new WsdlResult(wsdl));
		}

		// score every wsdl-url in the resultSet using every filter
		if (keyWords != null) {
			for (FilterStrategy filter : filterStrategies) {

				// Prepare Filter for Filtering with the new bpmn keywords
				filter.deleteAllKeyWords();
				filter.addKeyWords(keyWords);

				// score the wsdls in resultSet by using the current filter
				try {
					resultSet = filter.filterWsdls(resultSet);
				} catch (SAXException | IOException e) {
					System.err.println("A wsdl file could not be loaded.");
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}

	/**
	 * Die Methode erlaubt die Zuweisung einer FilterStrategie. Jede der
	 * zugewiesenen FilterStrategien wird zur Bewertung der Wsdl-xml-Dateien
	 * genutzt.
	 * 
	 * @param filterStrategy
	 * @return
	 */
	public Rater addFilterStrategy(FilterType filterStrategy) {
		FilterStrategy strategy = Filter.createFilter(filterStrategy);
		filterStrategies.add(strategy);
		return this;
	}

	/**
	 * Die Methode fügt dem Rater eine neue BpmnStrategie hinzu.
	 * 
	 * @param bpmnStrategyType
	 * @return
	 */
	public Rater setBpmnStrategy(BpmnStrategyType bpmnStrategyType) {
		bpmnInputStrategy = BpmnInput.create(bpmnStrategyType);
		return this;
	}

}
