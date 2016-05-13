package com.discoverer.bpmnInput;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BpmnNamesOfAllTags extends BpmnInput {
	
	String[] fillerWords = {"although", "and", "as", "because", "but", "either", "even", "if", "though", "how", "as", "in", "or", "since", "for", "when", "well", "so", "Dr.", "of", "from", "for", ""};
	Map<String, Integer> map;
	
	@Override
	public Map<String, Integer> getKeywords(String filePath) throws SAXException, IOException {
		map = new HashMap<String, Integer>();
		
		Document doc = getBpmnDocument(filePath);
		setKeyWordsFromDoc(doc, "userTask", 10, "name");
		setKeyWordsFromDoc(doc, "sendTask", 10, "name");
		setKeyWordsFromDoc(doc, "text", 10);
		setKeyWordsFromDoc(doc, "messageFlow", 8, "name");
		setKeyWordsFromDoc(doc, "participant", 4, "name");
		setKeyWordsFromDoc(doc, "lane", 4, "name");
		setKeyWordsFromDoc(doc, "startEvent", 2, "name");
		setKeyWordsFromDoc(doc, "endEvent", 2, "name");
		setKeyWordsFromDoc(doc, "intermediateCatchEvent", 2, "name");
		setKeyWordsFromDoc(doc, "manualTask", 2, "name");
		setKeyWordsFromDoc(doc, "boundaryEvent", 2, "name");
		setKeyWordsFromDoc(doc, "exclusiveGateway", 2, "name");
		setKeyWordsFromDoc(doc, "subProcess", 2, "name");
		
		for (String s : map.keySet()) {
			System.out.println(s + ": " + map.get(s));
		}
		
		return map;
	}
	
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
	
	private void setKeyWordsFromString(String value, int score) {
		List<String> result = new LinkedList<String>();
		String[] words = value.split(" ");
		for (String word : words) {
			for (String s : word.split("\n")) {
				if (!isFillerWords(s)) {
					map.put(s, score);
				}
			}
		}
	}
	
	private boolean isFillerWords(String s) {
		for (String word : fillerWords) {
			if (word.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	@Override
	public Map<String, Integer> getKeywords(String filePath) {
		Map<String, Integer> keyWords = new HashMap<String, Integer>();
		keyWords.put("Stock", 4);
		return keyWords;
	}*/
	
}
