//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.27 at 10:02:54 AM CDT 
//


package mastercard.mcwallet.sdk.xml.allservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorizeCheckoutRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorizeCheckoutRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OAuthToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuthorizedCheckout" type="{}AuthorizedCheckout"/>
 *         &lt;element name="Errors" type="{}Errors" minOccurs="0"/>
 *         &lt;element name="PreCheckoutTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MerchantParameterId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeviceType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="DESKTOP"/>
 *               &lt;enumeration value="MOBILE"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ExtensionPoint" type="{}ExtensionPoint" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorizeCheckoutRequest", propOrder = {
    "oAuthToken",
    "authorizedCheckout",
    "errors",
    "preCheckoutTransactionId",
    "merchantParameterId",
    "deviceType",
    "extensionPoint"
})
public class AuthorizeCheckoutRequest {

    @XmlElement(name = "OAuthToken", required = true)
    protected String oAuthToken;
    @XmlElement(name = "AuthorizedCheckout", required = true)
    protected AuthorizedCheckout authorizedCheckout;
    @XmlElement(name = "Errors")
    protected Errors errors;
    @XmlElement(name = "PreCheckoutTransactionId")
    protected String preCheckoutTransactionId;
    @XmlElement(name = "MerchantParameterId")
    protected String merchantParameterId;
    @XmlElement(name = "DeviceType")
    protected String deviceType;
    @XmlElement(name = "ExtensionPoint")
    protected ExtensionPoint extensionPoint;

    /**
     * Gets the value of the oAuthToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOAuthToken() {
        return oAuthToken;
    }

    /**
     * Sets the value of the oAuthToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOAuthToken(String value) {
        this.oAuthToken = value;
    }

    /**
     * Gets the value of the authorizedCheckout property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorizedCheckout }
     *     
     */
    public AuthorizedCheckout getAuthorizedCheckout() {
        return authorizedCheckout;
    }

    /**
     * Sets the value of the authorizedCheckout property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorizedCheckout }
     *     
     */
    public void setAuthorizedCheckout(AuthorizedCheckout value) {
        this.authorizedCheckout = value;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link Errors }
     *     
     */
    public Errors getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Errors }
     *     
     */
    public void setErrors(Errors value) {
        this.errors = value;
    }

    /**
     * Gets the value of the preCheckoutTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreCheckoutTransactionId() {
        return preCheckoutTransactionId;
    }

    /**
     * Sets the value of the preCheckoutTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreCheckoutTransactionId(String value) {
        this.preCheckoutTransactionId = value;
    }

    /**
     * Gets the value of the merchantParameterId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMerchantParameterId() {
        return merchantParameterId;
    }

    /**
     * Sets the value of the merchantParameterId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMerchantParameterId(String value) {
        this.merchantParameterId = value;
    }

    /**
     * Gets the value of the deviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * Sets the value of the deviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceType(String value) {
        this.deviceType = value;
    }

    /**
     * Gets the value of the extensionPoint property.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionPoint }
     *     
     */
    public ExtensionPoint getExtensionPoint() {
        return extensionPoint;
    }

    /**
     * Sets the value of the extensionPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionPoint }
     *     
     */
    public void setExtensionPoint(ExtensionPoint value) {
        this.extensionPoint = value;
    }

}
