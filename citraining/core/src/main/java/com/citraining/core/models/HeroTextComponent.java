package com.citraining.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.beans.HeroBean;

@Model (adaptables = Resource.class)
public class HeroTextComponent {

	private HeroBean heroBean = null;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named ("jcr:heading")
	private String heading;

	@Inject
	@Named ("jcr:description")
	private String description;

	@Inject
	@Named ("jcr:drop")
	private String drop;

	@Inject
	private String kitten;

	@Inject
	private String color;
	

	public HeroBean getHeroBean() {
		return this.heroBean;
	}

	public String getHeading() {
		return heading;
	}

	public String getDescription() {
		return description;
	}

	public String getDrop() {
		return drop;
	}

	public String getKitten() {
		return kitten;
	}

	public String getColor() {
		return color;
	}
}