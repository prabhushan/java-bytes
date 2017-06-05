package com.prabhu.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import com.prabhu.annotations.TransactionContext;

@Provider
public class MyRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		//Raw request
		TransactionContext.setContext("test Context");

	}

}
