package Queue;

public class CircularQueue<T> implements QueueInterface<T> {
	
	private T[] array;
	private int tail;
	private int head;
	private int elements;
	
	public CircularQueue(int size) {
		this.array = (T[]) new Object[size];
		this.tail = -1;
		this.head = -1;
		this.elements = 0;
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(this.isFull()){
			throw new QueueOverflowException();
		}
		if(element != null){
			array[++tail % this.array.length] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.isEmpty()){
			throw new QueueUnderflowException();
		}
		T element = array[++head % this.array.length];
		this.elements--;
		return element;
	}

	@Override
	public T head() {
		if(isEmpty())
			return null;
		else
			return this.array[(head + 1) % this.array.length];
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}

}
