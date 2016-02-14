package com.citraining.core.utils;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.QueryManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchUtils {

	private static final Logger LOGGER=LoggerFactory.getLogger(SearchUtils.class);
	private SearchUtils(){
		
	}
	private static QueryManager getQueryManger(final Session session){
		try {
			return session.getWorkspace().getQueryManager();
		} catch (RepositoryException e) {
			LOGGER.error("cannot get the query object return null{}",e);
		}
		return null;
	}
	public static String[] getSupportedQueryLanguage(final Session session){
		try {
			QueryManager qm=getQueryManger(session);
			return qm.getSupportedQueryLanguages();
		} catch (RepositoryException e) {
			LOGGER.error("error is {}",e);
		}
		return null;
	}
	
}
