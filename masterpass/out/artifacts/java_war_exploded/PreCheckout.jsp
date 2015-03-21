<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="data" class="com.mastercard.mcwallet.sampleapp.MasterpassData" scope="session">
</jsp:useBean>
<html>
<head>
	<title>
		MasterPass Connect Pairing Checkout Flow
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="Content/Site.css">
    <script type="text/javascript" src="Scripts/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="Scripts/common.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript" src="https://jquery-xml2json-plugin.googlecode.com/svn/trunk/jquery.xml2json.js"></script> 
    <script type="text/javascript" src="${data.lightboxUrl }"></script>
    <script type="text/javascript" src="https://sandbox.masterpass.com/lightbox/Switch/assets/js/MasterPass.omniture.js"></script>
    
</head>
<body class="pairing">
	<div class="page">
		<div id="header">
			<div id="title">
				<h1>MasterPass Connect Pairing Checkout Flow</h1>
			</div>
			<div id="logindisplay">&nbsp;</div>
			
		</div>
		<div id="main">
			<h1>Retrieved Pre Checkout XML</h1>
<c:if test="${ data.errorMessage != null }">
	<div class = "error">	
		<h2>Error</h2>
		<p>
		    The following error occurred while trying to get the Request Token from the MasterCard API.
		</p>		
<pre>
<code>
${ data.errorMessage }
</code>
</pre>
	</div>
</c:if>
			<p>Once a Access Token is gained, request the user protected
				resources (shipping and/or billing information)</p>
			<fieldset>
				<legend>Sent</legend>
				<table>
					<tr>
						<th>
							Authorization Header 
<!-- 							<span class='tooltip' id='authHeader'>[?]</span> -->
						</th>
						<td>
							<code>${ data.authHeader }</code>
						</td>
					</tr>
					<tr>
						<th>
							Signature Base String 
<!-- 							<span class='tooltip' id='sbs'>[?]</span> -->
						</th>
						<td>
							<hr>
							<code>${ data.signatureBaseString }</code>
						</td>
					</tr>
					<tr>
                        <th>
                            PreCheckout XML 
<!--                             <span class='tooltip' id='ShoppingXML'>[?]</span> -->
                        </th>
                        <td>
<pre>                        
<code>                        
${ data.precheckoutRequest }
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
                            Pre Checkout URL 
                        </th>

                        <td>
                            ${ data.preCheckoutUrl }
                        </td>
                    </tr>
                    
                 </table>  
                 </fieldset>
            <fieldset>
            <legend>Received:</legend>           
                    
           		<table>                     
                    <tr>
                        <th>
                            Pre Checkout Response 
<!--                             <span class='tooltip' id='ShoppingResponse'>[?]</span> -->
                        </th>
                        <td>
						<pre>                        
<code>                        
${ data.precheckoutResponse }
</code>
						</pre>                           
                        </td>
                    </tr>
				</table>
			</fieldset>
                     
                 </table>
                 </fieldset> 
         <c:if test="${ param.express == null}">
                 <h1>
                Received Request Token
            </h1>
<c:if test="${ data.errorMessage != null }">
		
	<h2>Error</h2>
	<div class = "error">
		<p>
		    The following error occurred while trying to get the Request Token from the MasterCard API.
		</p>
<pre>
<code>
${ data.errorMessage }
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
                        <td >                      
						<code>${ data.authHeader }</code>
						
                        </td>
                    </tr> 
	              	<tr>
                        <th>
                            Signature Base String 
<!--                             <span class='tooltip' id='sbs'>[?]</span> -->
                        </th>
                        <td >
                        	<hr>
                            <code>${ data.signatureBaseString }</code>
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
                           ${ data.requestURL }
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
                            ${ data.requestTokenResponse.oauthToken }
                        </td>
                    </tr>
                     <tr>
                        <th>
                            Authorize URL 
<!--                             <span class='tooltip' id='authorizeurl'>[?]</span> -->
                        </th>
                        <td>
                            ${ data.requestTokenResponse.authorizationUrl }
                        </td>
                    </tr>
                    <tr>
                        <th>
                           Expires in 
<!--                            <span class='tooltip' id='tokenexpires'>[?]</span> -->
                        </th>
                        <td>
                            ${ data.requestTokenResponse.oauthExpiresIn } <c:if test="${ data.requestTokenResponse.oauthExpiresIn != null }">Seconds</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Oauth Secret 
