package com.citraining.core.sample.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.framework.InvalidSyntaxException;
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
	public synchronized void unbindConfigurationFactory(final ConfigurationFactory config) {
		LOGGER.info("unbindConfigurationFactory:{} ", config.getContentConsumerUrl());
		configurationList.remove(config);
	}
	
	/*private Stream<ConfigurationFactory> getConfigurations0(String filter)
					throws IOException, InvalidSyntaxException {

		ConfigurationFactory[] configurations = configurationList.listConfigurations(filter);
				if (configurations == null)
					configurations = EMPTY;

				return Arrays.stream(configurations);
			}*/
}
