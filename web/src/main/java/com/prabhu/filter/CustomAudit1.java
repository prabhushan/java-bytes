package com.prabhu.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

public class CustomAudit1 extends BaseAudit{


	@Override
	public AuditEvent constructAuditEvent(ContainerRequestContext reqContext, ContainerResponseContext resContext) {
		// TODO Auto-generated method stub
		
		System.out.println("Inside Audit CUSTOM AUDIT 1");
		return null;
	}

}
