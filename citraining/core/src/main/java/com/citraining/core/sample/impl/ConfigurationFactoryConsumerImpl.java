package com.citraining.core.sample.impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.sample.ConfigurationFactory;
import com.citraining.core.sample.ConfigurationFactoryConsumer;

@Component (immediate = true, service = ConfigurationFactoryConsumer.class)
public class ConfigurationFactoryConsumerImpl implements ConfigurationFactoryConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFactoryConsumerImpl.class);

	private List<ConfigurationFactory> configurationList;

	/**
	 * Executed on Configuration Add event
	 * 
	 * @param config New configuration for factory
	 */
	@Override
	@Reference (name = "configurationFactory", cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public synchronized void bindConfigurationFactory(final ConfigurationFactory config) {
		LOGGER.info("bindConfigurationFactory:{} ", config.getContentConsumerUrl());
		if (configurationList == null){
			configurationList = new ArrayList<>();
		}
		configurationList.add(config);
	}

	/**
	 * Executed on Configuration Remove event
	 * 
	 * @param config New configuration for factory
	 */
	@Override
	public synchronized void unbindConfigurationFactory(final ConfigurationFactory config) {
		LOGGER.info("unbindConfigurationFactory:{} ", config.getContentConsumerUrl());
		configurationList.remove(config);
	}

	@Override
	public ConfigurationFactory get(String filter) {
		for (ConfigurationFactory confFact : configurationList) {
			if (filter.equals(confFact.getContentConsumerUrl()))
			 return confFact;
	}
	return null;
	}
}
