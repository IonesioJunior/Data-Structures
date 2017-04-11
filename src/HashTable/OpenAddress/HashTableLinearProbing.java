package HashTable.OpenAddress;
/**
 * @author Ionesio Junior
 * */


import java.util.LinkedList;

import HashTable.AbstractHashTable;
import HashTable.ClosedAddress.HashFunction.HashFunctionMethod;
import HashTable.Exception.HashTableOverflowException;
import HashTable.OpenAddress.Elements.DELETED;
import HashTable.OpenAddress.Elements.Storable;
import HashTable.OpenAddress.HashFunction.LinearProbing;
/**
 * Hash table(Open Address) Structure using linear probing
 * */
public class HashTableLinearProbing<T extends Storable> extends AbstractHashTable<T>{
	protected DELETED elementDeleted;
	private int tableSize;
	
	
	/**
	 * Hashtable open address linear probing implementation
	 * */
	public HashTableLinearProbing(int tableSize,HashFunctionMethod method){
		this.elementDeleted = new DELETED();
		this.tableSize = tableSize;
		this.OpenAddressHashFunction = new LinearProbing<T>(tableSize, method);
		this.initiateInternalTable(tableSize);
	}


	@Override
	public void insert(T element) throws HashTableOverflowException  {
		if(element == null 	|| indexOf(element) != -1/*Nao deixa colocar elementos repetidos*/){
			return;
		}else if(isFull()){ //Se já estiver cheia
			throw new HashTableOverflowException();
		}else{ //Tentando achar um espaço disponivel para o novo elemento
			int probe = 0; // indice secundário caso ja exista alguem na posicao
			int index = getIndex(element,probe); //Pega o indice se acordo com o hash  + probe(de inicio só o hash)
			if(isNull(index) || isDeleted(index)){ //Caso o hash inicial esteja vazio(nulo ou antiormente deletado)
				super.table[index] = element;
				super.elements++;
			}else{//Caso nao esteja vazio, busca os proximos espaços disponiveis
				while(!isNull(index) && !isDeleted(index) && probe < super.table.length){ //Enquanto os espaços estiverem cheios
					index = getIndex(element,++probe); //Aumenta o probe para o próximo espaço
					super.COLLISIONS++; //Aumenta o numero de colisoes
				}
				if(isNull(index) || isDeleted(index)){ //Caso encontre um espaço vazio
					super.table[index] = element;
					super.elements++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element == null) //Caso o element seja nulo
			return;
		int index =indexOf(element); //Busca o índice do elemento caso ele esteja na tabela
		if(index == -1) //Caso nao encontre
			return;
		else{//Caso encrontre
			super.table[index] = elementDeleted;
			super.elements--;
		}
	}

	@Override
	public T search(T element) {
		if(element == null)
			return null;
		else{
			int index = indexOf(element);
			if(index == -1)
				return null;
			else{
				return (T) super.table[index];
			}
		}
	}

	@Override
	public int indexOf(T element) {
		if(element == null){ //Se o elemento for nulo
			return -1;
		}else{//Caso nao seja nulo
			int prob = 0;
			int index = getIndex(element, prob); //Primeira posicao do hash
			if(isNull(index)){ //Se nunca teve um elemento na primeira posicao
				return -1;
			}else{//Caso ja tenha tido ou ainda tenha
				if(isEquals(element, index)){//Se o elemento dessa posicao for igual ao procurado
					return index;
				}else{//Caso nao seja, busque nas próximas
					prob = 1;
					index = getIndex(element,prob);
					while(!isNull(index) && prob < super.table.length){
						if(isEquals(element, index)){
							return index;
						}else{
							index = getIndex(element,++prob);
						}
					}
					return -1;
				}
			}
		}
	}
	
	
	//Auxiliar methods
	private boolean isEquals(T element,int index){
		return super.table[index].equals(element);
	}
	private boolean isDeleted(int index){
		if(isNull(index)){
			return false;
		}else{
			return (super.table[index] instanceof DELETED) ? true:false;
		}
	}
	private boolean isNull(int index){
		return super.table[index] == null;
	}
	private int getIndex(T element,int probe){
		return this.OpenAddressHashFunction.hash(element, probe);
	}
	
	public int getCOLLISIONS(){
		return this.COLLISIONS;
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
}
