package com.discoverer.filtering;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.discoverer.wsdlDiscoverer.*;

import org.xml.sax.SAXException;

public interface FilterStrategy {

	/**
	 * Die Methode bewertet einzelne Wsdl-xml-Dateien. Jede im übergebenen
	 * resultSet enthaltene URL einer Wsdl-xml-Datei wird bewertet, bzw. deren
	 * Bewertung geändert.
	 * 
	 * @param resultSet
	 *            - das resultSet, welches die Urls zu den Wsdl-xml-Dateien in
	 *            Form von WsdlResult-Objekten hält
	 * @return - Das eingangs übergebene resultSet, welches nach Ausführung der
	 *         Methode die Bewertung der Wsdl-Urls enthält
	 * @throws SAXException
	 * @throws IOException
	 */
	public Set<WsdlResult> filterWsdls(Set<WsdlResult> resultSet) throws SAXException, IOException;

	/**
	 * Die Methode erlaubt das Hinzufügen von Schlüsselworten zu einem Filter
	 * 
	 * @param keyWords
	 */
	public void addKeyWords(Map<String, Integer> keyWords);

	/**
	 * Die Methode löscht alle Schlüsselworte eines Filters.
	 */
	public void deleteAllKeyWords();

}
