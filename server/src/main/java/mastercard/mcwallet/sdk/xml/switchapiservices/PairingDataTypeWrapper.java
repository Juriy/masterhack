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
 * <p>Java class for PairingDataTypeWrapper complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PairingDataTypeWrapper">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PairingDataType" type="{}PairingDataType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PairingDataTypeWrapper", propOrder = {
    "pairingDataType"
})
public class PairingDataTypeWrapper {

    @XmlElement(name = "PairingDataType", required = true)
    protected PairingDataType pairingDataType;

    /**
     * Gets the value of the pairingDataType property.
     * 
     * @return
     *     possible object is
     *     {@link PairingDataType }
     *     
     */
    public PairingDataType getPairingDataType() {
        return pairingDataType;
    }

    /**
     * Sets the value of the pairingDataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PairingDataType }
     *     
     */
    public void setPairingDataType(PairingDataType value) {
        this.pairingDataType = value;
    }

}
