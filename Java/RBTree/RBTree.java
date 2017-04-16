package RBTree;
import BST.BSTInterface;
public interface RBTree <T extends Comparable<T>> extends BSTInterface<T>{
	
		public RBNode<T>[] rbPreOrder();
}
