package com.citraining.core.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;



@Service
@Component
@Path("/my-service")
/**
 * 
 * @author Phool
 * access it using following URL:
 *  http://localhost:4502/services/my-service
 *  here services is default context
 */
@Produces({ MediaType.APPLICATION_JSON })
public class ExampleResource implements Example{
	
	@GET
	@Override
	public String sayHello() {
		return "Hello World";
	}

}