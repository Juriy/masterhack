<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="data" class="com.mastercard.mcwallet.sampleapp.MasterpassData" scope="session">
</jsp:useBean>
<html>
<head>
    <title>
    	MasterPass Express Checkout Flow
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="Content/Site.css">
    <script type="text/javascript" src="Scripts/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="Scripts/common.js"></script>
    <script type="text/javascript" src="Scripts/tooltips/commonToolTips.js"></script>
    <script type="text/javascript" src="${data.lightboxUrl}"></script>
    <script type="text/javascript" src="https://www.masterpass.com/lightbox/Switch/assets/js/jquery-1.10.2.min.js "></script>
    <script type="text/javascript" src="Scripts/tooltips/jquery-1.3.2.min.js"></script> <!-- Needed for tooltips only -->
	<script type="text/javascript" src="Scripts/tooltips/jquery.qtip-1.0.0-rc3.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> 
	
	
</head>
<body class="express">
    <div class="page">
        <div id="header">
            <div id="title">
                <h1>
                    MasterPass Express Checkout Flow
                </h1>
            </div>
            <div id="logindisplay">
                &nbsp;
            </div>
            
        </div>
        <div id="main">
            <h1>
                Express Checkout
            </h1>
<c:if test="${ data.errorMessage != null }">
		
	<h2>Error</h2>
	<div class = "error">
		<p>
		    The following error occurred while trying to get the Express Checkout from the MasterCard API.
		</p>		
<pre>
<code>
${data.errorMessage }
</code>
</pre>
</div>

</c:if>           
            <p>
                This step requests an express checkout from MasterPass.
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
                            Express Checkout XML 
<!--                             <span class='tooltip' id='ShoppingXML'>[?]</span> -->
                        </th>
                        <td>
<pre>                        
<code>                        
${data.expressCheckoutRequest}
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
                            Express Checkout URL 
<!--                             <span class='tooltip' id='shopEndpoint'>[?]</span> -->
                        </th>
                        <td>
                            ${data.expressCheckoutUrl}
                        </td>
                    </tr>
                    
                 </table>  
                 </fieldset>
            <fieldset>
            <legend>Received:</legend>           
                    
           		<table>                     
                    <tr>
                        <th>
                            Express Checkout Response 
<!--                             <span class='tooltip' id='ShoppingResponse'>[?]</span> -->
                        </th>
                        <td>
						<pre>                        
<code>                        
${data.expressCheckoutResponse}
</code>
						</pre>                           
                        </td>
                    </tr>
                    
                 </table>
                 <form action="./Postback" method="POST">
						<input value="Post Transaction to Mastercard" type="submit">
				 	</form> 
                 </fieldset>
                 <script type="text/javascript">
                 	
                 	var securityRequired = ("${data.expressSecurityRequired}" === "true");
                 	console.log(securityRequired);
                 	if (securityRequired){
                 		console.log("SECURITY REQUIRED");
                 		MasterPass.client.cardSecurity({
                 			"requestToken":"${data.requestToken}",
                 			"callbackUrl":"${data.callbackDomain}/ExpressCheckout",
                 			"merchantCheckoutId":"${data.checkoutIdentifier}",
                 			"precheckoutTransactionId":"${data.precheckoutTransactionId}"
                 		});
                 	}
                 
                 </script>
                 
            </div>
            
        </div>
    
	
</body>
</html>
