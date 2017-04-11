package LinkedList.SimpleLinkedList;

public class SimpleLinkedList<T> implements LinkedListInterface<T>{

	private Node<T> head;
	
	public SimpleLinkedList() {
		this.head =  new Node<T>();
	}
	
	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		Node<T> aux = this.head;
		while(!aux.isNIL()){
			aux = aux.getNext();
			size++;
		}
		return size;
	}

	@Override
	public T search(T element) {
		Node<T> aux = this.head;
		while(!aux.isNIL()){
			if(aux.getData().equals(element)){
				return aux.getData();
			}
			aux = aux.getNext();
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if(element != null){
			Node<T> aux = this.head;
			while(!aux.isNIL()){
				aux = aux.getNext();
			}
			aux.setData(element);
			aux.setNext(new Node<T>());
		}
	}

	@Override
	public void remove(T element) {
		if(element != null){
			if(element.equals(this.head.getData())){
				this.head = this.head.getNext();
			}else{
				Node<T> aux = this.head;
				while(!aux.isNIL()){
					if(aux.getData().equals(element)){
						aux.setData(aux.getNext().getData());
						aux.setNext(aux.getNext().getNext());
						break;
					}
					aux = aux.getNext();
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		Node<T> aux = this.head;
		int i = 0;
		while(!aux.isNIL()){
			array[i++] = aux.getData();
			aux = aux.getNext();
		}
		return array;
	}
}
