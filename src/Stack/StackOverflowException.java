package Stack;

public class StackOverflowException extends Exception {
	public StackOverflowException(){
		super("Stack is Full!");
	}
}
