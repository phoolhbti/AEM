package com.citraining.core.workflow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.polling.importer.ImportException;
import com.day.cq.polling.importer.Importer;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component (service = Importer.class, property = { Importer.SCHEME_PROPERTY + "=Stock", })
public class StockDataImporter implements Importer {
	private final Logger LOGGER = LoggerFactory.getLogger(StockDataImporter.class);

	private final String SOURCE_URL = "http://finance.yahoo.com/d/quotes.csv?f=snd1lyr&s=";

	@Override
	public void importData(final String schema, final String dataSource, final Resource resource) throws ImportException {
		try{
			LOGGER.error("SOURCE_URL::{}", SOURCE_URL);
			URL souceURL = new URL(SOURCE_URL + dataSource);
			BufferedReader bufferInputStreem = new BufferedReader(new InputStreamReader(souceURL.openStream()));
			String readLine = bufferInputStreem.readLine();
			LOGGER.error("readLine:{}", readLine);
			String lastTrade = Arrays.asList(Pattern.compile(",").split(readLine)).get(3);
			LOGGER.error("last trade for stock {} was {}", dataSource, lastTrade);
			bufferInputStreem.close();
			writeToRepository(dataSource, lastTrade, resource);
		} catch (MalformedURLException e){
			LOGGER.error("MalformedURLException{}", e);
		} catch (IOException e){
			LOGGER.error("IOException{}", e);
		} catch (RepositoryException e){
			LOGGER.error("RepositoryException{}", e);
		}

	}

	@Override
	public void importData(String arg0, String arg1, Resource arg2, String arg3, String arg4) throws ImportException {
		/**
		 * Auto-generated method stub importData(arg0,arg1,arg2);
		 */

	}

	private void writeToRepository(String stockSymbol, String lastTrade, Resource resource) throws RepositoryException {
		LOGGER.error("starting point of writeToRepository");
		Node parent = resource.adaptTo(Node.class);
		PageManager pageManager = resource.getResourceResolver().adaptTo(PageManager.class);

		LOGGER.debug("parent path::::::::{}", parent.getPath());
		LOGGER.debug("stockSymbol::::::::::::{}", stockSymbol);
		try{
			Page stockdetails = pageManager.create(parent.getPath(), stockSymbol, "/apps/citraining/templates/page-content", "Stockpage");
			Node contentRes = stockdetails.getContentResource().adaptTo(Node.class);
			LOGGER.debug("Path of contentRes::::::::::::{}", contentRes.getPath());
			Node pageLastTradeNode = contentRes.addNode("par/lastTrade", "nt:unstructured");
			LOGGER.debug("Path of lastTrade::::::::::::{}", pageLastTradeNode);
			pageLastTradeNode.setProperty("lastTrade", lastTrade);
			pageLastTradeNode.setProperty("lastUpdate", Calendar.getInstance());
			pageLastTradeNode.setProperty("sling:resourceType", "citraining/components/content/text");
			pageLastTradeNode.getSession().save();
		} catch (WCMException e){
			LOGGER.error("WCMException{}", e);

		}

	}

}
