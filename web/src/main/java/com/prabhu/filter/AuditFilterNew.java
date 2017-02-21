package com.prabhu.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.ext.Provider;

@Provider
public class AuditFilterNew extends AuditManager{

	@Override
	public String constructAuditEvent(ContainerRequestContext reqContext, ContainerResponseContext resContext) {
		
		return "Inside Audit Filter New";
	}


}
