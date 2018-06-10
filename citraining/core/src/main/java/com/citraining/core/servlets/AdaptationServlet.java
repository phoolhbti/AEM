package com.citraining.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.models.AdaptationModel;

@Component (service = Servlet.class, property = { "sling.servlet.methods=GET", "sling.servlet.resourceTypes=" + "/apps/citraining/components/content/helloworld" })

public class AdaptationServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;

	private transient Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		try{
			AdaptationModel slingReqModel = request.adaptTo(AdaptationModel.class);
			if (null != slingReqModel){
				String message = slingReqModel.getMessage();
				response.getWriter().write(message);
				log.debug("Adaptattion DONE {}", message);
			}
		} catch (Exception e){
			log.error("{} Exception! ", e.getMessage());
		}
	}
}