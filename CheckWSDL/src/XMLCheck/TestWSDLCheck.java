package XMLCheck;

import static org.junit.Assert.*;

import org.junit.Test;
import XMLCheck.WSDLValidation.*;

public class TestWSDLCheck extends WSDLValidation{

	@Test
	public void wellformedAndValid() {
		String wsdlPath = "src/WSDL.xml";
		String xsdPath = "http://schemas.xmlsoap.org/wsdl/";
		assertTrue(isWellFormedXMLDocument(wsdlPath));
		assertTrue(isValidateXMLSchema(xsdPath,wsdlPath));
	}
	
	@Test
	public void wellformedButNOTValid() {
		String wsdlPath = "src/WSDL-notValid.xml";
		String xsdPath = "http://schemas.xmlsoap.org/wsdl/";
		assertTrue(isWellFormedXMLDocument(wsdlPath));
		assertFalse(isValidateXMLSchema(xsdPath,wsdlPath));
	}
	
	@Test
	public void NOTwellformedAndNOTValid() {
		String wsdlPath = "src/WSDL-notWellformed-notValid.xml";
		String xsdPath = "http://schemas.xmlsoap.org/wsdl/";
		assertFalse(isWellFormedXMLDocument(wsdlPath));
		assertFalse(isValidateXMLSchema(xsdPath,wsdlPath));
	}
	
}
