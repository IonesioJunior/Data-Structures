package HashTable;

import HashTable.Exception.HashTableOverflowException;
/**
 * Hash table structure methods contract
 * */
public interface HashTableInterface<T> {
	/**
	 * Return true if hash table structure is empty or false if not
	 * @return boolean
	 * */
	public boolean isEmpty();
	/**
	 * Return true if hash table is full or false if not
	 * @return boolean
	 * */
	public boolean isFull();
	/**
	 * Return length of internal table
	 * @return internal table length
	 * */
	public int capacity();
	/**
	 * Return how many elements have been in hash table
	 * @return size
	 * */
	public int size();
	/**
	 * Insert  a not null/duplicated element in hashtable structure
	 * @param element
	 * */
	public void insert(T element) throws HashTableOverflowException;
	/**
	 * Search and remove an element in hash table
	 * @param element to remove
	 * */
	public void remove(T element);
	/**
	 * Search an element in hash table, if it isn't in hash table , return null
	 * @param element to search
	 * @return element found
	 * */
	public T search(T element);
	/**
	 * Search an element in hash table and return index of this, if not found element,
	 * return -1
	 * @param element
	 * @return index
	 * */
	public int indexOf(T element);
}
