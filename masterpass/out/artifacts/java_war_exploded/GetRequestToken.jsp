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
	
</head>
<body class="standard">
    <div class="page">
        <div id="header">
            <div id="title">
                <h1>
                    MasterPass Standard Flow</h1>
            </div>
            <div id="logindisplay">
                &nbsp;
            </div>
        </div>
        <div id="main">
            <h1>
                Request Token Received
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
							<code>${data.encodedAuthHeader}</code> 
						
                        </td>
                    </tr> 
	              	<tr>
                        <th>
                            Signature Base String 
<!--                             <span class='tooltip' id='sbs'>[?]</span> -->
                        </th>
                        <td class="formatUrl">
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
                            Request Token 
<!--                             <span class='tooltip' id='requestToken'>[?]</span> -->
                        </th>
                        <td>
                            ${data.requestTokenResponse.oauthToken }
                        </td>
                    </tr>
                     <tr>
                        <th>
                            Authorize URL 
<!--                             <span class='tooltip' id='authorizeurl'>[?]</span> -->
                        </th>
                        <td>
                            ${data.requestTokenResponse.authorizationUrl }
                        </td>
                    </tr>
                    <tr>
                        <th>
                           Expires in 
<!--                            <span class='tooltip' id='tokenexpires'>[?]</span> -->
                        </th>
                        <td>
                            ${data.requestTokenResponse.oauthExpiresIn } <c:if test="${ data.requestTokenResponse.oauthExpiresIn != null }">Seconds</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Oauth Secret 
<!--                             <span class='tooltip' id='oauthSecret'>[?]</span> -->
                        </th>
                        <td>
                            ${data.requestTokenResponse.oauthTokenSecret }
                        </td>
                    </tr>
                 </table>
                 </fieldset>  
               
           
	            <form action="/ShoppingCart" method="POST">
		            <input type="hidden" name="oauth_token" id="oauth_token" value="${data.requestTokenResponse.oauthToken }">
		            <input type="hidden" name="RedirectUrl" id="RedirectUrl" value="${data.requestTokenResponse.redirectUrl }">
	                <input value="Shopping Cart" type="submit">
	             </form>
	             
	             
               
        </div>
        <div id="footer">
        </div>
    </div>
</body>


</html>
