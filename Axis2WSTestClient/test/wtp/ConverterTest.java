
/**
 * ConverterTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
package wtp;

/*
 *  ConverterTest Junit test case
*/

public class ConverterTest extends junit.framework.TestCase {

	/**
	 * Auto generated test method
	 */
	public void testfarenheitToCelsius() throws java.lang.Exception {

		wtp.ConverterStub stub = new wtp.ConverterStub();// the default
															// implementation
															// should point to
															// the right
															// endpoint

		wtp.ConverterStub.FarenheitToCelsius farenheitToCelsius8 = (wtp.ConverterStub.FarenheitToCelsius) getTestObject(
				wtp.ConverterStub.FarenheitToCelsius.class);
		farenheitToCelsius8.setFarenheit(212);

		assertNotNull(stub.farenheitToCelsius(farenheitToCelsius8));

	}

	/**
	 * Auto generated test method
	 */
	public void testStartfarenheitToCelsius() throws java.lang.Exception {
		wtp.ConverterStub stub = new wtp.ConverterStub();
		wtp.ConverterStub.FarenheitToCelsius farenheitToCelsius8 = (wtp.ConverterStub.FarenheitToCelsius) getTestObject(
				wtp.ConverterStub.FarenheitToCelsius.class);
		farenheitToCelsius8.setFarenheit(212);

		stub.startfarenheitToCelsius(farenheitToCelsius8, new tempCallbackN65548());

	}

	private class tempCallbackN65548 extends wtp.ConverterCallbackHandler {
		public tempCallbackN65548() {
			super(null);
		}

		public void receiveResultfarenheitToCelsius(wtp.ConverterStub.FarenheitToCelsiusResponse result) {

		}

		public void receiveErrorfarenheitToCelsius(java.lang.Exception e) {
			fail();
		}

	}

	/**
	 * Auto generated test method
	 */
	public void testcelsiusToFarenheit() throws java.lang.Exception {

		wtp.ConverterStub stub = new wtp.ConverterStub();// the default
															// implementation
															// should point to
															// the right
															// endpoint

		wtp.ConverterStub.CelsiusToFarenheit celsiusToFarenheit10 = (wtp.ConverterStub.CelsiusToFarenheit) getTestObject(
				wtp.ConverterStub.CelsiusToFarenheit.class);
		celsiusToFarenheit10.setCelsius(100);

		assertNotNull(stub.celsiusToFarenheit(celsiusToFarenheit10));

	}

	/**
	 * Auto generated test method
	 */
	public void testStartcelsiusToFarenheit() throws java.lang.Exception {
		wtp.ConverterStub stub = new wtp.ConverterStub();
		wtp.ConverterStub.CelsiusToFarenheit celsiusToFarenheit10 = (wtp.ConverterStub.CelsiusToFarenheit) getTestObject(
				wtp.ConverterStub.CelsiusToFarenheit.class);
		celsiusToFarenheit10.setCelsius(100);

		stub.startcelsiusToFarenheit(celsiusToFarenheit10, new tempCallbackN65589());

	}

	private class tempCallbackN65589 extends wtp.ConverterCallbackHandler {
		public tempCallbackN65589() {
			super(null);
		}

		public void receiveResultcelsiusToFarenheit(wtp.ConverterStub.CelsiusToFarenheitResponse result) {

		}

		public void receiveErrorcelsiusToFarenheit(java.lang.Exception e) {
			fail();
		}

	}

	// Create an ADBBean and provide it as the test object
	public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception {
		return (org.apache.axis2.databinding.ADBBean) type.newInstance();
	}

}
