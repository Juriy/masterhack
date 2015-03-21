/**
 * 
 */
package com.mastercard.mcwallet.sampleapp.helpers;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;




import com.mastercard.api.common.openapiexception.MCOpenApiRuntimeException;
import com.mastercard.mcwallet.sampleapp.MasterpassData;
import com.mastercard.mcwallet.sdk.MasterPassServiceRuntimeException;

/**
 * @author Brady Georgen - brady.georgen@daugherty.com
 *
 */
public class SampleApplicationHelper {
	/**
	 * Method to indent and format XML strings to be displayed.
	 * 
	 * @param input
	 * @param indent
	 * 
	 * @return Formatted XML string
	 */
	private static String prettyFormat(String input, String indent) {
	    try {
	    	//
	    	if (input == null || input.equals("")) { 
	    		return input;
	    	}
	    	input = input.replace(">  <", "><");
	    	if (input.contains("<html>") ) {
	    		return input;
	    	}
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",indent);
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	    	throw new MasterPassServiceRuntimeException(e);
	    }
	}
	
	public static String prettyFormat(String input) {
	    return prettyFormat(input,"4");
	}
	
	/**
	 * Converts a MerchantTransactions to a String containing all the data in the class in XML format
	 * 
	 * @param merchantTransactions
	 * 
	 * @return Marshaled string containing the data stored in merchantTransactions in an XML format
	 * 
	 * @throws JAXBException
	 */
	public static String printXML(Object xmlClass) {

			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(xmlClass.getClass());
				StringWriter st = new StringWriter();
				jaxbContext.createMarshaller().marshal(xmlClass, st);
				String xml = st.toString();
				return xml;
				
			} catch (JAXBException e) {
				throw new MasterPassServiceRuntimeException(e);
			}
	}
	
	/**
	 * Method to escape any HTML tags for displaying the XML in a web page.
	 * This method is for displaying the data only.
	 * 
	 * @param t
	 * 
	 * @return String with the escaped XML
	 */
	public static String xmlEscapeText(String t) {

		StringBuilder sb = new StringBuilder();
		   for(int i = 0; i < t.length(); i++){
		      char c = t.charAt(i);
		      switch(c){
		      case '<': sb.append("&lt;"); break;
		      case '>': sb.append("&gt;"); break;
		      case '\"': sb.append("&guot;"); break;
		      case '&': sb.append("&amp;"); break;
		      case '\'': sb.append("&apos;"); break;
		      default:
		            sb.append(c);
		      }
		   }
		   return sb.toString();
	}
	/**
	 * Method to format the error messages that will be displayed in the test app.
	 * This method is for displaying only.
	 * 
	 * @param errorMessage
	 * 
	 * @return
	 */
	public static String formatErrorMessage(String errorMessage) {
		if (errorMessage.contains("<Errors>")) {
			return xmlEscapeText(prettyFormat(errorMessage));
		}
		else {
			return errorMessage;
		}
	}
	/**
	 * This method is used only to make the shipping image URL dynamic when changing the app URL and context.
	 * This method is not intented to be used in a production application or environment.
	 * 
	 * @param  shoppingCartRequest
	 * @param  data
	 * 
	 * @return String with the replaced image URLs
	 */
	public static String xmlReplaceImageUrl(String shoppingCartRequest,MasterpassData data) {
		return shoppingCartRequest.replaceAll("http://ech-0a9d8167.corp.mastercard.test:8080/SampleApp",data.getAppBaseUrl() + data.getContextPath() );
	}
	
    
}
