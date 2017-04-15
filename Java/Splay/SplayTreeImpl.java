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
    @Override
    public Node<T> search(T element) {

        if (element != null) {

            Node<T> node = this.getRoot();
            while (!node.isNIL()) {

                if (node.getData().equals(element)) {
			if(!node.getData().equals(this.root.getData())){                    
				splay((Node<T>) node);
			}
                    return (Node<T>) node;
                } else if (node.getData().compareTo(element) > 0)
                    node = node.getLeft();

                else
                    node = node.getRight();
            }
            if (node.getParent() != null)
                splay((Node<T>) node.getParent());
        }
        return new Node<>();
    }

    public void insert(T element) {

        if (element != null) {

            insertRecursive(element, this.getRoot());
        }
    }

    public void insertRecursive(T element, Node<T> node) {

        if (node.isNIL()) {

            node.setData(element);

            Node<T> newLeft = new Node<>();
            newLeft.setParent(node);
            node.setLeft(newLeft);

            Node<T> newRight = new Node<>();
            newRight.setParent(node);
            node.setRight(newRight);

            if (node.getParent() != null)
                splay((Node<T>) node);

        } else if (node.getData().compareTo(element) > 0) {
            insertRecursive(element, node.getLeft());

        } else if (node.getData().compareTo(element) < 0) {
            insertRecursive(element, node.getRight());
        }
    }

    @Override
    public void remove(T element) {

        if (isEmpty() || element == null)
            return;

        else {

            Node<T> node = super.search(element);

            if (node.isNIL()) {
                splay((Node<T>) node.getParent());
                return;

            } else {
                Node<T> parent = (Node<T>) node.getParent();
                recursiveRemove(node);
                if (parent != null)
                    splay(parent);
            }
        }
    }

    private void splay(Node<T> node) {

        if (node == null || node.isNIL() || node.equals(getRoot()))
            return;

        Node<T> parent = (Node<T>) node.getParent();
        Node<T> granParent = (Node<T>) parent.getParent();

        while (parent != null) {

            if (parent.equals(getRoot())) {

                //ZIG Right
                if (parent.getLeft().equals(node))
                    Util.rightRotation(parent);

                //ZIG Left
                else
                    Util.leftRotation(parent);

                setRoot(node);

            } else {

                if (granParent.getRight().equals(parent)) {

                    //ZIG_ZIG Left
                    if (parent.getRight().equals(node)) {
                        Util.leftRotation(granParent);
                        Util.leftRotation(parent);
                    }
                        //ZIG Right ZAG Left
                    else {
                        Util.rightRotation(parent);
                        Util.leftRotation(granParent);
                    }
                } else {

                    //ZIG_ZIG Right
                    if (parent.getLeft().equals(node)) {
                        Util.rightRotation(granParent);
                        Util.rightRotation(parent);
                    }
                        //ZIG Right ZAG Left
                    else {
                        Util.leftRotation(parent);
                        Util.rightRotation(granParent);
                    }
                }
            }

            parent = (Node<T>) node.getParent();

            if (parent != null) {

                granParent = (Node<T>) parent.getParent();

                if (parent.getParent() == null)
                    setRoot(parent);
            }

            else
                setRoot(node);
        }
    }
}
