package com.citraining.core.servlets;

import java.io.IOException;
import java.util.HashMap;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.models.factory.ExportException;
import org.apache.sling.models.factory.MissingExporterException;
import org.apache.sling.models.factory.ModelFactory;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.models.ModelJAXB;

public class ResolveServletUsingPath extends SlingSafeMethodsServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Reference
	private transient ModelFactory modelFactory;

	private transient Logger logger = LoggerFactory.getLogger(ResolveServletUsingPath.class);

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
		Resource resource = request.getResourceResolver().getResource("/content/we-retail/ca/en/experience/jcr:content");
		try{
			response.getWriter().print(modelFactory.exportModelForResource(resource, "jaxb", ModelJAXB.class, new HashMap<>()));
		} catch (ExportException | MissingExporterException e){
			logger.error("ExportException/MissingExporterException{}", e);
		} 
	}
}