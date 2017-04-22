/*
*@author Ionésio Junior
*/
#include <cstdlib>
#include <vector>

/*
*Recursive Double Linked List Implementation
*/
//DoubleLinkedList.cpp
template<class T>
class DoubleLinkedList{
	private:
		T data;
		DoubleLinkedList<T> *next;
		DoubleLinkedList<T> *previous;
		bool head_empty;
		void recursiveToVector(std::vector<T> *vetor);
		DoubleLinkedList(T element,DoubleLinkedList<T> *previous);
		DoubleLinkedList(T element,DoubleLinkedList<T> *next,DoubleLinkedList<T> *previous);
	public:
		DoubleLinkedList();
		void insert(T element);
		void remove(T element);
		DoubleLinkedList<T> *search(T element);
		int size();
		std::vector<T> toVector();
		void insertFirst(T element);
		void removeFirst();
		void removeLast();
		bool isEmpty();
};



//DoubleLinkedList.cpp
///////////////////////////////////////////////////////////////

template<class T>
DoubleLinkedList<T>::DoubleLinkedList(){
	this->head_empty = true;	
};

///////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////

template<class T>
DoubleLinkedList<T>::DoubleLinkedList(T element,DoubleLinkedList<T> *previous){
	this->data = element;
	this->previous = previous;
	this->head_empty = false;
}

////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////

template<class T>
DoubleLinkedList<T>::DoubleLinkedList(T element,DoubleLinkedList<T> *next,DoubleLinkedList<T> * previous){
	this->data = element;
	this->next = next;
	this->previous = previous;
}

/////////////////////////////////////////////////////////////////


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


//////////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::remove(T element){
	DoubleLinkedList<T> *foundNode = search(element);
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


////////////////////////////////////////////////////////////////////

template<class T>
DoubleLinkedList<T> *DoubleLinkedList<T>::search(T element){
	if(data == element){
		return this;
	}else if(next){
		next->search(element);
	}else{
		return NULL;
	}
}

////////////////////////////////////////////////////////////////////


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


//////////////////////////////////////////////////////////////////

template<class T>
bool DoubleLinkedList<T>::isEmpty(){
	return head_empty;
}

/////////////////////////////////////////////////////////////////
