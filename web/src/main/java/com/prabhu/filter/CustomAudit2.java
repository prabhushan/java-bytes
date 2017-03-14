package com.prabhu.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomAudit2 extends BaseAudit{

	@Override
	public AuditEvent constructAuditEvent(ContainerRequestContext reqContext, ContainerResponseContext resContext) {
		
		System.out.println("Inside Audit CUSTOM AUDIT 2");
		return null;
	}


}
