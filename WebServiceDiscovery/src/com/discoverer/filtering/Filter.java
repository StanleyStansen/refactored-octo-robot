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

}