<!--                             <span class='tooltip' id='oauthSecret'>[?]</span> -->
                        </th>
                        <td>
                            ${ data.requestTokenResponse.oauthTokenSecret }
                        </td>
                    </tr>
                 </table>
                 
                 </fieldset>
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
				<fieldset>
					<legend>Paired Data</legend>
					<table id="pairedData"></table>
				</fieldset>
			<div id="expressCheckoutDiv">
	            <form id="expressCheckoutForm" method="POST" action="/ExpressCheckout">
		           	<input id="cardSubmit" type="hidden" name="cardSubmit" value="card">
		           	<input id="addressSubmit" type="hidden" name="addressSubmit" value="address">
		           	<input id="rewardSubmit" type="hidden" name="rewardSubmit">
		           	<input id="expressSubmit" class="expressButton" value="Express Checkout" type="submit">
	            </form>
            </div>
            <div id="checkoutButtonDiv" style="padding-bottom: 20px">
				<a href="#" onClick="handleCheckoutWithMasterpass()">
					<img src="https://www.mastercard.com/mc_us/wallet/img/en/US/mcpp_wllt_btn_chk_147x034px.png" alt="Buy with MasterPass">
				</a>
				<p><a href="http://www.mastercard.com/mc_us/wallet/learnmore/en" target="_blank">Learn More</a></p>
			</div>
            
            <div id="buttonJavaScriptDiv">
            	<fieldset>
            		<legend>Button Javascript</legend>
            		<pre><code id="sampleCode"></code></pre>
            	</fieldset>
            </div>
			</div>
			
		</div>
			
			<script type="text/javascript" language="Javascript">
				//var preCheckout = $.xml2json($.parseXML('${data.preCheckoutDataXml}')).PrecheckoutData;
				var json = ${data.preCheckoutDataJson};
				var preCheckout = json.precheckoutData;
				var supressShipping = ${data.shippingSuppression};
				var showRewards = ${data.rewards} == true;
				var addressId = $("#addressSelect option:selected").val();
				var cardId = $("#cardSelect option:selected").val();
				var rewardId = $("#rewardSelect option:selected").val();
				
				console.log(preCheckout);
				
				if (preCheckout.contact != null) $("<tr><th>Profile:  </th></tr>)").append(generateContactSelect(preCheckout.contact)).appendTo('#pairedData');
				if ((preCheckout.cards != null) && (preCheckout.cards.card != null)) $("<tr><th>Card:  </th></tr>)").append(generateCardSelect(preCheckout.cards.card)).appendTo("#pairedData");
				if ((preCheckout.shippingAddresses != null) && (preCheckout.shippingAddresses.shippingAddress != null)) {
					$("<tr><th>Address:  </th></tr>)").append(generateAddressSelect(preCheckout.shippingAddresses.shippingAddress)).appendTo('#pairedData');
				} else {
					supressShipping = true;
				}
				if (preCheckout.rewardPrograms != null) $("<tr><th>Rewards:  </th></tr>)").append(generateContactSelect(preCheckout.contact)).appendTo('#pairedData');
				
				// check if we are in the express flow
				var express = false
            	var locationParams = getJsonFromUrl();
            	if (locationParams.express) {
	            	console.log("express is true");
	            	express = true
	            	$(".pairing").toggleClass("pairing express");
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
				var precheckoutTransactionId = preCheckout.precheckoutTransactionId;
				
				
				function generateCardSelect(cards){
					var cardSelect = $("<select id='cardSelect'/>").change(function(){
						console.log("card changed");
						updateCheckoutButton();
					});
					switch (true){
						case Object.prototype.toString.call(cards) == "[object Array]":
							console.log("cards is array");
							for (var card in cards){
								console.log(cards[card]);
								generateCardOption(cards[card]).appendTo(cardSelect);
							}
							break;
						case Object.prototype.toString.call(cards) == "[object Object]":
							console.log("cards is object");
							generateCardOption(cards).appendTo(cardSelect);
							break;
					}
					return cardSelect;
				}
				
				function generateCardOption(card){
					option =  $('<option/>', {text: card.brandName+" - "+card.lastFour}).val(card.cardId);
					return option;
				}
				
				function generateAddressSelect(addresses){
					var addressSelect = $("<select id='addressSelect'/>").change(function(){
						console.log("address changed");
						updateCheckoutButton();
					});
					console.log(addresses);
					switch (true){
						case Object.prototype.toString.call(addresses) == "[object Array]":
							console.log("Address is Array")
							for (var address in addresses) {
								generateAddressOption(addresses[address]).appendTo(addressSelect);
							}
							break;
						case Object.prototype.toString.call(addresses) == "[object Object]":
							console.log("Address is Object")
							generateAddressOption(addresses).appendTo(addressSelect);
							break;
					}
					return addressSelect;
				}
				
				function generateAddressOption(address) {
					console.log("Creating option: "+address.line1);
					option = $('<option/>', {text: address.country+" : "+address.line1}).val(address.addressId);
					return option;
				}
				
				function generateContactSelect(contact){
					return $("<p>"+contact.firstName+" "+contact.lastName+"</p>");
				}
				
				function generateRewardSelect(rewards){
					var rewardsSelect = $("<select id='rewardsSelect'/>").change(function(){
						console.log("reward changed");
						updateCheckoutButton();
					});
					switch (true){
						case Object.prototype.toString.call(rewards) == "[object Array]":
							for (var reward in rewards){
								generateRewardOption(reward).appendTo(rewardsSelect);
							}
							break;
						case Object.prototype.toString.call(rewards) == "[object Object]":
							generateRewardOption(rewards).appendTo(rewardsSelect);
							break;
					}
					return rewardsSelect;
				}
								
				function generateRewardOption(reward){
					option = $('<option/>', {text: reward.name}).val(reward.rewardId);
					option.click(updateCheckoutButton);
					return option;
				}
				
				function handleCheckoutWithMasterpass() {
					console.log("handling checkout")
					addressId = $("#addressSelect option:selected").val();
					cardId = $("#cardSelect option:selected").val();
					rewardId = $("#rewardSelect option:selected").val();
					console.log("the selected address id is: "+addressId);
					console.log("the selected card id is: "+cardId);
					if (showRewards){
						MasterPass.client.checkout({
					 		"requestToken":"${data.requestTokenResponse.oauthToken}",
					 		"callbackUrl":"${data.callbackUrl}",
					 		"merchantCheckoutId":"${data.checkoutIdentifier}",
					 		"allowedCardTypes":["${data.acceptedCards}"],
					 		"cardId":cardId,
					 		"shippingId":addressId,
					 		"precheckoutTransactionId":precheckoutTransactionId,
					 		"suppressShippingAddressEnable": supressShipping,
					 		"requestBasicCheckout" : ${data.authLevelBasic},
					 		"walletName" : "${data.walletName}",
					 		"consumerWalletId" : "${data.consumerWalletId}",
					 		"loyaltyEnabled" : "${data.rewards}",
			       			"allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],
					 		"version": "v6"
							}
						);
					} else {
						MasterPass.client.checkout({
					 		"requestToken":"${data.requestTokenResponse.oauthToken}",
					 		"callbackUrl":"${data.callbackUrl}",
					 		"merchantCheckoutId":"${data.checkoutIdentifier}",
					 		"allowedCardTypes":["${data.acceptedCards}"],
					 		"cardId":cardId,
					 		"shippingId":addressId,
					 		"precheckoutTransactionId":precheckoutTransactionId,
					 		"suppressShippingAddressEnable": supressShipping,
					 		"requestBasicCheckout" : ${data.authLevelBasic},
					 		"walletName" : "${data.walletName}",
					 		"consumerWalletId" : "${data.consumerWalletId}",
					 		"loyaltyEnabled" : "${data.rewards}",
					 		"version": "v6"
							}
						);
					}
					
				}
				
				function updateCheckoutButton(){
					addressId = $("#addressSelect option:selected").val();
					cardId = $("#cardSelect option:selected").val();
					rewardId = $("#rewardSelect option:selected").val();
					console.log("the selected address id is: "+addressId);
					if (express){
						console.log("it's an express checkout")
						$("#expressCheckoutDiv").css({display:"block"});
		            	$("#checkoutButtonDiv").css({display:"none"});
		            	$("#buttonJavaScriptDiv").css({display:"none"});
		            	document.title = $("#title > h1").text('MasterPass Express Pairing Flow').text();
		            	$("#cardSubmit").val(cardId);
		            	$("#addressSubmit").val(addressId);
		            	$("#rewardSubmit").val(rewardId);
		            	console.log($("#cardSubmit").val());
		            	
		            	
					} else {
						$("#expressCheckoutDiv").css({display:"none"});
		            	$("#checkoutButtonDiv").css({display:"block"});
						if (showRewards) {
			            	var sampleButtonString = 'MasterPass.client.checkout({\n\t"element":"checkoutButtonDiv",\n\t"requestToken":"${data.requestTokenResponse.oauthToken}",\n\t"callbackUrl":"${data.callbackUrl}",\n\t"merchantCheckoutId":"${data.checkoutIdentifier}",\n\t"allowedCardTypes":["${data.acceptedCards}"],\n\t"cardId":'+cardId+',\n\t"shippingId":'+addressId+',\n\t"precheckoutTransactionId":'+precheckoutTransactionId+',\n\t"suppressShippingAddressEnable": '+supressShipping+',\n\t"requestBasicCheckout" : ${data.authLevelBasic},\n\t"walletName" : "${data.walletName}",\n\t"consumerWalletId" : "${data.consumerWalletId}",\n\t"loyaltyEnabled" : "${data.rewards}",\n\t"allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],\n\t"version": "v6"\n});'
						} else {
			            	var sampleButtonString = 'MasterPass.client.checkout({\n\t"element":"checkoutButtonDiv",\n\t"requestToken":"${data.requestTokenResponse.oauthToken}",\n\t"callbackUrl":"${data.callbackUrl}",\n\t"merchantCheckoutId":"${data.checkoutIdentifier}",\n\t"allowedCardTypes":["${data.acceptedCards}"],\n\t"cardId":'+cardId+',\n\t"shippingId":'+addressId+',\n\t"precheckoutTransactionId":'+precheckoutTransactionId+',\n\t"suppressShippingAddressEnable": '+supressShipping+',\n\t"requestBasicCheckout" : ${data.authLevelBasic},\n\t"walletName" : "${data.walletName}",\n\t"consumerWalletId" : "${data.consumerWalletId}",\n\t"loyaltyEnabled" : "${data.rewards}",\n\t"version": "v6"\n});'
						}
						$("#sampleCode").text(sampleButtonString);
						
					}
				}
				updateCheckoutButton();
				
				
			</script>
</body>
</html>
