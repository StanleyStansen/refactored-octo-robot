package com.discoverer.bpmnInput;

import java.io.IOException;
import java.util.Map;

import org.xml.sax.SAXException;

public interface BpmnStrategy {

	/**
	 * Die Methode liefert die Schlüsselworte eines Bpmn-Modells als Map zurück.
	 * Die Schlüsselworte können mittel Integerwerten gewichtet sein.
	 * 
	 * @param filePath
	 *            - Dateipfad einer Bpmn-xml-Datei, zu welcher passende
	 *            Webservices gefunden werden sollen
	 * @return - Eine Map, welche mit integer-Werten gewichtete Keywords enthält
	 * @throws SAXException
	 * @throws IOException
	 */
	public Map<String, Integer> getKeywords(String filePath) throws SAXException, IOException;
	
	public boolean allTagsRead();

}
