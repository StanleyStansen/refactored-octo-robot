package com.discoverer.wsdlDiscoverer;

public class WsdlResult implements Comparable<WsdlResult> {
	
	private String uri;
	private int score;
	
	public WsdlResult(String uri) {
		this.uri = uri;
		this.score = 0;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getUri() {
		return uri;
	}

	@Override
	public int compareTo(WsdlResult o) {
		if (o.score < this.score) {
			return -1;
		} else if (o.score > this.score) {
			return 1;
		}
		return o.uri.compareTo(this.uri);	
	}
	
	@Override
	public String toString() {
		return "Score: " + score + ", URI: " + uri;
	}


}
