package com.citraining.core.beans;

import java.util.List;

public class HeroTextBean {

	/** Stores the cities returned by the web service */
	private List<String> cityList;

	/** Stores the county name */
	private String county;

	public void setList(List<String> list) {
		this.cityList = list;
	}

	public List<String> getList() {
		return this.cityList;
	}

	public void setCounty(String country) {
		this.county = country;
	}

	public String getCounty() {
		return this.county;
	}

}