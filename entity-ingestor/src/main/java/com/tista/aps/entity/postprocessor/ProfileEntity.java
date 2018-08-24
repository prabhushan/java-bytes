package com.tista.aps.entity.postprocessor;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileEntity {
	private List<String> inputIdentitiesList;
	private List<String> matchedIdentitiesList;

}
