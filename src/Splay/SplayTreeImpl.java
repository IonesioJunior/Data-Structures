package Splay;
/*
 * @author Ionésio Junior
 * */
import BST.BST;
import BST.Node;
/*
 * Splay Tree Implementation
 * */
public class SplayTreeImpl<T extends Comparable<T>> extends BST<T> implements SplayInterface<T> {
   
  	private void splay(Node<T> node) {
 		if (node == null || node.isNIL()) return;
  		
  		while (!node.getData().equals(getRoot().getData())){
  			if (node.getParent().getData().equals(getRoot().getData())){
  				if(isRightChild(node)){
  					leftRotation((Node<T>) node.getParent());
  				}else{
  					rightRotation((Node<T>) node.getParent());
  				}
  			}else if (isRightChild(node) && isRightChild((Node<T>) node.getParent())){
  				leftRotation((Node<T>) node.getParent().getParent());
  				leftRotation((Node<T>) node.getParent());
  			}else if (!isRightChild(node) && !isRightChild((Node<T>) node.getParent())){
  				rightRotation((Node<T>) node.getParent().getParent());
  				rightRotation((Node<T>) node.getParent());
  			}else if(!isRightChild(node) && isRightChild((Node<T>) node.getParent())){
  				rightRotation((Node<T>) node.getParent());
  				leftRotation((Node<T>) node.getParent());
  			}else{
  				leftRotation((Node<T>) node.getParent());
  				rightRotation((Node<T>) node.getParent());
  			}
  		}
  	}
  	
  	public boolean isRightChild(Node<T> node){
  		return node.getParent().getRight().equals(node);
  	}
  	
  	public void insert(T element){
  		if (element == null) return;
  		
  		super.insert(element);
  		splay(super.search(element));	
  	}
  	
  	public Node<T> search(T element){
  		if (element == null) return null;
  		
  		Node<T> buscado = super.search(element);
  		if (buscado.isNIL()){
  			splay((Node<T>) buscado.getParent());
  		}else{
  			splay(buscado);
  		}
  		return buscado;
  	}
  	
  	public void remove(T element){
  		if (element == null) return;
  		if	(getRoot().isNIL()) return;
  		if (super.search(element).isNIL()){
  			if(!this.getRoot().getData().equals(element))
  				splay((Node<T>) super.search(element).getParent());
  		}else{
  			T parent = null;
  			if(!this.getRoot().getData().equals(element))
  				parent = super.search(element).getParent().getData();
  			super.remove(element);
  			
  			if(parent != null)
  				splay(super.search(parent));
  			}
  	}
  	
  	public void rightRotation(Node<T> node){
    		Node<T> newNode = this.right(node);
    		if (newNode.getParent() == null){
    			this.setRoot(newNode);
    		}
    	}
   
    public void leftRotation(Node<T> node){
    		Node<T> newNode = this.left(node);
    		if (newNode.getParent() == null){
    			this.setRoot(newNode);
    		}
    	}
    	
   public  Node<T> left(Node<T> node) {
  		Node<T> right = (Node<T>) node.getRight();
   
          node.setRight(right.getLeft());
          right.getLeft().setParent(node);
          right.setParent(node.getParent());
          if (node.getParent() != null)
          	if (node.getParent().getData().compareTo(right.getData()) > 0)
          		node.getParent().setLeft(right);
          	else{
         		node.getParent().setRight(right);
          	}
          node.setParent(right);
          right.setLeft(node);
   
          return right;
  	}
   
   public  Node<T> right(Node<T> node) {
  		Node<T> left = (Node<T>) node.getLeft();
          node.setLeft(left.getRight());
          left.getRight().setParent(node);
          left.setParent(node.getParent());
          if (node.getParent() != null)
          	if (node.getParent().getData().compareTo(left.getData()) < 0)
          		node.getParent().setRight(left);
          	else {
         		node.getParent().setLeft(left);
          	}
          node.setParent(left);
          left.setRight(node);
          return left;
	   }
  }
