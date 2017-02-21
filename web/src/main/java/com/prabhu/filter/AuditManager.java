package com.prabhu.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

public abstract class AuditManager {
	
	abstract String constructAuditEvent(ContainerRequestContext reqContext,ContainerResponseContext resContext);

}
