package com.citraining.core.beans;


public class HeroBean {

	private String heading;
	private String description;
	private String drop;
	private String check;
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDrop() {
		return drop;
	}

	public void setDrop(String drop) {
		this.drop = drop;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * @return the heading
	 */

	public String getHeading() {
		return heading;
	}

	/**
	 * @param heading
	 *            the heading to set
	 */
	public void setHeading(String heading) {
		this.heading = heading;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}