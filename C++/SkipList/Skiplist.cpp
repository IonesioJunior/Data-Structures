#include <cstdlib>
#include <iostream>
#include <climits>

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
	
	void showForwardKeyList(){
		std::cout << "Node key: " << key << std::endl; 
		for(int i = 0 ; i < height;i++){	
			if(forward[i]){
				std::cout << "Level:" << i << ":" << forward[i]->key << std::endl;
			}
		}
	}
};

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
		void debbugHead();
		int height();
		void insert(K key,T element,int height);
		Node<T> *getHead();
};

template<class T, class K>
SkipList<T,K>::SkipList(int height){
	this->max_height = height;
	this->head = new Node<T>(INT_MIN,height);
	this->tail = new Node<T>(INT_MAX,height);
	this->connectHeadToTail();
}

template<class T,class K>
void SkipList<T,K>::connectHeadToTail(){
	for(int i = 0 ; i < this->head->height;i++){
		this->head->forward[i] = this->tail;
	}	
}

template<class T,class K>
void SkipList<T,K>::debbugHead(){
	for(int i = 0 ; i < this->head->height;i++){
		if(this->head->forward[i]->key == this->tail->key){
			std::cout << "Ligado!" << std::endl;
		}	
	}	
}

template<class T,class K>
void SkipList<T,K>::insert(K key,T element,int height){
	if(&element){
		Node<T,K> *previousNodes[height];	
		Node<T,K> *aux = this->head;
		for(int i = this->max_height - 1 ; i >= 0 ; i--){
			while(&(aux->forward[i]->data) && aux->forward[i]->key < key){
				aux = aux->forward[i];
			}
		}
		//std::cout << "Passei!!" << std::endl;
		aux = aux->forward[0];
		if(aux->key == key){
			aux->data = element;
		}else{
			this->ajustHeight(height);
			aux = new Node<T,K>(element,key,height);
			this->changePointers(height,previousNodes,aux);			
		}
	}
}

template<class T,class K>
void SkipList<T,K>::ajustHeight(int height){
	if(height > this->max_height){
		for(int i = this->max_height; i < height;i++){
			this->head->forward[i] = this->tail;
		}
		this->max_height = height;
		this->head->height = height;
	}
}

template<class T,class K>
void SkipList<T,K>::changePointers(int height,Node<T,K> *previousNodes[],Node<T,K> *aux){
	for(int i = 0 ; i< height;i++){
		if(!previousNodes[i]){
			aux->forward[i] = this->tail;
		}else{
			aux->forward[i] = previousNodes[i]->forward[i];
			previousNodes[i]->forward[i] = aux;		
		}
	}
}

int main(){
	SkipList<std::string> *skip = new SkipList<std::string>(6);
	skip->insert(25,"A",9);
	//skip->insert(5,"B",2);
	//skip->insert(3,"C",12);
}
