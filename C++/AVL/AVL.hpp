/*
*@author Ionésio Junior
*/
#include <cstdlib>
#include "BinarySearchTree.hpp"
/*
*AVL Tree implementation
*/


//AVL.hpp
template<class T>
class AVL : public BinarySearchTree<T> {
	private:
		int calculateBalance(Node<T> *node);
		void rebalance(Node<T> *rebalance);
		void rebalanceUp(Node<T> *node);
		void leftRotation(Node<T> *node);
		void rightRotation(Node<T> *node);
		Node<T>* leftRotationNodes(Node<T> *node);
		Node<T>* rightRotationNodes(Node<T> *node);
		void recursiveInsert(Node<T> *root,T val);
		void recursiveRemove(Node<T> *node);
	public:
		AVL();
		void insert(T val);
		bool remove(T element);
};

///////////////////////////////////////////////		PUBLIC METHODS		/////////////////////////////////////////////////////////////////


//AVL.cpp
////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
AVL<T>::AVL(){};

/////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
void AVL<T>::insert(T val){
	if(this->getRoot()){
		this->recursiveInsert(this->getRoot(),val);
	}else{
		this->setRoot( new Node<T>(val));
	}
	this->rebalance(this->getRoot());
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
bool AVL<T>::remove(T element){

	if(!this->getRoot() || !&element){
		return 0;	
	}else{
		Node<T> *foundNode = BinarySearchTree<T>::search(element);
		if(foundNode){
			this->recursiveRemove(foundNode);
		}
	}
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////	PRIVATE METHODS		//////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
void AVL<T>::recursiveInsert(Node<T> *node,T val){
	if (node->data > val) {
            if (!node->left) {
                node->left = new Node<T>(val,node);
		this->rebalance(node->left);
            } else {
                recursiveInsert(node->left, val);
            }
        } else {
            if (!node->right) {
                node->right = new Node<T>(val,node);
		this->rebalance(node->right);
            } else {
                recursiveInsert(node->right, val);
            }
        }
	if(node != this->getRoot()){
		this->rebalance(node);
	}
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
void AVL<T>::recursiveRemove(Node<T>* node){
	if(!node->left && !node->right){
		if(node->parent->left = node){
			node->parent->left = NULL;
		}else{
			node->parent->right = NULL;
		}
		Node<T> *parent = node->parent;
		free(node);
		node = NULL;
		this->rebalanceUp(parent);
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
		this->rebalanceUp(node->parent);
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
		this->rebalanceUp(node->parent);
	}else{
		T removedValue = node->data;
		Node<T> *sucessor = BinarySearchTree<T>::sucessor(removedValue);
		node->data = sucessor->data;
		sucessor->data = removedValue;
		this->recursiveRemove(sucessor);
			
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
void AVL<T>::rebalance(Node<T> *node){
	int balance = this->calculateBalance(node);
	int leftBalance = this->calculateBalance(node->left);
	int rightBalance = this->calculateBalance(node->right);
	if(balance > 1){
		if(leftBalance < 0){
			this->leftRotation(node->left);
		}
		this->rightRotation(node);
	}else if(balance < -1){
		if(rightBalance > 0){
			this->rightRotation(node->right);	
		}
		this->leftRotation(node);
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
void AVL<T>::leftRotation(Node<T> *node){
	Node<T> *newRoot = this->leftRotationNodes(node);
	if(node == this->getRoot()){
		this->setRoot(newRoot);
	}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
void AVL<T>::rightRotation(Node<T> *node){
	Node<T> *newRoot = this->rightRotationNodes(node);
	if(node == this->getRoot()){
		this->setRoot(newRoot);
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////

template<class T>
Node<T> *AVL<T>::leftRotationNodes(Node<T> *node){
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
Node<T> *AVL<T>::rightRotationNodes(Node<T> * node){
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


/////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
int AVL<T>::calculateBalance(Node<T> *node){
	if(!node){
		return 0;
	}else{
		return this->recursiveHeight(node->left) - this->recursiveHeight(node->right);		
	}
}

//////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////////

template<class T>
void AVL<T>::rebalanceUp(Node<T> *parent){
	while(parent){
		this->rebalance(parent);
		parent = parent->parent;
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

