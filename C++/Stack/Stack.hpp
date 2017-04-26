/*
* @author Ionésio Junior
*/

#include <iostream>
#include <stdlib.h>
#include "Exceptions.hpp"

//Stack.hpp
template <typename T> 
class Stack{
	private:
		StackOverflowException overflow;
		StackUnderflowException underflow;
		T *top_ptr;
		T *begin;
		int size;
	public:
		Stack(int size);
		bool isEmpty();		
		bool isFull();
		void push(T element);
		T pop();
		T top();		
};


//Stack.cpp
///////////////////////////////////////////////////////////////////////////

template<typename T>
Stack<T>::Stack(int size){
	this->size = size;
	this->begin  = (T*) calloc(this->size,sizeof(T));
	this->top_ptr = this->begin - 1;
}

///////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////

template<typename T>
bool Stack<T>::isEmpty(){
	return this->top_ptr == (this->begin - 1);
}

template<typename T>
bool Stack<T>::isFull(){
	return this->top_ptr == (this->begin + size - 1);
}

////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////

template <typename T>
void Stack<T>::push(T element){
	if(&element != NULL){
		if(!this->isFull()){
			this->top_ptr++;
			*this->top_ptr = element;
		}else{
			throw overflow;
		}
	}
}

/////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////

template <typename T>
T Stack<T>::pop(){
	if(!this->isEmpty()){
		T removedValue = *this->top_ptr;
		--this->top_ptr;
		return removedValue;
	}else{
		throw underflow;
	}
}

///////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////

template <typename T>
T Stack<T>::top(){
	if(!this->isEmpty()){
		return *(this->top_ptr - 1);
	}else{
		return NULL;
	}
}

////////////////////////////////////////////////////////////////////////////////
