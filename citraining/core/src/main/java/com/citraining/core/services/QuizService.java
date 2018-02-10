package com.citraining.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class QuizService extends WCMUsePojo {

	protected Quiz services;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void activate() {
		this.services = getSlingScriptHelper().getService(Quiz.class);
		logger.info("**** THE QUIZ Activate method was invoked");
	}

	public String getMessage() {
		logger.info("The Data called");
		return services.getData("ss");
	}

}
