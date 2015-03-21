<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="data" class="com.mastercard.mcwallet.sampleapp.MasterpassData" scope="session">
</jsp:useBean>

<html>
<head>
    <title>
    	Masterpass Standard Checkout Flow
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="Content/Site.css">
    <script type="text/javascript" src="Scripts/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/jquery-1.3.2.min.js"></script> <!-- Needed for tooltips only -->
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
</head>
<body class="postCheckout">
    <div class="page">
        <div id="header">
            <div id="title">
                <h1>
                    Masterpass Standard Checkout Flow </h1>
            </div>
            <div id="logindisplay">
                &nbsp;
            </div>
        </div>
        <div id="main">
            <h1>
                Received Callback from Wallet Site
            </h1>
            <p>
                Data received from the Callback URL<br/>
                Use the Request Token and the Verifier to get the Access Token. The Checkout Resource URL will be used to get the users shipping and/or billing infomation after the Access Token is Received.
            </p>

             <fieldset>
            <legend>Data from the Callback URL</legend>
            <table>
                <tbody>
                    <tr>
                        <th>
                        Request Token 
<!--                         <span class='tooltip' id='requestToken'>[?]</span> -->
                        </th>
                        <td>
                             <code>${data.requestToken }</code>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Request Token Verifier 
<!--                             <span class='tooltip' id='verifier'>[?]</span> -->
                        </th>
                        <td>
                            ${data.verifier }
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Checkout Resource URL 
<!--                             <span class='tooltip' id='checkoutURL'>[?]</span> -->
                        </th>
                        <td>
                            ${data.checkoutResourceURL }
                        </td>
                    </tr>
                    
                    
                </tbody>
               
            </table>
            </fieldset>
            <c:if test="${param.connect == 'true'}">
                  <h1>
                Received Callback from Wallet Site
            </h1>
            <p>
                Data received from the Callback URL<br/>
                Use the Pairing Token and the Pairing Verifier to get the Long Access Token.
            </p>

             <fieldset>
            <legend>Data from the Callback URL</legend>
            <table>
                <tbody>
                    <tr>
                        <th>
                        Pairing Token 
<!--                         <span class='tooltip' id='pairingToken'>[?]</span> -->
                        </th>
                        <td>
                             ${data.pairingToken }
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Pairing Token Verifier 
<!--                             <span class='tooltip' id='pairingVerifier'>[?]</span> -->
                        </th>
                        <td>
                            ${data.pairingVerifier }
                        </td>
                    </tr>
                </tbody>
               
            </table>
            </fieldset>
            <c:choose>
            	<c:when test="${data.longAccessTokenResponse.oauthToken != null}">
            <h1>
                Received Long Access Token
            </h1>
            
            <fieldset>
            <legend>Sent</legend>
            <table>
                <tr>
                        <th>
                            Authorization Header 
<!--                             <span class='tooltip' id='authHeader'>[?]</span> -->
                        </th>
                        <td>                     
							<code>${ data.authHeader }</code>
                        </td>
                </tr> 
                <tr>
                        <th>
                            Signature Base String 
<!--                             <span class='tooltip' id='sbs'>[?]</span> -->
                        </th>
                        
                        <td class="formatUrl">
                        	<hr>
                           <code>${ data.signatureBaseString }</code>
                        </td>
                    </tr>
                    
                </tbody>
            </table>
            </fieldset>
             <fieldset>
            <legend>Sent to:</legend>
            <table>
             <tr>
                        <th>
                            Access Token URL 
<!--                             <span class='tooltip' id='accessEndpoint'>[?]</span> -->
                        </th>
                        <td>
                            ${data.accessURL }
                        </td>
                    </tr>
            </table>
            </fieldset>
            
             <fieldset>
            <legend>Received:</legend>
                       
            <table>
             <tr>
                        <th>
                            Access Token 
<!--                             <span class='tooltip' id='accessToken'>[?]</span> -->
                        </th>
                        <td>
                            ${data.longAccessTokenResponse.oauthToken }
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Oauth Secret 
<!--                             <span class='tooltip' id='oauthSecret'>[?]</span> -->
                        </th>
                        <td>
                            ${data.longAccessTokenResponse.oauthTokenSecret }
                        </td>
                    </tr>
            </table>
            </fieldset>
            </c:when>
            <c:otherwise>
            	<h1 style="color:red">
                Unable to retrieve Long Access Token. You have not successfully paired with Masterpass.
            	</h1>
            </c:otherwise>
            </c:choose>
                 </c:if>
            <form method="POST" action="/getAccessToken">
	            <p>
                    <input value="Retrieve Access Token" type="submit"/>
                    <input  type="hidden" name="OAUTH_TOKEN" id="OAUTH_TOKEN" value="${data.requestToken }"/>
                    <input type="hidden" name="OAUTH_VERIFIER" id="OAUTH_VERIFIER" value="${data.verifier }" />
                    <input type="hidden"name="CHECKOUT_RESOURCE_URL" id="CHECKOUT_RESOURCE_URL" value="${data.checkoutResourceURL }" />
	            </p>
            </form>
        </div>
        <div id="footer">
        </div>
    </div>
</body>
</html>