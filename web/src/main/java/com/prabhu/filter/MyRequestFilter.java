package com.prabhu.filter;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.prabhu.annotations.Audit;

@Provider
public class MyRequestFilter implements ContainerResponseFilter {

	@Context
	private ResourceInfo resourceInfo;
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		System.out.println(resourceInfo.getResourceMethod().getAnnotation(Audit.class).clazz());
		System.out.println(responseContext.getEntityType());
		Class<AuditManager> c = resourceInfo.getResourceMethod().getAnnotation(Audit.class).clazz();
		try {
			Constructor<AuditManager> constrcr = c.getConstructor();
			String result = constrcr.newInstance().constructAuditEvent(requestContext, responseContext);
			System.out.println("RESULT--"+result);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
