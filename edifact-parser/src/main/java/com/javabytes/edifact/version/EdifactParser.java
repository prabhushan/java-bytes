package com.javabytes.edifact.version;

/**
 * This is used to parse the request and send the parsed response
 * @author prabhu
 *
 */
public interface EdifactParser {
	
	String parseRequest(String request);
}
