package AVL;

public interface BSTInterface<T> {
	public boolean isEmpty();
	public void insert(T element);
	public void remove(T element);
	public int height();
	public int size();
	public Node<T> search(T element);
	public Node<T> predecessor(T element);
	public Node<T> sucessor(T element);
	public Node<T> maximum();
	public Node<T> minimum();
	public Node<T> getRoot();
	public T[] toArrayPreOrder();
	public T[] toArrayOrder();
	public T[] toArrayPostOrder();
}
