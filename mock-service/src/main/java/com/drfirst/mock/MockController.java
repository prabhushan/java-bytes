package com.drfirst.mock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

	private static Logger logger = Logger.getLogger(MockServiceApplication.class);

	Map<String, String> mockMap = new ConcurrentHashMap<>();

	@RequestMapping(path = { "/set/mock" }, method = RequestMethod.POST, consumes = {
			"application/x-www-form-urlencoded" })
	public String setMock(@RequestParam("mockPath") String mockPath,
			@RequestParam("mockResponse") String mockResponse) {
		logger.info("Mock Set" + mockPath + mockResponse);
		mockMap.put(mockPath, mockResponse);
		return "Mock set for url " + mockPath;
	}

	@RequestMapping(path = "/{pathParam}", method = { RequestMethod.POST, RequestMethod.GET })
	public String getMock(@PathVariable("pathParam") String pathParam,
			@RequestHeader(value = "Accept") String acceptType) {
		String response = mockMap.get(pathParam);
		logger.info(acceptType);
		return response;
	}

}
