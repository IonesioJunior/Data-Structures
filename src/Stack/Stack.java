package Stack;

public class Stack<T> implements StackInterface<T> {
	private T[] array;
	private int top = -1;
	
	public Stack(int size) {
		this.array = (T[]) new Object[size];
	}
	
	@Override
	public void push(T element) throws StackOverflowException {
		if(element == null)
			return;
		if(!this.isFull()){
			this.array[++top] = element;
		}else{
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(!this.isEmpty()){
			return array[this.top--];
		}else{
			throw new StackUnderflowException();
		}
	}

	@Override
	public T top() {
		if(!this.isEmpty()){
			return this.array[this.top];
		}else{
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return this.top < 0;
	}

	@Override
	public boolean isFull() {
		return this.top == this.array.length - 1;
	}
	
	//If you want create a auto-resize stack
	private void resizeArray(){
		if(this.isFull()){
			T[] newArray = (T[]) new Object[this.array.length* 2];
			for(int i = 0 ; i < this.array.length;i++){
				newArray[i] = this.array[i];
			}
			this.array = newArray;
		}
	}
}
