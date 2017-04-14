package HashTable.OpenAddress.HashFunction;

import HashTable.ClosedAddress.HashFunction.DivisionMethod;
import HashTable.ClosedAddress.HashFunction.HashFunctionInterface;
import HashTable.ClosedAddress.HashFunction.HashFunctionMethod;
import HashTable.ClosedAddress.HashFunction.MultiplicationMethod;
/**
 * Hash Function Linear Probing
 * */
public class LinearProbing<T> implements HashFunction<T> {
	//Attributes
	protected HashFunctionInterface<T> originalHashFunction;
	private int tableSize;
	/**
	 * Linear Probing constructor
	 * @param size
	 * @param method
	 * */
	public LinearProbing(int tableSize,HashFunctionMethod method) {
		this.tableSize = tableSize;
		if(method == HashFunctionMethod.MULTIPLICATION){
			this.originalHashFunction = new MultiplicationMethod<T>(this.tableSize);
		}else{
			this.originalHashFunction = new DivisionMethod<T>(this.tableSize);
		}
	}

	/**
	 * The hash function considering open address with linear probing. It uses
	 * the hashCode method inherited from Object.
	 * @param element
	 * @param probing
	 * @return index
	 */
	@Override
	public int hash(T element, int prob) {
		int generatedIndex = 0;
		generatedIndex = this.originalHashFunction.hash(element);
		generatedIndex = (generatedIndex + prob) % this.tableSize;
		return generatedIndex;
	}
	
}
