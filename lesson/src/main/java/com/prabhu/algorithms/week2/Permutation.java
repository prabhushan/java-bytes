package com.prabhu.algorithms.week2;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

	public static void main(String[] args) {
		if(args.length <= 0){
			throw new IllegalArgumentException("Arguments must be supplied");
		}
		 int k = Integer.parseInt(args[0]);
	        RandomizedQueue<String> rq = new RandomizedQueue<>();
	        String[] strs = StdIn.readAllStrings();
	        StdRandom.shuffle(strs);
	        for (int i = 0; i < k; i++) {
	            rq.enqueue(strs[i]);
	        }
	        for (int i = 0; i < k; i++) {
	            StdOut.println(rq.dequeue());
	        }

	}

}
