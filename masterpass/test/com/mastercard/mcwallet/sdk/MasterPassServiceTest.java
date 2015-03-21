/**
 * 
 */
package com.mastercard.mcwallet.sdk;
import java.io.FileInputStream;
import java.io.StringReader;
import java.security.KeyStore;
import java.security.PrivateKey;

import static org.junit.Assert.*;

import java.util.Map;

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

import com.mastercard.api.common.Connector;
import com.mastercard.api.common.OAuthParameters;
import com.mastercard.mcwallet.sdk.xml.allservices.Card;
import com.mastercard.mcwallet.sdk.xml.allservices.Checkout;
import com.mastercard.mcwallet.sdk.xml.allservices.Contact;
import com.mastercard.mcwallet.sdk.xml.allservices.MerchantTransaction;
import com.mastercard.mcwallet.sdk.xml.allservices.MerchantTransactions;
import com.mastercard.mcwallet.sdk.xml.allservices.ShippingAddress;
import com.mastercard.mcwallet.sdk.xml.allservices.ShoppingCartRequest;
import com.mastercard.mcwallet.sdk.xml.allservices.ShoppingCartResponse;
import com.mastercard.mcwallet.sdk.xml.allservices.TransactionStatus;



/**
 * @author Brady Georgen - brady.georgen@daugherty.com
 *
 */
public class MasterPassServiceTest {

	MockMasterPassService mock = null;
	
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
		PrivateKey sPrivateKey; 
		KeyStore ks = KeyStore.getInstance("PKCS12");
		java.io.FileInputStream fis = null;
		try {
			fis = new FileInputStream("keys/pkcs12.pfx");
			ks.load(fis, null);
		 }
		catch(Exception e){
			e.printStackTrace();
		}finally {
			 if (fis != null){fis.close();}
		 }
	    sPrivateKey = (PrivateKey) ks.getKey("testprivatekey", "test".toCharArray());
		mock = new MockMasterPassService("consumerKey", sPrivateKey, "http://projectabc.com");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void testMasterPassConnector() {
		assertTrue("MockMasterPassService is MasterPassService", mock instanceof MasterPassService);
		assertTrue("MasterPassService is Connector", mock instanceof Connector);
	}
	/*
	 * This tests that a properly formatted RequestTokenResponse
	 */
	@Test
	public void testGetRequestTokenAndRedirectUrl() {
		String requestUrl = "GetRequestToken_1";
        String callbackUrl = "url456";
        String acceptableCards = "mycard,yourcard,hiscard";
        String checkoutProjectId = "project123";
        String xmlVersion = "v5";
        Boolean shippingSuppression = true;
        Boolean rewards = true;
        Boolean authLevelBasic = true;
        String shippingLocationProfile = "profile123";
        //String walletSelector = "true";
        
        RequestTokenResponse response = mock.getRequestTokenAndRedirectUrl(requestUrl, callbackUrl, acceptableCards, checkoutProjectId, xmlVersion, shippingSuppression, authLevelBasic, rewards, shippingLocationProfile);
        String expected = "https://sandbox.masterpass.com/Checkout/Authorize?oauth_token=c70867d41ea59966f78d486a9afdba49&acceptable_cards=mycard,yourcard,hiscard&checkout_identifier=project123&version=v5&suppress_shipping_address=true&auth_level=basic&accept_reward_program=true";
        // No longer utilizing redirect url - will need to find another testing strategy for this method
        //assertEquals("Redirect url should match expected string", response.getRedirectUrl(), expected);
	}
	/*
	 * This tests the implementation of the postShoppingCartData method by marshaling an xml string into a
	 * corresponding ShoppingCartRequest object and assuring that it returns a marshaled ShoppingCartResponse
	 * object 
	 */
	@Test
	public void testPostShoppingCartData() throws JAXBException {
		String shoppingCartUrl = "PostShoppingCartData_1";
		String xml = "<?xml version='1.0' encoding='UTF-8'?><ShoppingCartRequest><OAuthToken>c13b8355f5f067233b7d04b548ac6503</OAuthToken><ShoppingCart><CurrencyCode>USD</CurrencyCode><Subtotal>29999</Subtotal><ShoppingCartItem><Description>XBøx 360©</Description><Quantity>1</Quantity><Value>29999</Value><ImageURL>http://projectabc.com/images/xbox.jpg</ImageURL></ShoppingCartItem></ShoppingCart></ShoppingCartRequest>";
		StreamSource stream = new StreamSource(new StringReader(xml));
		JAXBContext jaxb = JAXBContext.newInstance(ShoppingCartRequest.class);
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();
		JAXBElement<ShoppingCartRequest> je = unmarshaller.unmarshal(stream, ShoppingCartRequest.class);
		ShoppingCartRequest shoppingCartRequest = (ShoppingCartRequest) je.getValue();
		ShoppingCartResponse response = mock.postShoppingCartData(shoppingCartUrl, shoppingCartRequest);
		assertTrue("Response should have an OAuthToken", response.getOAuthToken().length() > 0);
		
	}
	/*
     * This tests the GetAccessToken method, which is expected to do these things:
     * 1) Call doRequest, passing along the three parameters provided
     * 2) Parse the response into an array and return it
     * The doRequest method is overridden in MasterPassServiceMock, to verify it is called with the right parameters
     */
	@Test
	public void testGetAccessToken() {
		String testAccessUrl = "getAccessTokenResponse_1";
		String testRequestToken = "4d5e6f";
		String testVerifier = "7g8h9i";
		
		AccessTokenResponse accessTokenResponse = mock.getAccessToken(testAccessUrl, testRequestToken, testVerifier);
		assertEquals("Access Token should be mockGetAccessToken", accessTokenResponse.getOauthToken(), "mockGetAccessToken");
		assertEquals("Access Token Secret should be mockGetAccessTokenSecret", accessTokenResponse.getOauthTokenSecret(), "mockGetAccessTokenSecret");
	}
    /*
    * This tests getPaymentShippingResource, which is another simple method.  It takes the token and passes it
    * to doRequest.  DoRequest is mocked, and simply returns the token back out to verify it was called.
    */    
	@Test
	public void testGetPaymentShippingResource() {
		String checkoutResourceUrl = "GetPaymentShippingResource_1";
		String accessToken = "token123";
		
		Checkout checkout = mock.getPaymentShippingResource(checkoutResourceUrl, accessToken);
		assertTrue("Checkout should have a card, transactionID, contact, shipping address, and paypass wallet indicator", checkout.getCard() instanceof Card && checkout.getTransactionId() != null && checkout.getShippingAddress() instanceof ShippingAddress && checkout.getContact() instanceof Contact);
	}
	/*
     * This tests postCheckoutTransaction, which submits the receipt transaction list.  The method
     * accepts a postback url, and an xml representation of merchant transactions. It is expected to return
     * a serialized MerchantTransactions object which contains subsequent MerchantTransaction objects
     */
	@Test
	public void testPostCheckoutTransaction() throws JAXBException {
		String postBackUrl = "PostCheckoutTransaction_1";
		
		String xml = "<?xml version='1.0' encoding='UTF-8'?><MerchantTransactions><MerchantTransactions><TransactionId>2007408</TransactionId><ConsumerKey>cLb0tKkEJhGTITp_6ltDIibO5Wgbx4rIldeXM_jRd4b0476c!414f4859446c4a366c726a327474695545332b353049303d</ConsumerKey><Currency>USD</Currency><OrderAmount>76239</OrderAmount><PurchaseDate>2014-05-07T16:40:13.847-05:00</PurchaseDate><TransactionStatus>Success</TransactionStatus><ApprovalCode>sample</ApprovalCode></MerchantTransactions></MerchantTransactions>";
		StreamSource stream = new StreamSource(new StringReader(xml));
		JAXBContext jaxb = JAXBContext.newInstance(MerchantTransactions.class);
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();
		JAXBElement<MerchantTransactions> je = unmarshaller.unmarshal(stream, MerchantTransactions.class);
		MerchantTransactions merchantTransactionsRequest = (MerchantTransactions) je.getValue();
		
		MerchantTransactions merchantTransactions = mock.postCheckoutTransaction(postBackUrl, merchantTransactionsRequest);
		System.out.println(merchantTransactions.getMerchantTransactions());
		assertTrue("MerchantTransactions contains one or more items", merchantTransactions.getMerchantTransactions().size() > 0);
		assertTrue("MerchantTransactions contains a MerchantTransaction", merchantTransactions.getMerchantTransactions().get(0) instanceof MerchantTransaction);
		MerchantTransaction transaction = merchantTransactions.getMerchantTransactions().get(0);
		assertTrue("Transaction has correct order amount", transaction.getOrderAmount() == 76239);
		assertTrue("Transaction status is a TransactionStatus object", transaction.getTransactionStatus() instanceof TransactionStatus);
		assertTrue("Transaction status is successful", transaction.getTransactionStatus().value() == "Success");
	}
	
