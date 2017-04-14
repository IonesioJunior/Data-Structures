package HashTable.ClosedAddress.HashFunction;

/**
 * This class represents hash function using multiplication method
 * */
public class MultiplicationMethod<T> implements HashFunctionInterface<T>{
	//Attributes
	protected int tableSize;
	private static final double A = (Math.sqrt(5) - 1) / 2;
	/**
	 * Hash function (Multiplication Method)
	 * */
	public MultiplicationMethod(int tableSize){
		this.tableSize = tableSize;
	}

	@Override
	public int hash(T element) {
		int hashKey = -1;
		int key = element.hashCode();
		double fractionalPart = key * A - Math.floor(key * A);
		hashKey = (int) (tableSize * fractionalPart);
		return hashKey;
	}
	
}
