package com.discoverer.bpmnInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BpmnNamesOfAllTags extends BpmnInput {

	String[] fillerWords = { "although", "and", "as", "because", "but", "either", "even", "if", "though", "how", "as",
			"in", "or", "since", "for", "when", "well", "so", "Dr.", "of", "from", "for", "" };
	Map<String, Integer> map;

	@Override
	public Map<String, Integer> getKeywords(String filePath) throws SAXException, IOException {
		map = new HashMap<String, Integer>();

		Document doc = getBpmnDocument(filePath);
		setKeyWordsFromDoc(doc, "userTask", 10, "name");
		setKeyWordsFromDoc(doc, "sendTask", 10, "name");
		setKeyWordsFromDoc(doc, "text", 10);
		setKeyWordsFromDoc(doc, "messageFlow", 8, "name");
		setKeyWordsFromDoc(doc, "participant", 10, "name");
		setKeyWordsFromDoc(doc, "lane", 4, "name");
		setKeyWordsFromDoc(doc, "startEvent", 2, "name");
		setKeyWordsFromDoc(doc, "endEvent", 2, "name");
		setKeyWordsFromDoc(doc, "intermediateCatchEvent", 2, "name");
		setKeyWordsFromDoc(doc, "manualTask", 2, "name");
		setKeyWordsFromDoc(doc, "boundaryEvent", 2, "name");
		setKeyWordsFromDoc(doc, "exclusiveGateway", 2, "name");
		setKeyWordsFromDoc(doc, "subProcess", 2, "name");

		return map;
	}

	/**
	 * Die Methode extrahiert die einzelnen Schlüsselworte aus einem
	 * spezifischen tag einer Bpmn-xml-Datei. Dabei werden alle Knoten im
	 * Dokument untersucht, welche von diesem spezifischen, übergebenen Typs
	 * (tag) sind. Es wird immer der Inhalt eines Knotens als Schlüsselworte
	 * extrahiert, wie bspw. <tag>Inhalt</tag>. Darüber hinaus können zusätzlich
	 * auch einzelne Attribute des Knotes untersucht werden.
	 * 
	 * @param doc
	 *            - Dokument, welches das zu untersuchende Bpmn-xml enthält
	 * @param tag
	 *            - tag, welcher untersucht werden soll
	 * @param score
	 *            - Die Gewichtung des tags, sodass den Schlüsselworten diese
	 *            Gewichtung zugewiesen werden kann
	 * @param attributes
	 *            - Attribute des tags, welche untersucht werden sollen.
	 *            optional
	 */
	private void setKeyWordsFromDoc(Document doc, String tag, int score, String... attributes) {
		List<String> keyWords = new LinkedList<String>();

		NodeList nodes = doc.getElementsByTagName(tag);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				// Gehe alle Attribute durch
				if (attributes != null) {
					for (String attribute : attributes) {
						String attributeValue = ((Element) node).getAttribute(attribute);

						// Falls Attribut in Tag in Wsdl existiert
						if (attributeValue != null) {
							setKeyWordsFromString(attributeValue, score);
						}
					}
				} else {
					if (node.getTextContent() != "") {
						setKeyWordsFromString(node.getTextContent(), score);
					}
				}
			}

		}
	}

	/**
	 * Die Methode extrahiert aus einem gegebenen String einzelne Wörter.
	 * Konkret unterteilt die Methode den übergebenen String in Substrings bei
	 * Leerzeichen (Spaces). Es werden nur solche Substrings als Wörter gezählt,
	 * welche mindestens einen Buchstaben [a-z] bzw. [A-Z] enthalten.
	 * 
	 * @param value
	 *            - String, aus welchem Wörter extrahiert werden sollen
	 * @param score
	 *            - Gewichtung, welche diese Wörter erhalten sollen
	 */
	private void setKeyWordsFromString(String value, int score) {
		String[] words = value.split(" ");
		for (String word : words) {
			for (String s : word.split("\n")) {
				if (!isFillerWords(s) && isNotANumber(s)) {
					if (map.containsKey(s)) {
						if (map.get(s) < score) {
							map.put(s, score);
						}
					} else {
						map.put(s, score);
					}
				}
			}
		}
	}

	/**
	 * Die Methode prüft, ob der übergebene String eines der Füllwörter ist,
	 * welche in der Klasse definiert sind
	 * 
	 * @param s
	 *            - zu untersuchender String
	 * @return - true, wenn s in der definierten Liste der Füllwörter gefunden
	 *         wurde, sonst false
	 */
	private boolean isFillerWords(String s) {
		for (String word : fillerWords) {
			if (word.equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Die Methode prüft, of ein String s keine Zahl ist.
	 * 
	 * @param s
	 *            - zu prüfender String
	 * @return - true genau dann, wenn s mindestens einen Character [a-z], [A-Z]
	 *         enthält.
	 */
	private boolean isNotANumber(String s) {
		return (s.matches("\\D+") ? true : false);
	}

}
