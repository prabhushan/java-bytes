package com.java.bytes.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
		DrugHistory a = new DrugHistory("a","2","3");
		DrugHistory b = new DrugHistory("b","2","3");
		DrugHistory c = new DrugHistory("c","2","3");
		DrugHistory d = new DrugHistory("d","2","3");
		List<DrugHistory> listDrugHistory = new ArrayList<DrugHistory>();
		listDrugHistory.add(a);
		listDrugHistory.add(b);
		listDrugHistory.add(c);
		listDrugHistory.add(d);
		new Test().dedupe(listDrugHistory);

	}

	private void dedupe(List<DrugHistory> listDrugHistory) {
		Set<DrugHistory> toRetain = new TreeSet<DrugHistory>(new DrugHistoryComparator());
		toRetain.addAll(listDrugHistory);
		
		for (DrugHistory drugHistory : toRetain) {
			System.out.println(drugHistory);
		}
		System.out.println("*****************");
		reverseOrder(listDrugHistory);
	}
	
	private void reverseOrder(List<DrugHistory> listDrugHistory){
		Set<DrugHistory> toRetain = new TreeSet<DrugHistory>(new ReverseComparator(new DrugHistoryComparator()));
		toRetain.addAll(listDrugHistory);
		for (DrugHistory drugHistory : toRetain) {
			System.out.println(drugHistory);
		}
	}
}
