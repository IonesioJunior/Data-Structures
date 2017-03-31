package LinkedList.DoubleLinkedList;


public class RecursiveDoubleLinkedList<T> implements DoubleLinkedListInterface<T> {
	private T data;
	private RecursiveDoubleLinkedList<T> next;
	private RecursiveDoubleLinkedList<T> previous;
	
	public RecursiveDoubleLinkedList() {}

	public RecursiveDoubleLinkedList(T data, RecursiveDoubleLinkedList<T> next,RecursiveDoubleLinkedList<T> previous) {
		this.data = data;
		this.next = next;
		this.previous = previous;
	}
	
	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if(isEmpty()){
			return 0;
		}else{
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty() || element == null){
			return null;
		}else{
			if(this.data.equals(element)){
				return this.data;
			}
			return this.next.search(element);
		}
	}

	@Override
	public void insert(T element) {
		if(element == null)
			return;
		if(isEmpty()){
			this.data = element;
			this.next  = new RecursiveDoubleLinkedList<T>();
			this.next.previous = this;
			if(this.previous == null){
				RecursiveDoubleLinkedList<T> NIL = new RecursiveDoubleLinkedList<T>();
				NIL.next = this;
				this.previous = NIL;
			}
		}else{
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(element == null)
			return;
		if(!isEmpty()){
			if(this.data.equals(element)){
				this.removeFirst();
			}else{
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		if(size() > 0){
			toArray(result,0);
		}
		return result;		
	}
	private void toArray(T[] array,int index){
		if(this.data != null){
			array[index] = this.data;
			next.toArray(array,index + 1);
		}
	}

	@Override
	public void insertFirst(T element) {
		if(element != null){
			RecursiveDoubleLinkedList<T> next;
			if(this.isEmpty()){
				next  = new RecursiveDoubleLinkedList<T>();
				this.data = element;
				this.next = next;
				this.previous = next;
			}else{
				next = new RecursiveDoubleLinkedList<T>(this.data,this,this.next);
				this.next.previous = next;
				this.data = element;
				this.next = next;
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!isEmpty()){
			if(this.next.isEmpty()){
				this.data = null;
				this.next = null;
				this.previous = null;
			}else{
				this.data = this.next.data;
				this.next.next.previous = this;
				this.next = this.next.next;
			}
		}
	}

	@Override
	public void removeLast() {
		if(!isEmpty()){
			if(this.next.isEmpty()){
				this.data = null;
				this.next = null;
				this.previous = null;
			}else{
				this.next.removeLast();
			}
		}
	}

}
