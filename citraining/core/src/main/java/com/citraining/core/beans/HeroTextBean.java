package com.citraining.core.beans;

import java.util.ArrayList;

public class HeroTextBean {

	/** Stores the cities returned by the web service */
	private ArrayList cityList;

	/** Stores the county name */
	private String county;

	public void setList(ArrayList list) {
		this.cityList = list;
	}

	public ArrayList getList() {
		return this.cityList;
	}

	public void setCounty(String country) {
		this.county = country;
	}

	public String getCounty() {
		return this.county;
	}

}