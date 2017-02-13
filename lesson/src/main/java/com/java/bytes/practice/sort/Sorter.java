package com.java.bytes.practice.sort;

import java.util.Arrays;
import java.util.List;

public class Sorter {

	public static void main(String[] args) {
		List<String> listInt = Arrays.asList(args);
		Sorter sort = new Sorter();
		sort.sortArray(listInt);

	}
	public void sortArray(List<String> listInt) {
		
		for(int i=0;i<listInt.size();i++){
			int indexMin = i;
			for(int j=i+1;j<listInt.size();j++){
				if(Integer.valueOf(listInt.get(indexMin)) > Integer.valueOf(listInt.get(j))){
					indexMin = j;
				}

			}
			
			String smallest = listInt.get(i);
			listInt.set(i, listInt.get(indexMin));
			listInt.set(indexMin,smallest);
		}
		System.out.println(listInt);
	}	

	


}

