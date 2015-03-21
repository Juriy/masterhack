// Fires when the document has loaded.
$(function () {
    var valueToFormat = $(".formatUrl");

    for (var i = 0; i < valueToFormat.length; i++) {
        // First take care of &.
        var result = valueToFormat[i].innerHTML.split("&").join("<br />&");
        // Second take care of %26.
        result = result.split("%26").join("<br />%26");
        // Third take care of ?.
        result = result.split("?").join("<br />?");
        
        //highlighting
        result = result.split("POST").join("<b>POST</b>");  
        result = result.split("GET").join("<b>GET</b>");    

        valueToFormat[i].innerHTML = result;
    }
});

$(function () {
    var valueToFormat = $(".formatAuth");

    for (var i = 0; i < valueToFormat.length; i++) {
        // First take care of &.
        var result = valueToFormat[i].innerHTML.split(",").join("<br />,");
        // Take care of "OAuth "
        result = result.split("OAuth ").join("<b>OAuth</b> <br />");  
        // Adding <code> to the signature to prevent the browser from interpreting the string as html
        result = result.split("oauth_signature=").join("oauth_signature=<code>");
        result = result.split(",oauth_nonce").join("</code>,oauth_nonce");
        
        // Splits the oauth_signature at position 120 and adds the reminder on a new line. This prevents the string from going off the page.
        pos = result.lastIndexOf(",oauth_signature=") + 150;
        signExtra = result.substr(pos);
        result = result.substr(0,pos) + '<br>' + signExtra;
        
        valueToFormat[i].innerHTML = result;
    }
});
