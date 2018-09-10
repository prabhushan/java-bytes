package com.prabhu.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalProfiles {

	private List<EntityProfile> listProfile;

	private EntityProfileIdnty entityProfileIdnty;

}
