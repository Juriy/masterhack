<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/jquery-1.3.2.min.js"></script> <!-- Needed for tooltips only -->
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
</head>
<body class="pairing">
    <div class="page">
        <div id="header">
            <div id="title">
                <h1>
                    MasterPass Connect Pairing Checkout Flow </h1>
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
                Unable to retrieve Long Access Token. You will not be able to request Precheckout Data.
            	</h1>
            </c:otherwise>
            </c:choose>
            <script>
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
	            	if (locationParams.express) {
		            	console.log("express is true");
		            	$(".pairing").toggleClass("pairing express");
		            	$(".expressCheckoutDiv").css({display:"block"});
		            	$(".preCheckoutDiv").css({display:"none"});
		            	$(".silentExpressCheckoutDiv").css({display:"none"});
		            	document.title = $("#title > h1").text('MasterPass Express Pairing Flow').text();
		            } else {
		            	$(".expressCheckoutDiv").css({display:"none"});
		            	$(".silentExpressCheckoutDiv").css({display:"none"});
		            	$(".preCheckoutDiv").css({display:"block"});
		            }
	            })
            </script>
            <c:if test="${data.longAccessTokenResponse.oauthToken != null }">
            
            
            <div class="preCheckoutDiv">
	            <form id="preCheckoutForm" method="POST" action="/PreCheckout">
		            <p>
	                    <input value="Retrieve Pre Checkout Data" type="submit">
		            </p>
		            <input id="OAUTH_TOKEN" name="OAUTH_TOKEN" value="${data.longAccessTokenResponse.oauthToken }" type="hidden">
	            </form>
            </div>
            <div class="expressCheckoutDiv">
	            <form id="expressCheckoutForm" method="POST" action="/PreCheckout?express=true">
	            <p>
	                    <input value="Retrieve Pre Checkout Data" type="submit">
		            </p>
		            <input id="OAUTH_TOKEN" name="OAUTH_TOKEN" value="${data.longAccessTokenResponse.oauthToken }" type="hidden">
	            </form>
            </div>

			</c:if>
        </div>
        <div id="footer">
        </div>
    </div>
</body>
</html>