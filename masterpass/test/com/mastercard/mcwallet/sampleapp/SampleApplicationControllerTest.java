/**
 * 
 */
package com.mastercard.mcwallet.sampleapp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mastercard.mcwallet.sdk.AccessTokenResponse;
import com.mastercard.mcwallet.sdk.RequestTokenResponse;
import com.mastercard.mcwallet.sdk.xml.allservices.ShoppingCartRequest;


/**
 * @author Brady Georgen - brady.georgen@daugherty.com
 *
 */
public class SampleApplicationControllerTest {

	MasterpassController controller;
	MasterpassData data;
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
		data = new MasterpassData();
		data.setAuthLevelBasic(false);
		data.setAcceptedCards("master,amex,diners,discover,maestro,visa");
		data.setCallbackUrl("http://projectabc.com/OauthCallbackServlet");
		data.setCheckoutIdentifier("a4a6x1ywxlkxzhensyvad1hepuouaesuv");
		data.setCheckoutOutResponse("");
		data.setCheckoutResourceURL("");
		data.setCheckoutXML("");
		data.setContextPath("");
		data.setPostTransactionReceivedXml("");
		data.setPostTransactionSentXml("");
		data.setRedirectShippingProfiles("US,CA,FR,MEX,NA,UK");
		data.setRequestToken("");
		data.setRewards(false);
		data.setShipping(895);
		data.setShippingSuppression(false);
		data.setTax(348);
		data.setSignatureBaseString("");
		data.setVerifier("");
		data.setXmlVersion("v5");
		data.setCallbackDomain("http://projectabc.com");
		
		controller = new MasterpassController(new MasterpassData());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSampleApplicationController() {
		assertTrue("test SampleApplicationController constructor", controller instanceof MasterpassController);
	}
	
	@Test
	public void testGetRequestTokenAndRedirectUrl() throws Exception {
		MasterpassData returnData = controller.getRequestToken(data);
		assertTrue("request token response is in return data and is RequestTokenResponse", returnData.getRequestTokenResponse() instanceof RequestTokenResponse);
	}
	
	@Test
	public void testPostShoppingCart() throws Exception {
		controller.getRequestToken(data);
		MasterpassData returnData = controller.postShoppingCart(data);
		assertTrue("shopping cart data should be SampleApplicationData class", returnData instanceof MasterpassData);
	}
	
	@Test
	public void testGetAccessToken() throws Exception {
		assertNull(data.getAccessTokenResponse());
		MasterpassData response = controller.getAccessToken(data);
		assertTrue("test that the response has an access token", response.getAccessTokenResponse() instanceof AccessTokenResponse);
	}
	
	public void testParseShoppingCartFile() throws Exception{
		controller.getRequestToken(data);
		ShoppingCartRequest request = controller.parseShoppingCartFile(data.getRequestToken(), data.getCallbackDomain());
		assertTrue("request should be correct type and have requestToken", request instanceof ShoppingCartRequest && request.getOAuthToken().equals(data.getRequestToken()) && request.getOriginUrl().equals(data.getCallbackDomain()));
	}
	
	

}
