package com.discoverer.filtering;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.discoverer.wsdlDiscoverer.*;

import org.xml.sax.SAXException;

public interface FilterStrategy {
	
	public Set<WsdlResult> filterWsdls(List<String> wsdls)  throws SAXException, IOException;

}
