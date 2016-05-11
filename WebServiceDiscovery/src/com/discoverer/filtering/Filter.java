package com.discoverer.filtering;

import java.util.List;

public abstract class Filter implements FilterStrategy {
	
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
