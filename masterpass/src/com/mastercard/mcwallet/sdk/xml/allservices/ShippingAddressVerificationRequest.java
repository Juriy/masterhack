//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.27 at 10:02:54 AM CDT 
//


package com.mastercard.mcwallet.sdk.xml.allservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ShippingAddressVerificationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ShippingAddressVerificationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OAuthToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VerifiableAddresses" type="{}VerifiableAddresses"/>
 *         &lt;element name="ShippingLocationProfileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShippingAddressVerificationRequest", propOrder = {
    "oAuthToken",
    "verifiableAddresses",
    "shippingLocationProfileName"
})
public class ShippingAddressVerificationRequest {

    @XmlElement(name = "OAuthToken", required = true)
    protected String oAuthToken;
    @XmlElement(name = "VerifiableAddresses", required = true)
    protected VerifiableAddresses verifiableAddresses;
    @XmlElement(name = "ShippingLocationProfileName")
    protected String shippingLocationProfileName;

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
     * Gets the value of the verifiableAddresses property.
     * 
     * @return
     *     possible object is
     *     {@link VerifiableAddresses }
     *     
     */
    public VerifiableAddresses getVerifiableAddresses() {
        return verifiableAddresses;
    }

    /**
     * Sets the value of the verifiableAddresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerifiableAddresses }
     *     
     */
    public void setVerifiableAddresses(VerifiableAddresses value) {
        this.verifiableAddresses = value;
    }

    /**
     * Gets the value of the shippingLocationProfileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShippingLocationProfileName() {
        return shippingLocationProfileName;
    }

    /**
     * Sets the value of the shippingLocationProfileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShippingLocationProfileName(String value) {
        this.shippingLocationProfileName = value;
    }

}
