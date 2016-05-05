package com.discoverer.filtering;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
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

public class FilterByIncludedWords implements FilterStrategy {

	private List<String> keyWords;
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;

	public FilterByIncludedWords() {
		this(new LinkedList<String>());
	}

	public FilterByIncludedWords(List<String> filterStrings) {
		this.keyWords = filterStrings;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<WsdlResult> filterWsdls(List<String> uris) throws SAXException, IOException {
		Set<WsdlResult> resultSet = new TreeSet<WsdlResult>();

		if (uris != null) {
			for (String uri : uris) {
				// Hier überprüfen, ob valide WSDL.xml !!!

				WsdlResult r = new WsdlResult(uri);
				int numberOfHits = 0;
				Document doc = dBuilder.parse(uri);
				doc.getDocumentElement().normalize();

				// Keywords zählen in Wsdl.xml
				numberOfHits += countKeywordsInDoc(doc, "wsdl:message", "name");
				numberOfHits += countKeywordsInDoc(doc, "wsdl:documentation");

				r.setNumberOfHits(numberOfHits);
				resultSet.add(r);
			}
		}
		return resultSet;

	}

	private int countKeywordsInDoc(Document doc, String tag, String... attributes) {

		int numberOfHits = 0;
		NodeList nodes = doc.getElementsByTagName(tag);

		// Für alle Knoten mit Tag tag
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

							// Zähle enthaltener KeyWords
							numberOfHits += countKeywords(attributeValue);
						}
					}
				}
			} // Suche außerdem immer im TextContent eines Knoten
			if (node.getTextContent() != "") {
				numberOfHits += countKeywords(node.getTextContent());
			}
		}
		return numberOfHits;
	}

	private int countKeywords(String string) {
		int numberOfHits = 0;
		for (String key : keyWords) {
			CharSequence c = key.subSequence(0, key.length());
			if (string.contains(c)) {
				numberOfHits++;
			}
		}
		return numberOfHits;
	}

	public List<String> getFilterWords() {
		return keyWords;
	}

	public void addFilterString(String string) {
		keyWords.add(string);
	}

	public void addFilterString(List<String> strings) {
		keyWords.addAll(strings);
	}

	public void addFilterWords(String words) {
		String[] singleWords = words.split(" ");
		for (String word : singleWords) {
			keyWords.add(word);
		}
	}

}
