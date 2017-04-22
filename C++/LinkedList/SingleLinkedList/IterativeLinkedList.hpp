/*
*@author Ionésio Junior
*/
#include <cstdlib>
#include <vector>
#include <iostream>
/*
* Single Linked List in iterative implementation
*/

/*
*Node struct 
*/
template<class T>
struct Node{
	T data;
	Node<T> *next;
	Node(T value){
		this->data = value;
	}
};

/*
*Single Linked List class
*/
//SingleLinkedList.hpp
template<class T>
class SingleLinkedList{
	private:
		Node<T> *head;
	public:
		SingleLinkedList();
		void insert(T element);
		void remove(T element);
		int size();
		Node<T> *search(T element);
		Node<T> *getHead();
		std::vector<T> toVector();
		bool isEmpty();
};

//SingleLinkedList.cpp
////////////////////////////////////////////////

template<class T>
SingleLinkedList<T>::SingleLinkedList(){};

////////////////////////////////////////////////


////////////////////////////////////////////////

template<class T>
bool SingleLinkedList<T>::isEmpty(){
	if(head){
		return true;
	}else{
		return false;	
	}
}

//////////////////////////////////////////////////


//////////////////////////////////////////////////

template<class T>
Node<T> *SingleLinkedList<T>::getHead(){
	return this->head;
}

//////////////////////////////////////////////////


////////////////////////////////////////////////////

template<class T>
void SingleLinkedList<T>::insert(T element){
	if(!head){
		this->head = new Node<T>(element);	
	}else{
		Node<T> *aux = this->head;
		while(aux->next){
			aux = aux->next;
		}
		aux->next = new Node<T>(element);
	}

}

//////////////////////////////////////////////////////


//////////////////////////////////////////////////////

template<class T>
void SingleLinkedList<T>::remove(T element){
	if(head){
		if(this->head->data == element){
			Node<T> *removedElement = this->head;
			this->head = this->head->next;
			free(removedElement);
			removedElement = NULL;
		}else{
			Node<T> *aux = this->head;
			while(aux->next){
				if(aux->next->data == element){
					Node<T> *removedElement = aux->next;
					aux->next = aux->next->next;
					free(removedElement);
					removedElement = NULL;
					break;
				}
				aux = aux->next;
			}
		}		
	}
}

////////////////////////////////////////////////////////


////////////////////////////////////////////////////////

template<class T>
int SingleLinkedList<T>::size(){
	if(head){
		Node<T> *aux = this->head;
		int count = 1;
		while(aux->next){
			aux = aux->next;
			count++;
		}
		return count;
	}else{
		return 0;
	}
}

/////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////

template<class T>
Node<T> *SingleLinkedList<T>::search(T element){
	if(head){
		if(this->head->data == element){
			return this->head;
		}else{
			Node<T> *aux = this->head;
			while(aux->next){
				if(aux->next->data == element){
					return aux->next;
				}
				aux = aux->next;
			}
			return NULL;
		}
	}else{
		return NULL;
	}
}

//////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////

template<class T>
std::vector<T> SingleLinkedList<T>::toVector(){
	std::vector<T> result;
	if(head){
		Node<T>	*aux = this->head;
		result.push_back(head->data);
		while(aux->next){
			result.push_back(aux->next->data);
			aux = aux->next;
		}
	}
	return result;
}

////////////////////////////////////////////////////////////
