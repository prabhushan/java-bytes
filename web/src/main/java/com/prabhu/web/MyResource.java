package com.prabhu.web;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.prabhu.annotations.Audit;
import com.prabhu.annotations.TransactionContext;
import com.prabhu.filter.CustomAudit1;
import com.prabhu.filter.CustomAudit2;
import com.prabhu.web.model.InputModel;
import com.prabhu.web.model.OutputModel;

@Path("/myresource")

public class MyResource{
	

	@GET
	@Path("name")
	@Audit(clazz=CustomAudit1.class)
	public String name() {
		return "hello World";
		
	}
	
	@POST
	@Path("details")
	@Audit(clazz=CustomAudit2.class)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public OutputModel getDetails(InputModel inputModel,@Context HttpServletRequest request) {
		 OutputModel model = new OutputModel("sai", TransactionContext.getContext());
		TransactionContext.setContext("sai changed");
		return model;
	}
}
