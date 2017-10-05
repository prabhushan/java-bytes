package com.drfirst.mock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

	static Logger logger = LoggerFactory.getLogger(MockController.class);

	Map<String,String> mockMap = new ConcurrentHashMap<>();
	@RequestMapping(path = { "/set/mock" }, method = RequestMethod.POST, consumes = {
			"application/x-www-form-urlencoded" })
	public String setMock(@RequestParam("mockPath") String mockPath,@RequestParam("mockResponse") String mockResponse) {
		logger.info("test" + mockPath + mockResponse);
		mockMap.put(mockPath, mockResponse);
		return "OK";
	}
	
	@RequestMapping(path="/{pathParam}", method = {RequestMethod.POST,RequestMethod.GET})
	public String getMock(@PathVariable("pathParam") String pathParam){
		return mockMap.get(pathParam);
	}

}
