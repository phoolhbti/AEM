package com.citraining.core.models;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.citraining.core.request.param.RequestParameter;

@Model (adaptables = SlingHttpServletRequest.class)
public class RequestParamsInjected {

	private static final Map<Object, String> CONTENT = new HashMap<>();

	static{
		CONTENT.put("param1", "Content 1");
		CONTENT.put(963, "Content 2");
		CONTENT.put("regexpparam", "Content for regexp");
	}
	@SlingObject
	private SlingHttpServletRequest request;

	@RequestParameter
	private String stringParam;

	@RequestParameter (optional = true)
	private Integer integerParam;

	@RequestParameter (regexp = "\\d\\w{2}\\d", optional = true)
	private String regexpParam;

	public String getStringContent() {
		return CONTENT.get(stringParam);
	}

	public String getIntegerContent() {
		return CONTENT.get(integerParam);
	}

	public String getRegexpContent() {
		return CONTENT.get(regexpParam);
	}

}