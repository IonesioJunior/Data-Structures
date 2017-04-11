package AVL;

import java.util.ArrayList;
import java.util.List;

public class AVL<T extends Comparable<T>> implements AVLInterface<T> {
	
	private Node<T> root;
	
	public AVL(){
		this.root = new Node<T>();
	}
	
	
	@Override
	public boolean isEmpty() {
		return this.root.getData() == null;
	}

	@Override
	public void insert(T element) {
		if(element == null)
			return;
		else{
			recursiveInsert(element,this.root,this.root.getParent());
		}
	}

    private void recursiveInsert(T element,Node<T> node, Node<T> parent) {
        if (node.isNIL()) {
            node.setData(element);
            node.setLeft(new Node<T>());
            node.setRight(new Node<T>());
            node.setParent(parent);
        } else {
            T nodeData = node.getData();

            if (nodeData.equals(element)) {
                return;
            } else if (nodeData.compareTo(element) > 0) {
                recursiveInsert( element,(Node<T>) node.getLeft(), node);
            } else {
                recursiveInsert(element,(Node<T>) node.getRight(),  node);
            }
        }
        rebalance(node);
    }

	@Override
	public void remove(T element) {
		Node<T> foundNode = search(element);
		if(foundNode == null){
			return;
		}else{
			recursiveRemove(foundNode);
		}
	}
    
    private void recursiveRemove(Node<T> node) {
        if (node.isLeaf()) { //Caso seja folha, apenas muda o valor da data para null e faz o rebalanceamento de baixo para cima
            node.setData(null);
            rebalanceUp((Node<T>) node);

        } else { //Caso nao seja folha

            if (!node.getRight().isNIL()) { //Se existir um nó a direita

                Node<T> sucessor = recursiveMinimum(node.getRight()); //Busca-se o sucessor
                node.setData(sucessor.getData()); //Troca-se o valor com o sucessor
                recursiveRemove(sucessor);//Aplica o mesmo algoritmo ao sucessor, até chegar a um nó folha ou que só tenha filho a esquerda

            } else {//Caso tenha dois filhos, ou só tenha filho a esquerda

                Node<T> predecessor = recursiveMinimum(node.getLeft()); //busca um predecessor
                node.setData(predecessor.getData());//Troca -se o valor com o predecessor
                recursiveRemove(predecessor);//Aplica o mesmo algoritmo ao predecessor
            }

            rebalanceUp((Node<T>) node); //Rebalanceamento de baixo para cima

        }
    }


	@Override
	public int height() {
		return recursiveHeight(this.root) - 1;
	}
	public int recursiveHeight(Node<T> node){
		if(!node.isNIL()){
			int leftHeight = recursiveHeight(node.getLeft());
			int rightHeight = recursiveHeight(node.getRight());
			if(leftHeight > rightHeight){
				return leftHeight + 1;
			}else{
				return rightHeight + 1; 
			}
		}else{
			return 0;
		}
	}
	@Override
	public int size() {
		return recursiveSize(this.root);
	}
	private int recursiveSize(Node<T> node){
		if(!node.isNIL()){
			return 1 + recursiveSize(node.getLeft()) + recursiveSize(node.getRight());
		}else{
			return 0;
		}
	}

	@Override
	public Node<T> search(T element) {
		if(element == null || this.root.isNIL())
			return null;
		else{
			return recursiveSearch(element,this.root);
		}
	}
	private Node<T> recursiveSearch(T element,Node<T> node){
		if(!node.isNIL()){
			if(node.getData().equals(element)){
				return node;
			}else if(node.getData().compareTo(element) > 0){
				return recursiveSearch(element,node.getLeft());
			}else{
				return recursiveSearch(element,node.getRight());
			}
		}
		return null;
	}
	
	@Override
	public Node<T> maximum() {
		if(size() == 0){
			return null;
		}else{
			return recursiveMaximum(this.root);
		}
	}
	
	protected Node<T> recursiveMaximum(Node<T> node){
		if(!node.getRight().isNIL()){
			return recursiveMaximum(node.getRight());
		}else{
			return node;
		}
	}

	@Override
	public Node<T> minimum() {
		if(size() == 0){
			return null;
		}else{
			return recursiveMinimum(this.root);
		}
	}
	
	protected Node<T> recursiveMinimum(Node<T> node){
		if(!node.getLeft().isNIL()){
			return recursiveMinimum(node.getLeft());
		}else{
			return node;
		}
	}
	
	@Override
	public Node<T> predecessor(T element) {
		Node<T> foundNode = search(element);
		if(foundNode == null){
			return null;
		}else if(!foundNode.getLeft().isNIL()){
			return recursiveMaximum(foundNode.getLeft());
		}else{
			Node<T> parent = foundNode.getParent();
			while(parent != null && !foundNode.equals(parent.getRight())){
				parent = parent.getParent();
				foundNode = foundNode.getParent();
			}
			return parent;
		}
	}

