/*
*@author Ionésio Junior
*/

#include <cstdlib>
#include "BinarySearchTree.hpp"

/*
*Splay Tree Implementation
*/

//SplayTree.hpp
template<class T>
class Splay : public BinarySearchTree<T>{
	private:
		void splay(Node<T> *node);
		Node<T>* leftRotation(Node<T>* node);
		Node<T>* rightRotation(Node<T> *node);
		void recursiveInsert(Node<T> *node,T val);
		void recursiveRemove(Node<T> *node);
	public:
		Node<T> *search(T element);
		bool remove(T element);
};

//SplayTree.cpp
////////////////////////////////////////////////////////////////////

template<class T>
void Splay<T>::splay(Node<T> *node){ 
	if(node == NULL || node == this->getRoot()){
		return;
	}
	Node<T> *parent = node->parent;
	Node<T> *grandParent = parent->parent;
	while(parent != NULL){
		if(parent == this->getRoot()){
			if(parent->left == node){
				this->rightRotation(parent);
			}else{
				this->leftRotation(parent);
			}
			this->setRoot(parent);
		}else{
			if(grandParent->right == parent){
				if(parent->right == node){
					this->leftRotation(grandParent);
					this->leftRotation(parent);
				}else{
					this->rightRotation(parent);
					this->leftRotation(grandParent);
				}
			}else{
				if(parent->left == node){
					this->rightRotation(grandParent);
					this->rightRotation(parent);
				}else{
					this->leftRotation(parent);
					this->rightRotation(grandParent);
				}
			}
		}
	parent = node->parent;
	if(parent){
		grandParent = parent->parent;
		if(!grandParent){
			this->setRoot(parent);
		}
	}else{
		this->setRoot(node);
	}
	}
}

///////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////

template<class T>
Node<T> *Splay<T>::leftRotation(Node<T> *node){
	if(node){
		Node<T> *parent = node->parent;
		Node<T> *right = node->right;
		
		right->parent = parent;
		if(parent){
			if(parent->right == node){
				parent->right = right;
			}else{
				parent->left = right;
			}
		}
			
		node->right = right->left;
		if(node->right){
			node->right->parent = node;
		}
		right->left = node;
		node->parent = right;
		return right;	
	}
	return NULL;	
}

///////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////

template<class T>
Node<T> *Splay<T>::rightRotation(Node<T> * node){
	if(node){
		Node<T> *parent = node->parent;
		Node<T> *left = node->left;
		
		left->parent = parent;
		if(parent){
			if(parent->left == node){
				parent->left = left;
			}else{
				parent->right = left;
			}
		}
		node->left = left->right;
		if(node->left){
			node->left->parent = node;
		}
		left->right = node;
		node->parent = left;
		return left;
	}else{
		return NULL;
	}
}

////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////

template<class T>
Node<T> *Splay<T>::search(T element){
	if(element){
		Node<T> *node = this->getRoot();
		Node<T> *parent = this->getRoot();
		while(node){
			if(node->data == element){
				if(node->data != this->getRoot()->data){
					this->splay(node);
				}
				return node;
			}else if(node->data > element){
				parent = node;
				node = node->left;
			}else{
				parent = node;
				node = node->right;
			}
		}
		if(parent != this->getRoot()){
			this->splay(parent);
		}
	}else{
		return NULL;	
	}
}

//////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////

template<class T>
void Splay<T>::recursiveInsert(Node<T> *node, T val) {        
	if (node->data > val) {
            if (!node->left) {
                node->left = new Node<T>(val,node);
		this->splay(node->left);
            } else {
                recursiveInsert(node->left, val);
            }
        } else {
            if (!node->right) {
                node->right = new Node<T>(val,node);
		this->splay(node->right);
            } else {
                recursiveInsert(node->right, val);
            }
        }
    }

///////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////

template<class T>
bool Splay<T>::remove(T element){
	if(!this->getRoot() || !element){
		return false;
	}else{
		Node<T> *node = BinarySearchTree<T>::search(element);
		if(node){
			Node<T> *parent = node->parent;
			this->recursiveRemove(node);
			if(parent){
				this->splay(parent);
			}
		}else{
			this->search(element);
		}
	}
}

//////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////

template<class T>
void Splay<T>::recursiveRemove(Node<T> *node){
	if(!node->left && !node->right){
		if(node->parent->left = node){
			node->parent->left = NULL;
		}else{
			node->parent->right = NULL;
		}
		free(node);
		node = NULL;
	}else if(!node->left){
		node->data = node->right->data;
		if(node->right->right){
			node->right->right->parent = node;
		}
		if(node->right->left){
			node->right->left->parent = node;
		}
		node->left = node->right->left;
		node->right = node->right->right;
	}else if(!node->right){
		node->data = node->left->data;
		if(node->left->right){
			node->left->right->parent = node;
		}
		if(node->left->left){
			node->left->left->parent = node;
		}
		node->right = node->left->right;
		node->left = node->left->left;
	}else{
		T removedValue = node->data;
		Node<T> *sucessor = BinarySearchTree<T>::sucessor(removedValue);
		node->data = sucessor->data;
		sucessor->data = removedValue;
		this->recursiveRemove(sucessor);
			
	}
}

////////////////////////////////////////////////////////////////////
