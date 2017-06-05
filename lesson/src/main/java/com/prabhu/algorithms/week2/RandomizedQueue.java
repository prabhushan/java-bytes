package com.prabhu.algorithms.week2;


import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] itemArr = null;
	private int N = 0;

	public RandomizedQueue() {
		itemArr = (Item[]) new Object[1];
	}

	public boolean isEmpty() {
		return N <= 0;
		// is the queue empty?
	}

	public int size() {
		return N;
		// return the number of items on the queue
	}

	public void enqueue(Item item) {
		if (N == itemArr.length) {
			resize(2 * itemArr.length);
		}
		itemArr[N++] = item;
	}

	public Item dequeue() {
		int randomIndex = StdRandom.uniform(N);
		Item returnValue = itemArr[randomIndex];
		itemArr[randomIndex] = itemArr[N - 1];
		itemArr[N - 1] = null;
		N--;
		if (N > 0 && N == itemArr.length / 4)
			resize(itemArr.length / 2);
		return returnValue;
		// remove and return a random item
	}

	public Item sample() {
		int randomIndex = StdRandom.uniform(N);
		return itemArr[randomIndex];
		// return (but do not remove) a random item
	}

	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> randomizeQueue = new RandomizedQueue<>();
		randomizeQueue.enqueue(1);
		randomizeQueue.enqueue(2);
		randomizeQueue.enqueue(3);
		randomizeQueue.enqueue(4);
		System.out.println("size--"+randomizeQueue.size());
		System.out.println(randomizeQueue.isEmpty());

		System.out.println(randomizeQueue.sample());
		System.out.println(randomizeQueue.sample());
		System.out.println(randomizeQueue.sample());
		System.out.println(randomizeQueue.sample());
		
		Iterator<Integer> itemIterator = randomizeQueue.iterator();
		while (itemIterator.hasNext()) {
			System.out.println("Iterator --"+itemIterator.next());
		}
		
		System.out.println("size--"+randomizeQueue.size());
		System.out.println(randomizeQueue.isEmpty());
		
		System.out.println(randomizeQueue.dequeue());
		System.out.println(randomizeQueue.dequeue());
		System.out.println(randomizeQueue.dequeue());
		System.out.println(randomizeQueue.dequeue());
		System.out.println("size--"+randomizeQueue.size());
		System.out.println(randomizeQueue.isEmpty());


	}

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		assert capacity >= N;

		// textbook implementation
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			temp[i] = itemArr[i];
		}
		itemArr = temp;

		// alternative implementation
		// a = java.util.Arrays.copyOf(a, capacity);
	}
	
	private class RandomQueueIterator implements Iterator<Item>
	{
		private int i = 0;
		private int[] indices;
		
		public RandomQueueIterator()
		{
			indices = new int[N];
			for(int j=0;j<indices.length;j++)
			{
				indices[j] = j;
			}
			StdRandom.shuffle(indices);
		}
		
		public boolean hasNext()
		{
			return i<N;
		}
		
		public Item next() throws java.util.NoSuchElementException
		{
			if(!hasNext()) throw new java.util.NoSuchElementException("No more items in iteration.");
			return (Item) itemArr[indices[i++]];
		}
		
		public void remove() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException("remove() is not supported");
		}
	}

}