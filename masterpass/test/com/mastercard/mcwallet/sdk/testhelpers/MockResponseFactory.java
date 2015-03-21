/**
 * 
 */
package com.mastercard.mcwallet.sdk.testhelpers;

import java.util.HashMap;
import java.util.Map;

import com.mastercard.api.common.OAuthParameters;
import com.mastercard.mcwallet.sdk.MockMasterPassService;


/**
 * @author Brady Georgen - brady.georgen@daugherty.com
 *
 */
public class MockResponseFactory {

	/**
	 * 
	 */
	public static Map<String, String>  generateMockResponse(String httpsURL,
			String requestMethod, OAuthParameters oparams, String body){
		Map<String, String> response = new HashMap<String,String>();
		response.put(MockMasterPassService.HTTP_CODE, "200");
		switch(httpsURL){
			case "getAccessTokenResponse_1":
				response.put(MockMasterPassService.MESSAGE, "oauth_token=mockGetAccessToken&oauth_token_secret=mockGetAccessTokenSecret");
				break;
			case "PostShoppingCartData_1":
				response.put(MockMasterPassService.MESSAGE, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ShoppingCartResponse><OAuthToken>f674b2a58b9716868374f3d033e1666d</OAuthToken></ShoppingCartResponse>");
				break;
			case "PostCheckoutTransaction_1":
				response.put(MockMasterPassService.MESSAGE, "<?xml version='1.0' encoding='UTF-8'?><MerchantTransactions><MerchantTransactions><TransactionId>2007408</TransactionId><ConsumerKey>cLb0tKkEJhGTITp_6ltDIibO5Wgbx4rIldeXM_jRd4b0476c!414f4859446c4a366c726a327474695545332b353049303d</ConsumerKey><Currency>USD</Currency><OrderAmount>76239</OrderAmount><PurchaseDate>2014-05-07T16:40:13.847-05:00</PurchaseDate><TransactionStatus>Success</TransactionStatus><ApprovalCode>sample</ApprovalCode></MerchantTransactions></MerchantTransactions>");
				break;
			case "GetPaymentShippingResource_1":
				response.put(MockMasterPassService.MESSAGE, "<?xml version='1.0' encoding='UTF-8'?><Checkout><Card><BrandId>master</BrandId><BrandName>MasterCard</BrandName><AccountNumber>5453010000064154</AccountNumber><BillingAddress><City>Niagara falls</City><Country>CA</Country><CountrySubdivision>CA-ON</CountrySubdivision><Line1>5943 Victoria Ave</Line1><Line2/><PostalCode>L2G 3L7</PostalCode></BillingAddress><CardHolderName>JOE Test</CardHolderName><ExpiryMonth>5</ExpiryMonth><ExpiryYear>2017</ExpiryYear></Card><TransactionId>2031782</TransactionId><Contact><FirstName>JOE</FirstName><LastName>Test</LastName><Country>US</Country><EmailAddress>joe.test@email.com</EmailAddress><PhoneNumber>1-9876543210</PhoneNumber></Contact><ShippingAddress><City>Seattle</City><Country>US</Country><CountrySubdivision>US-WA</CountrySubdivision><Line1>1326 5th Ave SE</Line1><Line2/><PostalCode>98101</PostalCode><RecipientName>JOE  Test</RecipientName><RecipientPhoneNumber>1-2061113333</RecipientPhoneNumber></ShippingAddress><PayPassWalletIndicator>101</PayPassWalletIndicator></Checkout>");
				break;
			case "GetRequestToken_1":
				response.put(MockMasterPassService.MESSAGE, "oauth_callback_confirmed=true&oauth_expires_in=900&oauth_token=c70867d41ea59966f78d486a9afdba49&oauth_token_secret=f3e09b49aa7806774f645591c0a802c4&xoauth_request_auth_url=https%3A%2F%2Fsandbox.masterpass.com%2FCheckout%2FAuthorize");
				break;
			case "DoRequest_1":
				response.put(MockMasterPassService.MESSAGE, "do request");
				break;
		}
		return response;
	}

}
