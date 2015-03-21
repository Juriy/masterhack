/**
 * 
 */
package com.mastercard.mcwallet.sampleapp;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.util.JSONPObject;

import com.mastercard.mcwallet.sampleapp.helpers.SampleApplicationHelper;
import com.mastercard.mcwallet.sdk.MasterPassService;
import com.mastercard.mcwallet.sdk.MasterPassServiceRuntimeException;
import com.mastercard.mcwallet.sdk.RequestTokenResponse;
import com.mastercard.mcwallet.sdk.xml.allservices.Checkout;
import com.mastercard.mcwallet.sdk.xml.allservices.ExpressCheckoutRequest;
import com.mastercard.mcwallet.sdk.xml.allservices.ExpressCheckoutResponse;
import com.mastercard.mcwallet.sdk.xml.allservices.MerchantTransaction;
import com.mastercard.mcwallet.sdk.xml.allservices.MerchantTransactions;
import com.mastercard.mcwallet.sdk.xml.allservices.PairingDataType;
import com.mastercard.mcwallet.sdk.xml.allservices.PairingDataTypes;
import com.mastercard.mcwallet.sdk.xml.allservices.PrecheckoutCard;
import com.mastercard.mcwallet.sdk.xml.allservices.PrecheckoutDataRequest;
import com.mastercard.mcwallet.sdk.xml.allservices.PrecheckoutDataResponse;
import com.mastercard.mcwallet.sdk.xml.allservices.PrecheckoutShippingAddress;
import com.mastercard.mcwallet.sdk.xml.allservices.ShoppingCartRequest;
import com.mastercard.mcwallet.sdk.xml.allservices.ShoppingCartResponse;
import com.mastercard.mcwallet.sdk.xml.allservices.TransactionStatus;
import com.mastercard.mcwallet.sdk.xml.switchapiservices.MerchantInitializationRequest;
import com.mastercard.mcwallet.sdk.xml.switchapiservices.MerchantInitializationResponse;



/**
 * @author Brady Georgen - brady.georgen@daugherty.com
 *
 */
public class MasterpassController {
	private PrivateKey privateKey;
	private MasterPassService service;
	
	private static final String SHOPPING_CART_XML = "shoppingCart.xml";
	private static final String MERCHANT_INIT_XML = "merchantInit.xml";
	private static final String PRECHECKOUT_REQUEST_XML = "preCheckoutRequest.xml";
	
	public MasterpassController(MasterpassData data){
		this.service = serviceFactory(data);
	}
	
	public MasterPassService serviceFactory(MasterpassData data){
		privateKey = this.getPrivateKey(data.getKeystorePath(),data.getKeystorePassword());
		return new MasterPassService(data.getConsumerKey(), privateKey, data.getCallbackDomain());
	}
	
	/**
	 * Abstracts the PrivateKey away from the rest of the code
	 * 
	 * @return PrivateKey
	 * 
	 */
	private PrivateKey getPrivateKey(String fileName, String password) {
		
		KeyStore ks;
		Key	key;
		try {
			ks = KeyStore.getInstance("PKCS12");
			// get user password and file input stream
			ClassLoader cl = this.getClass().getClassLoader();
			InputStream stream = cl.getResourceAsStream(fileName);	
			ks.load(stream, password.toCharArray());
			
			Enumeration<String> enumeration = ks.aliases ();
				
			// uses the default alias
			String keyAlias = (String) enumeration.nextElement();

			key = ks.getKey(keyAlias, password.toCharArray());
		} catch (KeyStoreException e) {
			throw new MasterPassServiceRuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new MasterPassServiceRuntimeException(e);
		} catch (CertificateException e) {
			throw new MasterPassServiceRuntimeException(e);
		} catch (IOException e) {
			throw new MasterPassServiceRuntimeException(e);
		} catch (UnrecoverableKeyException e) {
			throw new MasterPassServiceRuntimeException(e);
		}

		return (PrivateKey) key;
	}
	
