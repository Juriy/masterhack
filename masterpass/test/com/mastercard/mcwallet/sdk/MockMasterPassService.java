/**
 * 
 */
package com.mastercard.mcwallet.sdk;

import java.security.PrivateKey;
import java.util.Map;

import com.mastercard.api.common.OAuthParameters;
import com.mastercard.mcwallet.sdk.testhelpers.MockResponseFactory;

/**
 * @author Brady Georgen - brady.georgen@daugherty.com
 *
 */
public class MockMasterPassService extends MasterPassService {
	

	public MockMasterPassService(String consumerKey, PrivateKey privateKey, String originUrl) {
		super(consumerKey, privateKey, originUrl);
	}
	
	protected Map<String, String> doRequest(String httpsURL,String requestMethod,
			OAuthParameters oparams,String body) {
		Map<String, String> response = MockResponseFactory.generateMockResponse(httpsURL,requestMethod, oparams, body);
		return response;
	}
	
	public OAuthParameters OAuthParametersFactory(){
		return super.OAuthParametersFactory();
	}
}
