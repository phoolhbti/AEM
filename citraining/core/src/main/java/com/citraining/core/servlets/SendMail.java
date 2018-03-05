package com.citraining.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

@Component (service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=SendMail Servlet", "sling.servlet.paths=/bin/htlMailServlet", "sling.servlet.methods=" + HttpConstants.METHOD_POST })
public class SendMail extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;

	private List<MailService> mailService = new ArrayList<>();
	@Reference (name = "mailService",  cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC,target="(getMailServiceName=InternetA)")
	//private transient MailService mailService;
	
	/**
	 * Default log.
	 */
	private final transient Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

		try{
			String topicSubject = request.getParameter("topicSubject");
			String message = request.getParameter("message");

			log.info("*** SUBJECT: {}", topicSubject);
			boolean subject = "InternetA".equals(topicSubject);

			/*if (subject)
				mailService.sendMail(message);
			else 
				mailService.sendMail(message);*/
			response.getWriter().write("EMAIL GONE");

		} catch (Exception e){
			log.error("Exception{}", e);
		}
	}

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}
	void addMailService(MailService service, Map<String, Object> properties) {
        this.mailService.add(service);
        
        System.out.println("Added " + service.getClass().getName());
        properties.forEach((k, v) -> {
            System.out.println(k+"="+v);
        });
        System.out.println();
    }

    void removeMailService(MailService service) {
        this.mailService.remove(service);
        System.out.println("Removed " + service.getClass().getName());
    }

    public void retrieve(String message) {
        for (MailService service : this.mailService) {
            System.out.println("Hello world...");
        }
    }
}