	/**
	 * Method that retrieves the Request Token and then constructs the URl that redirects the user to the Wallet site.
	 * 
	 * @param data
	 * 
	 * @return data bean with the Request Token and redirect URL set
	 * 
	 * @throws Exception
	 */
	public MasterpassData getRequestToken(MasterpassData data) throws Exception {
		try {
			data.setRequestTokenResponse(this.service.getRequestTokenAndRedirectUrl(
						data.getRequestURL(),
						data.getCallbackUrl(),
						data.getAcceptedCards(),
						data.getCheckoutIdentifier(), 
						data.getXmlVersion(),
						data.getShippingSuppression(), 
						data.getAuthLevelBasic(),
						data.getRewards(),
						data.getRedirectShippingProfiles()));
			data.setRequestToken(data.getRequestTokenResponse().getOauthToken());
			saveConnectionHeader(data);
			
			return data;
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
	}
	/**
	 * Method to save the Authorization  Header and Signature Base String to the data bean for later display. This method is for test and display only.
	 * 
	 * @param data
	 */
	private void saveConnectionHeader(MasterpassData data) {
		data.setAuthHeader(this.service.getAuthHeader());
		data.setEncodedAuthHeader(SampleApplicationHelper.xmlEscapeText(data.getAuthHeader()));
		data.setSignatureBaseString(this.service.getSignatureBaseString());
	}
	/**
	 * Method to post the Shopping Cart XML to MasterCards services. The XML is parsed from the shoppingCart.xml file.
	 * 
	 * @param data
	 * 
	 * @return Command bean with the Shopping Cart response set.
	 * 
	 * @throws Exception
	 */
	public MasterpassData postShoppingCart(MasterpassData data) throws Exception {
		
		try {
				ShoppingCartRequest shoppingCartRequest;
				shoppingCartRequest = parseShoppingCartFile(data.getRequestTokenResponse().getOauthToken(), data.getCallbackDomain());
				String shoppingCartXml= SampleApplicationHelper.printXML(shoppingCartRequest);
				shoppingCartXml = SampleApplicationHelper.xmlReplaceImageUrl(shoppingCartXml,data);
				data.setShoppingCartRequest(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(shoppingCartXml)));
				ShoppingCartResponse response = this.service.postShoppingCartData(data.getShoppingCartUrl(), shoppingCartRequest);
				data.setShoppingCartResponse(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(SampleApplicationHelper.printXML(response))));
			
				saveConnectionHeader(data);
				return data;
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
	}
	/**
	 * Method that parse the shoppingCart.xml file, adds the Request Token to the XML and returns a ShoppingCartRequest object.
	 * 
	 * @param RequestToken 
	 * 
	 * @return ShoppingCartRequest with the data in the Request Token and the data in the shoppingCart.xml file
	 */
	public ShoppingCartRequest parseShoppingCartFile(String RequestToken, String originUrl) {
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream stream = cl.getResourceAsStream(SHOPPING_CART_XML);

		try {
			
			JAXBContext jaxb = JAXBContext.newInstance(ShoppingCartRequest.class);
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			JAXBElement<ShoppingCartRequest> je = (JAXBElement<ShoppingCartRequest>) unmarshaller.unmarshal(stream);
			ShoppingCartRequest shoppingCartRequest = (ShoppingCartRequest) je.getValue();
			shoppingCartRequest.setOAuthToken(RequestToken);
			shoppingCartRequest.setOriginUrl(originUrl);
			return shoppingCartRequest;
		} catch (JAXBException e) {
			throw new MasterPassServiceRuntimeException(e);
		}
	}
	/**
	 * Method to retrieve the Access Token from the MasterCard Services.
	 * 
	 * @param data
	 * 
	 * @return Command bean with the Access Token set.
	 * 
	 * @throws Exception
	 */
	public MasterpassData getAccessToken(MasterpassData data) throws Exception {
		try {
			data.setAccessTokenResponse(this.service.getAccessToken(data.getAccessURL(),data.getRequestToken(),data.getVerifier()));
			saveConnectionHeader(data);
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
		return data;
	}
	
	public MasterpassData getLongAccessToken(MasterpassData data) throws Exception {

		try {
			data.setLongAccessTokenResponse(this.service.getLongAccessToken(data.getAccessURL(),data.getPairingToken(),data.getPairingVerifier()));
			data.setLongAccessToken(data.getLongAccessTokenResponse().getOauthToken());
			saveConnectionHeader(data);
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
		return data;
	}
	
	/**
	 * Method to post the Merchant Initialization XML to MasterCards services. The XML is parsed from the shoppingCart.xml file.
	 * 
	 * @param data
	 * 
	 * @return Command bean with the Shopping Cart response set.
	 * 
	 * @throws Exception
	 */
	public MasterpassData postMerchantInit(MasterpassData data) throws Exception {
		
		try {
				MerchantInitializationRequest merchantInitRequest;
				merchantInitRequest = new MerchantInitializationRequest();
				merchantInitRequest.setOAuthToken(data.getPairingToken());
				merchantInitRequest.setOriginUrl(data.getCallbackDomain());
				String merchantInitXml= SampleApplicationHelper.printXML(merchantInitRequest);
				merchantInitXml = SampleApplicationHelper.xmlReplaceImageUrl(merchantInitXml,data);
				data.setMerchantInitRequest(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(merchantInitXml)));
				MerchantInitializationResponse response = this.service.postMerchantInitData(data.getMerchantInitUrl(),merchantInitRequest);
				data.setMerchantInitResponse(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(SampleApplicationHelper.printXML(response))));
			
				saveConnectionHeader(data);
				return data;
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
	}
	/**
	 * Method that parse the merchantInit.xml file, adds the Request Token to the XML and returns a MerchantInitRequest object.
	 * 
	 * @param RequestToken 
	 * 
	 * @return ShoppingCartRequest with the data in the Request Token and the data in the shoppingCart.xml file
	 */
	public MerchantInitializationRequest parseMerchantInitFile(String RequestToken, String OriginUrl) {
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream stream = cl.getResourceAsStream(MERCHANT_INIT_XML);

		try {
			JAXBContext jaxb = JAXBContext.newInstance( MerchantInitializationRequest.class );
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			JAXBElement<MerchantInitializationRequest> je = (JAXBElement<MerchantInitializationRequest>) unmarshaller.unmarshal(stream);
			MerchantInitializationRequest merchInitRequest = (MerchantInitializationRequest) je.getValue();
			merchInitRequest.setOAuthToken(RequestToken);
			merchInitRequest.setOriginUrl(OriginUrl);
			return merchInitRequest;

		} catch (JAXBException e) {
			throw new MasterPassServiceRuntimeException(e);
		}
	}
	
	public MasterpassData getPairingToken(MasterpassData data) throws Exception {
		try {
			//pairing token fun
			RequestTokenResponse pairingTokenResponse = this.service.getPairingToken(data.getRequestURL(),
					data.getCallbackUrl());			
			data.setPairingToken(pairingTokenResponse.getOauthToken());
			
			saveConnectionHeader(data);
			
			return data;
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
	}
	/**
	 * Method used by the Servlets to handle the Accepted Cards and xmlVersion data posted from the initial page.
	 * 
	 * @param request
	 * @param data
	 * 
	 * @return
	 */
	public MasterpassData processParameters(HttpServletRequest request, MasterpassData data){
		data.setXmlVersion(request.getParameter("xmlVersionDropdown"));
		String[] acceptedCards = request.getParameterValues("acceptedCardsCheckbox[]");
		data.setShippingSuppression(new Boolean(request.getParameter("shippingSuppressionDropdown")));
		data.setRewards(new Boolean(request.getParameter("rewardsDropdown")));
		data.setAuthLevelBasic(request.getParameter("authenticationCheckBox")==null?false:true);
		data.setRedirectShippingProfiles(request.getParameter("shippingProfileDropdown"));
		
		// process the accepted cards
		String acceptedCardString = "";
		if(acceptedCards != null && acceptedCards.length > 0){
			for (int i=0;i<acceptedCards.length; i++) {
				acceptedCardString = acceptedCardString + acceptedCards[i] + ",";
			  }
		}
		String privateLabel = request.getParameter("privateLabelText");
		if(privateLabel != null && privateLabel.trim().length() > 0) {
			acceptedCardString += privateLabel;
		}
		else if(acceptedCards != null && acceptedCards.length > 0){
			// remove trailing comma
			acceptedCardString = acceptedCardString.substring(0, acceptedCardString.length()-1);
		}
		else {
			// Do nothing. This means that the privateLabel is null and the acceptable cards are null. This will in a "&acceptable_cards=" parameter
		}
		data.setAcceptedCards(acceptedCardString);
		return data;
	}
	/**
	 * Method to get the Checkout Resources from MasterCard services. 
	 * 
	 * @param data
	 * 
	 * @return Command bean with the CheckoutXML set.
	 * 
	 * @throws Exception
	 */
	public MasterpassData getCheckoutData(MasterpassData data) throws Exception{
		try {
			
			Checkout checkout = this.service.getPaymentShippingResource(data.getCheckoutResourceURL(),data.getAccessTokenResponse().getOauthToken());
			data.setCheckout(checkout);
			
			data.setCheckoutXML(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(SampleApplicationHelper.printXML(checkout))));
			saveConnectionHeader(data);
			
			return data;
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
		
	}
	
	public MasterpassData getPreCheckoutData(MasterpassData data) throws Exception{
		
		try {
			PrecheckoutDataRequest preCheckoutDataRequest;
			PrecheckoutDataResponse response;
			preCheckoutDataRequest = generatePreCheckoutDataRequest(data.getPairingDataTypes());
			String preCheckoutXml= SampleApplicationHelper.printXML(preCheckoutDataRequest);
			data.setPrecheckoutRequest(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(preCheckoutXml)));
			
			
			response = this.service.getPreCheckoutData(data.getPreCheckoutUrl(),preCheckoutDataRequest,data.getLongAccessToken());
			
			data.setPreCheckoutData(response.getPrecheckoutData());
			data.setLongAccessToken(response.getLongAccessToken());
			data.setWalletName(response.getPrecheckoutData().getWalletName());
			data.setConsumerWalletId(response.getPrecheckoutData().getConsumerWalletId());
			data.setPrecheckoutResponse(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(SampleApplicationHelper.printXML(response))));
			data.setPreCheckoutDataXml(SampleApplicationHelper.printXML(response));
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(response);
			
			data.setPreCheckoutDataJson(json);
			 
			if (response.getPrecheckoutData().getShippingAddresses() != null){
				for (PrecheckoutShippingAddress shippingAddress : response.getPrecheckoutData().getShippingAddresses().getShippingAddress()) {
					data.setPrecheckoutShippingId(shippingAddress.getAddressId());
					break;
				}
			}
			
			if (response.getPrecheckoutData().getCards() != null){
				for (PrecheckoutCard preCheckoutCard : response.getPrecheckoutData().getCards().getCard()) {
					data.setPrecheckoutCardId(preCheckoutCard.getCardId());
					break;
				}
			}
			
			
			data.setPrecheckoutTransactionId(response.getPrecheckoutData().getPrecheckoutTransactionId());
			
			
			saveConnectionHeader(data);
			return data;
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
		
	}
	
	protected PrecheckoutDataRequest generatePreCheckoutDataRequest(List<String> dataTypes) {
		PrecheckoutDataRequest precheckout = new PrecheckoutDataRequest();
		PairingDataTypes pairingDataTypes = new PairingDataTypes();
		for (String type : dataTypes){
			PairingDataType dataType = new PairingDataType();
			dataType.setType(type);
			pairingDataTypes.getPairingDataType().add(dataType);
		}
		precheckout.setPairingDataTypes(pairingDataTypes);
		return precheckout;
	}
	
	public MasterpassData getExpressCheckoutData(MasterpassData data) throws Exception{
		
		try {
			ExpressCheckoutRequest expressCheckoutRequest;
			ExpressCheckoutResponse response;
			expressCheckoutRequest = parseExpressCheckoutFile(data);
			
			String expressCheckoutRequestXml= SampleApplicationHelper.printXML(expressCheckoutRequest);
			data.setExpressCheckoutRequest(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(expressCheckoutRequestXml)));
			data.setExpressCheckoutRequestData(expressCheckoutRequest);
			
			response = this.service.getExpressCheckoutData(data.getExpressCheckoutUrl(),expressCheckoutRequest,data.getLongAccessToken());
			
			data.setExpressCheckoutResponse(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(SampleApplicationHelper.printXML(response))));
			data.setExpressCheckoutResponseData(response);
			data.setCheckout(response.getCheckout());
			data.setLongAccessToken(response.getLongAccessToken());
			data.setExpressCheckoutIndicator(true);
			
			if (response.getErrors() != null && response.getErrors().getError().size() > 0){
				for (com.mastercard.mcwallet.sdk.xml.allservices.Error error : response.getErrors().getError()){
					if (error.getSource().equals("3DS Needed")) data.setExpressSecurityRequired(true);
				}
			} else {
				data.setExpressSecurityRequired(false);
			}
			
			
			saveConnectionHeader(data);
			return data;
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
		
	}
	
