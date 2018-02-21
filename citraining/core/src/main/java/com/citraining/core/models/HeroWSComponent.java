package com.citraining.core.models;

import java.io.StringReader;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.citraining.core.beans.HeroTextBean;
import com.citraining.ws.GlobalWeather;
import com.citraining.ws.GlobalWeatherSoap;

@Model (adaptables = Resource.class)
public class HeroWSComponent {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Inject
	@Optional
	public String countryName;

	@Inject
	@Optional
	public String countyNum;

	private HeroTextBean heroTextBean = null;

	private int size;

	private ArrayList<String> citiesList;

	@PostConstruct
	protected void init() {
		LOG.info("Web Service Component  **** INIT ***");
		heroTextBean = new HeroTextBean();
		// Set variables - we need city and country to make a successful web service call
		String number = countyNum;
		String country = countryName;
		String myXML = invokeWS(country);
		// populate data member size
		size = Integer.parseInt(number);
		// Convert the String XML to WC3 Document
		Document cityXML = FromXmlString(myXML);
		// Populate the List - this list contents will be displayed in the Client part of the HTL component
		ArrayList cityList = getAllCities(cityXML);
		heroTextBean.setList(cityList);
		heroTextBean.setCounty(country);
	}

	// Return a HeroTextBean!
	public HeroTextBean getHeroTextBean() {
		return this.heroTextBean;
	}

	// Place the specified number of cities into a List
	public ArrayList getAllCities(Document doc) {
		citiesList = new ArrayList(size);
		NodeList LineItemAttributeChildrenList = doc.getElementsByTagName("City");
		if (LineItemAttributeChildrenList != null && LineItemAttributeChildrenList.getLength() > 0){
			System.out.println("Inside if and checking length" + LineItemAttributeChildrenList.getLength());
			for (int i = 0; i < size; i++){
				// Add the city to the list!
				citiesList.add(LineItemAttributeChildrenList.item(i).getTextContent());
			}
		}
		return citiesList;
	}

	// Makes a call to the 3rd party SOAP Stack to weather information
	private String invokeWS(String country) {
		try{
			GlobalWeather global = new GlobalWeather();
			GlobalWeatherSoap weatherService = global.getGlobalWeatherSoap();
			String allCities = weatherService.getCitiesByCountry(country);
			return allCities;
		} catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}

	// Convert String to WC3 Document
	public Document FromXmlString(String xmlString) {
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(new StringReader(xmlString)));
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}