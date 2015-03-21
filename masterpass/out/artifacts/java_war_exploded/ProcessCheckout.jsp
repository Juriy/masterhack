<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="data" class="com.mastercard.mcwallet.sampleapp.MasterpassData" scope="session">
</jsp:useBean>
<html>
<head>
	<title>
		MasterPass Standard Checkout Flow
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="Content/Site.css">
    <script type="text/javascript" src="Scripts/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="Scripts/common.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/jquery-1.3.2.min.js"></script> <!-- Needed for tooltips only -->
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
</head>
<body class="postCheckout">
	<div class="page">
		<div id="header">
			<div id="title">
				<h1>MasterPass Standard Checkout Flow</h1>
			</div>
			<div id="logindisplay">&nbsp;</div>
			
		</div>
		<div id="main">
			<h1>Retrieved Checkout XML</h1>
<c:if test="${ data.errorMessage != null }">
	<div class = "error">	
		<h2>Error</h2>
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
				</table>
			</fieldset>

			<fieldset>
				<legend>Sent To:</legend>
				<table>
					<tr>
						<th>
							Checkout Resource URL 
<!-- 							<span class='tooltip' id='checkoutEndpoint'>[?]</span> -->
						</th>
						<td>
							${data.checkoutResourceURL }
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>Received:</legend>
				<table>
					<tr>
					<th>
						Checkout XML 
<!-- 						<span id='chekoutXML'>[?]</span> -->
					</th>
						<td><pre>
<code>
${data.checkoutXML }
</code>
</pre>
						</td>
					</tr>
				</table>
			</fieldset>

			<h2>
				Sample Form  
