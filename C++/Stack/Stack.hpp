/*
* @author Ionésio Junior
*/

#include <iostream>
#include <stdlib.h>

//Stack.hpp
template <typename T> class Stack{
	private:
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
	this->top_ptr = (T*) calloc(this->size,sizeof(T));
	this->begin = this->top_ptr;
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
	if(element != NULL){
		if(!this->isFull()){
			*this->top_ptr = element;
			this->top_ptr++;
		}else{
			std::cout << "Stack is Full!!"<< std::endl;
		}
	}
}

/////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////

template <typename T>
T Stack<T>::pop(){
	if(!this->isEmpty()){
		return *(--this->top_ptr);
	}else{
		std::cout << "Stack is Empty!!" << std::endl;
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
