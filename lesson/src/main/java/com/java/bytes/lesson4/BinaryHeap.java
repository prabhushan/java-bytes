package com.java.bytes.lesson4;

public class BinaryHeap {

	int[] heap = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private void swim(int k) {
		while (k >= 1) {
			if (less(k, k / 2)) {
				swap(k, k / 2);
				k = k / 2;
			}
		}
	}

	private void sink(int k) {
		int size = 0;
		while (2*k <= size) {
			if (heap[2 * k] > heap[(2 * k) + 1]) {
				swap(k, 2 * k);
			} else {
				swap(k, (2 * k) + 1);
			}
		}
	}

	private void swap(int k, int i) {
		// TODO Auto-generated method stub

	}

	private boolean less(int k, int i) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void delMax(){
		
	}

}
