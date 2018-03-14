package com.citraining.core.workflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.utils.CommonUtil;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.polling.importer.Importer;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component (service = Importer.class, property = { Importer.SCHEME_PROPERTY + "=Stock", })
public class StockDataImporter implements Importer {
	private final Logger logger = LoggerFactory.getLogger(StockDataImporter.class);

	@Reference
	private ResourceResolverFactory resolverFactory;

	private  String sourceUrl = "https://api.iextrading.com/1.0/stock/";

	Session session;
	BufferedReader bufferInputStreem;
	PageManager pageManager;
	@Override
	public void importData(final String schema, final String dataSource, Resource resource) {
		try{
			logger.debug("SOURCE_URL::{}", sourceUrl);
			URL souceURL = new URL(sourceUrl + dataSource + "/price");
			bufferInputStreem = new BufferedReader(new InputStreamReader(souceURL.openStream()));
			String readLine = bufferInputStreem.readLine();
			logger.debug("readLine:{}", readLine);
			logger.debug("last trade for stock {} was {}", dataSource, readLine);
			bufferInputStreem.close();
			writeToRepository(dataSource, readLine, resource);
		} catch ( IOException |RepositoryException e){
			logger.error("IOException/RepositoryException{}", e);
		} 

	}

	@Override
	public void importData(String arg0, String arg1, Resource arg2, String arg3, String arg4) {
		/**
		 * Auto-generated method stub importData(arg0,arg1,arg2);
		 */

	}

	private void writeToRepository(String stockSymbol, String lastTrade, Resource resource) throws RepositoryException {
		logger.debug("starting point of writeToRepository");
		try{
			ResourceResolver resolver = CommonUtil.getResourceResolver(resolverFactory);
			session = resolver.adaptTo(Session.class);
		    pageManager = resolver.adaptTo(PageManager.class);
			logger.debug("parent path::::::::{}", resource.getPath());
			logger.debug("stockSymbol::::::::::::{}", stockSymbol);
			Page stockdetails = pageManager.create(resource.getPath(), stockSymbol, "/apps/citraining/templates/page-content", "Stockpage");
			if (null != stockdetails){
				Node contentResource = stockdetails.getContentResource().adaptTo(Node.class);				
				if (null != session && null!=contentResource){
					logger.debug("Path of contentRes::::::::::::{}", contentResource.getPath());
					Node parNode = JcrUtils.getNodeIfExists(contentResource, "par");
					Node pageLastTradeNode = JcrUtils.getOrCreateByPath(parNode.getPath() + "/lastTrade", JcrConstants.NT_UNSTRUCTURED, session);
					logger.debug("Path of lastTrade::::::::::::{}", pageLastTradeNode);
					pageLastTradeNode.setProperty("lastTrade", lastTrade);
					pageLastTradeNode.setProperty("lastUpdate", Calendar.getInstance());
					pageLastTradeNode.setProperty("sling:resourceType", "citraining/components/content/text");
					session.save();
				}
			}
		} catch (WCMException | LoginException e){
			logger.error("WCMException{}", e);
			Objects.requireNonNull(pageManager, "page mangaer cannot be null");

		}

	}

}
