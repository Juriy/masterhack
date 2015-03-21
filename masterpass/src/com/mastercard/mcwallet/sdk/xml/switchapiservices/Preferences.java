//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.21 at 12:22:14 PM CDT 
//


package com.mastercard.mcwallet.sdk.xml.switchapiservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Preferences complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Preferences">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReceiveEmailNotification" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ReceiveMobileNotification" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PersonalizationOptIn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Preferences", propOrder = {
    "receiveEmailNotification",
    "receiveMobileNotification",
    "personalizationOptIn"
})
public class Preferences {

    @XmlElement(name = "ReceiveEmailNotification")
    protected boolean receiveEmailNotification;
    @XmlElement(name = "ReceiveMobileNotification")
    protected boolean receiveMobileNotification;
    @XmlElement(name = "PersonalizationOptIn")
    protected boolean personalizationOptIn;

    /**
     * Gets the value of the receiveEmailNotification property.
     * 
     */
    public boolean isReceiveEmailNotification() {
        return receiveEmailNotification;
    }

    /**
     * Sets the value of the receiveEmailNotification property.
     * 
     */
    public void setReceiveEmailNotification(boolean value) {
        this.receiveEmailNotification = value;
    }

    /**
     * Gets the value of the receiveMobileNotification property.
     * 
     */
    public boolean isReceiveMobileNotification() {
        return receiveMobileNotification;
    }

    /**
     * Sets the value of the receiveMobileNotification property.
     * 
     */
    public void setReceiveMobileNotification(boolean value) {
        this.receiveMobileNotification = value;
    }

    /**
     * Gets the value of the personalizationOptIn property.
     * 
     */
    public boolean isPersonalizationOptIn() {
        return personalizationOptIn;
    }

    /**
     * Sets the value of the personalizationOptIn property.
     * 
     */
    public void setPersonalizationOptIn(boolean value) {
        this.personalizationOptIn = value;
    }

}