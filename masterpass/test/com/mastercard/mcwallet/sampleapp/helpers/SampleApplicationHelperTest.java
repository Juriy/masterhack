/**
 * 
 */
package com.mastercard.mcwallet.sampleapp.helpers;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mastercard.mcwallet.sdk.xml.allservices.MerchantTransactions;



/**
 * @author Brady Georgen - brady.georgen@daugherty.com
 *
 */
public class SampleApplicationHelperTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPrettyFormat() {
		String testXML = "<helloWorld><foo>bar</foo></helloWorld>";
		
		String expected = "<helloWorld>\r\n    <foo>bar</foo>\r\n</helloWorld>\r\n";
		String[] testPretty = SampleApplicationHelper.prettyFormat(testXML).split("\r");
		String[] testExpected = expected.split("\r");
		assertTrue("PrettyFormat should return and indent xml string", Arrays.deepEquals(testPretty, testExpected));
		
	}
	
	@Test
	public void testPrintXML() throws JAXBException{
		String xml = "<?xml version='1.0' encoding='UTF-8'?><MerchantTransactions><MerchantTransactions><TransactionId>2007408</TransactionId><ConsumerKey>cLb0tKkEJhGTITp_6ltDIibO5Wgbx4rIldeXM_jRd4b0476c!414f4859446c4a366c726a327474695545332b353049303d</ConsumerKey><Currency>USD</Currency><OrderAmount>76239</OrderAmount><PurchaseDate>2014-05-07T16:40:13.847-05:00</PurchaseDate><TransactionStatus>Success</TransactionStatus><ApprovalCode>sample</ApprovalCode></MerchantTransactions></MerchantTransactions>";
		StreamSource stream = new StreamSource(new StringReader(xml));
		JAXBContext jaxb = JAXBContext.newInstance(MerchantTransactions.class);
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();
		JAXBElement<MerchantTransactions> je = unmarshaller.unmarshal(stream, MerchantTransactions.class);
		MerchantTransactions merchantTransactions = (MerchantTransactions) je.getValue();
		
		String expected = "<MerchantTransactions><MerchantTransactions><TransactionId>2007408</TransactionId><ConsumerKey>cLb0tKkEJhGTITp_6ltDIibO5Wgbx4rIldeXM_jRd4b0476c!414f4859446c4a366c726a327474695545332b353049303d</ConsumerKey><Currency>USD</Currency><OrderAmount>76239</OrderAmount><PurchaseDate>2014-05-07T16:40:13.847-05:00</PurchaseDate><TransactionStatus>Success</TransactionStatus><ApprovalCode>sample</ApprovalCode></MerchantTransactions></MerchantTransactions>";
		String printedXML = SampleApplicationHelper.printXML(merchantTransactions);
		assertTrue("printed xml should contain correct merchant transaction data", printedXML.contains(expected));
	}
	
	@Test
	public void testXmlEscapeText(){
		String xml = "<?xml version='1.0' encoding='UTF-8'?><MerchantTransactions><MerchantTransactions><TransactionId>2007408</TransactionId><ConsumerKey>cLb0tKkEJhGTITp_6ltDIibO5Wgbx4rIldeXM_jRd4b0476c!414f4859446c4a366c726a327474695545332b353049303d</ConsumerKey><Currency>USD</Currency><OrderAmount>76239</OrderAmount><PurchaseDate>2014-05-07T16:40:13.847-05:00</PurchaseDate><TransactionStatus>Success</TransactionStatus><ApprovalCode>sample</ApprovalCode></MerchantTransactions></MerchantTransactions>";
		String expected = "&lt;?xml version=&apos;1.0&apos; encoding=&apos;UTF-8&apos;?&gt;&lt;MerchantTransactions&gt;&lt;MerchantTransactions&gt;&lt;TransactionId&gt;2007408&lt;/TransactionId&gt;&lt;ConsumerKey&gt;cLb0tKkEJhGTITp_6ltDIibO5Wgbx4rIldeXM_jRd4b0476c!414f4859446c4a366c726a327474695545332b353049303d&lt;/ConsumerKey&gt;&lt;Currency&gt;USD&lt;/Currency&gt;&lt;OrderAmount&gt;76239&lt;/OrderAmount&gt;&lt;PurchaseDate&gt;2014-05-07T16:40:13.847-05:00&lt;/PurchaseDate&gt;&lt;TransactionStatus&gt;Success&lt;/TransactionStatus&gt;&lt;ApprovalCode&gt;sample&lt;/ApprovalCode&gt;&lt;/MerchantTransactions&gt;&lt;/MerchantTransactions&gt;";
		assertEquals("xmlEscapeText should convert xml for HTML rendering", expected, SampleApplicationHelper.xmlEscapeText(xml));
	}
	
	@Test
	public void testFormatErrorMessage(){
		String errorMessage = "<Errors><Error><Message>Error is bad</Message></Error></Errors>";
		String expected = "&lt;Errors&gt;\r\n    &lt;Error&gt;\r\n        &lt;Message&gt;Error is bad&lt;/Message&gt;\r\n    &lt;/Error&gt;\r\n&lt;/Errors&gt;\r\n";
		assertEquals("error formatting should return expected html escaped string", SampleApplicationHelper.formatErrorMessage(errorMessage),expected);
		
	}
}
