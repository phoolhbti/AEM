package com.citraining.core.servlets;

import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component (service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Authentication Servlet", "sling.servlet.methods=" + HttpConstants.METHOD_HEAD, "sling.servlet.paths="+"/bin/permissioncheck"})
public class AuthcheckerServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 7979979958671281729L;

	private transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void doHead(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try{
			String uri = request.getParameter("uri");
			Session session = request.getResourceResolver().adaptTo(Session.class);
			if (null != session){
				session.checkPermission(uri, Session.ACTION_READ);
				logger.info("authchecker says OK");
				response.setStatus(SlingHttpServletResponse.SC_OK);
			}
		} catch (Exception e){
			logger.info("authchecker says READ access DENIED!");
			response.setStatus(SlingHttpServletResponse.SC_FORBIDDEN);
		}

	}
}