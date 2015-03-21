<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="data" class="com.mastercard.mcwallet.sampleapp.MasterpassData" scope="session">
</jsp:useBean>
<html>
<head>
    <title>
    	MasterPass Standard Flow
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="Content/Site.css">
    <script type="text/javascript" src="Scripts/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="Scripts/common.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/jquery-1.3.2.min.js"></script> <!-- Needed for tooltips only -->
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
	<script type="text/javascript" src="${data.lightboxUrl }"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	
	
</head>
<body class="standard">
    <div class="page">
        <div id="header">
            <div id="title">
                <h1>
                    MasterPass Standard Flow
                </h1>
            </div>
            <div id="logindisplay">
                &nbsp;
            </div>
            
        </div>
        <div id="main">
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
                <fieldset>
	                <legend>Standard Checkout</legend>
		            <br/>
		            <div style="padding-bottom: 20px">
						<div id="checkoutButtonDiv" >
							<a href="#" onClick="handleBuyWithMasterPass()">
								<img src="https://www.mastercard.com/mc_us/wallet/img/en/US/mcpp_wllt_btn_chk_147x034px.png" alt="Buy with MasterPass">
							</a>
						</div>
						<p><a href="http://www.mastercard.com/mc_us/wallet/learnmore/en" target="_blank">Learn More</a></p>
					</div>
					<div>
            		<fieldset>
            		<legend>Javascript</legend>
            			<pre><code id="sampleCode"></code></pre>
            		</fieldset>
            	</div>
				</fieldset>
				<fieldset>
					<legend>Connected Checkout</legend>
					<br/>
					<div id="pairingCheckoutDiv">
						<form id="pairingCheckoutForm" method="POST">
							<input id="pairingCheckout" value="Checkout with Pairing Flow" type="submit">
						</form>
	                </div>
				</fieldset>
                
	            
	        <script>
	        var showRewards = ${data.rewards} == true;
	        $(document).ready(function(){
	        	console.log("document ready")
	        	
	        	if (showRewards){
		        	var sampleCodeString = 'MasterPass.client.checkout({\n\t"requestToken":${data.requestTokenResponse.oauthToken},\n\t"callbackUrl":${data.callbackUrl},\n\t"merchantCheckoutId":${data.checkoutIdentifier},\n\t"allowedCardTypes":${data.acceptedCards},\n\t"cancelCallback":${data.callbackDomain},\n\t"suppressShippingAddressEnable":${data.shippingSuppression},\n\t"loyaltyEnabled":${data.rewards},\n\t"allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],\n\t"requestBasicCheckout" : ${data.authLevelBasic},\n\t"version":"v6"\n});';
	        	} else {
		        	var sampleCodeString = 'MasterPass.client.checkout({\n\t"requestToken":${data.requestTokenResponse.oauthToken},\n\t"callbackUrl":${data.callbackUrl},\n\t"merchantCheckoutId":${data.checkoutIdentifier},\n\t"allowedCardTypes":${data.acceptedCards},\n\t"cancelCallback":${data.callbackDomain},\n\t"suppressShippingAddressEnable":${data.shippingSuppression},\n\t"loyaltyEnabled":${data.rewards},\n\t"requestBasicCheckout" : ${data.authLevelBasic},\n\t"version":"v6"\n});';
	        	}
	        	$("#sampleCode").text(sampleCodeString);
	        	$.get("/checkoutCallback", function(data){
	        		
	        		var stringify = JSON.stringify(data);
	        		console.log(stringify);
	        		
	        	})
	        	
	        })
	        
	        $('#pairingCheckout').click(function(event) {
					$("#pairingCheckoutForm").attr("action", "/Pairing?checkout=true");
					$("#pairingCheckoutForm").submit();
			});
	        
	        function handleBuyWithMasterPass() {
	        	if (showRewards){
	        		console.log("showing rewards")
	        		MasterPass.client.checkout({
		       			 "requestToken":"${data.requestTokenResponse.oauthToken}",
		       			 "callbackUrl":"${data.callbackUrl}",
		       			 "merchantCheckoutId":"${data.checkoutIdentifier}",
		       			 "allowedCardTypes":"${data.acceptedCards}",
		       			 "cancelCallback" : "${data.callbackDomain}",
		       			 "suppressShippingAddressEnable": "${data.shippingSuppression}",
		       			 "loyaltyEnabled" : "${data.rewards}",
		       			 "allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],
		       			 "requestBasicCheckout" : ${data.authLevelBasic},
		       		 	 "version":"v6"
		       		});
	        	} else {
	        		console.log("not showing rewards")
	        		MasterPass.client.checkout({
		       			 "requestToken":"${data.requestTokenResponse.oauthToken}",
		       			 "callbackUrl":"${data.callbackUrl}",
		       			 "merchantCheckoutId":"${data.checkoutIdentifier}",
		       			 "allowedCardTypes":"${data.acceptedCards}",
		       			 "cancelCallback" : "${data.callbackDomain}",
		       			 "suppressShippingAddressEnable": "${data.shippingSuppression}",
		       			 "loyaltyEnabled" : "${data.rewards}",
		       			 "requestBasicCheckout" : ${data.authLevelBasic},
		       		 	 "version":"v6"
		       		});
	        	}
	        }
	        
	        
       </script> 
        
        </div>
    </div>
</body>
</html>
