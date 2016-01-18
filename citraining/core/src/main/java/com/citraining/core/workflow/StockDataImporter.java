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

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.polling.importer.ImportException;
import com.day.cq.polling.importer.Importer;

@Component
@Service(value=Importer.class)
@Property(name=Importer.SCHEME_PROPERTY,value="Stock",propertyPrivate=true)
public class StockDataImporter implements Importer{
	private final Logger LOGGER=LoggerFactory.getLogger(StockDataImporter.class);
	private final String SOURCE_URL="http://finance.yahoo.com/d/quotes.csv?f=snd1lyr&s=";
	@Override
	public void importData(final String schema,final String dataSource,final Resource resource)throws ImportException{
		try{
			LOGGER.error("SOURCE_URL::"+SOURCE_URL);
			URL souceURL=new URL(SOURCE_URL+dataSource);
			BufferedReader in=new BufferedReader(new InputStreamReader(souceURL.openStream()));
			String readLine=in.readLine();
			LOGGER.error("readLine:"+readLine);
			String lastTrade=Arrays.asList(Pattern.compile(",").split(readLine)).get(3);
			LOGGER.error("last trade for stock {} was {}",dataSource,lastTrade);
			in.close();
			writeToRepository(dataSource,lastTrade,resource);
		}catch(MalformedURLException e){
			LOGGER.error("MalformedURLException",e);
		}
		catch(IOException e){
			LOGGER.error("IOException",e);
		}
		catch(RepositoryException e){
			LOGGER.error("RepositoryException",e);
		}
	}

	@Override
	public void importData(String arg0, String arg1, Resource arg2,
			String arg3, String arg4) throws ImportException {
		// TODO Auto-generated method stub
		//importData(arg0,arg1,arg2);
		
	}
	private void writeToRepository(String stockSymbol,String lastTrade,Resource resource) throws RepositoryException{
		LOGGER.error("starting point of writeToRepository");
		Node parent=resource.adaptTo(Node.class);
		LOGGER.debug("parent path::::::::"+parent.getPath());
		LOGGER.debug("stockSymbol::::::::::::"+stockSymbol);
		Node stockPageNode=JcrUtil.createPath(parent.getPath()+"/"+stockSymbol, "cq:Page", parent.getSession());
		Node lastTradeNode=JcrUtil.createPath(stockPageNode.getPath()+"/lastTrade", "nt:unstructured", parent.getSession());
		lastTradeNode.setProperty("lastTrade", lastTrade);
		lastTradeNode.setProperty("lastUpdate", Calendar.getInstance());
		parent.getSession().save();
	}

}
