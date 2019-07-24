package com.example.serviceA;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("serviceA")
public class Controller {

	@Value("${value.properties}")
	private String valueProperties;

	@GetMapping("org/get")
	public String getOrgDetails() {
		return valueProperties;
	}

}
