package com.citraining.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.qom.Constraint;
import javax.jcr.query.qom.QueryObjectModel;
import javax.jcr.query.qom.QueryObjectModelFactory;
import javax.jcr.query.qom.Selector;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.utils.CommonUtil;
import com.day.cq.commons.jcr.JcrConstants;

@Component (service = Servlet.class, property = { "sling.servlet.paths=/services/query", "sling.servlet.methods=" + HttpConstants.METHOD_GET, Constants.SERVICE_DESCRIPTION + "=Query Demo Servlet" })
public class QueryServlet extends SlingAllMethodsServlet {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	@Reference
	private transient ResourceResolverFactory resolverFactory;

	@Reference
	private transient SlingRepository repo;

	private final transient Logger log = LoggerFactory.getLogger(QueryServlet.class);

	@Override
	public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

		log.info("####### Inside Servlet ########");
		Session session = null;
		try{
			ResourceResolver resolver = CommonUtil.getResourceResolver(resolverFactory);
			session = resolver.adaptTo(Session.class);
			if (null != session){
				log.info("####### session ######### :{} ", session.getUserID());
				Node root = session.getRootNode();
				Node currentNode = root.getNode("content");
				QueryObjectModelFactory qf = currentNode.getSession().getWorkspace().getQueryManager().getQOMFactory();
				Selector selector = qf.selector("cq:PageContent", "s");
				Constraint constriant = qf.descendantNode("s", "/content/we-retail/language-masters/en");
				constriant = qf.and(constriant, qf.propertyExistence("s", JcrConstants.JCR_TITLE));
				QueryObjectModel qm = qf.createQuery(selector, constriant, null, null);
				log.info("######### Query ######### : " + qm.getStatement());
				NodeIterator nodeItr = qm.execute().getNodes();
				PrintWriter pw = response.getWriter();
				while (nodeItr.hasNext()){
					Node node = nodeItr.nextNode();
					log.info("##### NOde Path ####### : {}",  node.getPath() + node.getProperty(JcrConstants.JCR_TITLE).getString());
					pw.println(node.getPath() + " : " + node.getProperty(JcrConstants.JCR_TITLE).getString());
				}
			}
		} catch (Exception e){
			log.error("Exception{}", e.getMessage());
		}
		finally{
			if (null != session && session.isLive()){
				session.logout();
			}
		}

	}

}