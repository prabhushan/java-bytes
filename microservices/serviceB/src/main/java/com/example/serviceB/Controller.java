package com.example.serviceB;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("serviceB")
@RefreshScope
public class Controller {

	@Value("${test.properties}")
	private String testProperties;

	@GetMapping("get-something")
	public String getOrgDetails() {
		return testProperties;
	}

}