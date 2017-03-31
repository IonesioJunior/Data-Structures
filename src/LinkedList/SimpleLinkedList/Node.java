package LinkedList.SimpleLinkedList;

public class Node<T> {
	private Node<T> next;
	private T data;
	
	public Node(){};
	public Node(T data,Node<T> next){
		this.data = data;
		this.next = next;
	}
	
	public boolean isNIL(){
		return this.data == null;
	}
	
	public Node<T> getNext(){
		return this.next;
	}
	public void setNext(Node<T> next){
		this.next = next;
	}
	
	public T getData(){
		return this.data;
	}
	public void setData(T data){
		this.data = data;
	}
}
