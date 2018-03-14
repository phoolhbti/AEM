
package com.citraining.ws.finance;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetWorldMajorIndicesJsonResult" type="{http://www.restfulwebservices.net/DataContracts/2008/01}ArrayOfStockQuote" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getWorldMajorIndicesJsonResult"
})
@XmlRootElement(name = "GetWorldMajorIndicesJsonResponse")
public class GetWorldMajorIndicesJsonResponse {

    @XmlElementRef(name = "GetWorldMajorIndicesJsonResult", namespace = "http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", type = JAXBElement.class)
    protected JAXBElement<ArrayOfStockQuote> getWorldMajorIndicesJsonResult;

    /**
     * Gets the value of the getWorldMajorIndicesJsonResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStockQuote }{@code >}
     *     
     */
    public JAXBElement<ArrayOfStockQuote> getGetWorldMajorIndicesJsonResult() {
        return getWorldMajorIndicesJsonResult;
    }

    /**
     * Sets the value of the getWorldMajorIndicesJsonResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStockQuote }{@code >}
     *     
     */
    public void setGetWorldMajorIndicesJsonResult(JAXBElement<ArrayOfStockQuote> value) {
        this.getWorldMajorIndicesJsonResult = ((JAXBElement<ArrayOfStockQuote> ) value);
    }

}
