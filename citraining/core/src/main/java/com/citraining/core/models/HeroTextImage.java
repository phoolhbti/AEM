package com.citraining.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model (adaptables = Resource.class)
public class HeroTextImage {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Optional
	@Named ("jcr:title")
	private String heading;

	@Inject
	@Optional
	@Named ("jcr:description")
	private String description;

	
	@Inject
	@Optional
	@Named ("linkURL")
	private String linkURL;
	
	
	@Inject
	@Optional
	@Named ("fileReference")
	private String imageSrc;
	
	@Inject
    protected Resource resource;
	
	public String getHeading() {
		return heading;
	}

	public String getDescription() {
		return description;
	}


	public String getLinkURL() {
		return linkURL;
	}

	public String getImageSrc() {
		/*if(resource.hasChildren()) {
		Resource file = resource.getChild("file");
		imageSrc=file.getPath();
		}*/
		return imageSrc;
	}

	
}