
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
 *         &lt;element name="GetStockQuoteJsonResult" type="{http://www.restfulwebservices.net/DataContracts/2008/01}StockQuote" minOccurs="0"/>
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
    "getStockQuoteJsonResult"
})
@XmlRootElement(name = "GetStockQuoteJsonResponse")
public class GetStockQuoteJsonResponse {

    @XmlElementRef(name = "GetStockQuoteJsonResult", namespace = "http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", type = JAXBElement.class)
    protected JAXBElement<StockQuote> getStockQuoteJsonResult;

    /**
     * Gets the value of the getStockQuoteJsonResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StockQuote }{@code >}
     *     
     */
    public JAXBElement<StockQuote> getGetStockQuoteJsonResult() {
        return getStockQuoteJsonResult;
    }

    /**
     * Sets the value of the getStockQuoteJsonResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StockQuote }{@code >}
     *     
     */
    public void setGetStockQuoteJsonResult(JAXBElement<StockQuote> value) {
        this.getStockQuoteJsonResult = ((JAXBElement<StockQuote> ) value);
    }

}
