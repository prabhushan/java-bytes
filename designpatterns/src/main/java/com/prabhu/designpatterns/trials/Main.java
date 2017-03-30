package com.prabhu.designpatterns.trials;

import com.prabhu.designpatterns.trials.model.CanadaDrugHistoryResponse;
import com.prabhu.designpatterns.trials.model.DrugHistoryResponse;

public class Main {

	public static void main(String[] args) {
		IRequestProcessor<CanadaDrugHistoryResponse> canProcessor = new CanadaProcessor();
		canProcessor.process(new CanadaService().getResponse());
		System.out.println("**********");
		IRequestProcessor<DrugHistoryResponse> defaultProcessor = new DefaultProcessor();
		defaultProcessor.process(new DefaultService().getResponse());
		

	}

}
