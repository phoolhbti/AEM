package com.citraining.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.config.SolrConfiguration;

/**
 * @author Phool Chandra This servlet deletes all the indexes from the configured Solr server
 */

@Component (service = Servlet.class, configurationPolicy = ConfigurationPolicy.REQUIRE, property = { Constants.SERVICE_DESCRIPTION + "=Delete Solr Index Service", "sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=/bin/solr/delete/all/indexes" })
@Designate (ocd = SolrConfiguration.class)
public class DeleteIndexesFromSolr extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(DeleteIndexesFromSolr.class);

	private SolrConfiguration solrConfiguration;

	@Override
	protected void doPost(final SlingHttpServletRequest reqest, final SlingHttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		final String protocol = solrConfiguration.getSolrProtocol();
		final String serverName = solrConfiguration.getSolrServerName();
		final String serverPort = solrConfiguration.getSolrServerPort();
		final String coreName = solrConfiguration.getSolrCoreName();
		String URL = protocol + "://" + serverName + ":" + serverPort + "/solr/" + coreName;
		HttpSolrClient server = new HttpSolrClient(URL);
		try{
			server.deleteByQuery("*:*");
			server.commit();
			server.close();
			response.getWriter().write("<h3>Deleted all the indexes from solr server </h3>");
		} catch (SolrServerException e){
			LOG.error("Exception due to", e);
		}

	}

	@Override
	protected void doGet(final SlingHttpServletRequest reqest, final SlingHttpServletResponse response) throws ServletException, IOException {
		doPost(reqest, response);
	}

	@Activate
	protected void activate(SolrConfiguration config) {
		solrConfiguration = config;
	}
}
