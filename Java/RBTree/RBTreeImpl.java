package RBTree;
import java.util.ArrayList;
import java.util.List;

import BST.BST;
import Splay.Util;

public class RBTreeImpl<T extends Comparable<T>> extends BST<T> implements RBTree<T> {
  
  	public RBTreeImpl() {
  		this.setRoot(new RBNode<T>());
  	}
  
  	protected int blackHeight() {
  		if (isEmpty()) return 0;
  		
  		return recursiveBlackHeight((RBNode<T>) this.getRoot());
  	}
  	
  	public int recursiveBlackHeight(RBNode<T> node){
  		if (node == null || node.isNIL())
  			return 1;
  		
  		if (node.equals(this.getRoot()) || node.getColour().equals(Colour.RED))
  			return 0 + Math.max(recursiveBlackHeight((RBNode<T>) node.getLeft()), recursiveBlackHeight((RBNode<T>) node.getRight()));
  		
  		return 1 + Math.max(recursiveBlackHeight((RBNode<T>) node.getLeft()), recursiveBlackHeight((RBNode<T>) node.getRight()));
  	}  
  
  	protected boolean verifyProperties() {
  		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
  				&& verifyBlackHeight();
  
  		return resp;
  	}
  
  	/**
  	 * The colour of each node of a RB tree is black or red. This is guaranteed
  	 * by the type Colour.
  	 */
  	private boolean verifyNodesColour() {
  		return true; // already implemented
 	}
  
  	/**
  	 * The colour of the root must be black.
  	 */
  	private boolean verifyRootColour() {
  		return ((RBNode<T>) this.getRoot()).getColour() == Colour.BLACK; // already
  																// implemented
  	}
  
  	/**
  	 * This is guaranteed by the constructor.
  	 */
  	private boolean verifyNILNodeColour() {
  		return true; // already implemented
  	}
  
  	/**
  	 * Verifies the property for all RED nodes: the children of a red node must
  	 * be BLACK.
  	 */
  	private boolean verifyChildrenOfRedNodes() {
  		return verifyChildrenOfRedNodes((RBNode<T>) this.getRoot());
  	}
  
  	private boolean verifyChildrenOfRedNodes(RBNode<T> no) {
  		if (!no.isNIL()) {
  			RBNode<T> left = (RBNode<T>) no.getLeft();
  			RBNode<T> right = (RBNode<T>) no.getRight();
  
  			if (no.getColour() == Colour.RED) {
  				if (left.getColour() == Colour.RED || right.getColour() == Colour.RED) {
  					return false;
  				}
  			}
  			return verifyChildrenOfRedNodes(left) && verifyChildrenOfRedNodes(right);
 		}
  		return true;
  	}
  
  	/**
  	 * Verifies the black-height property from the root. The method blackHeight
  	 * returns an exception if the black heights are different.
  	 */
  	private boolean verifyBlackHeight() {
  		if(this.getRoot() == null){
  			return true;
  		}else{
  			return verifyBlackHeight((RBNode<T>) this.getRoot(),true);
  		}
  	}
  
  	private boolean verifyBlackHeight(RBNode<T> node,boolean flag){
  		if(!node.isNIL()){
  			if(node.getColour().equals(Colour.BLACK)){
  				int left = this.recursiveBlackHeight((RBNode<T>) node.getLeft());
  				int right = this.recursiveBlackHeight((RBNode<T>) node.getRight());
  				if(left == right){
  					return this.verifyBlackHeight((RBNode<T>) node.getLeft(), true) && this.verifyBlackHeight((RBNode<T>) node.getRight(),true);	
  				}else{
  					return false;
  				}
  			}else{
  				return this.verifyBlackHeight((RBNode<T>) node.getLeft(), true) && this.verifyBlackHeight((RBNode<T>) node.getRight(),true);
  			}
  		}else{
  			return true;
  		}
  	}
  	
 	@Override
 	public void insert(T value) {
 		if (value != null) {
 			RBNode<T> nulo = new RBNode<T>();
 			insert(nulo, (RBNode<T>) this.getRoot(), value);
 		}
 	}
 
 	private void insert(RBNode<T> parent, RBNode<T> no, T value) {
 		if (no.isNIL()) {
 			no.setData(value);
 			no.setLeft(new RBNode<T>());
 			no.setRight(new RBNode<T>());
 			no.setParent(parent);
 			no.setColour(Colour.RED);
 			fixUpCase1(no);
 		} else {
 			if (no.getData().compareTo(value) < 0) {
 				insert(no, (RBNode<T>) no.getRight(), value);
 			} else {
 				insert(no, (RBNode<T>) no.getLeft(), value);
 			}
 		}
 	}
 
 	@SuppressWarnings("unchecked")
 	@Override
 	public RBNode<T>[] rbPreOrder() {
 		List<RBNode<T>> auxiliar = new ArrayList<RBNode<T>>();
 		rbPreOrder(auxiliar, (RBNode<T>) this.getRoot());
 		RBNode<T>[] arrayRBTree = new RBNode[auxiliar.size()];
 		return auxiliar.toArray(arrayRBTree);
 	}
 
