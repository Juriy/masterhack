package model;

/**
 * Created by Juriy on 3/22/2015.
 */
public class Credentials {

    private String requestToken;
    private String callbackUrl;
    private String merchantCheckoutId;
    private String allowedCardTypes;
    private String cancelCallback;
    private Boolean suppressShippingAddressEnable;
    private Boolean loyaltyEnabled;
    private Boolean requestBasicCheckout;
    private String version = "v6";
    private String pairingRequestToken;
    private Boolean requestPairing;

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getMerchantCheckoutId() {
        return merchantCheckoutId;
    }

    public void setMerchantCheckoutId(String merchantCheckoutId) {
        this.merchantCheckoutId = merchantCheckoutId;
    }

    public String getAllowedCardTypes() {
        return allowedCardTypes;
    }

    public void setAllowedCardTypes(String allowedCardTypes) {
        this.allowedCardTypes = allowedCardTypes;
    }

    public String getCancelCallback() {
        return cancelCallback;
    }

    public void setCancelCallback(String cancelCallback) {
        this.cancelCallback = cancelCallback;
    }

    public Boolean getSuppressShippingAddressEnable() {
        return suppressShippingAddressEnable;
    }

    public void setSuppressShippingAddressEnable(Boolean suppressShippingAddressEnable) {
        this.suppressShippingAddressEnable = suppressShippingAddressEnable;
    }

    public Boolean getLoyaltyEnabled() {
        return loyaltyEnabled;
    }

    public void setLoyaltyEnabled(Boolean loyaltyEnabled) {
        this.loyaltyEnabled = loyaltyEnabled;
    }

    public Boolean getRequestBasicCheckout() {
        return requestBasicCheckout;
    }

    public void setRequestBasicCheckout(Boolean requestBasicCheckout) {
        this.requestBasicCheckout = requestBasicCheckout;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPairingRequestToken() {
        return pairingRequestToken;
    }

    public void setPairingRequestToken(String pairingRequestToken) {
        this.pairingRequestToken = pairingRequestToken;
    }

    public Boolean getRequestPairing() {
        return requestPairing;
    }

    public void setRequestPairing(Boolean requestPairing) {
        this.requestPairing = requestPairing;
    }
}
