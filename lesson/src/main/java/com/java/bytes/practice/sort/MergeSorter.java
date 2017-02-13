package com.java.bytes.practice.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSorter {

	public static void main(String[] args) {
		System.out.println(args.length);

		System.out.println(Math.ceil(args.length / 2d));
		List<String> listInt = Arrays.asList(args);
		MergeSorter sort = new MergeSorter();
		sort.sortArray(listInt);

	}

	public void sortArray(List<String> listInt) {

		splitRecursively(listInt);
		System.out.println(listInt);
	}

	private void splitRecursively(List<String> listInt) {
		if (listInt != null && listInt.size() > 0) {
			if (listInt.size() == 1) {
				System.out.println(listInt.get(0));
				return;
			}
			int d = (listInt.size()/2);
			List<String> listSplit = new ArrayList<>();
			for(int j = 0;j<d;j++ ){
				listSplit.add(listInt.get(j));
			}
			splitRecursively(listSplit);
		}

	}

}
