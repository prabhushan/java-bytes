package com.javabytes.edifact.version;

import org.springframework.stereotype.Component;

@Component("5010")
public class Edifact5010 implements EdifactParser {

	@Override
	public String parseRequest(String request) {
		// TODO Auto-generated method stub
		return "5010 Parsed";
	}

}
