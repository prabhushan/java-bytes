package com.prabhu.mockito;

import java.util.List;

public interface MockDBService {

	List<String> getListFromDB();

	void getUserDetails(String upperCase);

}
