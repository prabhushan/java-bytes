package com.java.bytes.test;

import java.util.Comparator;

public class ReverseComparator implements Comparator {
	
	private Comparator comparator;
	public ReverseComparator(Comparator comparator){
		this.comparator = comparator;
	}

	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return comparator.compare(o1, o2)*-1;
	}

}
