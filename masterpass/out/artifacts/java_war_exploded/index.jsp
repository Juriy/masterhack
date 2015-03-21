<%@page import="java.net.MalformedURLException"%>
<%@page import="java.util.ArrayList, java.util.List, java.util.Map, java.util.HashMap, java.io.*"%>
<%@page import="com.mastercard.mcwallet.sampleapp.MasterpassData, com.mastercard.mcwallet.sdk.MasterPassServiceRuntimeException"%>
<%@page import="java.io.File"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	session.setAttribute("data", new MasterpassData());
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>MasterPass SDK Sample Application</title>
	<link rel="stylesheet" type="text/css" href="Content/Site.css">
	<script type="text/javascript" src="Scripts/jquery-1.5.1.js"></script> 
	<script type="text/javascript" src="Scripts/index.js"></script>
	<script type="text/javascript" src="Scripts/tooltips/jquery-1.3.2.min.js"></script> <!-- Needed for tooltips only -->
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
	<script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
	<script type="text/javascript">
	
	 
	</script>
</head>
<body>
	
	
	<div class="page">
		<div id="header">
			<div id="title">
				<h1>MasterPass SDK Sample Application </h1>
			</div>
			<div id="logindisplay">&nbsp;</div>
		</div>
		
		<div id="main">
			<h1>Configuration Information</h1>
			<p>Following is the information that will be used to interact with
				the MasterCard API:</p>
				
			<h3 class = "error">
				Please note: For this sample app to work end to end, the host file <u>MUST</u> be updated with the callback URL. See ReadMe file for more details.
				<span class='tooltip' id='hostFile'>[?]</span>
			</h3>
			
			<fieldset>
				<legend>Config File Data</legend>
				<div id="configData">
				<table>
					<tbody>
						<tr>
							<td>
								Consumer Key
								<span class='tooltip' id='conKey'>[?]</span>
							</td>
							<td id="consumerKey">
								${data.consumerKey }
							</td>
						</tr>
						<tr>
							<td>
								Checkout Identifier
									<span class='tooltip' id='chkId'>[?]</span>
							</td>
							<td id="checkoutIdentifier">
								${data.checkoutIdentifier }
							</td>
						</tr>
						<tr>
							<td>
								Keystore Path 
								<span class='tooltip' id='keyStorepath' >[?]</span>	
							</td>
							<td id="keystorePath">
								${data.keystorePath }
							</td>
						</tr>
						<tr>
							<td>
								Keystore Password
								<span class='tooltip' id='keyStorepass'>[?]</span>
							</td>
							<td id="keystorePassword">
								${data.keystorePassword }
							</td>
						</tr>
						<tr>
							<td>
								Callback URL
								<span class='tooltip' id='callback'>[?]</span>
							</td>
							<td id="callBackUrl">
								${data.callbackUrl }
							</td>
						</tr>
						<tr>
							<td>
								Request Token URL
								<span class='tooltip' id='requestEndpoint'>[?]</span>	
							</td>
							<td id="requestUrl">
								${data.requestURL }
							</td>
						</tr>
						<tr>
							<td>
								Shopping Cart URL
								<span class='tooltip' id='shopEndpoint' >[?]</span>
							</td>
							<td id="shoppingCartUrl">
								${data.shoppingCartUrl }
							</td>
						</tr>
						<tr>
							<td>
								Access Token URL
								<span class='tooltip' id='accessEndpoint'>[?]</span>
							</td>
							<td id="accessUrl">
								${data.accessURL }
							</td>
						</tr>
						<tr>
							<td>
								Postback URL
								<span class='tooltip' id='postbackEndpoint'>[?]</span>
							</td>
							<td id="postbackurl">
								${data.postbackurl }
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</fieldset>
			<form id="merchantInfo" name="merchantInfo" method="POST">
				<fieldset>
					<legend>Redirect Options</legend>
					<table id="merchantOptions" class="ui-responsive">
						<tr>
							<td>
							</td>
							<td class="errorText">
								<span id="shippingSuppressionErrorMessage">Shipping Suppression cannot be used when Xml Version is less then v2.</span>
							</td>
							<td class="errorText">
								<span id="rewardsProgramErrorMessage">Rewards Program cannot be used when Xml Version is less then v4.</span>
							</td>
							<td class="errorText">
								<span id="authLevelErrorMessage">Authentication Level Basic cannot be used when Xml Version is less then v3.</span>
							</td>
							<td class="errorText">
								<span id="shippingProfileErrorMessage">Shipping Profiles cannot be used when Xml Version is less then v4.</span>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td>
								XML Version
								<span class='tooltip' id='xmlversion'>[?]</span>
							</td>
							<td>
								<select name="xmlVersionDropdown" id="xmlVersionDropdown">
										<option selected="selected"  value="v6">v6</option>
										<!--<option value="v5">v5</option>
										<optionvalue="v4">v4</option>
										<option value="v3">v3</option>
										<option value="v2">v2</option>
										<option value="v1">v1</option>
										 -->
								</select>
							</td>
							<td>
								Suppress Shipping Address Enable
								<span class='tooltip' id='shippingsuppression'>[?]</span>
							</td>
							<td>
								<select name="shippingSuppressionDropdown" id="shippingSuppressionDropdown">
									<option value="true">True</option>
									<option selected="selected" value="false">False</option>
								</select>
							</td>
							<td>
								Loyalty Enabled
								<span class='tooltip' id='rewards'>[?]</span>
							</td>
							<td>
								<select name="rewardsDropdown" id="rewardsDropdown">
									<option value="true">True</option>
									<option selected="selected" value="false">False</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								Request Basic Checkout
								<span class='tooltip' id='authlevel'>[?]</span>
							</td>
							<td>
								<input type="checkbox" name="authenticationCheckBox" id="authenticationCheckBox">
							</td>
							<!--<td>
								Shipping Profile ID
								<span class='tooltip' id=shippingprofile>[?]</span>
							</td>
							<td>
								<select name="shippingProfileDropdown" id="shippingProfileDropdown">
								<option value="${data.shippingProfiles}">No Shipping Preference</option>
								<c:forEach var="shippingLocation" items="${fn:split(data.shippingProfiles,',')}">
										<c:if test="${fn:length( shippingLocation ) > 0 }">
									       <option value="<c:out value="${shippingLocation}"/>" ><c:out value="${shippingLocation}"/></option>
								        </c:if>
							    	</c:forEach>
								</select>
							</td> -->
						</tr>
						<tr>
							<td>
								Allowed Card Types
								<span class='tooltip' id='acceptedcards'>[?]</span>
							</td>
							<td width=150>
								<table >
									<tr>
										<td>
											<input type="checkbox" name="acceptedCardsCheckbox[]" value="master" id="master" checked="checked">MasterCard 
										</td>
										<td>
											<input type="checkbox" name="acceptedCardsCheckbox[]" value="amex" id="amex" checked="checked">Amex
										</td>
										<td>	
											<input type="checkbox" name="acceptedCardsCheckbox[]" value="diners" id="diners" checked="checked">Diners
										</td>
									</tr>
									<tr>
										<td>	 
											<input type="checkbox" name="acceptedCardsCheckbox[]" value="discover" id="discover" checked="checked">Discover
										</td>
										<td>	 
											<input type="checkbox" name="acceptedCardsCheckbox[]" value="maestro" id="maestro" checked="checked">Maestro
										</td>
										<td>	 
											<input type="checkbox" name="acceptedCardsCheckbox[]" value="visa" id="visa" checked="checked">Visa
										</td>
									</tr>
								</table>
							</td>
							<td>
								Private Label Card
								<span class='tooltip' id='privatelabel'>[?]</span>
							</td>
							<td>
								<input type="text" name="privateLabelText" id="privateLabelText">
							</td>	
						</tr>
					</table>
				</fieldset>
				<fieldset>
					<legend>User Flows</legend>
					<p>Click the Checkout, Pairing, or Cart Example buttons below to begin an SDK demo.</p>
					<input id="checkout" value="Checkout Flow" type="submit"> 
					<input id="pairing" value="Pairing Flow" type="submit">
					<input id="example" value="Cart Example Flow" type="submit">

				</fieldset>
				
			</form>
		</div>
		<div id="footer"></div>
	</div>
	<script>
		$(document).ready(function(){
			
			$('#profileName').change(function() {  
			var profileName = $("#profileName").val();
			$.ajax({
				type : "POST",
				url :"ProfileSelServlet",
				cache : false,
				dataType: "json",
				data : {"profileName":profileName} ,
				success : function(data) {
					var jsonOBJ = data;
					var consumerKey = jsonOBJ["consumerKey"];
					var checkoutIdentifier = jsonOBJ["checkoutIdentifier"];
					var keystorePath = jsonOBJ["keystorePath"];
					var keystorePassword = jsonOBJ["keystorePassword"];
					var callbackUrl = jsonOBJ["callbackUrl"];
					var requestUrl = jsonOBJ["requestURL"];
					var shoppingCartUrl = jsonOBJ["shoppingCartUrl"];
					var accessURL = jsonOBJ["accessURL"];
					var postbackurl = jsonOBJ["postbackurl"];
					
						$('#configData').html('');
						$('#configData').append('<table><tbody>');
						$('#configData').append('<tr><td>Consumer Key <span class="tooltip" id="conKey">[?]</span></td><td>&nbsp;</td><td id="consumerKey">' + consumerKey + '</td></tr>');
						$('#configData').append('<tr></tr>');
 						$('#configData').append('<tr><td>Checkout Identifier <span class="tooltip" id="chkId">[?]</span></td><td>&nbsp;</td><td id="checkoutIdentifier">' + checkoutIdentifier + '</td></tr>');
 						$('#configData').append('<tr></tr>');
						$('#configData').append('<tr><td>Keystore Path <span class="tooltip" id="keyStorepath">[?]</span></td><td>&nbsp;</td><td id="keystorePath">' + keystorePath + '</td></tr>');
						$('#configData').append('<tr></tr>');
						$('#configData').append('<tr><td>Keystore Password <span class="tooltip" id="keyStorepass">[?]</span></td><td>&nbsp;</td><td id="keystorePassword">' + keystorePassword + '</td></tr>');
						$('#configData').append('<tr></tr>');
						$('#configData').append('<tr><td>Callback URL <span class="tooltip" id="callback">[?]</span></td><td>&nbsp;</td><td id="callBackUrl">' + callbackUrl + '</td></tr>');
						$('#configData').append('<tr></tr>');
						$('#configData').append('<tr><td>Request Token URL <span class="tooltip" id="requestEndpoint">[?]</span></td><td>&nbsp;</td><td id="requestUrl">' + requestUrl + '</td></tr>');
						$('#configData').append('<tr></tr>');
						$('#configData').append('<tr><td>Shopping Cart URL <span class="tooltip" id="shopEndpoint">[?]</span></td><td>&nbsp;</td><td id="shoppingCartUrl">' + shoppingCartUrl + '</td></tr>');
						$('#configData').append('<tr></tr>');
						$('#configData').append('<tr><td>Access Token URL <span class="tooltip" id="accessEndpoint">[?]</span></td><td>&nbsp;</td><td id="accessUrl">' + accessURL + '</td></tr>');
						$('#configData').append('<tr></tr>');
						$('#configData').append('<tr><td>Postback URL <span class="tooltip" id="postbackEndpoint">[?]</span></td><td>&nbsp;</td><td id="postbackurl">' + postbackurl + '</td></tr>');
						$('#configData').append('<tr></tr>');
						$('#configData').append('</tbody></table>');
						
						$('#conKey').qtip(ToolTipWithLink( '/Tooltips/ConsumerKey.html','Consumer Key',900));
						$('#chkId').qtip(ToolTipWithLink('/Tooltips/CheckoutID.html','Checkout Identifier',900));
						$('#keyStorepath').qtip(ToolTip('Private Key is managed by the merchant and corresponding public key is stored on MasterPass side. Key exchange happens on MasterCard developer zone (https://developer.mastercard.com). Every call to MasterPass has to be signed by the merchant\'s private key.',900));
						$('#keyStorepass').qtip(ToolTip('Private Key is managed by the merchant and corresponding public key is stored on MasterPass side. Key exchange happens on MasterCard developer zone (https://developer.mastercard.com). Every call to MasterPass has to be signed by the merchant\'s private key.',900));
						$('#callback').qtip(ToolTipWithLink('/Tooltips/CallbackURL.html','Callback URL',900));
						$('#requestEndpoint').qtip(ToolTip('Endpoint to the request the request token from MasterPass services. <br>Sandbox URL: https://sandbox.api.mastercard.com/oauth/consumer/v1/request_token <br>MTF URL: https://api.mastercard.com/oauth/consumer/v1/request_token <br>Stage URL: https://stage.api.mastercard.com/oauth/consumer/v1/request_token',600));
						$('#shopEndpoint').qtip(ToolTip('Endpoint to the post the shopping cart data to MasterPass services.<br>Sandbox URL: https://sandbox.api.mastercard.com/masterpass/v6/shopping-cart <br>MTF URL: https://api.mastercard.com/mtf/masterpass/v6/shopping-cart <br>Stage URL: https://stage.api.mastercard.com/masterpass/v6/shopping-cart',600));
						$('#accessEndpoint').qtip(ToolTip('Endpoint to the request the access token from MasterPass services.<br>Sandbox URL: https://sandbox.api.mastercard.com/oauth/consumer/v1/access_token <br>MTF URL: https://api.mastercard.com/oauth/consumer/v1/access_token <br>Stage URL: https://stage.api.mastercard.com/oauth/consumer/v1/access_token',600));
						$('#postbackEndpoint').qtip(ToolTip('Endpoint to the post the post transaction data to MasterPass services.<br>Sandbox URL: https://sandbox.api.mastercard.com/masterpass/v6/transaction <br>MTF URL: https://api.mastercard.com/mtf/masterpass/v6/transaction <br>Stage URL: https://stage.api.mastercard.com/masterpass/v6/transaction',500));
						 
						
					}
					
			});
            });
			});
            
    </script>

	<script>
	
	$('#checkout').click(function(event) {
		 var checkedCheckboxes = $('input[name="acceptedCardsCheckbox[]"]:checked').length;
		 
			if(checkedCheckboxes == 0){
				alert("There are no Cards selected");
					event.preventDefault();
			}
			else{
				$("#merchantInfo").attr("action", "/oauth");
				$("#merchantInfo").submit();
			}
	});
		
	$('#pairing').click(function(event) {
		var checkedCheckboxes = $('input[name="acceptedCardsCheckbox[]"]:checked').length;
		 
		if(checkedCheckboxes == 0){
			alert("There are no Cards selected");
				event.preventDefault();
		}
		else{
			$("#merchantInfo").attr("action", "/Pairing");
			$("#merchantInfo").submit();
		}
	});
	
	
	$('#example').click(function(event) {
		var checkedCheckboxes = $('input[name="acceptedCardsCheckbox[]"]:checked').length;
		 
		if(checkedCheckboxes == 0){
			alert("There are no Cards selected");
				event.preventDefault();
		}
		else{
			$("#merchantInfo").attr("action", "/Cart");
			$("#merchantInfo").submit();
		}
	});
	
	
	</script>

</body>
</html>