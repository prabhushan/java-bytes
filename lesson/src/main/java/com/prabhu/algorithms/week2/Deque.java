package com.prabhu.algorithms.week2;


import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
	private Node<Item> firstNode = new Node<Item>();
	private Node<Item> lastNode = new Node<Item>();
	private int count;

	public Deque() {
		firstNode.next = lastNode;
		firstNode.prev = null;
		firstNode.item = null;
		lastNode.item = null;
		lastNode.prev = firstNode;
		lastNode.next = null;
	}

	public boolean isEmpty() {
		return count <= 0;
	}

	public int size() {
		return count;
		// return the number of items on the deque
	}

	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		Node<Item> newNode = new Node<>();
		newNode.next = firstNode.next;
		newNode.prev = firstNode;
		newNode.item = item;
		firstNode.next.prev = newNode;
		firstNode.next = newNode;
		count++;
	}

	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		Node<Item> newNode = new Node<>();
		newNode.prev = lastNode.prev;
		newNode.next = lastNode;
		newNode.item = item;
		lastNode.prev.next = newNode;
		lastNode.prev = newNode;
		count++;
	}

	public Item removeFirst() {
		count--;
		Node<Item> node = firstNode.next;
		if (node == null || node.item == null) {
			throw new NoSuchElementException();
		}
		firstNode.next = firstNode.next.next;
		firstNode.next.prev = firstNode;
		return node.item;

	}

	public Item removeLast() {
		count--;
		Node<Item> node = lastNode.prev;
		if (node == null || node.item == null) {
			throw new NoSuchElementException();
		}
		lastNode.prev = lastNode.prev.prev;
		lastNode.prev.next = lastNode;
		return node.item;
		// remove and return the item from the end
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
		// return an iterator over items in order from front to end
	}

	public static void main(String[] args) {
		Deque<Integer> intDeque = new Deque<>();
		intDeque.addFirst(0);
		intDeque.addLast(10);
		intDeque.addLast(100);
		intDeque.addFirst(11);
		intDeque.addLast(20);
		System.out.println("size--" + intDeque.size());
		Iterator<Integer> itemIterator = intDeque.iterator();
		while (itemIterator.hasNext()) {
			System.out.println(itemIterator.next());
		}
		System.out.println("Remove -->" + intDeque.removeFirst());
		System.out.println("Remove -->" + intDeque.removeFirst());
		System.out.println("Remove -->" + intDeque.removeFirst());
		System.out.println("Remove -->" + intDeque.removeLast());
		System.out.println("is Empty --"+intDeque.isEmpty());

		System.out.println("Remove -->" + intDeque.removeLast());
		//itemIterator.next();
		System.out.println("is Empty --"+intDeque.isEmpty());
		System.out.println("Remove exception-->" + intDeque.removeLast());


	}

	private class Node<Item> {
		Item item;
		Node<Item> next;
		Node<Item> prev;

		public Node() {
			item = null;
			next = null;
			prev = null;
		}

	}

	private class ListIterator implements Iterator<Item> {

		private Deque<Item>.Node<Item> current = firstNode.next;

		@Override
		public boolean hasNext() {
			return current != lastNode;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (current == lastNode) {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;

			return item;
		}

	}

}