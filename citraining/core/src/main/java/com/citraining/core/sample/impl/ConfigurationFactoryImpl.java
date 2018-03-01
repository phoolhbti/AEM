package com.citraining.core.sample.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.sample.ConfigurationFactory;

@Component (service = ConfigurationFactory.class,configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate (ocd = ConfigurationFactoryImpl.Config.class, factory = true)
public class ConfigurationFactoryImpl implements ConfigurationFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFactoryImpl.class);

	@ObjectClassDefinition (name = "Citraining configuration factory")
	public @interface Config {
		@AttributeDefinition (name = "Content Consumer URL", defaultValue = "http://localhost:8081")
		String getContentConsumerUrl();
	}

	private String contentConsumerUrl;

	@Activate
	@Modified
	protected void activate(final Config config) {
		contentConsumerUrl = config.getContentConsumerUrl();
		LOGGER.info("Read the content Consumer Url :{} ", contentConsumerUrl);
	}

	@Override
	public String getContentConsumerUrl() {
		return contentConsumerUrl;
	}
}