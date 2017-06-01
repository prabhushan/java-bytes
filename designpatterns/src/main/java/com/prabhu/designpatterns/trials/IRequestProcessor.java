package com.prabhu.designpatterns.trials;

import com.prabhu.designpatterns.trials.model.DrugHistoryResponse;

public interface IRequestProcessor<T extends DrugHistoryResponse> {
	
	T process(T response);

}
