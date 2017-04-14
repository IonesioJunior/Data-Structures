package SkipList;

/*
 * @author Ionésio Junior
 * */
public class SkipListNode<T> {
	private T data;
	private int key;
	private SkipListNode<T>[] forward;
	
	public SkipListNode(int key,int height,T data){
		this.data  = data;
		this.key = key;
		this.forward = new SkipListNode[height];
	}
	/**
	 * Return the height of node
	 * @return height
	 * */
	public int height(){
		return this.forward.length;
	}
	/**
	 * Return the value of data stored in node
	 * @return data
	 * */
	public T getValue(){
		return this.data;
	}
	/**
	 * Change value of data stored in node
	 * @param newData
	 * */
	public void setValue(T newData){
		this.data = newData;
	}
	/**
	 * Get key of this node
	 * @return nodeKey
	 * */
	public int getKey(){
		return this.key;
	}
	/**
	 * Change key value of this node
	 * @param newKey
	 * */
	public void setKey(int key){
		this.key = key;
	}
	/**
	 * Get all of  next elements linked with this node
	 * @return elements
	 * */
	public SkipListNode<T>[] getForward(){
		return this.forward;
	}
	/**
	 * Return an specific element linked in level position
	 * @param level
	 * @return Node
	 * */
	public SkipListNode<T> getForward(int level){
		return this.forward[level];
	}
	
	/**
	 * Change an specific element linked in level position
	 * @param level
	 * @param newNode
	 * */
	public void setForward(int level,SkipListNode<T> newNode){
		this.forward[level] = newNode;
	}
	
	/////////////////////////		AUXILIAR METHODS		////////////////////////
	
  	private int countForwardsNotNull() {
  		int resp = 0;
  		for (int i = 0; i < forward.length; i++) {
  			if (forward[i] != null) {
  				resp++;
  			}
  		}
  		return resp;
  	}

  	@SuppressWarnings("unchecked")
  	@Override
  	public boolean equals(Object obj) {
  		boolean resp = false;
  		if (obj instanceof SkipListNode) {
  			resp = this.key == ((SkipListNode<T>) obj).key
  					&& this.forward.length == ((SkipListNode<T>) obj).forward.length
  					&& this.countForwardsNotNull() == ((SkipListNode<T>) obj)
  							.countForwardsNotNull();
  		}
  		return resp;
  	}	

  	@Override
  	public String toString() {
  		String result = "";
  		if (this.key == Integer.MIN_VALUE) {
  			result = "<ROOT," + this.forward.length + "," + this.countForwardsNotNull()
  					+ ">";
  		} else if (this.key == Integer.MAX_VALUE) {
  			result = "<NIL," + this.forward.length + ">";
  		} else {
  			result = "<" + this.key + "," + this.forward.length + ">";
  		}
  		return result;
  	}  	
}
