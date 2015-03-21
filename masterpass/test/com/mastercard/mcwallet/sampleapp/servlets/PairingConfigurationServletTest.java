/**
 * 
 */
package com.mastercard.mcwallet.sampleapp.servlets;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.mastercard.mcwallet.sampleapp.MasterpassData;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

/**
 * @author Brady Georgen - brady.georgen@daugherty.com
 *
 */
public class PairingConfigurationServletTest {

	
	protected ServletRunner servletRunner;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	   if (servletRunner == null)
	    {
	     servletRunner = new ServletRunner(new FileInputStream("WebContent/WEB-INF/web.xml"), null);
	    }
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		servletRunner.shutDown();
	}


	/**
	 * Test method for {@link com.mastercard.mcwallet.sampleapp.servlets.PairingConfigurationServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 * @throws SAXException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() throws IOException, SAXException, ServletException {
		InputStream body = new FileInputStream("resources/test/sampleApplicationDataJSON.txt");
		StringBuilder builder = new StringBuilder();
		int ch;
		while((ch = body.read()) != -1){
			builder.append((char)ch);
		}
		System.out.print(builder.toString());
		String dataString = builder.toString();
		ServletUnitClient sc = servletRunner.newClient();
		WebRequest request = new PostMethodWebRequest("http://projectabc.com/pairingConfig");
		
		request.setParameter("data", dataString);
		WebResponse response = sc.getResponse(request);
		
		InvocationContext ic = sc.newInvocation(request);
		PairingConfigurationServlet servlet = (PairingConfigurationServlet) ic.getServlet();
		WebResponse servletResponse = ic.getServletResponse();
		HttpSession session = ic.getRequest().getSession();
		
		assertNotNull(session.getAttribute("data"));
		assertTrue("test SampleApplicationData is correct class", session.getAttribute("data") instanceof MasterpassData);
		
		
	}

}
