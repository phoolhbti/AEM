
package com.citraining.ws.finance;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.citraining.ws.finance package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _ArrayOfStockQuote_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "ArrayOfStockQuote");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _StockQuote_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "StockQuote");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _GetStockQuoteJsonResponseGetStockQuoteJsonResult_QNAME = new QName("http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", "GetStockQuoteJsonResult");
    private final static QName _GetStockQuoteJsonSymbol_QNAME = new QName("http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", "Symbol");
    private final static QName _GetWorldMajorIndicesResponseGetWorldMajorIndicesResult_QNAME = new QName("http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", "GetWorldMajorIndicesResult");
    private final static QName _StockQuoteChange_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Change");
    private final static QName _StockQuoteTime_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Time");
    private final static QName _StockQuoteOpen_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Open");
    private final static QName _StockQuoteName_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Name");
    private final static QName _StockQuoteDate_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Date");
    private final static QName _StockQuotePreviousClose_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "PreviousClose");
    private final static QName _StockQuoteMktCap_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "MktCap");
    private final static QName _StockQuoteEarns_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Earns");
    private final static QName _StockQuoteHigh_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "High");
    private final static QName _StockQuoteSymbol_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Symbol");
    private final static QName _StockQuotePercentageChange_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "PercentageChange");
    private final static QName _StockQuoteAnnRange_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "AnnRange");
    private final static QName _StockQuoteLast_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Last");
    private final static QName _StockQuoteLow_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Low");
    private final static QName _StockQuoteVolume_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "Volume");
    private final static QName _StockQuotePE_QNAME = new QName("http://www.restfulwebservices.net/DataContracts/2008/01", "PE");
    private final static QName _GetStockQuoteResponseGetStockQuoteResult_QNAME = new QName("http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", "GetStockQuoteResult");
    private final static QName _GetWorldMajorIndicesJsonResponseGetWorldMajorIndicesJsonResult_QNAME = new QName("http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", "GetWorldMajorIndicesJsonResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.citraining.ws.finance
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetStockQuoteJsonResponse }
     * 
     */
    public GetStockQuoteJsonResponse createGetStockQuoteJsonResponse() {
        return new GetStockQuoteJsonResponse();
    }

    /**
     * Create an instance of {@link GetStockQuoteJson }
     * 
     */
    public GetStockQuoteJson createGetStockQuoteJson() {
        return new GetStockQuoteJson();
    }

    /**
     * Create an instance of {@link GetWorldMajorIndicesJson }
     * 
     */
    public GetWorldMajorIndicesJson createGetWorldMajorIndicesJson() {
        return new GetWorldMajorIndicesJson();
    }

    /**
     * Create an instance of {@link GetWorldMajorIndicesResponse }
     * 
     */
    public GetWorldMajorIndicesResponse createGetWorldMajorIndicesResponse() {
        return new GetWorldMajorIndicesResponse();
    }

    /**
     * Create an instance of {@link ArrayOfStockQuote }
     * 
     */
    public ArrayOfStockQuote createArrayOfStockQuote() {
        return new ArrayOfStockQuote();
    }

    /**
     * Create an instance of {@link StockQuote }
     * 
     */
    public StockQuote createStockQuote() {
        return new StockQuote();
    }

    /**
     * Create an instance of {@link GetWorldMajorIndices }
     * 
     */
    public GetWorldMajorIndices createGetWorldMajorIndices() {
        return new GetWorldMajorIndices();
    }

    /**
     * Create an instance of {@link GetStockQuoteResponse }
     * 
     */
    public GetStockQuoteResponse createGetStockQuoteResponse() {
        return new GetStockQuoteResponse();
    }

    /**
     * Create an instance of {@link GetWorldMajorIndicesJsonResponse }
     * 
     */
    public GetWorldMajorIndicesJsonResponse createGetWorldMajorIndicesJsonResponse() {
        return new GetWorldMajorIndicesJsonResponse();
    }

    /**
     * Create an instance of {@link GetStockQuote }
     * 
     */
    public GetStockQuote createGetStockQuote() {
        return new GetStockQuote();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfStockQuote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "ArrayOfStockQuote")
    public JAXBElement<ArrayOfStockQuote> createArrayOfStockQuote(ArrayOfStockQuote value) {
        return new JAXBElement<ArrayOfStockQuote>(_ArrayOfStockQuote_QNAME, ArrayOfStockQuote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StockQuote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "StockQuote")
    public JAXBElement<StockQuote> createStockQuote(StockQuote value) {
        return new JAXBElement<StockQuote>(_StockQuote_QNAME, StockQuote.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StockQuote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", name = "GetStockQuoteJsonResult", scope = GetStockQuoteJsonResponse.class)
    public JAXBElement<StockQuote> createGetStockQuoteJsonResponseGetStockQuoteJsonResult(StockQuote value) {
        return new JAXBElement<StockQuote>(_GetStockQuoteJsonResponseGetStockQuoteJsonResult_QNAME, StockQuote.class, GetStockQuoteJsonResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", name = "Symbol", scope = GetStockQuoteJson.class)
    public JAXBElement<String> createGetStockQuoteJsonSymbol(String value) {
        return new JAXBElement<String>(_GetStockQuoteJsonSymbol_QNAME, String.class, GetStockQuoteJson.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfStockQuote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", name = "GetWorldMajorIndicesResult", scope = GetWorldMajorIndicesResponse.class)
    public JAXBElement<ArrayOfStockQuote> createGetWorldMajorIndicesResponseGetWorldMajorIndicesResult(ArrayOfStockQuote value) {
        return new JAXBElement<ArrayOfStockQuote>(_GetWorldMajorIndicesResponseGetWorldMajorIndicesResult_QNAME, ArrayOfStockQuote.class, GetWorldMajorIndicesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Change", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteChange(String value) {
        return new JAXBElement<String>(_StockQuoteChange_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Time", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteTime(String value) {
        return new JAXBElement<String>(_StockQuoteTime_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Open", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteOpen(String value) {
        return new JAXBElement<String>(_StockQuoteOpen_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Name", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteName(String value) {
        return new JAXBElement<String>(_StockQuoteName_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Date", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteDate(String value) {
        return new JAXBElement<String>(_StockQuoteDate_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "PreviousClose", scope = StockQuote.class)
    public JAXBElement<String> createStockQuotePreviousClose(String value) {
        return new JAXBElement<String>(_StockQuotePreviousClose_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "MktCap", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteMktCap(String value) {
        return new JAXBElement<String>(_StockQuoteMktCap_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Earns", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteEarns(String value) {
        return new JAXBElement<String>(_StockQuoteEarns_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "High", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteHigh(String value) {
        return new JAXBElement<String>(_StockQuoteHigh_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Symbol", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteSymbol(String value) {
        return new JAXBElement<String>(_StockQuoteSymbol_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "PercentageChange", scope = StockQuote.class)
    public JAXBElement<String> createStockQuotePercentageChange(String value) {
        return new JAXBElement<String>(_StockQuotePercentageChange_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "AnnRange", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteAnnRange(String value) {
        return new JAXBElement<String>(_StockQuoteAnnRange_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Last", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteLast(String value) {
        return new JAXBElement<String>(_StockQuoteLast_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Low", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteLow(String value) {
        return new JAXBElement<String>(_StockQuoteLow_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "Volume", scope = StockQuote.class)
    public JAXBElement<String> createStockQuoteVolume(String value) {
        return new JAXBElement<String>(_StockQuoteVolume_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/DataContracts/2008/01", name = "PE", scope = StockQuote.class)
    public JAXBElement<String> createStockQuotePE(String value) {
        return new JAXBElement<String>(_StockQuotePE_QNAME, String.class, StockQuote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StockQuote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", name = "GetStockQuoteResult", scope = GetStockQuoteResponse.class)
    public JAXBElement<StockQuote> createGetStockQuoteResponseGetStockQuoteResult(StockQuote value) {
        return new JAXBElement<StockQuote>(_GetStockQuoteResponseGetStockQuoteResult_QNAME, StockQuote.class, GetStockQuoteResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfStockQuote }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", name = "GetWorldMajorIndicesJsonResult", scope = GetWorldMajorIndicesJsonResponse.class)
    public JAXBElement<ArrayOfStockQuote> createGetWorldMajorIndicesJsonResponseGetWorldMajorIndicesJsonResult(ArrayOfStockQuote value) {
        return new JAXBElement<ArrayOfStockQuote>(_GetWorldMajorIndicesJsonResponseGetWorldMajorIndicesJsonResult_QNAME, ArrayOfStockQuote.class, GetWorldMajorIndicesJsonResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.restfulwebservices.net/ServiceContracts/Rest/2008/01", name = "Symbol", scope = GetStockQuote.class)
    public JAXBElement<String> createGetStockQuoteSymbol(String value) {
        return new JAXBElement<String>(_GetStockQuoteJsonSymbol_QNAME, String.class, GetStockQuote.class, value);
    }

}
