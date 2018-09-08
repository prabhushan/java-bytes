package com.prabhu.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyzeModel {

	private String profileEntityId;
	private String resolvedidentityIds;
	private String currentIdentityIds;

	@Override
	public String toString() {
		return profileEntityId + "," + currentIdentityIds + "," + resolvedidentityIds;
	}

}
