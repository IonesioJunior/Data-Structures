/*
*@author Ionésio Junior
*/
#include "DoubleLinkedList.hpp"
/*
*Recursive Double Linked List Implementation
*/
//DoubleLinkedList.cpp
template<class T>
class DoubleLinkedList : public LinkedList<T>{
	private:
		T data;
		DoubleLinkedList<T> *next;
		DoubleLinkedList<T> *previous;
		DoubleLinkedList<T> *searchNode(T element);
		bool head_empty;
		void recursiveToVector(std::vector<T> *vetor);
		DoubleLinkedList(T element,DoubleLinkedList<T> *previous);
		DoubleLinkedList(T element,DoubleLinkedList<T> *next,DoubleLinkedList<T> *previous);
	public:
		DoubleLinkedList();
		void insert(T element) override;
		void remove(T element) override;
		T *search(T element) override;
		int size() override;
		std::vector<T> toVector() override;
		void insertFirst(T element) override;
		void removeFirst() override;
		void removeLast() override;
		bool isEmpty() override;
};



//DoubleLinkedList.cpp
/*
* Empty Constructor of linked list node
*/
///////////////////////////////////////////////////////////////

template<class T>
DoubleLinkedList<T>::DoubleLinkedList(){
	this->head_empty = true;	
};

///////////////////////////////////////////////////////////////

/*
* Constructor of linked list node
* @param element
* @param PreviousNode
*/
///////////////////////////////////////////////////////////////

template<class T>
DoubleLinkedList<T>::DoubleLinkedList(T element,DoubleLinkedList<T> *previous){
	this->data = element;
	this->previous = previous;
	this->head_empty = false;
}

////////////////////////////////////////////////////////////////

/*
* Constructor of linked list node
* @param element
* @param NextNode
* @param PreviousNode
*/
////////////////////////////////////////////////////////////////

template<class T>
DoubleLinkedList<T>::DoubleLinkedList(T element,DoubleLinkedList<T> *next,DoubleLinkedList<T> * previous){
	this->data = element;
	this->next = next;
	this->previous = previous;
}

/////////////////////////////////////////////////////////////////

/*
* Insert new element in last position of the list
* @param new element
*/
/////////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::insert(T element){
	if(head_empty){
		this->data = element;
		this->head_empty = false;
	}else{
		if(!next){
			this->next = new DoubleLinkedList<T>(element,this);	
		}else{
			next->insert(element);
		}
	}
}

//////////////////////////////////////////////////////////////////

/*
* Remove an list element
* @param element to be removed
*/
//////////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::remove(T element){
	DoubleLinkedList<T> *foundNode = searchNode(element);
	if(foundNode){
		if(data == element){
			if(next){
				std::cout << data << std::endl;
				DoubleLinkedList<T> *removedNode = this->next;
				this->data = this->next->data;
				this->next->previous = this;
				this->next = this->next->next;
				free(removedNode);
				removedNode = NULL;
				return;
			}else{
				this->head_empty = true;
			}
		}else{	
			DoubleLinkedList<T> *removedNode = foundNode;
			if(foundNode->next){
				foundNode->previous->next = foundNode->next;
				foundNode->next->previous = foundNode->previous;
				free(removedNode);
				removedNode = NULL;
			}else{
				
				this->removeLast();
			}
		}
	}
}

////////////////////////////////////////////////////////////////////

/*
* Search an element, if found return it return your pointer, else return NULL
* @param element to be searched
* @return elementFoundPointer
*/
////////////////////////////////////////////////////////////////////

template<class T>
T *DoubleLinkedList<T>::search(T element){
	if(data == element){
		return &(this->data);
	}else if(next){
		next->search(element);
	}else{
		return NULL;
	}
}

////////////////////////////////////////////////////////////////////

/*
* Search a node of element, or NULL if element isn't in the list
* @param element
* @return NodePointer 
*/
////////////////////////////////////////////////////////////////////

template<class T>
DoubleLinkedList<T> *DoubleLinkedList<T>::searchNode(T element){
	if(data == element){
		return this;
	}else if(next){
		next->search(element);
	}else{
		return NULL;
	}
}

////////////////////////////////////////////////////////////////////

/*
* Return size of list
* @return int
*/
/////////////////////////////////////////////////////////////////////

template<class T>
int DoubleLinkedList<T>::size(){
	if(!head_empty){
		if(!next){
			return 1;
		}else{
			return 1 + next->size();
		}
	}else{
		return 0;
	}
}

/////////////////////////////////////////////////////////////////////

/*
* Return an vector with list elements
* @return vector
*/
/////////////////////////////////////////////////////////////////////

template<class T>
std::vector<T> DoubleLinkedList<T>::toVector(){
	std::vector<T> result;
	if(!head_empty){
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
void DoubleLinkedList<T>::recursiveToVector(std::vector<T> *vetor){
	if(next){
		vetor->push_back(data);
		next->recursiveToVector(vetor);
	}else{
		vetor->push_back(data);
	}
}

///////////////////////////////////////////////////////////////////////

/*
* Insert new element in head of linked list
* @param element
*/
//////////////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::insertFirst(T element){
	if(head_empty){
		this->data = element;
		this->head_empty = false;	
	}else{
		DoubleLinkedList<T> *nextNode = new DoubleLinkedList(this->data,this->next,this);
		if(next){
			this->next->previous = nextNode;
			this->data = element;
			this->next = nextNode;
		}else{
			this->data = element;
			this->next = nextNode;
		}
	}
}

//////////////////////////////////////////////////////////////////////

/*
* Remove the first element of the list
*/
//////////////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::removeFirst(){
	if(!head_empty){
		if(!next){
			this->head_empty = true;
		}else if(next->next){
			DoubleLinkedList<T> *removedNode = this->next;
			this->data = this->next->data;
			this->next->next->previous = this;
			this->next = this->next->next;
			free(removedNode);
			removedNode = NULL;
		}else{
			DoubleLinkedList<T> *removedNode = this->next;
			this->data = this->next->data;
			this->next = this->next->next;
			free(removedNode);
			removedNode = NULL;
		}
	}
}

//////////////////////////////////////////////////////////////////////

/*
* Remove the last element of the list
*/
//////////////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::removeLast(){
	if(!head_empty){
		if(!next){
			if(previous){
				this->previous->next = NULL;
			}else{
				this->head_empty = true;
			}
		}else{
			next->removeLast();
		}
	}
}

//////////////////////////////////////////////////////////////////////

/*
* Return true if list is empty or false,otherwise.
* @return bool
*/
//////////////////////////////////////////////////////////////////

template<class T>
bool DoubleLinkedList<T>::isEmpty(){
	return head_empty;
}

/////////////////////////////////////////////////////////////////
