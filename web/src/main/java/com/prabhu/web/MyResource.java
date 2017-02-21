package com.prabhu.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;

import com.prabhu.annotations.Audit;
import com.prabhu.filter.AuditFilter;
import com.prabhu.filter.AuditFilterNew;
import com.prabhu.web.model.InputModel;
import com.prabhu.web.model.OutputModel;

@Path("/myresource")

public class MyResource extends ResourceConfig{
	
//	public MyResource() {
//		packages("com.prabhu.web");
//	}

	@GET
	@Path("name")
	@Audit(clazz=AuditFilterNew.class)
	public String name() {
		return "hello World";
		
	}
	
	@POST
	@Path("details")
	@Audit(clazz=AuditFilter.class)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public OutputModel getDetails(InputModel inputModel) {
		return new OutputModel("sai", "1234");
		
	}
}
