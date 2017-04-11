package HashTable;
/**
 * @author Ionesio Junior
 * */
import HashTable.ClosedAddress.HashFunction.HashFunctionInterface;
import HashTable.Exception.HashTableOverflowException;
import HashTable.OpenAddress.HashFunction.HashFunction;
/**
 * This class represents generic hash table structure methods implementations
 *  and attributes
 *  */
public abstract class AbstractHashTable<T> implements HashTableInterface<T> {
	//Attributes
	protected Object[] table;
	protected int elements;
	protected int COLLISIONS;
	protected HashFunctionInterface hashFunction;
	protected HashFunction OpenAddressHashFunction;
	
	@Override
	public boolean isEmpty() {
		return( elements == 0);
	}

	@Override
	public boolean isFull() {
		return (elements == table.length);
	}

	@Override
	public int capacity() {
		return table.length;
	}

	@Override
	public int size() {
		return elements;
	}
	
}