	@Override
	public Node<T> sucessor(T element) {
		Node<T> foundNode = search(element);
		if(foundNode == null){
			return foundNode;
		}else if(!foundNode.getRight().isNIL()){
			return recursiveMinimum(foundNode.getRight());
		}else{
			Node<T> parent = foundNode.getParent();
			while(parent != null && !foundNode.equals(parent.getLeft())){
				parent = parent.getParent();
				foundNode = foundNode.getParent();
			}
			return parent;
		}
	}
	@Override
	public T[] toArrayPreOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		List<T> arrayList = new ArrayList<T>();
		recursivePreOrder(this.root,arrayList);
		for(int i = 0 ; i < arrayList.size();i++){
			array[i] = arrayList.get(i);
		}
		return array;
	}
	private void recursivePreOrder(Node<T> node,List list){
		if(!node.isNIL()){
			list.add(node.getData());
			recursivePreOrder(node.getLeft(),list);
			recursivePreOrder(node.getRight(),list);
		}
	}

	@Override
	public T[] toArrayOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		List<T> arrayList = new ArrayList<T>();
		recursiveOrder(this.root,arrayList);
		for(int i = 0 ; i < arrayList.size();i++){
			array[i] = arrayList.get(i);
		}
		return array;
		
	}
	private void recursiveOrder(Node<T> node,List list){
		if(!node.isNIL()){
			recursiveOrder(node.getLeft(),list);
			list.add(node.getData());
			recursiveOrder(node.getRight(),list);
		}
	}
	@Override
	public T[] toArrayPostOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		List<T> arrayList = new ArrayList<T>();
		recursiveOrder(this.root,arrayList);
		for(int i = 0 ; i < arrayList.size();i++){
			array[i] = arrayList.get(i);
		}
		return array;
		
	}
	private void recursivePostOrder(Node<T> node,List list){
		if(!node.isNIL()){
			recursivePostOrder(node.getLeft(),list);
			recursivePostOrder(node.getRight(),list);
			list.add(node.getData());
		}
	}
	protected void setRoot(Node<T> newRoot){
		this.root = newRoot;
	}
	
////////////////////////////////   AVL AUXILIAR METHODS 	//////////////////////////////////////////////////////////
	
	private int calculateBalance(Node<T> node){
		if(node.isNIL())
			return 0;
		else{
			return recursiveHeight(node.getLeft()) - recursiveHeight(node.getRight());
		}
	}
	
	private void rebalance(Node<T> node){
		int balance = this.calculateBalance(node);
		int leftBalance = this.calculateBalance(node.getLeft());
		int rightBalance = this.calculateBalance(node.getRight());
		if(balance > 1){
			if(leftBalance < 0)
				this.leftRotation(node.getLeft());
			this.rightRotation(node);
		}else if(balance < -1){
			if(rightBalance > 0)
				this.rightRotation(node.getRight());
			this.leftRotation(node);
		}
	}
	
	private void rebalanceUp(Node<T> node){
		Node<T> parent = node.getParent();
		while(parent != null){
			this.rebalance(node);
			node = node.getParent();
		}
	}
	
    // AUXILIARY
    protected void leftRotation(Node<T> node) {
        Node<T> newRoot = leftRotationNodes(node);
        if (node == getRoot()) {
            this.root = newRoot;
        }
    }


    // AUXILIARY
    protected void rightRotation(Node<T> node) {
        Node<T> newRoot = rightRotationNodes(node);
        if (node == getRoot()) {
        	this.root = newRoot;
        }
    }
 
    public static <T extends Comparable<T>> Node<T> rightRotationNodes(Node<T> node) {

        if (node != null && !node.isNIL()) {

            Node<T> parent = node.getParent();
            Node<T> left = node.getLeft();

            left.setParent(parent);
            if (parent != null) {

                if (parent.getLeft().equals(node))
                    parent.setLeft(left);

                else
                    parent.setRight(left);
            }

            node.setLeft(left.getRight());
            if (node.getLeft() != null)
                node.getLeft().setParent(node);

            left.setRight(node);
            node.setParent(left);

            return (Node<T>) left;
        }
        return null;
    }

    public static <T extends Comparable<T>> Node<T> leftRotationNodes(Node<T> node) {

        if (node != null && !node.isNIL()) {

            Node<T> parent = node.getParent();
            Node<T> right = node.getRight();

            right.setParent(parent);
            if (parent != null) {

                if(parent.getRight().equals(node))
                    parent.setRight(right);

                else
                    parent.setLeft(right);
            }

            node.setRight(right.getLeft());
            if (node.getRight() != null)
                node.getRight().setParent(node);

            right.setLeft(node);
            node.setParent(right);

            return (Node<T>) right;
        }
        return null;
    }

	@Override
	public Node<T> getRoot() {
		return this.root;
	}
}
