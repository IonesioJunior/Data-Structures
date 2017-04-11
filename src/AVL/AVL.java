package AVL;

import BST.BST;
import BST.Node;

public class AVL<T extends Comparable<T>> extends BST<T> {
	
	public AVL(){
		super();
		
	}
    public int calculateBalance(Node<T> node) {
        if (node.isNIL()) return 0;
        return recursiveHeight((Node<T>) node.getLeft()) - recursiveHeight((Node<T>) node.getRight());
    }

    // AUXILIARY
    protected void rebalance(Node<T> node) {

        int balance = calculateBalance(node);
        int balanceLeftChild = calculateBalance((Node<T>) node.getLeft());
        int balanceRightChild = calculateBalance((Node<T>) node.getRight());


        if (balance > 1) {

            if (balanceLeftChild < 0) {
                leftRotation((Node<T>) node.getLeft());
            }

            rightRotation(node);


        } else if (balance < -1) {

            if (balanceRightChild > 0) {
                rightRotation((Node<T>) node.getRight());
            }

            leftRotation(node);
        }

    }


    @Override
    protected void recursiveInsert(T element,Node<T> node, Node<T> parent) {

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
    protected void recursiveRemove(Node<T> node) {

        if (node.isLeaf()) {
            node.setData(null);
            rebalanceUp((Node<T>) node);

        } else {

            if (!node.getRight().isNIL()) {

                Node<T> minNode = recursiveMinimum(node.getRight());
                node.setData(minNode.getData());
                recursiveRemove(minNode);

            } else {

                Node<T> maxNode = recursiveMinimum(node.getLeft());
                node.setData(maxNode.getData());
                recursiveRemove(maxNode);
            }

            rebalanceUp((Node<T>) node);

        }
    }


    // AUXILIARY
    protected void rebalanceUp(Node<T> node) {
        Node<T> parent = (Node<T>) node.getParent();
        
        while (parent != null) {
            rebalance(parent);
            parent =  parent.getParent();
        }

    }

    // AUXILIARY
    protected void leftRotation(Node<T> node) {
        Node<T> newRoot = leftRotationNodes(node);
        if (node == getRoot()) {
            setRoot(newRoot);
        }
    }


    // AUXILIARY
    protected void rightRotation(Node<T> node) {
        Node<T> newRoot = rightRotationNodes(node);
        if (node == getRoot()) {
            setRoot(newRoot);
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

}
