package com.citraining.core.models;

import java.util.Map;

import org.apache.sling.models.factory.ExportException;

public interface ModelExporter {
	public <T> T export(Object model, Class<T> clazz, Map<String, String> options) throws ExportException;

	public String getName();

	public boolean isSupported(Class<?> model1);

}
