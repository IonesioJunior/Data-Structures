package HashTable.OpenAddress.HashFunction;

import HashTable.ClosedAddress.HashFunction.HashFunctionInterface;
/**
 * Hash Function Interface
 * */
public interface HashFunction<T>{
	/**
	 * Method to calculate hash key using open address implementation
	 * @param element
	 * @param prob
	 * @return key
	 * */
	public int hash(T element,int prob);
}
