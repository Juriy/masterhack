//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.21 at 12:20:47 PM CDT 
//


package mastercard.mcwallet.sdk.xml.commontypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PersonalGreetingWrapper complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonalGreetingWrapper">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PersonalGreeting" type="{}PersonalGreeting"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonalGreetingWrapper", propOrder = {
    "personalGreeting"
})
public class PersonalGreetingWrapper {

    @XmlElement(name = "PersonalGreeting", required = true)
    protected PersonalGreeting personalGreeting;

    /**
     * Gets the value of the personalGreeting property.
     * 
     * @return
     *     possible object is
     *     {@link PersonalGreeting }
     *     
     */
    public PersonalGreeting getPersonalGreeting() {
        return personalGreeting;
    }

    /**
     * Sets the value of the personalGreeting property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalGreeting }
     *     
     */
    public void setPersonalGreeting(PersonalGreeting value) {
        this.personalGreeting = value;
    }

}
