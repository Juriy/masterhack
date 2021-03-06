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
 * <p>Java class for PrecheckoutCard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrecheckoutCard">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrandId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BrandName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BillingAddress" type="{}Address"/>
 *         &lt;element name="CardHolderName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExpiryMonth" type="{}Month" minOccurs="0"/>
 *         &lt;element name="ExpiryYear" type="{}Year" minOccurs="0"/>
 *         &lt;element name="CardId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastFour" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CardAlias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SelectedAsDefault" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrecheckoutCard", propOrder = {
    "brandId",
    "brandName",
    "billingAddress",
    "cardHolderName",
    "expiryMonth",
    "expiryYear",
    "cardId",
    "lastFour",
    "cardAlias",
    "selectedAsDefault"
})
public class PrecheckoutCard {

    @XmlElement(name = "BrandId", required = true)
    protected String brandId;
    @XmlElement(name = "BrandName", required = true)
    protected String brandName;
    @XmlElement(name = "BillingAddress", required = true)
    protected Address billingAddress;
    @XmlElement(name = "CardHolderName", required = true)
    protected String cardHolderName;
    @XmlElement(name = "ExpiryMonth")
    protected Integer expiryMonth;
    @XmlElement(name = "ExpiryYear")
    protected Integer expiryYear;
    @XmlElement(name = "CardId", required = true)
    protected String cardId;
    @XmlElement(name = "LastFour", required = true)
    protected String lastFour;
    @XmlElement(name = "CardAlias", required = true)
    protected String cardAlias;
    @XmlElement(name = "SelectedAsDefault")
    protected boolean selectedAsDefault;

    /**
     * Gets the value of the brandId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandId() {
        return brandId;
    }

    /**
     * Sets the value of the brandId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandId(String value) {
        this.brandId = value;
    }

    /**
     * Gets the value of the brandName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * Sets the value of the brandName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandName(String value) {
        this.brandName = value;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setBillingAddress(Address value) {
        this.billingAddress = value;
    }

    /**
     * Gets the value of the cardHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Sets the value of the cardHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolderName(String value) {
        this.cardHolderName = value;
    }

    /**
     * Gets the value of the expiryMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExpiryMonth() {
        return expiryMonth;
    }

    /**
     * Sets the value of the expiryMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExpiryMonth(Integer value) {
        this.expiryMonth = value;
    }

    /**
     * Gets the value of the expiryYear property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExpiryYear() {
        return expiryYear;
    }

    /**
     * Sets the value of the expiryYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExpiryYear(Integer value) {
        this.expiryYear = value;
    }

    /**
     * Gets the value of the cardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * Sets the value of the cardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardId(String value) {
        this.cardId = value;
    }

    /**
     * Gets the value of the lastFour property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastFour() {
        return lastFour;
    }

    /**
     * Sets the value of the lastFour property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastFour(String value) {
        this.lastFour = value;
    }

    /**
     * Gets the value of the cardAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardAlias() {
        return cardAlias;
    }

    /**
     * Sets the value of the cardAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardAlias(String value) {
        this.cardAlias = value;
    }

    /**
     * Gets the value of the selectedAsDefault property.
     * 
     */
    public boolean isSelectedAsDefault() {
        return selectedAsDefault;
    }

    /**
     * Sets the value of the selectedAsDefault property.
     * 
     */
    public void setSelectedAsDefault(boolean value) {
        this.selectedAsDefault = value;
    }

}
