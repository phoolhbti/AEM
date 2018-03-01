package com.citraining.core.models;

import java.util.Map;

import org.apache.sling.models.factory.ExportException;
import org.osgi.service.component.annotations.Component;

import com.google.gson.Gson;

@Component(service = ModelExporter.class)
public class GSONExporter implements ModelExporter {
	public <T> T export(Object model, Class<T> clazz, Map<String, String> options) throws ExportException {
		return (T) new Gson().toJson(model);
	}

	public String getName() {
		return "gson";
	}

	public boolean isSupported(Class model) {
		return true;
	}
}
