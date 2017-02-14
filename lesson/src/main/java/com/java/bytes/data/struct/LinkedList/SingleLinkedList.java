package com.java.bytes.data.struct.LinkedList;

public class SingleLinkedList {

	public static void main(String[] args) {
		ListNode<Integer> ln0 = new ListNode<Integer>(0);
		ListNode<Integer> ln1 = new ListNode<Integer>(1,ln0);
		ListNode<Integer> ln2 = new ListNode<Integer>(2,ln1);
		ListNode<Integer> ln3 = new ListNode<Integer>(3,ln2);
		ListNode<Integer> ln4 = new ListNode<Integer>(4,ln3);
		ListNode<Integer> ln5 = new ListNode<Integer>(5,ln4);
		ListNode<Integer> ln6 = new ListNode<Integer>(6,ln5);
		ListNode<Integer> ln7 = new ListNode<Integer>(7,ln1);


		
		iterate(ln0);

	}
	static void iterate(ListNode<Integer> headNode){
		ListNode<Integer> listNode = headNode;
		
		while(listNode.getNextNode()!=null){
			listNode = listNode.getNextNode();
			System.out.println(listNode.getData());
			
		}
	}

}

class ListNode<T>{
	private ListNode<T> nextNode;
	private T data;
	public ListNode(T data) {
		this.data = data;
	}
	
	public ListNode(T data,ListNode<T> prevNode) {
		this.data = data;
		this.nextNode = prevNode.nextNode;
		prevNode.nextNode = this;
	}
	
	public ListNode<T> getNextNode() {
		return nextNode;
	}
	public void setNextNode(ListNode<T> nextNode) {
		this.nextNode = nextNode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
