package mastercard;

import mastercard.mcwallet.sampleapp.MasterpassController;
import mastercard.mcwallet.sampleapp.MasterpassData;
import model.Credentials;
import model.Item;

import java.io.*;
import java.util.Collection;

/**
 * Created by Juriy on 3/22/2015.
 */
public class MasterPass {
    private static final String SHOPPING_CART_XML = "shoppingCart.xml";


    private Credentials getCredentials() throws Exception {
        MasterpassData data = new MasterpassData();
        data.setErrorMessage(null);
        data.setAppBaseUrl("http://localhost:8080");
        data.setContextPath("/");

        MasterpassController controller = new MasterpassController(data);

        // This is extracted from
        // data = controller.processParameters(request, data);
        data.setXmlVersion("v6");
        data.setShippingSuppression(false);
        data.setRewards(false);
        data.setAuthLevelBasic(false);
        data.setRedirectShippingProfiles(null);
        data.setAcceptedCards("master,amex,diners,discover,maestro,visa");
        data = controller.getRequestToken(data);

        if (data.getErrorMessage() != null) {
            System.out.println("ERROR:" + data.getErrorMessage());
        }

        // STEP 2
        System.out.println("Authorization Header" + data.getEncodedAuthHeader());
        System.out.println("Signature Base String" + data.getSignatureBaseString());
        System.out.println("Request Token URL" + data.getRequestURL());

        System.out.println("RECEIVED: ");
        System.out.println("Request Token URL " + data.getRequestTokenResponse().getOauthToken());
        System.out.println("Authorize URL " + data.getRequestTokenResponse().getAuthorizationUrl());
        System.out.println("Expires in " + data.getRequestTokenResponse().getOauthExpiresIn());
        System.out.println("Oauth Secret " + data.getRequestTokenResponse().getOauthToken());
        System.out.println("Request Token URL" + data.getRequestTokenResponse().getOauthTokenSecret());

        controller = new MasterpassController(data);
        controller.postShoppingCart(data);


        if (data.getErrorMessage() != null) {
            System.out.println("ERROR:" + data.getErrorMessage());
        }

        System.out.println("SENT: ");
        System.out.println("Authorization Header " + data.getAuthHeader());
        System.out.println("Signature Base String  " + data.getSignatureBaseString());
        System.out.println("Shopping Cart XML  " + data.getShoppingCartRequest());


        System.out.println("ULR: " + data.getShoppingCartUrl());

        System.out.println("RECEIVED: ");
        System.out.println("Shopping Cart Response: " + data.getShoppingCartResponse());

        // STEP 3

        System.out.println("Set the following in page to enable popup:");
        System.out.println("MasterPass.client.checkout( { ... } )");
        System.out.println("requestToken" +  data.getRequestTokenResponse().getOauthExpiresIn());
        System.out.println("callbackUrl" +  data.getCallbackUrl());
        System.out.println("merchantCheckoutId" +  data.getCheckoutIdentifier());
        System.out.println("allowedCardTypes" +  data.getAcceptedCards());
        System.out.println("cancelCallback" +  data.getCallbackDomain());
        System.out.println("cancelCallback" +  data.getShippingSuppression());
        System.out.println("loyaltyEnabled" +  data.getRewards());
        System.out.println("requestBasicCheckout" +  data.getAuthLevelBasic());

        System.out.print("DONE!");


        Credentials cred = new Credentials();
        cred.setRequestToken(data.getRequestToken());
        cred.setCallbackUrl(data.getCallbackUrl());
        cred.setMerchantCheckoutId(data.getCheckoutIdentifier());
        cred.setAllowedCardTypes(data.getAcceptedCards());
        cred.setCancelCallback(data.getCallbackDomain());
        cred.setSuppressShippingAddressEnable(data.getShippingSuppression());
        cred.setLoyaltyEnabled(data.getRewards());
        cred.setRequestBasicCheckout(data.getAuthLevelBasic());
        return cred;
    }

    public Credentials getCredentials(Collection<Item> items) throws Exception {
        double total =0;
        for(Item item:items){
            total = total + item.getItemPrice();
        }

        StringBuffer sb = new StringBuffer("<?xml version=\"1.0\"?>\n" +
                "<ShoppingCartRequest>\n" +
                "  <OAuthToken></OAuthToken>\n" +
                "  <ShoppingCart>\n" +
                "    <CurrencyCode>USD</CurrencyCode>\n" +
                "    <Subtotal>"+(int) total+"</Subtotal>");
        for(Item i:items){
            sb.append("<ShoppingCartItem>\n" +
                    "      <Description>"+i.getItemDescription()+"</Description>\n" +
                    "      <Quantity>1</Quantity>\n" +
                    "      <Value>"+i.getItemPrice()+"</Value>\n" +
                    "      <ImageURL></ImageURL>\n" +
                    "    </ShoppingCartItem>");
        }
        sb.append("</ShoppingCart>\n" +
                "</ShoppingCartRequest>");
        rewriteShoppingCart(sb.toString());
        return getCredentials();
    }

    public Credentials getCredentials(int total) throws Exception {
        StringBuffer sb = new StringBuffer("<?xml version=\"1.0\"?>\n" +
                "<ShoppingCartRequest>\n" +
                "  <OAuthToken></OAuthToken>\n" +
                "  <ShoppingCart>\n" +
                "    <CurrencyCode>USD</CurrencyCode>\n" +
                "    <Subtotal>"+(int) total+"</Subtotal>");

            sb.append("<ShoppingCartItem>\n" +
                    "      <Description>Splities payment</Description>\n" +
                    "      <Quantity>1</Quantity>\n" +
                    "      <Value>"+total+"</Value>\n" +
                    "      <ImageURL></ImageURL>\n" +
                    "    </ShoppingCartItem>");

        sb.append("</ShoppingCart>\n" +
                "</ShoppingCartRequest>");
        rewriteShoppingCart(sb.toString());
        return getCredentials();
    }

    private void rewriteShoppingCart(String xml){
        ClassLoader cl = this.getClass().getClassLoader();
        try {
            FileWriter writer = new FileWriter(cl.getResource(SHOPPING_CART_XML).getFile());
            writer.write(xml);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
