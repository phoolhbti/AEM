package com.citraining.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import com.drew.lang.annotations.NotNull;

public class CommonUtil {
	private CommonUtil() {
		throw new IllegalStateException("CommonUtil class");
	}

	public static ResourceResolver getResourceResolver(@NotNull ResourceResolverFactory resourceResolverFactory) throws LoginException {
		Map<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, "citrainingService");
		return resourceResolverFactory.getServiceResourceResolver(param);
	}

}
