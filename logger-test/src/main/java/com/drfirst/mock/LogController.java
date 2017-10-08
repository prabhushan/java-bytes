package com.drfirst.mock;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogController {
	
	private static Logger logger = Logger.getLogger(LogController.class);

	@RequestMapping(path="/log/message",method={RequestMethod.POST})
	public String logMessage(@RequestBody String message) {
		logger.warn("---- Going to log message -- ");
		logger.warn(message);
		logger.warn("--- logged message --");
		return "ok";
	}

}
