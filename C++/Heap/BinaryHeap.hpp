/*
*@author Ionésio Junior
*/

#include <iostream>
#include <stdlib.h>
#include "Exception.hpp"

//BinaryHeap.hpp
template <typename T>
struct Array{
	int size;
	T *ptr;
};


template <typename T> class BinaryHeap{
	private:
		HeapUnderflowException underflow;
		Array<T> array;
		int index;
		void buildHeap(Array<T> inputArray);
		int parent(int index);
		int left(int index);
		int right(int index);
		void heapify(int index);
		void responsiveArray();
	public:
		BinaryHeap(int size);
		bool isEmpty();			
		bool isFull();
		void insert(T element);
		T extractRoot();
		T rootElement();
		Array<T> heapSort(Array<T> inputArray);
};

//BinaryHeap.cpp
//////////////////////////////////////////////////////////////////////

template<typename T>
BinaryHeap<T>::BinaryHeap(int size){
	this->array = {size};
	this->array.ptr = (T*) calloc(this->array.size,sizeof(T));
	this->index = -1;
}

//////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////

template <typename T>
bool BinaryHeap<T>::isEmpty(){
	return this->index < 0;
}

/////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////

template <typename T>
bool BinaryHeap<T>::isFull(){
	return this->index == this->array.size - 1;
}

////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template <typename T>
void BinaryHeap<T>::insert(T element){
	if(this->isFull()){
		this->responsiveArray();
	}
	if(&element != NULL){
		this->array.ptr[++index] = element;
		int i = this->index;
		while(i >= 0 && this->array.ptr[this->parent(i)] < this->array.ptr[i]){
			T aux = this->array.ptr[i];
			this->array.ptr[i] = this->array.ptr[this->parent(i)];
			this->array.ptr[this->parent(i)] = aux;
			i = this->parent(i);
		}
	}
}

///////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////

template<typename T>
T BinaryHeap<T>::extractRoot(){
	if(this->isEmpty()){
		throw underflow;
	}else{
		T removedElement = *(this->array.ptr);
		*(this->array.ptr) = this->array.ptr[this->index--];
		this->heapify(0);
		return removedElement;
	}
}

////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////

template<typename T>
T BinaryHeap<T>::rootElement(){
	if(this->isEmpty()){
		return NULL;
	}else{
		return this->array.ptr[0];
	}
}

/////////////////////////////////////////////////////////////////

template<typename T>
Array<T> BinaryHeap<T>::heapSort(Array<T> inputArray){
	this->buildHeap(inputArray);
	for(int i = this->index;i > 0;i--){
		T aux = this->array.ptr[0];
		this->array.ptr[0] = this->array.ptr[i];
		this->array.ptr[i] = aux;
		this->index--;
		this->heapify(0);
	}
	return this->array;
}

////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////

template<typename T>
void BinaryHeap<T>::buildHeap(Array<T> inputArray){
	this->array.ptr= (T*) calloc(inputArray.size,sizeof(T));
	this->array.size = inputArray.size;
	this->index = -1;
	for(int i = 0; i < inputArray.size;i++){
		this->insert(inputArray.ptr[i]);
	}
}

//////////////////////////////////////////////////////////////


////////////////////////////////////////////	AUXILIAR METHODS	///////////////////////////////////////////////////////


//////////////////////////////////////

template<typename T>
int BinaryHeap<T>::parent(int index){
	return index/2;
}

///////////////////////////////////////


////////////////////////////////////

template<typename T>
int BinaryHeap<T>::left(int index){
	return (2 * index);
}

/////////////////////////////////////


////////////////////////////////////

template<typename T>
int BinaryHeap<T>::right(int index){
	return (2 * index) + 1;
}

/////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////

template<typename T>
void BinaryHeap<T>::heapify(int index){
	int left = this->left(index);
	int right = this->right(index);
	int largest;
	if(left <= this->index && this->array.ptr[left]	> this->array.ptr[index]){
		largest = left;
	}else{
		largest = index;
	}

	if(right <=this->index && this->array.ptr[right] > this->array.ptr[largest]){
		largest = right;
	}
	
	if(largest != index){
		T aux = this->array.ptr[index];
		this->array.ptr[index] = this->array.ptr[largest];
		this->array.ptr[largest] = aux;
		this->heapify(largest);
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////

template<typename T>
void BinaryHeap<T>::responsiveArray(){
	this->array.ptr = (T*) realloc(this->array.ptr,(this->array.size * 2)  * sizeof(T));	
}

/////////////////////////////////////////////////////////////////////////
