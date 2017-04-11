package LinkedList.DoubleLinkedList;
/*
 * @author Ionésio Junior
 * */

/*
 * Double Linked List in Iterative implementation
 * */
public class DoubleLinkedList<T> implements DoubleLinkedListInterface<T> {

	private DoubleNode<T> head;
	private DoubleNode<T> tail;
	
	public DoubleLinkedList() {
		this.head = new DoubleNode<T>();
		this.tail = new DoubleNode<T>();
		this.head.setNext(this.tail);
		this.tail.setPrevious(this.head);
	}
	
	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		DoubleNode<T> aux = this.head;
		while(!aux.isNIL()){
			aux = aux.getNext();
			size++;
		}
		return size;
	}

	@Override
	public T search(T element) {
		if(element != null){
			DoubleNode<T> aux = this.head;
			while(!aux.isNIL()){
				if(aux.getData().equals(element)){
					return aux.getData();
				}
				aux = aux.getNext();
			}
			return null;
		}else{
			return null;
		}
	}

	@Override
	public void insert(T element) {
		if(element != null){
			if(this.isEmpty()){
				this.insertFirst(element);
			}else{
				DoubleNode<T> node = new DoubleNode<T>(element,this.tail,new DoubleNode<T>());
				this.tail.setNext(node);
				this.tail = node;
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !this.isEmpty()){
			if(this.head.getData().equals(element)){
				this.removeFirst();
			}else{
				DoubleNode<T> aux = this.head;
				while(!aux.isNIL()){
					if(aux.getData().equals(element)){
						aux.getPrevious().setNext(aux.getNext());
						aux.getNext().setPrevious(aux.getPrevious());
						if(aux.getNext().isNIL()){
							this.tail = aux.getPrevious();
						}
						break;
					}
					aux = aux.getNext();
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[this.size()];
		DoubleNode<T> aux = this.head;
		int index = 0;
		while(!aux.isNIL()){
			result[index++] = aux.getData();
			aux = aux.getNext();
		}
		return result;
	}

	@Override
	public void insertFirst(T element) {
		if(element != null){
			if(this.isEmpty()){
				DoubleNode<T> newHead = new DoubleNode<T>(element,new DoubleNode<T>(),new DoubleNode<T>());
				this.head = newHead;
				this.tail = newHead;
			}else{
				DoubleNode<T> newHead = new DoubleNode<T>(element,new DoubleNode<T>(),this.head);
				this.head.setPrevious(newHead);
				this.head = newHead;
			}
		}
	}

	@Override
	public void removeFirst() {
		if(this.isEmpty())
			return;
		else{
			this.head.getNext().setPrevious(new DoubleNode<T>());
			if(this.size() == 1){
				this.tail = this.head.getNext();
			}
			this.head = this.head.getNext();
		}
	}

	@Override
	public void removeLast() {
		if(!this.isEmpty()){
			this.tail.getPrevious().setNext(new DoubleNode<T>());
			if(this.size() == 1){
				this.head = this.tail.getPrevious();
			}
			this.tail = this.tail.getPrevious();
		}
	}
}
