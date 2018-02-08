package com.citraining.core.services;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component
public class PageServiceImp implements PageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PageServiceImp.class);
	
	/**
	 * Inject a Sling ResourceResolverFactory
	 */
	@Reference
	private ResourceResolverFactory resolverFactory;

	public String createPage(String pageName) {
		String pagePath = "/content/citraining/en";
		String templatePath = "/apps/citraining/templates/page-home";
		String pageTitle = "AEM home page";
		Page newPage;
		PageManager pageManager;

		ResourceResolver resolver = null;

		try{

			// Invoke the adaptTo method to create a Session
			resolver = resolverFactory.getAdministrativeResourceResolver(null);

			Session session = resolver.adaptTo(Session.class);

			// create a page manager instance
			pageManager = resolver.adaptTo(PageManager.class);

			// create a new page
			newPage = pageManager.create(pagePath, pageName, templatePath, pageTitle);
			if (newPage != null){
				String user = resolver.getUserID();

				Node newNode = newPage.adaptTo(Node.class);
				Node cont = newNode.getNode("jcr:content");
				if (cont != null){
					Node rootNode = session.getRootNode();
					String path = rootNode.getPath();
					Node parNode = JcrUtils.getNodeIfExists(cont, "par");
					Node imageNode = JcrUtils.getOrCreateByPath(parNode.getPath() + "/image", JcrConstants.NT_UNSTRUCTURED, session);
					Node textNode = JcrUtils.getNodeIfExists(parNode, "text");
					imageNode.setProperty("sling:resourceType", "foundation/components/image");
					imageNode.setProperty("fileReference", "/content/dam/we-retail-screens/we-retail-instore-logo.png");
					textNode.setProperty("text", "<p>This page is created using page manager</p>");
					session.save();
				}
			}

			return pageName;
		} catch (Exception e){
			LOGGER.error("Exception", e);			
		}

		return "";

	}

}
