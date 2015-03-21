package mastercard.connection;

import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthRsaSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthUtil;
import com.google.gdata.util.common.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Connector {

    /*
 * Establish an OAuth connection to a MasterCard API over HTTPS.
 * @param httpsURL The full URL to call, including any querystring parameters.
 * @param body The body to include.  If this has a body, an HTTP POST will be established,
 * 			   this content will be used to generate the oauth_body_hash and the contents passed
 * 			   as the body of the request.  If the body parameter is null, an HTTP GET
 *             will be established.
 */
    public HttpsURLConnection createOpenAPIConnection(String httpsURL, String body) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, OAuthException, KeyStoreException, CertificateException, UnrecoverableEntryException, KeyManagementException {
        String clientId = "bogdan";
        HttpsURLConnection con = null;
        PrivateKey privKey = getPrivateKey();
        if (privKey != null) {
            OAuthRsaSha1Signer rsaSigner = new OAuthRsaSha1Signer();
            OAuthParameters params = new OAuthParameters();
            params.setOAuthConsumerKey(clientId);
            params.setOAuthNonce(OAuthUtil.getNonce());
            params.setOAuthTimestamp(OAuthUtil.getTimestamp());
            params.setOAuthSignatureMethod("RSA-SHA1");
            params.setOAuthType(OAuthParameters.OAuthType.TWO_LEGGED_OAUTH);
            params.addCustomBaseParameter("oauth_version", "1.0");
            rsaSigner.setPrivateKey(privKey);

            String method = "GET";
            if (body != null) {
                method = "POST";

                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                digest.reset();
                byte[] hash = digest.digest(body.getBytes("UTF-8"));
                String encodedHash = Base64.encode(hash);

                params.addCustomBaseParameter("oauth_body_hash", encodedHash);
            }

            String baseString = OAuthUtil.getSignatureBaseString(httpsURL, method, params.getBaseParameters());
            System.out.println(baseString);

            String signature = rsaSigner.getSignature(baseString, params);

            params.addCustomBaseParameter("oauth_signature", signature);

            URL url = new URL(httpsURL);
            con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
            con.setDoOutput(true);
            con.setDoInput(true);
            con.addRequestProperty("Authorization", buildAuthHeaderString(params));

            System.out.println(buildAuthHeaderString(params));

            if (body != null) {
                con.addRequestProperty("content-type", "application/xml; charset=UTF-8");
                con.addRequestProperty("content-length", Integer.toString(body.length()));
            }
            con.connect();

            if (body != null) {
                CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
                OutputStreamWriter request = new OutputStreamWriter(con.getOutputStream(), encoder);
                request.append(body);
                request.flush();
                request.close();
            }


        }

        return con;
    }

    protected PrivateKey getPrivateKey()
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, KeyStoreException, CertificateException, UnrecoverableEntryException {

        String kspw = "123456";
        String privKeyFile = "C:\\workspace\\mastercard-api\\src\\main\\resources\\key\\MCOpenAPI.p12";
        String keyAlias = "mastercardKey";

        KeyStore ks = KeyStore.getInstance("PKCS12");


        // get user password and file input stream

        InputStream stream = new FileInputStream(privKeyFile);
        ks.load(stream, kspw.toCharArray());
        Key key = ks.getKey(keyAlias, kspw.toCharArray());

        return (PrivateKey) key;

    }


    private String buildAuthHeaderString(OAuthParameters params) {
        StringBuffer buffer = new StringBuffer();
        int cnt = 0;
        buffer.append("OAuth ");
        Map<String, String> paramMap = params.getBaseParameters();
        Object[] paramNames = paramMap.keySet().toArray();
        for (Object paramName : paramNames) {
            String value = paramMap.get((String) paramName);
            buffer.append(paramName + "=\"" + OAuthUtil.encode(value) + "\"");
            cnt++;
            if (paramNames.length > cnt) {
                buffer.append(",");
            }

        }
        return buffer.toString();
    }
}
