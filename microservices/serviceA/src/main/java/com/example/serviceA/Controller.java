package com.example.serviceA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("serviceA")
public class Controller {

	@Value("${value.properties}")
	private String valueProperties;

	@Autowired
	private ServiceBClient serviceClient;

	@GetMapping("org/get")
	public String getOrgDetails() {
		return valueProperties;
	}

	@GetMapping("{org}/{license}")
	public License getLicense(@PathVariable("org") String organizationId, @PathVariable("license") String licenseId) {
		return serviceClient.getLicensesWithClient(organizationId, licenseId);

	}

}
