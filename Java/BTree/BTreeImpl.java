package BTree;

import java.util.ArrayList;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		setOrder(order);
		setRoot(new BNode<>(order));
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	public void setRoot(BNode<T> root) {
		this.root = root;
	}


	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {

		if (node.isEmpty()) {
			return -1;

		} else {

			if (node.getChildren().size() > 0) {
				return height(node.getChildren().getFirst()) + 1;

			} else {
				return 0;
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public BNode<T>[] depthLeftOrder() {

		ArrayList<BNode<T>> list = new ArrayList<>();
		BNode<T>[] array;

		if (isEmpty()) {
			array = new BNode[list.size()];
			return array;

		} else {

			depthLeftOrder(getRoot(), list);
			array = new BNode[list.size()];
			for (int i = 0; i < list.size(); i++) {
				array[i] = list.get(i);
			}

			return array;
		}
	}

	private void depthLeftOrder(BNode<T> node, List<BNode<T>> list) {

		if (node.isEmpty())
			return;

		else {
			list.add(node);
			List<BNode<T>> children = node.getChildren();

			for (BNode<T> child : children) {
				depthLeftOrder(child, list);
			}
		}
	}

	@Override
	public int size() {
		if (isEmpty())
			return 0;

		else
			return size(getRoot());
	}

	private int size(BNode<T> node) {

		if (isEmpty())
			return 0;

		else {

			int childSize = node.size();
			List<BNode<T>> children = node.getChildren();

			for (BNode<T> child : children) {
				childSize += size(child);
			}
			return childSize;
		}
	}

	@Override
	public BNodePosition<T> search(T element) {
		if (element == null)
			return new BNodePosition<>();

		return search(getRoot(), element);
	}

	private BNodePosition<T> search(BNode<T> node, T element) {

		int index = 0;

		while (index < node.size() && element.compareTo(node.getElementAt(index)) > 0) {
			index++;
		}

		if (index < node.size() && element.equals(node.getElementAt(index)))
			return new BNodePosition<>(node, index);

		else if (node.isLeaf())
			return new BNodePosition<>();

		else
			return search(node.getChildren().get(index), element);
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;

		insert(getRoot(), element);
	}

	private void insert(BNode<T> node, T element) {
		int index = 0;

		while (index < node.size() && node.getElementAt(index).compareTo(element) < 0) {
			index++;
		}

		if (index >= node.size() || ! node.getElementAt(index).equals(element)) {

		 if (node.isLeaf())
				node.addElement(element);

		 else
			insert(node.getChildren().get(index), element);

		if (node.getMaxKeys() < node.size())
				split(node);
		}
	}

	private void split(BNode<T> node) {

		int indexMid = node.getElements().size() / 2;

		BNode<T> leftNode = new BNode<>(getOrder());
		BNode<T> rightNode = new BNode<>(getOrder());

		for (int i = 0; i < node.size(); i++) {

			if (i < indexMid)
				leftNode.getElements().add(node.getElementAt(i));

			else if (i > indexMid)
				rightNode.getElements().add(node.getElementAt(i));
		}

		T mid = node.getElementAt(indexMid);

		if(!node.isLeaf()) {

			List<BNode<T>> children = node.getChildren();

			if (children.size() > 0) {

				int rChildIndex = 0;

				for (int i = 0; i < children.size(); i++) {

					if (i <= indexMid)
						leftNode.addChild(i, children.get(i));

					else
						rightNode.addChild(rChildIndex++, children.get(i));
				}
			}
		}


		if (node.equals(getRoot())) {
			BNode<T> newRoot = new BNode<>(getOrder());
			newRoot.addElement(mid);
			node.setParent(newRoot);
			setRoot(newRoot);

			newRoot.addChild(0, leftNode);
			newRoot.addChild(1, rightNode);
			newRoot.getChildren().get(0).setParent(newRoot);
			newRoot.getChildren().get(1).setParent(newRoot);

		} else {

			node.addChild(0, leftNode);
			node.addChild(1, rightNode);

			promote(node);
		}

	}

	private void promote(BNode<T> node) {

		int indexMid = node.getElements().size() / 2;
		T mid = node.getElementAt(indexMid);

		node.getElements().clear();
		node.addElement(mid);

		BNode<T> parent = node.getParent();

		if (parent != null) {

			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);

			int index = parent.getChildren().indexOf(node);

			parent.addElement(mid);
			parent.addChild(index, node.getChildren().get(0));
			parent.addChild(index + 1, node.getChildren().get(1));

			node.getChildren().get(0).setParent(parent);
			node.getChildren().get(1).setParent(parent);

			parent.getChildren().remove(node);
		}
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}