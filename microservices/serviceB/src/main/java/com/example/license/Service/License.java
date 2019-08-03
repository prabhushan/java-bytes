package com.example.license.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class License {
	private String licenceNumber;
	private String organizationId;
}
