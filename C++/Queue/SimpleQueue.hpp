/*
*@author Ionésio Junior
*/
#include "Queue.hpp"
//Queue.hpp
/*
* Simple Queue Implementation
*/

template<typename T> 
class SimpleQueue : public Queue<T>{
	private:
		T *head_ptr;
		T *tail;
		int size;
		void shiftLeft();
	public:
		SimpleQueue(int size);
		bool isEmpty() override;
		bool isFull() override;
		void enqueue(T element) override;
		T dequeue() override;
		T head() override;		
};


//Queue.cpp
////////////////////////////////////////////////////////////

template<typename T>
SimpleQueue<T>::SimpleQueue(int size){
	this->head_ptr = (T*) calloc(size,sizeof(T));
	this->tail = this->head_ptr - 1;
	this->size = size;
}

////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////

template<typename T>
bool SimpleQueue<T>::isEmpty(){
	return (this->tail < this->head_ptr);
}

//////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////

template <typename T>
bool SimpleQueue<T>::isFull(){
	return (this->tail == (this->head_ptr + this->size - 1));
}

//////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////

template <typename T>
void SimpleQueue<T>::enqueue(T element){
	if(!this->isFull()){
		this->tail++;
		*this->tail = element;
	}else{
		std::cout << "Queue is Full!!" << std::endl;	
	}
}

/////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////

template <typename T>
T SimpleQueue<T>::dequeue(){
	if(!this->isEmpty()){
		T removedElement = *this->head_ptr;
		this->shiftLeft();
		this->tail--;
		return removedElement;
	}else{
		std::cout << "Queue is Empty!!" << std::endl;
		return NULL;
	}
}

///////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////

template<typename T>
void SimpleQueue<T>::shiftLeft(){
	for(int *i = this->head_ptr; i < this->tail;i++){
		*i = *(i + 1);
	}
}

//////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////

template<typename T>
T SimpleQueue<T>::head(){
	if(!this->isEmpty()){
		return *this->head_ptr;
	}else{
		return NULL;
	}
}

///////////////////////////////////////////////////////////
