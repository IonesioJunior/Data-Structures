/*
*@author Ionésio Junior
*/

#include "LinkedList.hpp"
/*
* Recursive Linked List Implementation
*/
//RecursiveLinkedList.hpp
template<class T>
class SingleLinkedList : public LinkedList<T>{
	private:
		T data;
		SingleLinkedList<T> *next;
		SingleLinkedList(T element);
		bool empty_root;
		void recursiveToVector(std::vector<T> * vetor);
	public:
		SingleLinkedList();
		void insert(T element) override;
		void remove(T element) override;
		T *search(T element) override;
		T *getRoot();
		int size() override;
		std::vector<T> toVector() override;
		bool isEmpty() override;
		SingleLinkedList *searchPrevious(T element);
		T getData();
};


//RecursiveLinkedList.cpp

/*
* Empty Constructor of linked list node
*/
//////////////////////////////////////////////////////////////

template<class T>
SingleLinkedList<T>::SingleLinkedList(){
	this->empty_root = true;
}

/////////////////////////////////////////////////////////////

/*
* Constructor of linked list node
* @param element
*/
//////////////////////////////////////////////////////////////

template<class T>
SingleLinkedList<T>::SingleLinkedList(T element){
	this->data = element;
	this->empty_root = false;
}

/////////////////////////////////////////////////////////////

/*
* Return true  if linked list is empty or false,otherwise.
* @return bool
*/
/////////////////////////////////////////////////////////////

template<class T>
bool SingleLinkedList<T>::isEmpty(){
	return this->empty_root;
}

/////////////////////////////////////////////////////////////

/*
* Get data stored in node
* @return element
*/
//////////////////////////////////////////////////////////////

template<class T>
T SingleLinkedList<T>::getData(){
	return this->data;
}

///////////////////////////////////////////////////////////////

/*
* Return size of list
* @return int
*/
///////////////////////////////////////////////////////////////

template<class T>
int SingleLinkedList<T>::size(){
	if(!empty_root){	
		if(!next){
			return 1;	
		}else{
			return 1 + next->size();
		}
	}else{
		return 0;	
	}
}

////////////////////////////////////////////////////////////////

/*
* Search an element, if found return it return your pointer, else return NULL
* @param element to be searched
* @return elementFoundPointer
*/
/////////////////////////////////////////////////////////////////

template<class T>
T * SingleLinkedList<T>::search(T element){
	if(data == element){
		return &data;	
	}else if(!next){
		return NULL;	
	}else{
		return next->search(element);
	}
}

//////////////////////////////////////////////////////////////////

/*
* Insert new element in last position of the list
* @param new element
*/
//////////////////////////////////////////////////////////////////

template<class T>
void SingleLinkedList<T>::insert(T element){
	if(this->empty_root){
		this->data = element;
		this->empty_root = false;
	}else{
		if(!next){
			this->next = new SingleLinkedList<T>(element);
		}else{
			this->next->insert(element);
		}
	}
}

//////////////////////////////////////////////////////////////////

/*
* Remove an list element
* @param element to be removed
*/
///////////////////////////////////////////////////////////////////

template<class T>
void SingleLinkedList<T>::remove(T element){
	SingleLinkedList<T> *previous = this->searchPrevious(element);
	if(!previous){
		if(data == element){
			if(next){
				SingleLinkedList<T> *removedNode = this->next;
				this->data = this->next->data;
				this->next = this->next->next;
				free(removedNode);
				removedNode = NULL;
			}else{
				this->empty_root = true;
				return;
			}
		}else{
			return;
		}
	}else{
		SingleLinkedList<T> *removedNode = this->next;
		previous->next = previous->next->next;
		free(removedNode);
		removedNode = NULL;
	}
}

////////////////////////////////////////////////////////////////////

/*
* Search a previous node of element, or NULL if element isn't in the list
* @param element
* @return NodePointer 
*/
////////////////////////////////////////////////////////////////////

template<class T>
SingleLinkedList<T> *SingleLinkedList<T>::searchPrevious(T element){
	if(!next){
		return NULL;	
	}else{
		if(next->data == element){
			return this;
		}else{
			next->searchPrevious(element);
		}
	}
}

////////////////////////////////////////////////////////////////////

/*
* Return head of the list
* @return elementPointer
*/
////////////////////////////////////////////////////////////////////

template<class T>
T *SingleLinkedList<T>::getRoot(){
	if(this->empty_root){
		return NULL;
	}else{
		return &data;
	}
}

/////////////////////////////////////////////////////////////////////

/*
* Return an vector with list elements
* @return vector
*/
/////////////////////////////////////////////////////////////////////

template<class T>
std::vector<T> SingleLinkedList<T>::toVector(){
	std::vector<T> result;
	if(!empty_root){
		this->recursiveToVector(&result);
	}	
	return result;
}

//////////////////////////////////////////////////////////////////////

/*
* Recursive method to build elements vector
*/
//////////////////////////////////////////////////////////////////////

template<class T>
void SingleLinkedList<T>::recursiveToVector(std::vector<T> *vetor){
	if(next){
		vetor->push_back(data);
		next->recursiveToVector(vetor);
	}else{
		vetor->push_back(data);
	}
}

///////////////////////////////////////////////////////////////////////
