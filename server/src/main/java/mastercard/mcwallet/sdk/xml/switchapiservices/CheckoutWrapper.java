//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.21 at 12:22:14 PM CDT 
//


package mastercard.mcwallet.sdk.xml.switchapiservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckoutWrapper complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckoutWrapper">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Checkout" type="{}Checkout"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckoutWrapper", propOrder = {
    "checkout"
})
public class CheckoutWrapper {

    @XmlElement(name = "Checkout", required = true)
    protected Checkout checkout;

    /**
     * Gets the value of the checkout property.
     * 
     * @return
     *     possible object is
     *     {@link Checkout }
     *     
     */
    public Checkout getCheckout() {
        return checkout;
    }

    /**
     * Sets the value of the checkout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Checkout }
     *     
     */
    public void setCheckout(Checkout value) {
        this.checkout = value;
    }

}