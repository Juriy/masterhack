<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

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
    <script type="text/javascript" src="Scripts/common.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/jquery-1.3.2.min.js"></script> <!-- Needed for tooltips only -->
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
</head>
<body class="postCheckout">
    <div class="page">
        <div id="header">
            <div id="title">
                <h1>
                   Masterpass Standard Checkout Flow</h1>
            </div>
            <div id="logindisplay">
                &nbsp;
            </div>
           
        </div>
        <div id="main">
            <h1>
                Retrieved Access Token
            </h1>
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
            <p>
                Use the Request Token and Verifier retrieved in the previous step to request an Access Token.
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
                            ${data.accessTokenResponse.oauthToken }
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Oauth Secret 
<!--                             <span class='tooltip' id='oauthSecret'>[?]</span> -->
                        </th>
                        <td>
                            ${data.accessTokenResponse.oauthTokenSecret }
                        </td>
                    </tr>
            </table>
            </fieldset>
            <form method="POST" action="/Checkout">
	            <p>
                    <input value="Retrieve Checkout Data" type="submit">
	            </p>
	            <input id="OAUTH_TOKEN" name="OAUTH_TOKEN" value="${data.accessTokenResponse.oauthToken }" type="hidden">
	            <input id="CHECKOUT_RESOURCE_URL" name="CHECKOUT_RESOURCE_URL" value="${data.checkoutResourceURL }" type="hidden">
            </form>
        </div>
        <div id="footer">
        </div>
    </div>
</body>
</html>