package com.citraining.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class QuizService extends WCMUsePojo {

	protected QuizImpl services;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void activate() {
		this.services = ((QuizImpl) getSlingScriptHelper().getService(QuizImpl.class));
		this.logger.info("**** THE QUIZ Activate method was invoked");
	}

	public String getMessage() {
		this.logger.info("The Data called");
		return this.services.getData("ss");
	}

}
