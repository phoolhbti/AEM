package com.citraining.core.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model (adaptables = Resource.class, resourceType = { "weretail/components/structure/page" }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter (name = "jaxb", selector = "test", extensions = "xml")
@XmlRootElement
public class ModelJAXB {
	@ValueMapValue (name = "jcr:title")
	private String title;

	@XmlElement
	public String getTitle() {
		return title;
	}
}