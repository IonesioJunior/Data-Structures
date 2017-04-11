package HashTable.ClosedAddress;
/**
 * @author Ionesio Junior
 * */

import java.util.LinkedList;

import HashTable.AbstractHashTable;
import HashTable.ClosedAddress.HashFunction.HashFunctionFactory;
import HashTable.ClosedAddress.HashFunction.HashFunctionMethod;
import HashTable.Exception.HashTableOverflowException;


/**
 * This class implements the hash table closed address concept
 * */
public class HashTableClosedAddress<T> extends AbstractHashTable<T> {

	/**
	 * Hash table closed address constructor
	 * @param method used to calculate hash
	 * @param size of internal table
	 * */
	public HashTableClosedAddress(HashFunctionMethod method, int tableSize) {
		this.COLLISIONS = 0;
		this.elements = 0;
		int realSize = tableSize;
		if(method == HashFunctionMethod.MULTIPLICATION){
			realSize = this.getPrimeAbove(tableSize);
		}
		initiateInternalTable(realSize);
		this.hashFunction = HashFunctionFactory.createHashFunction(method, realSize);
		
	}
	
	@Override
	public void insert(T element) throws HashTableOverflowException {
		if(isFull()){
			throw new HashTableOverflowException();
		}else{
			if(element == null)
				return;
			else{
				int hashKey = this.hashFunction.hash(element);
				if(table[hashKey] == null){
					table[hashKey] = new LinkedList<T>();
				}
				((LinkedList<T>) this.table[hashKey]).add(element);
				this.elements++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if(isEmpty() || element == null)
			return;
		else{
			int hashKey = this.hashFunction.hash(element);
			if(this.table[hashKey] == null){
				return;
			}
			if(((LinkedList<T>) this.table[hashKey]).remove(element)){
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		if(element == null)
			return null;
		else{
			int hashKey = hashFunction.hash(element);
			LinkedList<T> list = (LinkedList<T>) this.table[hashKey];
			if(list == null){
				return null;
			}
			int index = list.indexOf(element);
			if(index == -1){
				return null;
			}else{
				return (T) list.get(index); 
			}
		}
	}

	@Override
	public int indexOf(T element) {
		if(element == null){
			return -1;
		}else{
			int hashKey = this.hashFunction.hash(element);
			if(this.table[hashKey] == null){
				return -1;
			}else{
				LinkedList<T> list = (LinkedList<T>) this.table[hashKey];
				return list.indexOf(element);
			}
		}
			
	}

	
	
	//AUXILIAR METHODS
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	private int getPrimeAbove(int number) {
		int result = number;
		while(this.isPrime(result)){
			result = result+1;
		}
		return result;
	}
	/**
	 * It says if a specific number is prime or not.
	 * 
	 * @param n
	 * @return
	 */
	private static boolean isPrime(long n) {
		boolean result = true;
		for (int i = 2; i < n; i++) {
			if (n % i == 0){
				result = false;
				break;
			}
		}
		return result;
	}
	/**
	 * Initiate a internal table
	 * @param table size
	 * */
	protected void initiateInternalTable(int size) {
		this.table = this.<LinkedList<T>> makeArray(size);
	}
	
	private static <T> T[] makeArray(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size];
		return array;
	}
	
	/**
	 * Get number of collisions
	 * @return collisions
	 * */
	private int getColisionNumbers(){
		int col = 0;
		for(int i = 0 ; i < this.table.length;i++){
			if(this.table[i] != null){
				LinkedList<T> list = (LinkedList<T>) this.table[i];
				col += (list.size() - 1);
			}
		}
		this.COLLISIONS = col;
		return this.COLLISIONS;
	}
}
