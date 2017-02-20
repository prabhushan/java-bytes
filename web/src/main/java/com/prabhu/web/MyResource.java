package com.prabhu.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;

@Path("/myresource")

public class MyResource extends ResourceConfig{
	
//	public MyResource() {
//		packages("com.prabhu.web");
//	}

	@GET
	public String name() {
		return "hello World";
		
	}
}
