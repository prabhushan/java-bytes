package com.java.bytes.practice.sort;

import java.util.Arrays;
import java.util.List;

public class InsertionSorter {

	public static void main(String[] args) {
		System.out.println(args.length);

		System.out.println(Math.ceil(args.length/2d));
		List<String> listInt = Arrays.asList(args);
		InsertionSorter sort = new InsertionSorter();
		sort.sortArray(listInt);

	}
	public void sortArray(List<String> listInt) {
		
		for(int i=0;i<listInt.size();i++){
			for(int j=0;j<listInt.size()-1;j++){
				if(Integer.valueOf(listInt.get(j)) > Integer.valueOf(listInt.get(j+1))){
					swap(j,j+1,listInt);
				}

			}
			
		
		}
		System.out.println(listInt);
	}
	private void swap(int j, int i, List<String> listInt) {
		String smallest = listInt.get(i);
		listInt.set(i, listInt.get(j));
		listInt.set(j,smallest);
		
	}	

	


}

