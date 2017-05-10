package com.citraining.core.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;


	// Plain old Java Object it does not extend as class or implements 
	// an interface

	// The class registers its methods for the HTTP GET request using the @GET annotation. 
	// Using the @Produces annotation, it defines that it can deliver several MIME types,
	// text, XML and HTML. 

	// The browser requests per default the HTML MIME type.

	//Sets the path to base URL + /hello
// http://localhost:4502/services/hellotest
@Component
@Service
@Path("/hellotest")

	public class TextResponseSample implements Example{

	  // This method is called if TEXT_PLAIN is request
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
	    return "Hello Jersey";
	  }

	  // This method is called if XML is request
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	  }
	// This method is called if JSON is request
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Track  sayJSONHello() {
	    //return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;

	  }
	  // This method is called if HTML is request
	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	  }
	  @POST
	  @Produces(MediaType.TEXT_HTML)
	  public String sayPostlHello() {
	    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
	        + "<body><h1>" + "Hello Jersey Post method" + "</body></h1>" + "</html> ";
	  }
	@Override
	public String sayHello() {
		// TODO Auto-generated method stub
		return "Hello world";
	}

}
