package HashTable.ClosedAddress.HashFunction;

/**
 * This class represents hash function using division method
 * */
public class DivisionMethod<T> implements HashFunctionInterface<T> {
	protected int tableSize;
	
	/**
	 * Hash function (Division Method)
	 * */
	public DivisionMethod(int tableSize) {
		this.tableSize = tableSize;
	}
	
	@Override
	public int hash(T element) {
		int hashKey = -1;
		int key = element.hashCode();
		hashKey = (int) key % tableSize;
		return hashKey;
	}
	
}
