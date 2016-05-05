package com.discoverer.wsdlDiscoverer;

import java.util.Comparator;

public class WsdlResult implements Comparable<WsdlResult> {
	
	private String uri;
	private int numberOfHits;
	
	public WsdlResult(String uri) {
		this.uri = uri;
		this.numberOfHits = 0;
	}
	
	public int getNumberOfHits() {
		return numberOfHits;
	}
	
	public void setNumberOfHits(int numberOfHits) {
		this.numberOfHits = numberOfHits;
	}
	
	public String getUri() {
		return uri;
	}

	@Override
	public int compareTo(WsdlResult o) {
		if (o == null) {
			return 0;
		}
		if (o.numberOfHits < this.numberOfHits) {
			return -1;
		} else if (o.numberOfHits > this.numberOfHits) {
			return 1;
		}
		return 0;
	}


}
