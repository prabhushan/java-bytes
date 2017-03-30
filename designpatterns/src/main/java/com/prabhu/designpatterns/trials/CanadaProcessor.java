package com.prabhu.designpatterns.trials;

import com.prabhu.designpatterns.trials.model.CanadaDrug;
import com.prabhu.designpatterns.trials.model.CanadaDrugHistoryResponse;

public class CanadaProcessor implements IRequestProcessor<CanadaDrugHistoryResponse> {


	@Override
	public CanadaDrugHistoryResponse process(CanadaDrugHistoryResponse response) {
		System.out.println("Canada Processor");
		System.out.println(response.getDrug().getDefaultField1());
		System.out.println(((CanadaDrug)response.getDrug()).getNewDrugField1());
		System.out.println(((CanadaDrug)response.getDrug()).getNewDrugField2());
		return null;
	}



}
