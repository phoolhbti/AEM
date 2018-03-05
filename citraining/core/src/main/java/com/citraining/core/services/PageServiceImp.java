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

import com.citraining.core.utils.CommonUtil;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

/**
 * @author Phool Chandra 
 * @description 
 * A component is a piece of software managed by a (component) container. In Java this means this is an instance, created and
 *         managed by a container. Therefore it is not the task of the developer to create or configure these instances. This is the task of the
 *         container. If the component uses other services, it is also the task of the container to pass these services to the component. Therefore a
 *         service is a component which provides one or more services. In Java this means a service implements one or more interfaces where an
 *         interface represents the service description or contract.
 */
@Component (service = { PageService.class })
public class PageServiceImp implements PageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PageServiceImp.class);

	/**
	 * Inject a Sling ResourceResolverFactory
	 */
	@Reference
	private ResourceResolverFactory resolverFactory;

	Session session;

	public String createPage(String pageName) {
		String pagePath = "/content/citraining/en";
		String templatePath = "/apps/citraining/templates/page-home";
		String pageTitle = "AEM home page";
		Page newPage;
		PageManager pageManager;

		ResourceResolver resolver = null;

		try{
			resolver = CommonUtil.getResourceResolver(resolverFactory);
			session = resolver.adaptTo(Session.class);
			// create a page manager instance
			pageManager = resolver.adaptTo(PageManager.class);
			// create a new page
			newPage = pageManager.create(pagePath, pageName, templatePath, pageTitle);
			if (newPage != null){
				Node newNode = newPage.adaptTo(Node.class);
				Node cont = newNode.getNode("jcr:content");
				if (null != cont && null != session){
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
			LOGGER.error("Exception{}", e);
		}
		finally{
			if (null != session && session.isLive()){
				session.logout();
			}
		}

		return "";

	}

}
