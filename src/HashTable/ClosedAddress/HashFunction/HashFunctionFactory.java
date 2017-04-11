package HashTable.ClosedAddress.HashFunction;

/**
 * This class creates hash functions suitable to each kind of
 * hash table.
 * 
 * @author Ionesio
 *
 */
public class HashFunctionFactory<T> {
	
	public static HashFunctionInterface createHashFunction(HashFunctionMethod method, int tableSize){
		HashFunctionInterface function = null;
		switch(method){
			case DIVISION:
				function = new DivisionMethod(tableSize);
				break;
			case MULTIPLICATION:
				function = new MultiplicationMethod(tableSize);
				break;
		}
		return function;
	}
}
