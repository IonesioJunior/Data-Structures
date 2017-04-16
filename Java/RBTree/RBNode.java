	package RBTree;

import BST.Node;
   
   public class RBNode<T extends Comparable<T>> extends Node<T> {
  	
  
  	private Colour colour;
  
  	public RBNode() {
  		this.colour = Colour.BLACK;
  	}
  
  	public Colour getColour() {
  		return colour;
  	}
  
  	public void setColour(Colour color) {
  		if (isNIL() && color == Colour.RED) {
  			throw new UnsupportedOperationException(
  					"NIL node colour cannot be RED");
  		}
  		this.colour = color;
  	}
  
  	@SuppressWarnings("unchecked")
  	@Override
  	public boolean equals(Object obj) {
  		return super.equals(obj)
  				&& this.colour == ((RBNode<T>) obj).getColour();
  	}
  
  	@Override
  	public String toString() {
  		String resp = "NIL";
  		if (!isNIL()) {
  			resp = "(" + this.getData().toString();
  			if (colour == Colour.RED) {
  				resp = resp + ",R)";
  			} else {
  				resp = resp + ",B)";
  			}
  		}
  		return resp;
  	}
  
  }
