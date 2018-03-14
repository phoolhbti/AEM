package com.citraining.core.utils;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.lang.annotations.NotNull;

public class JcrUtils {
	private static Logger logger = LoggerFactory.getLogger(JcrUtils.class);

	private JcrUtils() {
		throw new IllegalStateException("JcrUtils class");
	}

	public static Node getJCRContent(@NotNull Node node) {
		Node jcrConentNode = null;
		try{
			jcrConentNode = node.getNode("jcr:content");
		} catch (RepositoryException e){
			logger.error("RepositoryException", e);
		}
		return jcrConentNode;
	}

	public static boolean hasJCRContent(@NotNull Node node) {
		boolean jcrConent = false;
		try{
			jcrConent = node.hasNode("jcr:content");
		} catch (RepositoryException e){
			logger.error("RepositoryException{}", e);
		}
		return jcrConent;
	}

}
