/*
*@author Ionésio Junior
*/

#include "DoubleLinkedList.hpp"
/*
*Double Linked List node implementation
*/

template<class T>
struct Node{
	T data;
	Node<T> * next;
	Node<T> *previous;
	
	Node(T value){
		this->data = value;
	}

	Node(T value,Node<T> *next){
		this->data = value;
		this->next = next;
	}

	Node(Node<T> *previous){
		this->previous = previous;
	}

	Node(T value,Node<T> *next,Node<T> *previous){
		this->data = value;
		this->next = next;
		this->previous = previous;
	}
};

/*
* Double Linked List Class Implementation
*/
//DoubleLinkedList.hpp
template<class T>
class DoubleLinkedList : public LinkedList<T>{
	private:
		Node<T> *head;
		Node<T> *tail;
		Node<T> *searchNode(T element);
	public:
		DoubleLinkedList();
		bool isEmpty() override;
		void insert(T element) override;
		void remove(T element) override;
		int size() override;
		T *search(T element);
		Node<T> *getHead();
		void insertFirst(T element) override;
		void removeFirst() override;
		void removeLast() override;
		std::vector<T> toVector() override;
	
};

//DoubleLinkedList.cpp
///////////////////////////////////////////////////////////

template<class T>
DoubleLinkedList<T>::DoubleLinkedList(){};

///////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////

template<class T>
bool DoubleLinkedList<T>::isEmpty(){
	if(this->head){
		return false;
	}else{
		return true;
	}
}

////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::insert(T element){
	if(this->isEmpty()){
		Node<T> *head = new Node<T>(element);
		this->head = head;
		this->tail = new Node<T>(this->head);
		this->head->next = this->tail;
	}else{
		Node<T> *aux = this->head;
		while(aux != this->tail){
			aux = aux->next;
		}
		aux->data = element;
		aux->next = new Node<T>(aux);
		this->tail = aux->next;
	}
}

////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////

template<class T>
int DoubleLinkedList<T>::size(){
	if(isEmpty()){
		return 0;	
	}else{
		int count = 0;
		Node<T> *aux = this->head;
		while(aux != this->tail){
			aux = aux->next;
			count++;
		}
		return count;
	}
}

////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////

template<class T>
Node<T>* DoubleLinkedList<T>::searchNode(T element){
	if(!isEmpty()){
		Node<T> *aux = this->head;
		while(aux != this->tail){
			if(aux->data == element){
				return aux;
			}
			aux = aux->next;
		}
		return NULL;
	}else{
		return NULL;
	}
}

/////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////

template<class T>
T* DoubleLinkedList<T>::search(T element){
	if(!isEmpty()){
		Node<T> *aux = this->head;
		while(aux != this->tail){
			if(aux->data == element){
				return &(aux->data);
			}
			aux = aux->next;
		}
		return NULL;
	}else{
		return NULL;
	}
}

/////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::remove(T element){
	if(!isEmpty()){
		Node<T> *foundNode = this->searchNode(element);
		if(foundNode){
			if(foundNode == this->head){
				this->removeFirst();
			}else{
				foundNode->next->previous = foundNode->previous;
				foundNode->previous->next = foundNode->next;
				free(foundNode);
				foundNode == NULL;
			}
		}
	}
}

//////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////

template<class T>
Node<T> *DoubleLinkedList<T>::getHead(){
	return this->head;
}

//////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::insertFirst(T element){
	if(!isEmpty()){
		Node<T> *newNode = new Node<T>(element,this->head);
		this->head->previous = newNode;
		this->head = newNode;
	}else{
		this->insert(element);
	}
}

/////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::removeFirst(){
	if(!isEmpty()){
		if(this->head->next == this->tail){
			free(this->head);
			this->head = NULL;
		}else{
			Node<T> *removedValue = this->head;
			this->head = this->head->next;
			this->head->previous == NULL;
			free(removedValue);
			removedValue = NULL;
		}
	}
}

//////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////

template<class T>
void DoubleLinkedList<T>::removeLast(){
	if(!isEmpty()){
		if(this->head->next == this->tail){
			free(this->head);	
			this->head = NULL;
		}else{
			Node<T> *removedNode = this->tail->previous;
			this->tail->previous = this->tail->previous->previous;
			this->tail->previous->next = this->tail;
			free(removedNode);
			removedNode = NULL;
		}
	}
}

/////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////

template<class T>
std::vector<T> DoubleLinkedList<T>::toVector(){
	std::vector<T> result;
	if(!isEmpty()){
		Node<T> *aux = this->head;
		while(aux != this->tail){
			result.push_back(aux->data);
			aux = aux->next;
		}
	}
	return result;
}

//////////////////////////////////////////////////////////////
