package com.citraining.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.citraining.core.mail.MailService;

@Component (service = Servlet.class,property = { Constants.SERVICE_DESCRIPTION + "=SendMail Servlet", "sling.servlet.paths=/bin/htlMailServlet", "sling.servlet.methods="+ HttpConstants.METHOD_POST })
public class SendMail extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;

	//@Reference (target = "(mailservice.label=InternetA)")
	@Reference(name = "configurationFactory", cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	private transient MailService mailService;

	//@Reference (target = "(mailservice.label=InternetB)")
	//private transient MailService mailServiceB;

	/**
	 * Default log.
	 */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

		try{
			// Get the submitted data from the HTL front end
			String topicSubject = request.getParameter("TopicSubject");
			String message = request.getParameter("message");

			log.info("*** SUBJECT: {}", topicSubject);

			if ("InternetA".equals(topicSubject) == true)
				mailServiceA.sendMail(message);
			else mailServiceB.sendMail(message);

			// Return the JSON formatted data
			response.getWriter().write("EMAIL GONE");

		} catch (Exception e){
			log.error("Exception{}", e);
		}
	}
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

}