	/*
	 * This tests to assure that the protected OAuthParametersFactory does, indeed, return an OauthParameters object
	 */
	
	@Test
	public void testOAuthParametersFactory() {
		assertTrue("OAuuthParametersFactory should return OAuthParameters", mock.OAuthParametersFactory() instanceof OAuthParameters);
	}
	
	/*
	 * This tests the general functionality of the doRequest method, assuring that a Map<String, String> is returned
	 * and its payload can be accessed with the MESSAGE and HTTP_CODE constants
	 */
	
	@Test
	public void testDoRequest() {
		String doRequestUrl = "DoRequest_1";
		Map<String, String> response = mock.doRequest(doRequestUrl, MockMasterPassService.POST, mock.OAuthParametersFactory(), "do request");
		assertTrue("do request returns a Map", response instanceof Map<?, ?>);
		assertEquals("response body is 'do request'", response.get(MockMasterPassService.MESSAGE), "do request");
		assertEquals("response http code is 200", response.get(MockMasterPassService.HTTP_CODE), "200");
	}
	/*
	 * This tests the parseConnectionResponse method, assuring that it will parse and normalize http strings
	 */
	@Test
	public void testParseConnectionResponse() {
		String testInput = "ORD=Chicago&DFW=Dallas&LGA=New+York&STL=St+Louis";
		
		Map<String, String> paramMap = mock.parseConnectionResponse(testInput);
		assertEquals("ORD should be Chicago", paramMap.get("ORD"),"Chicago");
		assertEquals("DFW should be Dallas", paramMap.get("DFW"), "Dallas");
		assertEquals("LGA should be New York", paramMap.get("LGA"), "New York");
		assertEquals("STL should be St Louis", paramMap.get("STL"), "St Louis");
	}

}
