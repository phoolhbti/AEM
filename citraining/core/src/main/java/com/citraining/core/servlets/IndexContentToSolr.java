package com.citraining.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.config.SolrConfiguration;
import com.citraining.core.search.SolrSearchService;

/**
 * This servlet acts as a bulk update to index content pages and assets to the configured Solr server
 */
@Component (service = Servlet.class, configurationPolicy = ConfigurationPolicy.REQUIRE, property = { Constants.SERVICE_DESCRIPTION + "=IndexContentToSolr Service", "sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=/bin/solr/push/pages" })
@Designate (ocd = SolrConfiguration.class)
public class IndexContentToSolr extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(IndexContentToSolr.class);

	private SolrConfiguration solrConfiguration;

	@Reference
	private transient SolrSearchService solrSearchService;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String indexType = request.getParameter("indexType");
		final String protocol = solrConfiguration.getSolrProtocol();
		final String serverName = solrConfiguration.getSolrServerName();
		final String serverPort = solrConfiguration.getSolrServerPort();
		final String coreName = solrConfiguration.getSolrCoreName();
		final String pagesResourcePath = solrConfiguration.getSolrCorePagepath();
		String URL = protocol + "://" + serverName + ":" + serverPort + "/solr/" + coreName;
		HttpSolrClient server = new HttpSolrClient(URL);
		if (indexType.equalsIgnoreCase("indexpages")){
			try{
				JSONArray indexPageData = solrSearchService.crawlContent(pagesResourcePath, "cq:PageContent");
				boolean resultindexingPages = solrSearchService.indexPagesToSolr(indexPageData, server);
				if (resultindexingPages == true){
					response.getWriter().write("<h3>Successfully indexed content pages to Solr server </h3>");
				} else{
					response.getWriter().write("<h3>Something went wrong</h3>");
				}
			} catch (JSONException | SolrServerException e){
				LOG.error("Exception due to", e);
				response.getWriter().write("<h3>Something went wrong. Please make sure Solr server is configured properly in Felix</h3>");
			}

		} else{
			response.getWriter().write("<h3>Something went wrong</h3>");
		}

	}
	 @Activate
	    protected void activate( SolrConfiguration config ) {
		 solrConfiguration=config;
	 }

}
