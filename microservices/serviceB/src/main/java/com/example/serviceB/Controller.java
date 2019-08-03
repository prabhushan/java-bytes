package com.example.serviceB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.license.Service.License;
import com.example.license.Service.LicenseService;

@RestController
@RequestMapping("serviceB")
@RefreshScope
public class Controller {

	@Value("${test.properties}")
	private String testProperties;

	@Autowired
	private LicenseService licenseService;

	@GetMapping("get-something")
	public String getOrgDetails() {
		return testProperties;
	}

	@RequestMapping(value = "/{licenseId}/{organizationId}", method = RequestMethod.GET)
	public License getLicensesWithClient(@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
		return licenseService.getLicense(organizationId, licenseId);
	}

}