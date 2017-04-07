package AVL;
/*
*@author Ionésio Junior
*/
public class Node<T> {
	private T data;
	private Node<T> left;
	private Node<T> right;
	private Node<T> parent;
	
	public Node(){
		
	}
	public Node(T element,Node<T> left,Node<T> right,Node<T> parent){
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	public boolean isNIL(){
		return this.data == null;
	}
	public boolean isLeaf(){
		return (this.left.data == null && this.right.data == null);
	}
	public T getData(){
		return this.data;
	}
	public Node<T> getLeft(){
		return this.left;
	}
	public Node<T> getRight(){
		return this.right;
	}
	public Node<T> getParent(){
		return this.parent;
	}
	public void setData(T element){
		this.data = element;
	}
	public void setLeft(Node<T>  newLeft){
		this.left = newLeft;
	}
	public void setRight(Node<T> newRight){
		this.right = newRight;
	}
	public void setParent(Node<T> newParent){
		this.parent = newParent;
	}
	
	public boolean equals(Object obj) {
		boolean resp = false;
		if (obj instanceof Node) {
			if (!this.isNIL() && !((Node<T>) obj).isNIL()) {
				resp = this.data.equals(((Node<T>) obj).data);
			} else {
				resp = this.isNIL() && ((Node<T>) obj).isNIL();
			}

		}
		return resp;
	}
}
