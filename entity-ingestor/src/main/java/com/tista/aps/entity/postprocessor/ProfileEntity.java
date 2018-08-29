package com.tista.aps.entity.postprocessor;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity {
	private List<String> inputIdentitiesList;
	private List<String> matchedIdentitiesList;

}
