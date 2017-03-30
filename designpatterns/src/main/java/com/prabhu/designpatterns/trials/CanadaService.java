package com.prabhu.designpatterns.trials;

import com.prabhu.designpatterns.trials.model.CanadaDrug;
import com.prabhu.designpatterns.trials.model.CanadaDrugHistoryResponse;

public class CanadaService {

	CanadaDrugHistoryResponse getResponse(){
		CanadaDrugHistoryResponse response = new CanadaDrugHistoryResponse();
		CanadaDrug drug = new CanadaDrug();
		drug.setDefaultField1("US default value");
		drug.setNewDrugField1("Canada Drug field1");
		drug.setNewDrugField2("Canada Drug field2");
		response.setDrug(drug);
		return response;
		
	}
}
