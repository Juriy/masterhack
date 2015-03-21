<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="data" class="com.mastercard.mcwallet.sampleapp.MasterpassData" scope="session">
</jsp:useBean>
<html>
<head>
    <title>
    	MasterPass Pairing Flow
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="Content/Site.css">
    <script type="text/javascript" src="Scripts/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="Scripts/common.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
    <script type="text/javascript" src="${data.lightboxUrl }"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> 
    <script type="text/javascript" src="Scripts/tooltips/jquery-1.3.2.min.js"></script> <!-- Needed for tooltips only -->
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
		
	
</head>
<body class="pairing">
    <div class="page">
        <div id="header">
            <div id="title">
                <h1>
                   MasterPass Pairing Flow
                </h1>
            </div>
            <div id="logindisplay">
                &nbsp;
            </div>
        </div>
        <div id="main">
            <h1>
                Merchant Initialization Received
            </h1>
<c:if test="${ command.errorMessage != null }">
		
	<h2>Error</h2>
	<div class = "error">
		<p>
		    The following error occurred while trying to get the Request Token from the MasterCard API.
		</p>		
<pre>
<code>
${data.errorMessage }
</code>
</pre>
</div>

</c:if>           
            <p>
                This step sends the Merchants URL to MasterCard services for lighbox security.
            </p>
            
          <fieldset>
            <legend>Sent:</legend>
          	<table>
                 <tr>
                        <th>
                            Authorization Header 
<!--                             <span class='tooltip' id='authHeader'>[?]</span> -->
                        </th>
                        <td>                      
							<code>${data.authHeader }</code>
                        </td>
                    </tr> 
	              	<tr>
                        <th>
                            Signature Base String 
<!--                             <span class='tooltip' id='sbs'>[?]</span> -->
                        </th>
                        <td>
                        	<hr>
                             <code>${data.signatureBaseString }</code>
                        </td>
                    </tr>  
                    <tr>
                        <th>
                            Merchant Initialization XML 
<!--                             <span class='tooltip' id='ShoppingXML'>[?]</span> -->
                        </th>
                        <td>
<pre>                        
<code>                        
${data.merchantInitRequest }
</code>
							</pre>                            
                        </td>
                    </tr>
                    
                    
                      
           </table>
           </fieldset>
           
           <fieldset>
            <legend>Sent To:</legend>
           		<table>                     
                    <tr>
                        <th>
                            Merchant Init URL 
<!--                             <span class='tooltip' id='shopEndpoint'>[?]</span> -->
                        </th>
                        <td>
                            ${data.merchantInitUrl }
                        </td>
                    </tr>
                    
                 </table>  
                 </fieldset>
                 <fieldset>
            <legend>Received:</legend>           
                    
           		<table>                     
                    <tr>
                        <th>
                            Merchant Initialization Response 
<!--                             <span class='tooltip' id='ShoppingResponse'>[?]</span> -->
                        </th>
                        <td>
<pre>                        
<code>                        
${data.merchantInitResponse }
</code>
</pre>                           
                        </td>
                    </tr>
                     
                 </table>
                 </fieldset> 
            
            <c:if test="${ param.checkout == 'true'}">
              <!-- REQUEST TOKEN --> 
               
               <h1>
                Request Token Received
            </h1>
<c:if test="${ data.errorMessage != null }">
		
	<h2>Error</h2>
	<div class = "error">
		<p>
		    The following error occurred while trying to get the Request Token from the MasterCard API.
		</p>
<pre>
<code>
${data.errorMessage }
</code>
</pre>
</div>
</c:if>           
            <p>
                Use the following Request Token to call subsequent MasterPass services.
               
            </p>
            
			<fieldset>
            <legend>Sent</legend>
          	<table>
          	       <tr>
                        <th>
                            Authorization Header
                            
<!--                             <span class='tooltip' id='authHeader'>[?]</span> -->
                        </th>
                        <td>                      
							<code>${data.encodedAuthHeader}</code> 
						
                        </td>
                    </tr> 
	              	<tr>
                        <th>
                            Signature Base String 
<!--                             <span class='tooltip' id='sbs'>[?]</span> -->
                        </th>
                        <td class="formatUrl">
                        	<hr>
                            <code>${data.signatureBaseString }</code>
                        </td>
                    </tr>  
           </table>
           </fieldset>
           
           <fieldset>
            <legend>Sent to:</legend>          		
           		<table>                     
                    <tr>
                        <th>
                            Request Token URL  
