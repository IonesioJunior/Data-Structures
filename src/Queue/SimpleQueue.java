package Queue;

public class SimpleQueue<T> implements QueueInterface<T>{
	private T[] array;
	private int tail;
	
	public SimpleQueue(int size){
		this.array = (T[]) new Object[size];
		this.tail = -1;
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(!this.isFull()){
			this.array[++tail] = element;
		}else{
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(!this.isEmpty()){
			T removedElement = this.array[0];
			switchArray();
			tail--;
			return removedElement;
		}else{
			throw new QueueUnderflowException();
		}
	}
	private void switchArray(){
		for(int i = 0 ; i < tail;i++){
			this.array[i] = this.array[i+1];		
		}	
	}
	@Override
	public T head() {
		if(!this.isEmpty())
			return this.array[0];
		else
			return null;
	}

	@Override
	public boolean isEmpty() {
		return this.tail < 0;
	}

	@Override
	public boolean isFull() {
		return this.tail == this.array.length - 1;
	}
}
