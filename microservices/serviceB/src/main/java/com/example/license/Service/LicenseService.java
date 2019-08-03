package com.example.license.Service;

import org.springframework.stereotype.Component;

@Component
public class LicenseService {

	public License getLicense(String organizationId, String licenseId) {

		return License.builder().licenceNumber(licenseId).organizationId(organizationId).build();
	}

}
