package com.java.bytes.data.struct.LinkedList;

public class DoubleLinkedList {

	public static void main(String[] args) {
		DoubleListNode<Integer> lnheader = new DoubleListNode<Integer>(0);
		DoubleListNode<Integer> lnTail = new DoubleListNode<Integer>(0);
		initialise(lnheader,lnTail);
		DoubleListNode<Integer> ln1 = new DoubleListNode<Integer>(1,lnheader);
		DoubleListNode<Integer> ln2 = new DoubleListNode<Integer>(2,ln1);
		DoubleListNode<Integer> ln3 = new DoubleListNode<Integer>(3,ln2);
		DoubleListNode<Integer> ln4 = new DoubleListNode<Integer>(4,ln3);
		DoubleListNode<Integer> ln5 = new DoubleListNode<Integer>(5,ln4);
		DoubleListNode<Integer> ln6 = new DoubleListNode<Integer>(6,ln5);
		DoubleListNode<Integer> ln7 = new DoubleListNode<Integer>(7,ln1);
		iterate(lnheader);
	}
	private static void initialise(DoubleListNode<Integer> lnheader, DoubleListNode<Integer> lnTail) {
		lnheader.setNextNode(lnTail);
		lnTail.setPrevNode(lnheader);
		
	}
	static void iterate(DoubleListNode<Integer> headNode){
		DoubleListNode<Integer> listNode = headNode;
		
		while(listNode.getNextNode()!=null){
			listNode = listNode.getNextNode();
			System.out.println(listNode.getData());
			
		}
	}

}

class DoubleListNode<T>{
	private DoubleListNode<T> nextNode;
	private DoubleListNode<T> prevNode;
	public DoubleListNode<T> getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(DoubleListNode<T> prevNode) {
		this.prevNode = prevNode;
	}
	private T data;
	public DoubleListNode(T data) {
		this.data = data;

	}
	
	public DoubleListNode(T data,DoubleListNode<T> prevNode) {
		this.data = data;
		this.nextNode = prevNode.nextNode;
		this.prevNode = prevNode.nextNode.prevNode;

		prevNode.nextNode = this;
		prevNode.nextNode.prevNode = this;
	}
	
	public DoubleListNode<T> getNextNode() {
		return nextNode;
	}
	public void setNextNode(DoubleListNode<T> nextNode) {
		this.nextNode = nextNode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