<!--                             <span class='tooltip' id='requestEndpoint'>[?]</span> -->
                        </th>
                        <td>
                           ${data.requestURL }
                        </td>
                    </tr>
                    
                 </table>  
            </fieldset>
            
            <fieldset>
            <legend>Received</legend>  
                   <table>                     
                    <tr>
                        <th>
                            Request Token 
<!--                             <span class='tooltip' id='requestToken'>[?]</span> -->
                        </th>
                        <td>
                            ${data.requestTokenResponse.oauthToken }
                        </td>
                    </tr>
                     <tr>
                        <th>
                            Authorize URL 
<!--                             <span class='tooltip' id='authorizeurl'>[?]</span> -->
                        </th>
                        <td>
                            ${data.requestTokenResponse.authorizationUrl }
                        </td>
                    </tr>
                    <tr>
                        <th>
                           Expires in 
<!--                            <span class='tooltip' id='tokenexpires'>[?]</span> -->
                        </th>
                        <td>
                            ${data.requestTokenResponse.oauthExpiresIn } <c:if test="${ data.requestTokenResponse.oauthExpiresIn != null }">Seconds</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Oauth Secret 
<!--                             <span class='tooltip' id='oauthSecret'>[?]</span> -->
                        </th>
                        <td>
                            ${data.requestTokenResponse.oauthTokenSecret }
                        </td>
                    </tr>
                 </table>
                 </fieldset>
               <!-- PAIRING TOKEN -->
               <h1>
                Pairing Token Received
            </h1>
<c:if test="${ data.errorMessage != null }">
		
	<h2>Error</h2>
	<div class = "error">
		<p>
		    The following error occurred while trying to get the Request Token from the MasterCard API.
		</p>
<pre>
<code>
${data.errorMessage }
</code>
</pre>
</div>
</c:if>           
            <p>
                Use the following Request Token to call subsequent MasterPass services.
            </p>
            
			<fieldset>
            <legend>Sent</legend>
          	<table>
          	       <tr>
                        <th>
                            Authorization Header 
<!--                             <span class='tooltip' id='authHeader'>[?]</span> -->
                        </th>
                        <td>                      
							<code>${data.authHeader }</code>
						
                        </td>
                    </tr> 
	              	<tr>
                        <th>
                            Signature Base String 
<!--                             <span class='tooltip' id='sbs'>[?]</span> -->
                        </th>
                        <td>
                        	<hr>
                            <code>${data.signatureBaseString }</code>
                        </td>
                    </tr>  
           </table>
           </fieldset>
           
           <fieldset>
            <legend>Sent to:</legend>          		
           		<table>                     
                    <tr>
                        <th>
                            Request Token URL  
<!--                             <span class='tooltip' id='requestEndpoint'>[?]</span> -->
                        </th>
                        <td>
                           ${data.requestURL }
                        </td>
                    </tr>
                    
                 </table>  
            </fieldset>
            
            <fieldset>
            <legend>Received</legend>  
                   <table>                     
                    <tr>
                        <th>
                            Pairing Token 
<!--                             <span class='tooltip' id='requestToken'>[?]</span> -->
                        </th>
                        <td>
                            ${data.pairingToken }
                        </td>
                    </tr>
                     <tr>
                        <th>
                            Pairing Callback Path 
<!--                             <span class='tooltip' id='authorizeurl'>[?]</span> -->
                        </th>
                        <td>
                            ${data.pairingCallbackPath }
                        </td>
                    </tr>
                 </table>
                 </fieldset>
               <!-- SHOPPING CART DATA -->
               <h1>
                Shopping Cart Data Submitted
            </h1>
<c:if test="${ data.errorMessage != null }">
		
	<h2>Error</h2>
	<div class = "error">
		<p>
		    The following error occurred while trying to get the Request Token from the MasterCard API.
		</p>		
<pre>
<code>
${data.errorMessage }
</code>
</pre>
</div>

</c:if>           
            <p>
                This step sends the Merchants shopping cart data to MasterCard services for display in the Wallet.
            </p>
            
               <fieldset>
               	<legend>Sent:</legend>
               	<table>
                 <tr>
                        <th>
                            Authorization Header 
