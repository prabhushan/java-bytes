package com.java.bytes.test;

import java.util.Comparator;

public class DrugHistoryComparator implements Comparator<DrugHistory> {

	public int compare(DrugHistory o1, DrugHistory o2) {

		if (o1 == null && o2 == null) {
			return 0;
		}

		if (o1 == null && o2 != null) {
			return -1;
		}

		if (o1 != null && o2 == null) {
			return 1;
		}
		
		return o1.getDrugName().compareToIgnoreCase(o2.getDrugName());
	}


}
