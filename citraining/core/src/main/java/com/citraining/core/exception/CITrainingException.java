package com.citraining.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Phool Chandra on 3/27/2017.
 */
public class CITrainingException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CITrainingException.class);

	final String loggingMessage = CITrainingException.class.toString();

	public CITrainingException() {
		super();
		LOGGER.error(loggingMessage);
	}

	public CITrainingException(String message) {
		super(message);
		LOGGER.error(loggingMessage, message);
	}

	public CITrainingException(Throwable message) {
		super(message);
		LOGGER.error(loggingMessage, message);
	}

	public CITrainingException(Exception message) {
		super(message);
		LOGGER.error(loggingMessage, message);
	}

	public String getFullMessage() {
		return super.getMessage();
	}
}
