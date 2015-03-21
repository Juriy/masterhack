<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="data" class="com.mastercard.mcwallet.sampleapp.MasterpassData" scope="session">
</jsp:useBean>


<html>
<head>
	<title>Shopping Cart Sample Flow</title>
	<link rel="stylesheet" type="text/css" href="Content/Site.css" />
	<script type="text/javascript" src="Scripts/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="Scripts/common.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/jquery-1.3.2.min.js"></script> <!-- Needed for tooltips only -->
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
	<script type="text/javascript" src="${data.lightboxUrl}"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body class="cart">
	<div class="page">
		<div id="header">
			<div id="title">
				<h1>Shopping Cart Sample Flow</h1>
			</div>
			<div id="logindisplay">&nbsp;</div>
		</div>
		<div id="main">
			<div id="reviewOrder">
			 <div>
                    <fieldset>
                        <legend>Shopping Cart</legend>
				<table>
					<tr>
						<td class="centerText" colspan="3">Description</td>
						<td colspan="2">Price</td>
						<td class="centerText" colspan="2">Quantity</td>
						<td class="textFloatRight">Total</td>
					</tr>
					
					<c:forEach var="entry" items="${data.shoppingCart.shoppingCart.shoppingCartItem}">
						<tr id="items">
						<td><img alt="Widget_Icon" id="imageSize" src="<c:out value="${entry.imageURL}"/>" /></td>
						<td colspan="2"><c:out value="${entry.description}"/></td>
						<td colspan="2"><fmt:formatNumber value="${entry.value/entry.quantity/100}"  type="currency"/></td>
						<td class="centerText" colspan="2"><c:out value="${entry.quantity}"/><br /></td>
						<td class="textFloatRight"><fmt:formatNumber value="${entry.value/100}" type="currency"/></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="8">
							<div id="charge-container">
								<ul id="charges">
									<li id="subtotal"><span>Subtotal: </span><fmt:formatNumber value="${data.shoppingCart.shoppingCart.subtotal/100 }" type="currency"/></li>
									<li id="shipping"><span>Estimated Shipping: </span><fmt:formatNumber value="${data.shipping/100 }" type="currency"/></li>
									<li id="tax"><span>Estimated Tax: </span><fmt:formatNumber value="${data.tax/100 }" type="currency"/></li>
									<li id="total"><span>Total: </span><fmt:formatNumber value="${(data.shoppingCart.shoppingCart.subtotal + data.tax + data.shipping)/100 }" type="currency"/></li>
								</ul>
							</div>
						</td>
					</tr>
					<c:if test="${ data.errorMessage != null }">
						<tr>
							<td>
								<div class = "error">
								Error when connecting to MasterCard Wallet
								</div>
							</td>
						</tr>
					</c:if>
				</table>
				</fieldset>
				<div style="padding-bottom: 20px" >
						<div id="checkoutButtonDiv" onClick="handleBuyWithMasterPass()">
							<a href="#">
								<img src="https://www.mastercard.com/mc_us/wallet/img/en/US/mcpp_wllt_btn_chk_147x034px.png" alt="Buy with MasterPass">
							</a>
						</div>
						<p><a href="http://www.mastercard.com/mc_us/wallet/learnmore/en" target="_blank">Learn More</a></p>
					
				</div>
				<div>
            	<fieldset>
            		<legend>Javascript</legend>
            			
<pre><code id="sampleCode">
</code></pre>
            		</fieldset>
            	</div>
				
				<!-- <div id="labelCallbackData">
	                <div class="labelData">
	                    <label>Oauth Token</label>
	                    <input type="text" id="oauthToken" maxlength="20" autocomplete="off" value="" disabled="" class="inputData">
	                </div>
	                <div class="labelData">
	                    <label>Oauth Verifier</label>
	                    <input type="text" id="oauthVerifer" maxlength="20" autocomplete="off" value="" disabled="" class="inputData">
	                </div>
	                <div class="labelData">
	                    <label>Checkout Resource Url</label>
	                    <input type="text" id="checkoutUrl" maxlength="20" autocomplete="off" value="" disabled="" class="inputData">
	                </div>
  				</div>-->
				</div>
			</div>
		</div>
		<div id="footer"></div>
	</div>
	<script type="text/javascript" language="Javascript">
		var showRewards;
		$(document).ready(function(){
			showRewards = ${data.rewards} == true;
			var code;
			if (showRewards){
				code = 'MasterPass.client.checkout({\n\t"requestToken":${data.requestTokenResponse.oauthToken},\n\t"callbackUrl":${data.callbackUrl},\n\t"merchantCheckoutId":${data.checkoutIdentifier},\n\t"allowedCardTypes":${data.acceptedCards},\n\t"cancelCallback":${data.callbackDomain},\n\t"suppressShippingAddressEnable":${data.shippingSuppression},\n\t"loyaltyEnabled":${data.rewards},\n\t"allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],\n\t"requestBasicCheckout" : "${data.authLevelBasic}",\n\t"version":"v6"\n});'
			} else {
				code = 'MasterPass.client.checkout({\n\t"requestToken":${data.requestTokenResponse.oauthToken},\n\t"callbackUrl":${data.callbackUrl},\n\t"merchantCheckoutId":${data.checkoutIdentifier},\n\t"allowedCardTypes":${data.acceptedCards},\n\t"cancelCallback":${data.callbackDomain},\n\t"suppressShippingAddressEnable":${data.shippingSuppression},\n\t"loyaltyEnabled" :"${data.rewards}",\n\t"requestBasicCheckout":"${data.authLevelBasic}",\n\t"version":"v6"\n});'

			}
			$("#sampleCode").text(code);
		});
	
		function handleBuyWithMasterPass() {
			if (showRewards){
				MasterPass.client.checkout({
	       			 "requestToken":"${data.requestTokenResponse.oauthToken}",
	       			 "callbackUrl":"${data.callbackUrl}",
	       			 "merchantCheckoutId":"${data.checkoutIdentifier}",
	       			 "allowedCardTypes":"${data.acceptedCards}",
	       			 "cancelCallback" : "${data.callbackDomain}",
	       			 "suppressShippingAddressEnable":"${data.shippingSuppression}",
	       			 "loyaltyEnabled" :"${data.rewards}",
	       			 "allowedLoyaltyPrograms":["${data.allowedLoyaltyPrograms}"],
	       			 "requestBasicCheckout" : ${data.authLevelBasic},
	       		 	 "version":"v6"
	       			}
	       		);
			} else {
				MasterPass.client.checkout({
	       			 "requestToken":"${data.requestTokenResponse.oauthToken}",
	       			 "callbackUrl":"${data.callbackUrl}",
	       			 "merchantCheckoutId":"${data.checkoutIdentifier}",
	       			 "allowedCardTypes":"${data.acceptedCards}",
	       			 "cancelCallback" : "${data.callbackDomain}",
	       			 "suppressShippingAddressEnable":"${data.shippingSuppression}",
	       			 "loyaltyEnabled" :"${data.rewards}",
	       			 "requestBasicCheckout" : ${data.authLevelBasic},
	       		 	 "version":"v6"
	       			}
	      		 );
			}
			
		}
		
	</script>
</body>
</html>