	public ExpressCheckoutRequest parseExpressCheckoutFile(MasterpassData data) {
		
		ExpressCheckoutRequest expressCheckoutRequest = new ExpressCheckoutRequest();		

		expressCheckoutRequest.setMerchantCheckoutId(data.getCheckoutIdentifier());
		expressCheckoutRequest.setPrecheckoutTransactionId(data.getPrecheckoutTransactionId());
		long orderAmount = 1000;
		if(data.getShoppingCart() != null) {
			orderAmount = data.getShoppingCart().getShoppingCart().getSubtotal();
			orderAmount = orderAmount + data.getTax() + (long)data.getShipping();
		}
		expressCheckoutRequest.setCurrencyCode("USD");
		expressCheckoutRequest.setOrderAmount(orderAmount);		
		expressCheckoutRequest.setCardId(data.getPrecheckoutCardId());				
		expressCheckoutRequest.setShippingAddressId(data.getPrecheckoutShippingId());
				
		expressCheckoutRequest.setWalletId(data.getPreCheckoutData().getWalletName());
		expressCheckoutRequest.setOriginUrl(data.getCallbackDomain());
		expressCheckoutRequest.setAdvancedCheckoutOverride(false);
			    
		return expressCheckoutRequest;
	}
	
	public PrecheckoutDataRequest parsePreCheckoutFile() {
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream stream = cl.getResourceAsStream(PRECHECKOUT_REQUEST_XML);

		try {
			JAXBContext jaxb = JAXBContext.newInstance( PrecheckoutDataRequest.class );
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			JAXBElement<PrecheckoutDataRequest> je = (JAXBElement<PrecheckoutDataRequest>) unmarshaller.unmarshal(stream);
			PrecheckoutDataRequest preCheckoutReqeust = (PrecheckoutDataRequest) je.getValue();
			return preCheckoutReqeust;

		} catch (JAXBException e) {
			throw new MasterPassServiceRuntimeException(e);
		}
	}
	
