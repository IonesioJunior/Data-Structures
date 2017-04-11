package HashTable.OpenAddress.HashFunction;

import HashTable.ClosedAddress.HashFunction.DivisionMethod;
import HashTable.ClosedAddress.HashFunction.HashFunctionInterface;
import HashTable.ClosedAddress.HashFunction.HashFunctionMethod;
import HashTable.ClosedAddress.HashFunction.MultiplicationMethod;
/**
 * Hash Function Quadratic Probing
 * */
public class QuadraticMethod<T> implements HashFunction<T> {
	//Attributes
	private int tableSize;
	private HashFunctionInterface<T> originalHashFunction;
	private int c1;
	private int c2;
	/**
	 * Quadratic Method Constructor
	 * @param tableSize
	 * @param method
	 * @param c1
	 * @param c2
	 * */
	public QuadraticMethod(int tableSize,HashFunctionMethod method,int c1, int c2) {
		this.tableSize = tableSize;
		if(method == HashFunctionMethod.DIVISION){
			originalHashFunction = new DivisionMethod<T>(tableSize);
		}else{
			originalHashFunction = new MultiplicationMethod<T>(tableSize);
		}
		this.c1 = c1;
		this.c2 = c2;
	}
	
	/**
	 * The hash function considering open address with quadratic probing. It uses
	 * the hashCode method inherited from Object.
	 * @param element
	 * @param probing
	 * @return index
	 */
	@Override
	public int hash(T element, int prob) {
		int generatedIndex = 0;
		generatedIndex = originalHashFunction.hash(element);
		generatedIndex = ((generatedIndex + c1 * prob + c2 * prob * prob)% this.tableSize);
		return generatedIndex;
	}

}
