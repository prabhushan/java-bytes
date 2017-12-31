package com.prabhu.mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockTest {
	
	private MockDBService mockDBService;

	public List<String> getSomeValue(String comparisonValue) {
		List<String> listValues = mockDBService.getListFromDB();
		List<String> returnValues = new ArrayList<>();
		if(listValues == null){
			return Collections.EMPTY_LIST;
		}
		for (String value : listValues) {
			if(value.equalsIgnoreCase(comparisonValue)){
				returnValues.add(value);
			}
		}
		return returnValues;
		
	}
	
	public MockDBService getMockDBService() {
		return mockDBService;
	}
	public void setMockDBService(MockDBService mockDBService) {
		this.mockDBService = mockDBService;
	}
	
	
	 
	
}