	/**
	 * Method to construct the MerchantTransactions XML and log the transaction to the MasterCard servers.
	 * 
	 * @param data
	 * 
	 * @return Command bean that has the postback response set.
	 * 
	 * @throws Exception
	 */
	public MasterpassData logTransaction(MasterpassData data) throws Exception{
		//Default order amount. Used in the OAuth process.
		long orderAmount = 1000;
		if(data.getShoppingCart() != null) {
			orderAmount = data.getShoppingCart().getShoppingCart().getSubtotal();
			orderAmount = orderAmount + data.getTax() + (long)data.getShipping();
		}
		
		
		String currency = "USD";
		TransactionStatus status = TransactionStatus.SUCCESS;
		
		String approvalCode = getApprovalCode();
		if(approvalCode == null){
			// if approval code is not returned then use UNAVBL
			approvalCode = "UNAVBL";
		}
		
		try {
			MerchantTransactions merchantTransactions = new MerchantTransactions();
			MerchantTransaction merchantTransaction = new MerchantTransaction();
			merchantTransaction.setConsumerKey(data.getConsumerKey());
			merchantTransaction.setCurrency(currency);
			merchantTransaction.setOrderAmount(orderAmount);
			merchantTransaction.setPurchaseDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()) );
			merchantTransaction.setTransactionId(data.getCheckout().getTransactionId());	
			merchantTransaction.setTransactionStatus(status);
			merchantTransaction.setApprovalCode(approvalCode);
			merchantTransaction.setPreCheckoutTransactionId(data.getPrecheckoutTransactionId());
			merchantTransaction.setExpressCheckoutIndicator(data.getExpressCheckoutIndicator());
			merchantTransactions.getMerchantTransactions().add(merchantTransaction);
			
			
			// Convert the XML to String 
			String merchantTransactionsXml = SampleApplicationHelper.printXML(merchantTransactions);
			data.setPostTransactionSentXml(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(merchantTransactionsXml)));
			
			MerchantTransactions loggingResponseXML =this.service.postCheckoutTransaction(data.getPostbackurl(),merchantTransactions);

			
			data.setPostTransactionReceivedXml(SampleApplicationHelper.xmlEscapeText(SampleApplicationHelper.prettyFormat(SampleApplicationHelper.printXML(loggingResponseXML))));

			saveConnectionHeader(data);
			return data;
			
		} catch (Exception e) {
			saveConnectionHeader(data);
			throw new MasterPassServiceRuntimeException(e);
		}
	}
	private String getApprovalCode(){
		   return "sample";
	}
	
}
