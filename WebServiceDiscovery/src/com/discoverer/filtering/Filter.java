package com.discoverer.filtering;

/**
 * Die abstrakte Klasse Filter dient zur Erstellung von konkreten Filtern.
 * 
 * @author igt
 *
 */
public abstract class Filter implements FilterStrategy {

	/**
	 * Diese Methode erzeugt einen konkreten Filter des Types Type.
	 * 
	 * @param type
	 *            - Typ des gewünschten Filter-Objektes
	 * @return - das konkrete Filter-Objekt
	 */
	public static FilterStrategy createFilter(FilterType type) {
		switch (type) {
		case FilterByIncludedWords:
			return new FilterByIncludedWords();
		default:
			System.err.println("The Filter " + type + " does not exist.");
		}
		return null;
	}

	/**
	 * Diese Methode erzeugt einen konkreten Filter des Types Type.
	 * 
	 * @param type
	 *            - Typ des gewünschten Filter-Objektes
	 * @param numberOf
	 *            Results - Anzahl der vom Filter zurückzugebenden Ergebnisse
	 * @return - das konkrete Filter-Objekt
	 */
	public static FilterStrategy createFilter(FilterType type, int numberOfResults) {
		switch (type) {
		case FilterReduceResultSet:
			return new FilterReduceResultSet(numberOfResults);
		default:
			System.err.println("The Filter " + type + " does not exist.");
		}
		return null;
	}

}
