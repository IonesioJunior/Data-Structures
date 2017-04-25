/*
*@author Ionésio Junior
*/
#include <cstdlib>
#include <iostream>

/*
* Queue Abstract class implementation
*/

template<class T>
class Queue{
	public:
		virtual bool isEmpty() = 0;
		virtual bool isFull() = 0;
		virtual void enqueue(T element) = 0;
		virtual T dequeue() = 0;
		virtual T head() = 0;
};
