package com.discoverer.bpmnInput;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Die Klasse BpmnInput ist eine abtrakte Implementierung des Interfaces
 * BpmnStrategy. Die Klasse implementiert Methoden, welche alle Strategien zum
 * Laden der Schlüsselworte aus einem Bpmn-XML gemein haben.
 * 
 * @author igt
 *
 */
public abstract class BpmnInput implements BpmnStrategy {

	private DocumentBuilder dBuilder;

	public BpmnInput() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Die Methode instanziiert eine konkrete BpmnStrategie zum Laden von
	 * Schlüsselworten aus einem Bpmn-xml-Datei.
	 * 
	 * @param type
	 *            - Typ der konkreten Strategie.
	 * @return - die konkrete BpmnStrategie zugehörig zum angegebenen Typ
	 */
	public static BpmnStrategy create(BpmnStrategyType type) {
		switch (type) {
		case BpmnNamesOfAllTags:
			return new BpmnNamesOfAllTags();
		}
		return null;
	}

	/**
	 * Die Methode gibt das XMLDokument zu dem angegebenen Dateipfad zurück
	 * 
	 * @param filePath
	 *            - Dateipfad einer Bpmn-xml-Datei
	 * @return - Das Java-Dokument Objekt der geladenen Bpmn-xml-Datei
	 * @throws SAXException
	 * @throws IOException
	 */
	protected Document getBpmnDocument(String filePath) throws SAXException, IOException {

		Document doc = dBuilder.parse(new File(filePath));
		doc.getDocumentElement().normalize();
		return doc;
	}
}
