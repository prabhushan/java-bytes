package com.prabhu.designpatterns.trials;

import com.prabhu.designpatterns.trials.model.Drug;
import com.prabhu.designpatterns.trials.model.DrugHistoryResponse;

public class DefaultService {
	
	DrugHistoryResponse getResponse(){
		DrugHistoryResponse response = new DrugHistoryResponse();
		Drug drug = new Drug();
		drug.setDefaultField1("US default value");
		response.setDrug(drug);
		return response;
		
	}

}
