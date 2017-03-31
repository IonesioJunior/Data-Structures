package LinkedList.DoubleLinkedList;

public class DoubleNode<T> {
	private DoubleNode<T> next;
	private DoubleNode<T> previous;
	private T data;
	
	public DoubleNode(){
		
	}
	public DoubleNode(T data,DoubleNode<T> previous,DoubleNode<T> next){
		this.data = data;
		this.next = next;
		this.previous = previous;
	}
	
	public boolean isNIL(){
		return this.data == null;
	}
	
	
	public T getData(){
		return this.data;
	}
	
	public DoubleNode<T> getNext(){
		return this.next;
	}
	public DoubleNode<T> getPrevious(){
		return this.previous;
	}
	
	public void setData(T element){
		this.data = data;
	}
	public void setNext(DoubleNode<T> next){
		this.next = next;
	}
	
	public void setPrevious(DoubleNode<T> previous){
		this.previous = previous;
	}
}
