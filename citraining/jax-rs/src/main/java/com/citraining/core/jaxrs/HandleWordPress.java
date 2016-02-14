package com.citraining.core.jaxrs;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bican.wordpress.FilterPost;
import net.bican.wordpress.Post;
import net.bican.wordpress.Wordpress;

import java.util.List;

@SlingServlet(paths = "/bin/myWordPress", methods = "GET", metatype = true)
public class HandleWordPress extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8161756868066757481L;

	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private ArrayList<String> posts;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) {

		try {
			// Get the form data that is sent from the CQ web page
			String wordpressid = request.getParameter("wordpressid");
			String mypassword = request.getParameter("mypassword");
			String url = request.getParameter("url");

			log.info("*********** THE WordPress ID is " + wordpressid);

			// Create the WordPress instance
			Wordpress wp = new Wordpress(wordpressid, mypassword, url);

			final FilterPost filter = new FilterPost();
			filter.setNumber(7);
			final List<Post> recentPosts = wp.getPosts(filter);
			System.out.println("Here are the ten recent posts:");

			posts = new ArrayList<String>();

			String myTitle = "";

			int size = 0;

			// Place each WordPress post into an element of an ArrayList
			for (final Post page : recentPosts) {

				myTitle = page.getPost_title();

				posts.add(myTitle);
				size++;
			}

			String listString = "";

			for (String s : posts) {
				listString += s + "\n";
			}

			// Set Response
			response.setContentType("text/html");
			response.getWriter().write(listString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}