<!-- 				<span class='tooltip' id='sampleForm'>[?]</span> -->
			</h2>
			<div>
				<form method="POST" action="./Postback">
					<p>
						<input value="Post Transaction To MasterCard" type="submit">
					</p>
				</form>
				<h2>Results</h2>
				<p>Following are the results returned after retrieving Shipping
					Address &amp; Credit Card information from the Wallet.</p>

				<fieldset>
					<legend>General Information</legend>
					<table>
						<tbody>
							<tr>
								<th><label for="TransactionId"> Transaction Id:</label>
								</th>
								<td>
									${data.checkout.transactionId }
								</td>
							</tr>
							
						</tbody>
					</table>
				</fieldset>
				<fieldset>
					<legend>Card Information</legend>
					<table>
						<tbody>
							<tr>
								<th><label for="Card_CardHolderName"> Cardholder Name:</label>
								</th>
								<td>
									${data.checkout.card.cardHolderName }
								</td>
							</tr>
							<tr>
								<th><label for="Card_AccountNumber"> Account Number:</label>
								</th>
								<td>
									${data.checkout.card.accountNumber }
								</td>
							</tr>
							<tr>
								<th>Billing Address:</th>
								<td>
									${data.checkout.card.billingAddress.line1 }
									<br> 
									${data.checkout.card.billingAddress.line2 }
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									${data.checkout.card.billingAddress.city } ${data.checkout.card.billingAddress.countrySubdivision } ${data.checkout.card.billingAddress.postalCode }
									<br>
									${data.checkout.card.billingAddress.country }
								</td>
							</tr>
							<tr>
								<th><label for="Card_ExpiryDate"> Expiration Date:</label>
								</th>
								<td>
									<c:if test="${data.checkout.card != null }">
									  ${data.checkout.card.expiryMonth }/${data.checkout.card.expiryYear }
									  </c:if>
								</td>
							</tr>
							<tr>
								<th><label for="Card_BrandId">Brand Id:</label>
								</th>
								<td>
									${data.checkout.card.brandId }
								</td>
							</tr>
							<tr>
								<th><label for="Card_BrandName">Brand Name:</label>
								</th>
								<td>
									${data.checkout.card.brandName }
								</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
				<fieldset>
					<legend>Contact Information</legend>
					<table>
						<tbody>
							<tr>
								<th>Name:</th>
								<td>
									${data.checkout.contact.firstName } ${data.checkout.contact.lastName }
								</td>
							</tr>
							<tr>
								<th>Gender:</th>
								<td>
									${data.checkout.contact.gender }
								</td>
							</tr>	
							<tr>
								<th>Date of Birth:</th>
								<td>
									<c:if test="${data.checkout.contact.dateOfBirth } != null }">
										${ data.checkout.contact.dateOfBirth.month } / ${data.checkout.contact.dateOfBirth.day } / ${data.checkout.contact.dateOfBirth.year } 
									</c:if>
								</td>
							</tr>
							<tr>
								<th>National ID:</th>
								<td>
									${ data.checkout.contact.nationalID }
								</td>
							</tr>			
							<tr>
								<th>Country:</th>
								<td>
									${ data.checkout.contact.country }
								</td>
							</tr>																									
							<tr>
								<th><label for="Contact_PhoneNumber"> Phone Number:</label>
								</th>
								<td>
									${data.checkout.contact.phoneNumber }
								</td>
							</tr>
							<tr>
								<th><label for="Contact_EmailAddress"> Email Address:</label>
								</th>
								<td>
									${data.checkout.contact.emailAddress }
								</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
				<fieldset>
					<legend>Shipping Address</legend>
					<table>
						<tbody>
							<tr>
								<th><label for="ShippingAddress_RecipientName"> Recipient Name:</label>
								</th>
								<td>
									${data.checkout.shippingAddress.recipientName }
								</td>
							</tr>
							<tr>
								<th><label for="ShippingAddress_RecipientPhoneNumber"> Recipient
										Phone Number:</label>
								</th>
								<td>
									${data.checkout.shippingAddress.recipientPhoneNumber }
								</td>
							</tr>
							<tr>
								<th>Address:</th>
								<td>
									${data.checkout.shippingAddress.line1 }
									<br>
									${data.checkout.shippingAddress.line2 }
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									${data.checkout.shippingAddress.city } ${data.checkout.shippingAddress.countrySubdivision } ${data.checkout.shippingAddress.postalCode }
									<br>
									${data.checkout.shippingAddress.country }
								</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
				<fieldset>
					<legend>Rewards Program</legend>
					<table>
						<tbody>
							<tr>
								<th><label for="RewardProgram_RewardNumber">Reward Number:</label>
								</th>
								<td>
									${data.checkout.rewardProgram.rewardNumber }
								</td>
							</tr>
							<tr>
								<th><label for="RewardProgram_RewardsId">Rewards Id:</label>
								</th>
								<td>
									${data.checkout.rewardProgram.rewardId }
								</td>
							</tr>
							<tr>
								<th><label for="RewardProgram_RewardName">Reward Name:</label>
								</th>
								<td>
									${data.checkout.rewardProgram.rewardName }
								</td>
							</tr>
							<tr>
								<th><label for="RewardProgram_ExpiryMonth">Expiry Month:</label>
								</th>
								<td>
									${data.checkout.rewardProgram.expiryMonth }
								</td>
							</tr>
							<tr>
								<th><label for="RewardProgram_ExpiryYear">Expiry Year:</label>
								</th>
								<td>
									${data.checkout.rewardProgram.expiryYear }
								</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
				<fieldset>
					<legend>Advanced Authentication (3DS)</legend>
					<table>
						<tbody>
							<tr>
								<th><label for="AuthenticateMethod">Authenticate Method:</label>
								</th>
								<td>
									${data.checkout.authenticationOptions.authenticateMethod }
								</td>
							</tr>
							<tr>
								<th><label for="CardEnrollmentMethod">Card Enrollment Method:</label>
								</th>
								<td>
									${data.checkout.authenticationOptions.cardEnrollmentMethod }
								</td>
							</tr>
							<tr>
								<th><label for="CAvv">CAvv:</label>
								</th>
								<td>
									${data.checkout.authenticationOptions.CAvv }
								</td>
							</tr>
							<tr>
								<th><label for="eciFlag">EciFlag:</label>
								</th>
								<td>
									${data.checkout.authenticationOptions.eciFlag }
								</td>
							</tr>
							<tr>
								<th><label for="MasterCardAssignedID">Master Card Assigned Id</label>
								</th>
								<td>
									${data.checkout.authenticationOptions.masterCardAssignedID }
								</td>
							</tr>
							<tr>
								<th><label for="paResStatus">PaResStatus:</label>
								</th>
								<td>
									${data.checkout.authenticationOptions.paResStatus }
								</td>
							</tr>
							<tr>
								<th><label for="SCEnrollmentStatus">SCEnrollmentStatus:</label>
								</th>
								<td>
									${data.checkout.authenticationOptions.SCEnrollmentStatus }	
								</td>
							</tr>
							<tr>
								<th><label for="signatureVerification">SignatureVerification:</label>
								</th>
								<td>
									${data.checkout.authenticationOptions.signatureVerification }
								</td>
							</tr>
							<tr>
								<th><label for="xid">Xid:</label>
								</th>
								<td>
									${data.checkout.authenticationOptions.xid }${data.checkout.authenticationOptions.xid }
								</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
			</div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>