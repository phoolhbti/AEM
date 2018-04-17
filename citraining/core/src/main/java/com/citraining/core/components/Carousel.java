package com.citraining.core.components;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@Model (adaptables = Resource.class)
public class Carousel {
	Logger logger = LoggerFactory.getLogger(Carousel.class);

	@Inject
	@Optional
	@Named ("slides")
	private String[] slides;

	private List<Slide> slideList;

	@PostConstruct
	protected void init() {
		logger.info("slides{}", slides);
		this.slideList = new ArrayList<>();
		Gson gson = new Gson();

		for (String slide : slides){
			Slide eachSlide = gson.fromJson(slide, Slide.class);
			logger.info("json object is {}", eachSlide);
			slideList.add(eachSlide);
		}
		logger.info("linkList is{}", slideList);
	}

	public List<Slide> getList() {
		return slideList;
	}

}