 	private void rbPreOrder(List<RBNode<T>> auxiliar, RBNode<T> no) {
 		if (!no.isNIL()) {
 			auxiliar.add(no);
 			rbPreOrder(auxiliar, (RBNode<T>) no.getLeft());
 			rbPreOrder(auxiliar, (RBNode<T>) no.getRight());
 		}
 	}
 
 	protected void fixUpCase1(RBNode<T> no) {
 		if (no == (RBNode<T>) this.getRoot()) {
 			no.setColour(Colour.BLACK);
 		} else {
 			fixUpCase2(no);
 		}
 	}
 
 	protected void fixUpCase2(RBNode<T> no) {
 		RBNode<T> father = (RBNode<T>) no.getParent();
 
 		if (((RBNode<T>) father).getColour() != Colour.BLACK) {
 			fixUpCase3(no);
 		}
 	}
 
 	protected void fixUpCase3(RBNode<T> no) {
 		RBNode<T> father = (RBNode<T>) no.getParent();
 		RBNode<T> parent = (RBNode<T>) no.getParent().getParent();
 		if ((parent.getRight() != father) && (((RBNode<T>) parent.getRight()).getColour() == Colour.RED)) {
 			father.setColour(Colour.BLACK);
 			((RBNode<T>) parent.getRight()).setColour(Colour.BLACK);
 			parent.setColour(Colour.RED);
 			this.fixUpCase1(parent);
 
 		} else if (parent.getLeft() != father && (((RBNode<T>) parent.getLeft()).getColour() == Colour.RED)) {
 			father.setColour(Colour.BLACK);
 			((RBNode<T>) parent.getLeft()).setColour(Colour.BLACK);
 			parent.setColour(Colour.RED);
 			this.fixUpCase1(parent);
 		} else {
 			this.fixUpCase4(no);
 		}
 	}
 
 	protected void fixUpCase4(RBNode<T> no) {
 		if ((no.getParent().getRight() == no) && (no.getParent().getParent().getLeft() == no.getParent())) {
 			this.leftRotation((RBNode<T>) no.getParent());
 			this.fixUpCase5((RBNode<T>) no.getLeft());
 		} else if ((no.getParent().getLeft() == no) && (no.getParent().getParent().getRight() == no.getParent())) {
 			this.rightRotation((RBNode<T>) no.getParent());
			this.fixUpCase5((RBNode<T>) no.getRight());
 		} else {
 			this.fixUpCase5(no);
 		}
 	}
 
 	protected void fixUpCase5(RBNode<T> no) {
 		((RBNode<T>) no.getParent()).setColour(Colour.BLACK);
 		((RBNode<T>) no.getParent().getParent()).setColour(Colour.RED);
 		
 		if ((no.getParent().getLeft() == no) && (no.getParent().getParent().getLeft() == no.getParent())) {
 			this.rightRotation((RBNode<T>) no.getParent().getParent());
 		} else if ((no.getParent().getRight() == no) && (no.getParent().getParent().getRight() == no.getParent())) {
 			this.leftRotation((RBNode<T>) no.getParent().getParent());
 		}
 	}
 
 	private void rightRotation(RBNode<T> no) {
 		RBNode<T> rightAuxiliar = (RBNode<T>) no.getLeft();
 		no.setLeft(rightAuxiliar.getRight());
 		rightAuxiliar.getRight().setParent(no);
 		rightAuxiliar.setRight(no);
 		rightAuxiliar.setParent(no.getParent());
		no.setParent(rightAuxiliar);
 
 		if (no != this.getRoot()) {
 			if (rightAuxiliar.getParent().getLeft() == no)
 				rightAuxiliar.getParent().setLeft(rightAuxiliar);
 			else
 				rightAuxiliar.getParent().setRight(rightAuxiliar);
 		} else {
 			this.setRoot( (RBNode<T>) rightAuxiliar );
 		}
 	}
 
 	protected void leftRotation(RBNode<T> node) {
 		RBNode<T> leftAuxiliar = (RBNode<T>) node.getRight();
 		node.setRight(leftAuxiliar.getLeft());
 		leftAuxiliar.getLeft().setParent(node);
 		leftAuxiliar.setLeft(node);
 		leftAuxiliar.setParent(node.getParent());
 		node.setParent(leftAuxiliar);
 
 		if (node != this.getRoot()) {
 			if (leftAuxiliar.getParent().getLeft() == node) {
 				leftAuxiliar.getParent().setLeft(leftAuxiliar);
 			} else {
 				leftAuxiliar.getParent().setRight(leftAuxiliar);
 			}
 		} else {
 			this.setRoot( (RBNode<T>) leftAuxiliar);
 		}
 	}
 }