<!--                             <span class='tooltip' id='authHeader'>[?]</span> -->
                        </th>
                        <td>                      
							<code>${data.authHeader }</code>
                        </td>
                    </tr> 
	              	<tr>
                        <th>
                            Signature Base String 
<!--                             <span class='tooltip' id='sbs'>[?]</span> -->
                        </th>
                        <td>
                        	<hr>
                             <code>${data.signatureBaseString }</code>
                        </td>
                    </tr>  
                    <tr>
                        <th>
                            Shopping Cart XML 
<!--                             <span class='tooltip' id='ShoppingXML'>[?]</span> -->
                        </th>
                        <td>
<pre>                        
<code>                        
${data.shoppingCartRequest }
</code>
</pre>                            
                        </td>
                    </tr>  
           </table>
               </fieldset>
               <fieldset>
            <legend>Sent To:</legend>
           		<table>                     
                    <tr>
                        <th>
                            Shopping Cart URL 
<!--                             <span class='tooltip' id='shopEndpoint'>[?]</span> -->
                        </th>
                        <td>
                            ${data.shoppingCartUrl }
                        </td>
                    </tr>
                    
                 </table>  
                 </fieldset>
            <fieldset>
            <legend>Received:</legend>           
                    
           		<table>                     
                    <tr>
                        <th>
                            Shopping Cart Response 
<!--                             <span class='tooltip' id='ShoppingResponse'>[?]</span> -->
                        </th>
                        <td>
						<pre>                        
<code>                        
${data.shoppingCartResponse}
</code>
						</pre>                           
                        </td>
                    </tr>
                     
                 </table>
                 </fieldset>  
            </c:if>     
                 
                 
            
                <h1>
                Configure Pairing Options
            	</h1>
            	<p>
            	Select Data Types to be Paired with MasterPass
            	</p>
	            <fieldset>
	            	<legend>Pairing Configuration</legend>
	            	
	            	<form id="pairConfiguration" >
		            	<table>
		            		<tr><th>Pairing Data Types</th></tr>
		            		<tr>
		            			
		            			<td><input type="checkbox" onclick="handleUpdatePairConfiguration()" id="creditCardPairing" value="creditCard">Credit Card</td>
		            			<td><input type="checkbox" onclick="handleUpdatePairConfiguration()" id="profilePairing" value="profile">Profile</td>
		            			<td><input type="checkbox" onclick="handleUpdatePairConfiguration()" id="addressPairing" value="address">Address</td>
		            			<td><input type="checkbox" onclick="handleUpdatePairConfiguration()" id="rewardsPairing" value="rewards">Rewards</td>
		            		</tr>
		            		<tr><th>Express Option</th></tr>
		            		<tr>
		            			<td><input type="checkbox" onclick="handleUpdatePairConfiguration()" id="expressPairing" value="express">Express Pairing</td>
		            		</tr>
		            	</table>
	            	</form>
	            	<br>
	            	<div id="buttonsDiv">
	            	<c:choose>
		            	
		            	<c:when test="${ param.checkout == 'true'}">
		            		<div style="padding-bottom: 20px">
								<div id="checkoutButtonDiv" onClick="handleConnectWithMasterPass()">
									<a href="#">
										<img src="https://www.mastercard.com/mc_us/wallet/img/en/US/mcpp_wllt_btn_chk_147x034px.png" alt="Buy with MasterPass">
									</a>
								</div>
								
								<p><a href="http://www.mastercard.com/mc_us/wallet/learnmore/en" target="_blank">Learn More</a></p>
							</div>
		            	</c:when>
		            	<c:otherwise>
		            		<div style="padding-bottom: 20px">
								<div id="connectButtonDiv" onClick="handleConnectWithMasterPass()">
									<a href="#">
										<img src="https://www.mastercard.com/mc_us/wallet/img/en/US/mp_connect_with_button_034px.png" alt="Connect with MasterPass">
									</a>
								</div>
								<p><a href="http://www.mastercard.com/mc_us/wallet/learnmore/en" target="_blank">Learn More</a></p>
							</div>
		            	</c:otherwise>
	            	</c:choose>
	            	</div>
	            	<div id="sampleCodeDiv">
	            		<fieldset>
	            			<legend>Javascript</legend>
