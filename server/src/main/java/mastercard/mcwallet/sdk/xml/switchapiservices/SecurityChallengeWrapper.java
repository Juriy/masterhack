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
 * <p>Java class for SecurityChallengeWrapper complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SecurityChallengeWrapper">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SecurityChallenge" type="{}SecurityChallenge"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecurityChallengeWrapper", propOrder = {
    "securityChallenge"
})
public class SecurityChallengeWrapper {

    @XmlElement(name = "SecurityChallenge", required = true)
    protected SecurityChallenge securityChallenge;

    /**
     * Gets the value of the securityChallenge property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityChallenge }
     *     
     */
    public SecurityChallenge getSecurityChallenge() {
        return securityChallenge;
    }

    /**
     * Sets the value of the securityChallenge property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityChallenge }
     *     
     */
    public void setSecurityChallenge(SecurityChallenge value) {
        this.securityChallenge = value;
    }

}
