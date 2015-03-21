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
                    MasterPass Pairing Flow</h1>
            </div>
            <div id="logindisplay">
                &nbsp;
            </div>
            
        </div>
        <div id="main">
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
            <div id="pairingConfigDiv" style="display: none">
            	<h1>
                Configure Pairing Options
            	</h1>
            	<p>
            	Select Data Types to be Paired with MasterPass
            	</p>
	            <fieldset>
	            	<legend>Pairing Configuration</legend>
	            	
	            	<form id="pairConfiguration">
		            	<table>
		            		<tr><th>Pairing Data Types</th></tr>
		            		<tr>
		            			
		            			<td><input type="checkbox" onclick="handleUpdatePairConfiguration()" id="creditCardPairing" value="creditCard" name="creditCardPairing">Credit Card</td>
		            			
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
	            	<div style="padding-bottom: 20px" >
						<div id="checkoutButtonDiv" onClick="handleConnectWithMasterPass()">
							<a href="#">
								<img src="https://www.mastercard.com/mc_us/wallet/img/en/US/mcpp_wllt_btn_chk_147x034px.png" alt="Buy with MasterPass">
							</a>
						</div>
						<p><a href="http://www.mastercard.com/mc_us/wallet/learnmore/en" target="_blank">Learn More</a></p>
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
            </div>
			<form id="merchantInit" action="/MerchantInit" method="POST">
				<input type="hidden" name="oauth_token" id="oauth_token" value="${data.requestTokenResponse.oauthToken }">
				<input type="hidden" name="RedirectUrl" id="RedirectUrl" value="${data.requestTokenResponse.redirectUrl }">
	    		<input value="Merchant Initialization" type="submit">
			</form>
			<script>
				var config = null;
				var data = null;
				var checkout = false;
				callbackPath = "${data.appBaseUrl}"+"${data.callbackPath}"
				var showRewards = ${data.rewards} == true;
				function getJsonFromUrl() {
	            	  var query = location.search.substr(1);
	            	  var result = {};
	            	  query.split("&").forEach(function(part) {
	            	    var item = part.split("=");
	            	    result[item[0]] = decodeURIComponent(item[1]);
	            	  });
	            	  return result;
            	}
	            
	            $(document).ready(function(){
	            	var locationParams = getJsonFromUrl();
	            	if (locationParams.checkout) {
	            		console.log("checkout is true");
	            		$("#pairingConfigDiv").css("display", "block");
	            		$("#checkoutButtonDiv").css("display", "none");
	            		$("#merchantInit").css("display", "none");
		            	$("#merchantInit").attr("action", "/MerchantInit?checkout=true");
		            }
	            	
	            });
	            
	            function handleConnectWithMasterPass() {
	            	if (showRewards){
	            		MasterPass.client.checkout({
				     		"callbackUrl":callbackPath,
			     			"pairingRequestToken":"${data.pairingToken}",
			     			"requestToken": "${data.requestToken}",
			     			"requestExpressCheckout":config.expressPairing,
			     			"requestedDataTypes":data.pairingDataTypes,
			     		 	"merchantCheckoutId":"${data.checkoutIdentifier}",
			     		 	"allowedCardTypes":["${data.acceptedCards}"],
			     		 	"suppressShippingAddressEnable":"${data.shippingSuppression}",
			     		 	"requestPairing":true,
			     		 	"loyaltyEnabled" : "${data.rewards}",
			       			"allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],
			     		 	"requestBasicCheckout" : ${data.authLevelBasic},
			     		 	"version" : "v6"
			     		});
	            	} else {
	            		MasterPass.client.checkout({
				     		"callbackUrl":callbackPath,
			     			"pairingRequestToken":"${data.pairingToken}",
			     			"requestToken": "${data.requestToken}",
			     			"requestExpressCheckout":config.expressPairing,
			     			"requestedDataTypes":data.pairingDataTypes,
			     		 	"merchantCheckoutId":"${data.checkoutIdentifier}",
			     		 	"allowedCardTypes":["${data.acceptedCards}"],
			     		 	"suppressShippingAddressEnable":"${data.shippingSuppression}",
			     		 	"requestPairing":true,
			     		 	"loyaltyEnabled" : "${data.rewards}",
			     		 	"requestBasicCheckout" : ${data.authLevelBasic},
			     		 	"version" : "v6"
			     		});
	            	}
	            	
	            }
	            
	            function handleUpdatePairConfiguration() {
	            	console.log("config:");
	            	console.log($('#creditCardPairing').is(':checked'));
	            	console.log($('#profilePairing').is(':checked'));
	            	console.log($('#addressPairing').is(':checked'));
	            	console.log($('#rewardsPairing').is(':checked'));
	            	config = {
				    		creditCardPairing:$('#creditCardPairing').is(':checked'),
				    		profilePairing:$('#profilePairing').is(':checked'),
				    		addressPairing:$('#addressPairing').is(':checked'),
				    		rewardsPairing:$('#rewardsPairing').is(':checked')
				    };
				    console.log("Config:");
				    console.log(config);
				    if ($('#expressPairing').is(':checked')){
			    		config.expressPairing = true;
			    		console.log("if statement");
			    		console.log(!config.creditCardPairing || !config.addressPairing);
			    		if (!config.creditCardPairing || !config.addressPairing){
			    			console.log("CHECK EM");
			    			$('#creditCardPairing').attr('checked', true);
			    			config.creditCardPairing = true;
			    			$('#addressPairing').attr('checked', true);
			    			config.addressPairing = true;
			    		}
			    	} else {
			    		config.expressPairing = false;
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
				    console.log(requestedDataTypes)
				    $('#sampleCode').empty();
				    $('#sampleCodeDiv.legend').empty();
				    $('#pairConfiguration :input').attr("disabled", true);
				    
				    
				    $.post('pairingConfig', {dataTypes:requestedDataTypes.toString(), express:config.expressPairing}, function(dataString) {
				    	if (dataString.length > 0) data = eval('('+dataString+')');
				    	console.log("data:");
				    	console.log(data);
				    	
				    	if (requestedDataTypes.length > 0){
					    	$("#checkoutButtonDiv").css("display", "block");
					    } else {
					    	$("#checkoutButtonDiv").css("display", "none");
					    }
				    	
					    if ((requestedDataTypes.length >0) || (config.expressPairing && requestedDataTypes.length >0)){
						    if (showRewards) {
						    	var sampleButtonString = 'MasterPass.client.checkout({\n\t"callbackUrl":'+callbackPath+',\n\t"pairingRequestToken":"${data.pairingToken}",\n\t"requestToken": "${data.requestToken}",\n\t"requestExpressCheckout":'+config.expressPairing+',\n\t"requestedDataTypes":["'+requestedDataTypes.toString()+'"],\n\t"merchantCheckoutId":"${data.checkoutIdentifier}",\n\t"allowedCardTypes":["${data.acceptedCards}"],\n\t"suppressShippingAddressEnable": ${data.shippingSuppression},\n\t"loyaltyEnabled" : "${data.rewards}",\n\t"allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],\n\t"version":"v6",\n\t"requestBasicCheckout" : ${data.authLevelBasic},\n\t"requestPairing":true \n});';
						    } else {
						    	var sampleButtonString = 'MasterPass.client.checkout({\n\t"callbackUrl":'+callbackPath+',\n\t"pairingRequestToken":"${data.pairingToken}",\n\t"requestToken": "${data.requestToken}",\n\t"requestExpressCheckout":'+config.expressPairing+',\n\t"requestedDataTypes":["'+requestedDataTypes.toString()+'"],\n\t"merchantCheckoutId":"${data.checkoutIdentifier}",\n\t"allowedCardTypes":["${data.acceptedCards}"],\n\t"suppressShippingAddressEnable": ${data.shippingSuppression},\n\t"loyaltyEnabled" : "${data.rewards}",\n\t"version":"v6",\n\t"requestBasicCheckout" : ${data.authLevelBasic},\n\t"requestPairing":true \n});';

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
