package com.discoverer.filtering;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import com.discoverer.wsdlDiscoverer.*;

public class FilterByIncludedWords extends Filter {

	private Map<String, Integer> keyWords;
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;

	public FilterByIncludedWords() {
		this(new HashMap<String, Integer>());
	}

	public FilterByIncludedWords(Map<String, Integer> keyWords) {
		this.keyWords = keyWords;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<WsdlResult> filterWsdls(Set<WsdlResult> resultSet) throws SAXException, IOException {
		
		if (resultSet != null) {
			for (WsdlResult r : resultSet) {
				System.out.println("checking url " + r.getUri());
				int numberOfHits = r.getScore();
				Document doc = dBuilder.parse(r.getUri());
				doc.getDocumentElement().normalize();

				// Keywords z�hlen in Wsdl.xml
				numberOfHits = numberOfHits + countKeywordsInDoc(doc, "wsdl:message", "name");
				numberOfHits = numberOfHits + countKeywordsInDoc(doc, "wsdl:documentation");

				r.setScore(numberOfHits);
			}
		}
		return resultSet;

	}

	private int countKeywordsInDoc(Document doc, String tag, String... attributes) {

		int numberOfHits = 0;
		NodeList nodes = doc.getElementsByTagName(tag);

		// F�r alle Knoten mit Tag tag
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);

			// Falls in Attributen gesucht werden soll
			if (attributes.length > 0) {

				// Falls Knoten ein Attribut hat
				if (node.getNodeType() == Node.ELEMENT_NODE) {

					// Gehe alle Attribute durch
					for (String attribute : attributes) {
						String attributeValue = ((Element) node).getAttribute(attribute);

						// Falls Attribut in Tag in Wsdl existiert
						if (attributeValue != null) {

							// Z�hle enthaltener KeyWords
							numberOfHits += countKeywords(attributeValue);
						}
					}
				}
			} // Suche au�erdem immer im TextContent eines Knoten
			if (node.getTextContent() != "") {
				numberOfHits += countKeywords(node.getTextContent());
			}
		}
		return numberOfHits;
	}

	private int countKeywords(String string) {
		int numberOfHits = 0;
		for (String key : keyWords.keySet()) {
			CharSequence c = key.subSequence(0, key.length());
			if (string.contains(c)) {
				numberOfHits++;
			}
		}
		return numberOfHits;
	}
	
	@Override
	public void deleteAllKeyWords() {
		keyWords = new HashMap<String, Integer>();
	}

	@Override
	public void addKeyWords(Map<String, Integer> keyWords) {
		this.keyWords.putAll(keyWords); //Operation ist undefiniert, falls keyWords währenddessen verändert wird
		
	}

}
