<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="data" class="com.mastercard.mcwallet.sampleapp.MasterpassData" scope="session">
</jsp:useBean>

<html>
<head>
	<title>
		Shopping Cart Sample Flow
	</title>
	<META content="text/html; charset=utf-8" http-equiv="Content-Type">
	<link rel="stylesheet" type="text/css" href="Content/Site.css" />
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
						</table>
					</fieldset>
					<fieldset>
						<legend>Shipping Address</legend>
						<table>
							<tbody>
								<tr>
									<th>
										<label for="ShippingAddress_RecipientName"> Recipient Name:</label>&nbsp;
									</th>
									<td>
										${data.checkout.shippingAddress.recipientName }
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
									<td>&nbsp;</td>
									<td>
										${data.checkout.shippingAddress.city }  ${data.checkout.shippingAddress.countrySubdivision } ${data.checkout.shippingAddress.postalCode }
										<br>
										${data.checkout.shippingAddress.country }
									</td>
								</tr>
								<tr>
									<th>
										<label for="ShippingAddress_RecipientPhoneNumber">
											Recipient Phone Number:</label>
									</th>
									<td>
										${data.checkout.shippingAddress.recipientPhoneNumber }
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
									<th>
										<label for="Contact_Gender"> Gender:</label>
									</th>
									<td>
										${data.checkout.contact.gender }
									</td>
								</tr>	
								<tr>
									<th>
										<label for="Contact_DOB"> Date of Birth:</label>
									</th>
									<td>
										${data.checkout.contact.dateOfBirth.month }
										<c:if test="${ data.checkout.contact.dateOfBirth.month != null }">/</c:if>
										${data.checkout.contact.dateOfBirth.day }
										<c:if test="${ data.checkout.contact.dateOfBirth.day != null }">/</c:if>
										${data.checkout.contact.dateOfBirth.year }
									</td>
								</tr>
								<tr>
									<th>
										<label for="Contact_NationalID"> National ID:</label>
									</th>
									<td>
										${data.checkout.contact.nationalID }
									</td>
								</tr>	
								<tr>
									<th>
										<label for="Contact_Country"> Country:</label>
									</th>
									<td>
										${data.checkout.contact.country }
									</td>
								</tr>																														
								<tr>
									<th>
										<label for="Contact_PhoneNumber"> Phone Number:</label>
									</th>
									<td>
										${data.checkout.contact.phoneNumber }
									</td>
								</tr>
								<tr>
									<th>
										<label for="Contact_EmailAddress"> Email Address:</label>
									</th>
									<td>
										${data.checkout.contact.emailAddress }
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
									<th>
										<label for="Card_CardHolderName"> Cardholder Name:</label>
									</th>
									<td>
										${data.checkout.card.cardHolderName }
									</td>
								</tr>
								<tr>
									<th>
										<label for="Card_BrandName">Brand Name:</label>
									</th>
									<td>
										${data.checkout.card.brandName }
									</td>
								</tr>
								<tr>
									<th>
										<label for="Card_ExpiryDate"> Expiration Date:</label>
									</th>
									<td>
										${data.checkout.card.expiryMonth }/${data.checkout.card.expiryYear }
									</td>
								</tr>
								<tr>
									<th>
										<label for="Card_AccountNumber"> Account Number:</label>
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
										${data.checkout.card.billingAddress.city }  ${data.checkout.card.billingAddress.countrySubdivision }
				                   		${data.checkout.card.billingAddress.country }
				                   		 <br>
				                     	${data.checkout.card.billingAddress.postalCode }
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
									<th>
										<label for="RewardsProgram_RewardsName">Rewards Name:</label>
									</th>
									<td>
										${data.checkout.rewardProgram.rewardName }
									</td>
								<tr>
									<th>
										<label for="RewardsProgram_RewardsNumber">Rewards Number:</label>
									</th>
									<td>
										${data.checkout.rewardProgram.rewardNumber }
									</td>
								<tr>
									<th>
										<label for="RewardsProgram_ExpiryDate">Rewards Expiration Date:</label>
									</th>
									<td>
										${data.checkout.rewardProgram.expiryMonth }
										<c:if test="${ data.checkout.rewardProgram.expiryMonth != null }">/</c:if>
										${data.checkout.rewardProgram.expiryYear }
									</td>
								</tr>				
							</tbody>
						</table>
					</fieldset>
					&nbsp;&nbsp;
					<table>
						<tbody>
							<tr>
								<td>
									<form action="./CartPostback" method="POST">
										<input type="submit" value="Place Order" />
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>