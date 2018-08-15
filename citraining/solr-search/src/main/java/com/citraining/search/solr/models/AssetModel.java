package com.citraining.search.solr.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

@Model(adaptables= {Resource.class,SlingHttpServletRequest.class})
public class AssetModel {
	@Inject
    @Named("jcr:created")
    private String createdDate;

    @Inject
    @Named("jcr:createdBy")
    private String createdBy;

    @Inject
    @Named("jcr:content/jcr:lastModified")
    @Optional
    private String lastModified;

    @Inject
    @Named("jcr:content/metadata/dc:title")
    @Optional
    private String title;

    @Inject
    @Named("jcr:content/metadata/dc:description")
    @Optional
    private String description;

    @PostConstruct
    protected void init() {
         // You can code here any task needed to be executed after all the injections of the model are done
    }

	public String getCreatedDate() {
		return createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getLastModified() {
		return lastModified;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}


}
