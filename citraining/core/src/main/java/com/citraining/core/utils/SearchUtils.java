package com.citraining.core.utils;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.InvalidQueryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.lang.annotations.NotNull;

public class SearchUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchUtils.class);

	private SearchUtils() {
		throw new IllegalStateException("SearchUtils class");
	}

	private static QueryManager getQueryManger(final Session session) {
		try{
			return session.getWorkspace().getQueryManager();
		} catch (RepositoryException e){
			LOGGER.error("cannot get the query object return null{}", e);
		}
		return null;
	}

	public static String[] getSupportedQueryLanguage(final Session session) {
		try{
			QueryManager qm = getQueryManger(session);			
			return qm.getSupportedQueryLanguages();
		} catch (RepositoryException e){
			LOGGER.error("error is {}", e);
		}
		return new String[0];
	}

	@NotNull
	public static String getLastMoidifedPage(final Session session, String path, String template) {
		String sqlStatement = "SELECT parent.* FROM [cq:Page] AS parent INNER JOIN [nt:base] AS child ON ISCHILDNODE(child,parent) WHERE ISDESCENDANTNODE(parent, '" + path + "')" + " AND child.[cq:template] = '" + template + "' " + "ORDER BY child.[cq:lastModified] desc";
		QueryManager qm = getQueryManger(session);
		try{
			Query query = qm.createQuery(sqlStatement, "JCR-SQL2");
			QueryResult result = query.execute();
			NodeIterator nodeIter = result.getNodes();

			if (nodeIter.hasNext()){
				Node mynoed = (Node) nodeIter.next();
				return mynoed.getName();
			}
		} catch (InvalidQueryException e){		
			LOGGER.error("Invalid Query{}", e);
		} catch (RepositoryException e){
			LOGGER.error("Repository Exception", e);
		}

		return "";
	}

}
