package com.prabhu.designpatterns.trials;

import com.prabhu.designpatterns.trials.model.DrugHistoryResponse;

public class DefaultProcessor implements IRequestProcessor<DrugHistoryResponse> {


	@Override
	public DrugHistoryResponse process(DrugHistoryResponse response) {
		System.out.println("Default Processor");
		System.out.println(response.getDrug().getDefaultField1());
		return null;
	}

}
