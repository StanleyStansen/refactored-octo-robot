import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.text.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class WSDLValidation {
	
	public static void main(String[] args) {
		// XML Richtig beides
		System.out.println("Test 1: Wohlgeformt und Valide");
		System.out.println("Prüfung auf Wohlgeformtheit: " + isWellFormedXMLDocument("src/WSDL.xml"));
		System.out.println("Prüfung auf Validität: " + isValidateXMLSchema("http://schemas.xmlsoap.org/wsdl/", "src/WSDL.xml"));
		System.out.println("---------------------------------");
		// Wohlgeformt, aber nicht valide
		System.out.println("Test 2: Wohlgeformt und NICHT Valide");
		System.out.println("Prüfung auf Wohlgeformtheit: " + isWellFormedXMLDocument("src/WSDL-notValid.xml"));
		System.out.println("Prüfung auf Validität: " + isValidateXMLSchema("http://schemas.xmlsoap.org/wsdl/", "src/WSDL-notValid.xml"));
		System.out.println("---------------------------------");
		// Nicht Wohlgeformed, nicht valide
		System.out.println("Test 3: NICHT Wohlgeformt und NICHT Valide");
		System.out.println("Prüfung auf Wohlgeformtheit: " + isWellFormedXMLDocument("src/WSDL-notWellformed-notValid.xml"));
		System.out.println("Prüfung auf Validität: " + isValidateXMLSchema("http://schemas.xmlsoap.org/wsdl/", "src/WSDL-notWellformed-notValid.xml"));
		System.out.println("---------------------------------");
	}
	
	
	/* http://www.edankert.com/validate.html */
	 public static boolean isWellFormedXMLDocument(String xmlPath){
		 /* Ein XML-Dokument heißt wohlgeformt, wenn
		  * es einen Prolog mit der XML-Version enthält
		  * es genau ein Wurzelelement (root element) hat
		  * jedem öffnenden Tag ein schließendes gegenüber steht (können zusammengefasst sein)
		  * alle Elemente korrekt verschachtelt sind, d.h. sich nicht überlappen
		  * kein Element zwei Attribute mit dem selben Namen hat
		  * alle angegebenen Attribute einen Wert haben
		  * alle Attributwerte in 'einfachen' oder "doppelten" Anführungszeichen stehen
		  * reservierte Zeichen als Entitäten (Entities) geschrieben sind
		  * Umlaute direkt nutzbar, sofern entsprechende Zeichensatzcodierung gewählt (z.B. UTF-8)
		  * KURZ: Wenn die Basis-Regeln der XML-Recommendation eingehalten werden.
		  */
       try {
    	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	   factory.setValidating(false);
    	   factory.setNamespaceAware(true);
    	   
    	   DocumentBuilder builder = factory.newDocumentBuilder();
    	   
    	   // the "parse" method also validates XML, will throw an exception if misformatted
    	   builder.parse(new InputSource(xmlPath));
    	   
       } catch (SAXException | ParserConfigurationException | IOException e) {
           System.out.println("Exception: "+e.getMessage());
           return false;
       }
       return true;
	 }
	 
	 /* http://www.journaldev.com/895/how-to-validate-xml-against-xsd-in-java */
	 public static boolean isValidateXMLSchema(String xsdPath, String xmlPath){

        try {
        	/* Java unterstützt die Schema-Validierung, die jedoch standardmäßig ausgeschaltet ist. 
        	 * Ein Grund ist, dass die Validierung die Rechenzeit und den Speicherbedarf erhöht. 
        	 * Um die Validierung zu aktivieren, muss zunächst ein Schema-Objekt auf Grundlge 
        	 * des XML-Schemas aufgebaut werden.
        	 */
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new URL(xsdPath));
            // Schema-Objekt bietet die Methode newValidator(), die einen Validator liefert
            Validator validator = schema.newValidator();
            // Validierung gegen die XML-Datei
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}
