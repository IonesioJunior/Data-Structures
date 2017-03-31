package LinkedList.SimpleLinkedList;

public class RecursiveLinkedList<T> implements LinkedListInterface<T> {
	private T data;
	private RecursiveLinkedList<T> next;
	
	public RecursiveLinkedList() {
		
	}
	
	public RecursiveLinkedList(T data,RecursiveLinkedList<T> next){
		this.data = data;
		this.next = next;
	}
	
	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if(isEmpty())
			return 0;
		else{
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty())
			return null;
		else{
			if(data == element){
				return data;
			}else{
				return this.next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(element != null){
			if(this.isEmpty()){
				this.data = element;
				this.next = new RecursiveLinkedList<T>();
			}else{
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty()){
			if(data == element){
				this.data = this.next.data;
				this.next = this.next.next;
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
}
