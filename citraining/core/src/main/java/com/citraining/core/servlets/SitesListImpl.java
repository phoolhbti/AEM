package com.citraining.core.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.nodetype.NodeType;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.TidyJSONWriter;

@Component (service = Servlet.class, property = { "sling.servlet.paths=/services/siteslist", "sling.servlet.methods=get" })
public class SitesListImpl extends SlingAllMethodsServlet {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	@Reference
	private transient ResourceResolverFactory resolverFactory;

	@Reference
	private transient SlingRepository slingRepository;

	private final transient Logger LOGGER = LoggerFactory.getLogger(SitesListImpl.class);

	@Override
	public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

		LOGGER.info("####### Inside SitesListImpl ########");
		Session session = null;
		TidyJSONWriter tidyJSONWriter = new TidyJSONWriter(response.getWriter());

		try{
			tidyJSONWriter.array();
			session = slingRepository.login(new SimpleCredentials("admin", "admin".toCharArray()));
			Node root = session.getRootNode();
			Node currentNode = root.getNode("content");
			getNodes(currentNode, tidyJSONWriter);

			tidyJSONWriter.endArray();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

		} catch (Exception e){
			LOGGER.error(e.getMessage());
		}
		finally{
			if (null != session && session.isLive()){
				session.logout();
			}
		}

	}

	public void getNodes(Node path, TidyJSONWriter tidyJSONWriter) {
		try{
			NodeIterator itr = path.getNodes();

			while (itr.hasNext()){
				Node node = itr.nextNode();
				NodeType nt = node.getPrimaryNodeType();
				if (nt.isNodeType("cq:Page")){
					LOGGER.info(" Node Type :{} ", nt);
					LOGGER.info(" Node Path :{} ", node.getPath());

					String[] sites = node.getPath().split("/");
					String site = sites[2];

					tidyJSONWriter.object();

					tidyJSONWriter.key("title").value(node.getName());
					tidyJSONWriter.key("path").value(node.getPath());
					tidyJSONWriter.key("site").value(site);
					tidyJSONWriter.key("collapsed").value(true);
					tidyJSONWriter.key("child");
					tidyJSONWriter.array();
					if (node.hasNodes()){
						getNodes(node, tidyJSONWriter);
					}
					tidyJSONWriter.endArray();
					tidyJSONWriter.endObject();
				}
			}

		} catch (Exception e){
			LOGGER.error(e.getMessage());
		}

	}

}