<pre>
<code id="sampleCode">
</code>
</pre>
	            		</fieldset>
           			 </div>
	            </fieldset>
	            <script type="text/javascript" language="Javascript">
		            $(document).ready(function(){
		            	$("#buttonsDiv").css("display","none");
		            	setCheckout();
		            });
		            var checkout = false;
	            	var config = null;
		            var data = null;
		            var callbackPath = "${data.pairingCallbackPath}";
		            var showRewards = ${data.rewards} == true;
		            var locationParams = getJsonFromUrl();
	            	var setCheckout = function(){
	            		if (locationParams.checkout) {
			            	console.log("checkout is true");
			            	callbackPath = "${data.appBaseUrl}"+"${data.callbackPath}"
			            	console.log(callbackPath);
			            	checkout = true
			            }
	            	}
		            
	            	function getJsonFromUrl() {
		            	  var query = location.search.substr(1);
		            	  var result = {};
		            	  query.split("&").forEach(function(part) {
		            	    var item = part.split("=");
		            	    result[item[0]] = decodeURIComponent(item[1]);
		            	  });
		            	  return result;
	          		}
	            	
		            
		            function handleConnectWithMasterPass(){
		            	console.log("checkout: "+checkout);
		            	console.log("data:");
				    	console.log(data);
				    	console.log(data.pairingDataTypes);
				    	console.log(config.expressPairing);
				    	console.log(callbackPath)
		            	console.log("request token: "+data.requestToken)

		            	if (checkout){
		            		
		            		if (showRewards){
		            			MasterPass.client.checkout({
						     		"callbackUrl":callbackPath,
					     			"pairingRequestToken":"${data.pairingToken}",
					     			"requestToken": data.requestToken,
					     			"requestExpressCheckout":config.expressPairing,
					     			"requestedDataTypes":data.pairingDataTypes,
					     		 	"merchantCheckoutId":"${data.checkoutIdentifier}",
					     		 	"allowedCardTypes":["${data.acceptedCards}"],
					     		 	"suppressShippingAddressEnable": data.shippingSuppression,
					     		 	"loyaltyEnabled" : "${data.rewards}",
					       			"allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],
					     		 	"requestPairing":true,
					     		 	"requestBasicCheckout" : ${data.authLevelBasic},
					     		 	"version" : "v6"
					     		});
		            		} else {
		            			MasterPass.client.checkout({
						     		"callbackUrl":callbackPath,
					     			"pairingRequestToken":"${data.pairingToken}",
					     			"requestToken": data.requestToken,
					     			"requestExpressCheckout":config.expressPairing,
					     			"requestedDataTypes":data.pairingDataTypes,
					     		 	"merchantCheckoutId":"${data.checkoutIdentifier}",
					     		 	"allowedCardTypes":["${data.acceptedCards}"],
					     		 	"suppressShippingAddressEnable": data.shippingSuppression,
					     		 	"loyaltyEnabled" : "${data.rewards}",
					     		 	"requestPairing":true,
					     		 	"requestBasicCheckout" : ${data.authLevelBasic},
					     		 	"version" : "v6"
					     		});
		            		}
		            		
		            	} else {
		            		MasterPass.client.connect({
					     		"callbackUrl":callbackPath,
				     			"pairingRequestToken":"${data.pairingToken}",
				     			"requestExpressCheckout":config.expressPairing,
				     			"requestedDataTypes":data.pairingDataTypes,
				     		 	"merchantCheckoutId":"${data.checkoutIdentifier}",
				     		 	"allowedCardTypes":["${data.acceptedCards}"],
				     		 	"suppressShippingAddressEnable": data.shippingSuppression,
				     		 	"requestPairing":true,
				     		 	"version" : "v6"
				     		});
		            	}
		            	
		            	
		            }
		            
		            function handleUpdatePairConfiguration() {
					    config = {
					    		creditCardPairing:$('#creditCardPairing').is(':checked'),
					    		profilePairing:$('#profilePairing').is(':checked'),
					    		addressPairing:$('#addressPairing').is(':checked'),
					    		rewardsPairing:$('#rewardsPairing').is(':checked')
					    };
					    console.log("Config:")
					    console.log(config)
					    if ($('#expressPairing').is(':checked')){
				    		config.expressPairing = true;
				    		if (!checkout) callbackPath = "${data.expressCallbackPath}";
				    		if (!config.creditCardPairing || !config.addressPairing){
				    			console.log("CHECK EM")
				    			$('#creditCardPairing').attr('checked', true);
				    			config.creditCardPairing = true;
				    			$('#addressPairing').attr('checked', true);
				    			config.addressPairing = true;
				    		}
				    	} else {
				    		config.expressPairing = false;
				    		callbackPath = "${data.pairingCallbackPath}"
				    	}
					    console.log("callback path "+callbackPath);
					    
					    var requestedDataTypes = [];
					    
					    for (var prop in config){
					    	switch(prop){
					    	case "creditCardPairing":
					    		if (config[prop]) requestedDataTypes.push("CARD");
					    		break;
					    	case "profilePairing":
					    		if (config[prop]) requestedDataTypes.push("PROFILE");
					    		break;
					    	case "addressPairing":
					    		if (config[prop]) requestedDataTypes.push("ADDRESS");
					    		break;
					    	case "rewardsPairing":
					    		if (config[prop]) requestedDataTypes.push("REWARD_PROGRAM");
					    		break;
					    	}
					    }
					    $('#sampleCode').empty();
					    $('#sampleCodeDiv.legend').empty();
					    $('#pairConfiguration :input').attr("disabled", true);
					    
					    
					    $.post('pairingConfig', {dataTypes:requestedDataTypes.toString(), express:config.expressPairing}, function(dataString) {
					    	if (dataString.length > 0) data = eval('('+dataString+')');
						    
					    	console.log("data:");
					    	console.log(data);
					    	
					    	if (requestedDataTypes.length > 0){
						    	$("#buttonsDiv").css("display", "block");
						    } else {
						    	$("#buttonsDiv").css("display", "none");
						    }
					    	
						    if ((requestedDataTypes.length >0) || (config.expressPairing && requestedDataTypes.length >0)){
						    	if (checkout){
							    	if (showRewards){
							    		var sampleButtonString = 'MasterPass.client.checkout({\n\t"callbackUrl":'+callbackPath+',\n\t"pairingRequestToken":"${data.pairingToken}",\n\t"requestExpressCheckout":'+config.expressPairing+',\n\t"requestedDataTypes":["'+requestedDataTypes.toString()+'"],\n\t"merchantCheckoutId":"${data.checkoutIdentifier}",\n\t"allowedCardTypes":["${data.acceptedCards}"],\n\t"suppressShippingAddressEnable": '+data.shippingSuppression+',\n\t"loyaltyEnabled" : "${data.rewards}",\n\t"allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],\n\t"version":"v6",\n\t"requestBasicCheckout" : ${data.authLevelBasic},\n\t"requestPairing":true \n});';
							    	} else {
							    		var sampleButtonString = 'MasterPass.client.checkout({\n\t"callbackUrl":'+callbackPath+',\n\t"pairingRequestToken":"${data.pairingToken}",\n\t"requestExpressCheckout":'+config.expressPairing+',\n\t"requestedDataTypes":["'+requestedDataTypes.toString()+'"],\n\t"merchantCheckoutId":"${data.checkoutIdentifier}",\n\t"allowedCardTypes":["${data.acceptedCards}"],\n\t"suppressShippingAddressEnable": '+data.shippingSuppression+',\n\t"loyaltyEnabled" : "${data.rewards}",\n\t"version":"v6",\n\t"requestBasicCheckout" : ${data.authLevelBasic},\n\t"requestPairing":true \n});';
							    	}
						    	} else {
							    	var sampleButtonString = 'MasterPass.client.connect({\n\t"callbackUrl":'+callbackPath+',\n\t"pairingRequestToken":"${data.pairingToken}",\n\t"requestExpressCheckout":'+config.expressPairing+',\n\t"requestedDataTypes":["'+requestedDataTypes.toString()+'"],\n\t"merchantCheckoutId":"${data.checkoutIdentifier}",\n\t"allowedCardTypes":["${data.acceptedCards}"],\n\t"suppressShippingAddressEnable": '+data.shippingSuppression+',\n\t"version":"v6",\n\t"requestPairing":true \n});';
						    	}
						    }
						    $("#sampleCode").text(sampleButtonString);
					    	$('#pairConfiguration :input').attr("disabled", false);
					    });
					    
					 }
		            
		             
				</script>     	 
        </div>
        <div id="footer">
        </div>
    </div>
    
	
</body>
</html>
