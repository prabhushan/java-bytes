package com.prabhu.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

public class AuditFilter extends AuditManager{


	@Override
	public String constructAuditEvent(ContainerRequestContext reqContext, ContainerResponseContext resContext) {
		// TODO Auto-generated method stub
		return "Inside Audit Filter";
	}

}
