/*
*@author Ionésio Junior
*/

#include <cstdlib>
#include <iostream>

/*
* Queue in circular implementation
*/
//CircularQueue.hpp
template<class T>
class CircularQueue{
	private:
		T *head_ptr;
		int tail;
		int head_index;
	public:
		int size;
		int elements;
		CircularQueue(int size);
		bool isEmpty();
		bool isFull();
		void enqueue(T element);
		T dequeue();
		T head();
};

//CircularQueue.cpp
//////////////////////////////////////////

template<class T>
CircularQueue<T>::CircularQueue(int size){
	this->head_ptr = (T*) calloc(size,sizeof(T));
	this->head_index = -1;
	this->tail = -1;
	this->elements = 0;
	this->size = size;
}

//////////////////////////////////////////


//////////////////////////////////////////

template<class T>
void CircularQueue<T>::enqueue(T element){
	if(this->isFull()){
		std::cout << "Queue is Full!!" << std::endl;
		return;		
	}
	if(&element){
		this->elements++;
		if(this->head_index == -1 && this->tail == -1){
			this->head_index = (this->head_index + 1 ) % this->size;
		}
		this->tail = (this->tail + 1 ) % this->size;
		this->head_ptr[this->tail] = element;
	}
}

///////////////////////////////////////////


///////////////////////////////////////////

template<class T>
T CircularQueue<T>::dequeue(){
	if(this->isEmpty()){
		std::cout << "Queue is Empty!!" << std::endl;
		return NULL;
	}
	this->elements--;
	T aux = this->head_ptr[this->head_index];
	this->head_index = (this->head_index + 1) % size;
	return aux;
}

/////////////////////////////////////////////


/////////////////////////////////////////////

template<class T>
T CircularQueue<T>::head(){
	if(this->isEmpty()){
		return NULL;
	}else{
		return this->head_ptr[this->head_index];
	}
}

///////////////////////////////////////////////


///////////////////////////////////////////////

template<class T>
bool CircularQueue<T>::isEmpty(){
	return this->elements == 0;
}

////////////////////////////////////////////////


/////////////////////////////////////////////////

template<class T>
bool CircularQueue<T>::isFull(){
	return this->elements == this->size;
}

//////////////////////////////////////////////////
