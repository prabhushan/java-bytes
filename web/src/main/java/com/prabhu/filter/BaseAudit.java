package com.prabhu.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

public abstract class BaseAudit {
	
	abstract AuditEvent constructAuditEvent(ContainerRequestContext reqContext,ContainerResponseContext resContext);

}
