package BST;
/**
 * @author Ionesio Junior
 * */

/**
 * Node Class for Binary Search Tree Implementation
 * */
public class Node<T> {
	//Attributes
	private T data;
	private Node<T> left;
	private Node<T> right;
	private Node<T> parent;
	/**
	 * Node Constructor (Empty)
	 * */
	public Node(){
		
	}
	/**
	 * Node Constructor
	 * @param element
	 * @param left Node
	 * @param right Node
	 * @param parent Node
	 * */
	public Node(T element,Node<T> left,Node<T> right,Node<T> parent){
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	/**
	 * Return true if data is null or false if not
	 * @return boolean
	 * */
	public boolean isNIL(){
		return this.data == null;
	}
	/**
	 * Return true if this node is a bst leaf or false if not
	 * @return boolean
	 * */
	public boolean isLeaf(){
		return (this.left.data == null && this.right.data == null);
	}
	/**
	 * Return data
	 * @return data
	 * */
	public T getData(){
		return this.data;
	}
	/**
	 * Return left Node
	 * @return Node
	 * */
	public Node<T> getLeft(){
		return this.left;
	}
	/**
	 * Return right Node
	 * @return Node
	 * */
	public Node<T> getRight(){
		return this.right;
	}
	/**
	 * Return parent Node
	 * @return Node
	 * */
	public Node<T> getParent(){
		return this.parent;
	}
	/**
	 * Set data value
	 * @param value
	 * */
	public void setData(T value){
		this.data = value;
	}
	/**
	 * Set left node
	 * @param newLeft
	 * */
	public void setLeft(Node<T>  newLeft){
		this.left = newLeft;
	}
	/**
	 * Set right node
	 * @param newRight
	 * */
	public void setRight(Node<T> newRight){
		this.right = newRight;
	}
	/**
	 * Set parent node
	 * @param newParent
	 * */
	public void setParent(Node<T> newParent){
		this.parent = newParent;
	}
}
