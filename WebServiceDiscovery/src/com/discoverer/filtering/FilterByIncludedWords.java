package com.discoverer.filtering;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import com.discoverer.wsdlDiscoverer.*;

/**
 * Die Klasse implementiert eine konkrete Filterstrategie. Bei
 * FilterByIncludedWords bewertet der Filter jede Wsdl-xml-Datei mittels
 * gewichteten Schlüsselworten. Es wird dabei geprüft, ob die Wörter in der
 * Wsdl-xml-Datei enthalten sind, oder nicht.
 * 
 * @author igt
 *
 */
public class FilterByIncludedWords extends Filter {

	private Map<String, Integer> keyWords;
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;

	/**
	 * Mit diesem Konstruktor kann ein Filter ohne Schlüsselworte erstellt
	 * werden.
	 */
	public FilterByIncludedWords() {
		this(new HashMap<String, Integer>());
	}

	/**
	 * In diesem Konstruktor wird dem erstellten Filter direkt eine Liste an
	 * Schlüsselwörtern übergeben.
	 * 
	 * @param keyWords
	 */
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
	public Collection<WsdlResult> filterWsdls(Collection<WsdlResult> resultSet) throws SAXException, IOException {

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

	/**
	 * Die Methode zählt und bewertet das Vorkommen aller Schlüsselworte des
	 * Filters in einem konkreten tag des übergebenen Dokumentes. Es werden die
	 * Vorkommen in allen Knoten dieses tags in die Bewertung einbezogen.
	 * 
	 * @param doc
	 *            - Xml-Dokument, welches untersucht werden soll
	 * @param tag
	 *            - Knoten eines spezifischen tags, welche untersucht werden
	 *            sollen
	 * @param attributes
	 *            - Attribute des tags, welche untersucht werden sollen -
	 *            optional
	 * @return - Die Bewertung des Dokumentes hinsichtlich eines spezifischen
	 *         Tags
	 */
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

					// Gehe alle �bergebenen Attribute durch
					for (String attribute : attributes) {
						String attributeValue = ((Element) node).getAttribute(attribute);

						// Falls �bergebenes Attribut im Tag in der Wsdl
						// existiert
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

	/**
	 * Die Methode addiert das Gewicht der gefundenen Schlüsselworte in einer
	 * Zeichenkette
	 * 
	 * @param string
	 *            - Zeichenkette, die auf Schlüsselworte überprüft werden soll
	 * @return - Summe der Gewichte der in der Zeichenkette vorkommenden
	 *         Schlüsselworte. Pro Vorkommen wird das jeweilige Gewicht addiert
	 */
	private int countKeywords(String string) {
		int numberOfHits = 0;
		for (String key : keyWords.keySet()) {
			CharSequence c = key.subSequence(0, key.length());
			if (string.contains(c)) {
				numberOfHits += keyWords.get(key);
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
		this.keyWords.putAll(keyWords); // Operation ist undefiniert, falls
										// keyWords währenddessen verändert wird

	}

}
