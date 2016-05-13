package com.discoverer.bpmnInput;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
	
	public static BpmnStrategy create(BpmnStrategyType type) {
		switch (type) {
		case BpmnNamesOfAllTags:
			return new BpmnNamesOfAllTags();
		}
		return null;
	}
	
	protected Document getBpmnDocument(String filePath) throws SAXException, IOException { 
	
		Document doc = dBuilder.parse(new File(filePath));
		doc.getDocumentElement().normalize();
		return doc;
	}
}
