package model;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Merchant {
    private String merchantId;
    private String merchantAccountNumber;

    public Merchant() {
    }

    public Merchant(String merchantId, String merchantAccountNumber) {
        this.merchantId = merchantId;
        this.merchantAccountNumber = merchantAccountNumber;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantAccountNumber() {
        return merchantAccountNumber;
    }

    public void setMerchantAccountNumber(String merchantAccountNumber) {
        this.merchantAccountNumber = merchantAccountNumber;
    }
}
