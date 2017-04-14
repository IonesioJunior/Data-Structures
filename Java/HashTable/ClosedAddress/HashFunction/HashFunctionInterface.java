package HashTable.ClosedAddress.HashFunction;
/**
 * It represents a hash function to be used in hashtable that work with closed
 * address.
 */
public interface HashFunctionInterface<T> {
	/**
	 * The hash function considering closed address.
	 * */
	public int hash(T element);
}
