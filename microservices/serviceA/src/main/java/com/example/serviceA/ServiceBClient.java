package com.example.serviceA;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(url = "http://localhost:8081/", name = "SERVICEB")
@FeignClient(value = "SERVICEB")
@RequestMapping("serviceB")
public interface ServiceBClient {

	@RequestMapping(value = "/{licenseId}/{organizationId}", method = RequestMethod.GET)
	public License getLicensesWithClient(@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId);

}
