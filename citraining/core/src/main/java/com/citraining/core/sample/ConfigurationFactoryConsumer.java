package com.citraining.core.sample;

public interface ConfigurationFactoryConsumer {
	public void bindConfigurationFactory(final ConfigurationFactory config);

	public void unbindConfigurationFactory(final ConfigurationFactory config);

}