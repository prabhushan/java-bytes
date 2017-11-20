package com.javabytes.edifact.version;

import org.springframework.stereotype.Component;

@Component("10.6")
public class Edifact106 implements EdifactParser {

	@Override
	public String parseRequest(String request) {
		// TODO Auto-generated method stub
		return "10.6 Parsed";
	}
}
