package HashTable.Exception;

public class HashTableOverflowException extends Exception {
	public HashTableOverflowException(){
		super("This hash table is full!!");
	}
}
