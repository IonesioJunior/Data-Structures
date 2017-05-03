#ifndef _SKIPLIST_H_
#define _SKIPLIST_H_

/*
*@author Ionesio Junior
*/

#include <cstdlib>
#include <iostream>
#include <climits>
#include <vector>

/*
* SkipList node class
*/
template<class T, class K = int>
struct Node{
	K key;
	T data;
	Node<T> **forward;
	int height;
	
	Node(T element,K key,int height){
		this->data = element;
		this->key = key;
		this->forward = (Node<T,K> **) calloc(height,sizeof(Node<T,K>));
		this->height = height;
	}
	Node(K key,int height){
		this->key = key;
		this->forward  = (Node<T,K> **) calloc(height,sizeof(Node<T,K>));
		this->height = height;
	}
};

/*
* Skip List class Implementation
*/

//SkipList.hpp
template<class T,class K = int>
class SkipList{
	private:
		Node<T> *head;
		Node<T> *tail;
		int max_height;
		void connectHeadToTail();
		void ajustHeight(int height);
		void changePointers(int height,Node<T,K> *previousNodes[],Node<T,K> *next);
	public:
		SkipList(int height);
		int height();
		void insert(K key,T element,int height);
		void remove(int key);
		int size();
		Node<T,K> *search(int key);
		std::vector<Node<T,K> *> toVector();
		Node<T,K> *getHead();
};


//SkipList.cpp
///////////////////////////////////////////////////

template<class T, class K>
SkipList<T,K>::SkipList(int height){
	this->max_height = height;
	this->head = new Node<T>(INT_MIN,height);
	this->tail = new Node<T>(INT_MAX,height);
	this->connectHeadToTail();
}

////////////////////////////////////////////////////


////////////////////////////////////////////////////

template<class T,class K>
void SkipList<T,K>::connectHeadToTail(){
	for(int i = 0 ; i < this->head->height;i++){
		this->head->forward[i] = this->tail;
	}	
}

////////////////////////////////////////////////////


////////////////////////////////////////////////////

template<class T,class K>
void SkipList<T,K>::insert(K key,T element,int height){
		this->ajustHeight(height);
		Node<T,K> *previousNodes[height];	
		Node<T,K> *aux = this->head;
		for(int i = height - 1 ; i >= 0 ; i--){
			while(&(aux->forward[i]->data) && aux->forward[i]->key < key){
				aux = aux->forward[i];
			}
			previousNodes[i] = aux;
		}
		aux = aux->forward[0];
		if(aux->key == key){
			aux->data = element;
		}else{
			aux = new Node<T,K>(element,key,height);
			this->changePointers(height,previousNodes,aux);			
		}
}

/////////////////////////////////////////////////////


/////////////////////////////////////////////////////

template<class T,class K>
void SkipList<T,K>::remove(int key){
	Node<T,K> *previousNodes[this->max_height];
	Node<T,K> *aux = this->head;
	for(int i = this->max_height - 1; i >= 0;i--){
		while(&(aux->forward[i]->data) && aux->forward[i]->key < key){
			aux = aux->forward[i];
		}
		previousNodes[i] = aux;
	}
	aux = aux->forward[0];
	if(aux->key == key){
		for(int i = 0 ; i < this->max_height;i++){
			if(previousNodes[i]->forward[i] != aux){
				break;
			}
			previousNodes[i]->forward[i] = aux->forward[i];
		}
	}
}

////////////////////////////////////////////////////////


////////////////////////////////////////////////////////

template<class T,class K>
Node<T,K> *SkipList<T,K>::search(int key){
	Node<T,K> *aux = this->head;
	for(int i = this->max_height - 1; i >= 0;i--){
		while(&(aux->forward[i]->data) && aux->forward[i]->key < key){
			aux = aux->forward[i];
		}
	}
	aux = aux->forward[0];
	if(aux->key == key){
		return aux;
	}else{
		return NULL;
	}
}

///////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////

template<class T,class K>
int SkipList<T,K>::height(){
	int height = this->max_height - 1;
	while(height >= 0 && this->head->forward[height] == this->tail){
		height--;
	}	
	return height + 1;
}

///////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////

template<class T,class K>
int SkipList<T,K>::size(){
	int count = 0;
	Node<T,K> *aux = this->head->forward[0];
	while(aux->forward[0]){
		count++;
		aux = aux->forward[0];
	}
	return count;
}

///////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////

template<class T,class K>
std::vector<Node<T,K> *> SkipList<T,K>::toVector(){
	std::vector<Node<T,K> *> result;
	Node<T,K> *aux = this->head->forward[0];
	while(aux->forward[0]){
		result.push_back(aux);
		aux = aux->forward[0];
	}
	return result;
}

////////////////////////////////////////////////////////////


//////////////////////////////////////		AUXILIAR METHODS	////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////////

template<class T,class K>
void SkipList<T,K>::ajustHeight(int height){
	if(height > this->max_height){
		this->head->forward = (Node<T,K> **) realloc(this->head->forward,height * sizeof(Node<T,K>));
		this->tail->forward = (Node<T,K> **) realloc(this->tail->forward,height * sizeof(Node<T,K>));
		this->head->height = height;
		this->tail->height = height;
		for(int i = this->max_height; i < height;i++){
			this->head->forward[i] = this->tail;
		}
		this->max_height = height;
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////////

template<class T,class K>
void SkipList<T,K>::changePointers(int height,Node<T,K> *previousNodes[],Node<T,K> *aux){
	for(int i = 0 ; i < height;i++){
		if(!previousNodes[i]->forward[i]){
			aux->forward[i] = this->tail;
		}else{
			aux->forward[i] = previousNodes[i]->forward[i];
			previousNodes[i]->forward[i] = aux;		
		}
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
#endif
