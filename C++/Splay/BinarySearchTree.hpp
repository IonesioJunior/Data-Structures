/*
*@author Ionésio Junior
*/

#include <iostream>
#include <math.h>
#include <vector>

//BST Node implementation
template <class T>
struct Node {
    public:
    	T data;
    	Node *left;
    	Node *right;
    	Node *parent;

    	Node(T val,Node<T> *parent) {
       	this->data = val;
	this->parent = parent;
    	}

 	Node(T val){
		this->data = val;
    	}

   	Node(T val, Node<T> left, Node<T> right) {
        	this->data = val;
        	this->left = left;
        	this->right = right;
    	}
};

//BST Class Implementation
//BinarySearchTree.hpp
template <class T>
class BinarySearchTree {

    private:
    	Node<T> *root;
    	virtual void recursiveInsert(Node<T> *root, T val);
	bool recursiveRemove(Node<T> *parent,Node<T> *current,T element);    	
	int recursiveSize(Node<T> *root);
    	int recursiveHeight(Node<T> *root);
    	Node<T>* recursiveSearch(Node<T> *root,T val);
	Node<T>* recursiveMaximum(Node<T> *root);
	Node<T>* recursiveMinimum(Node<T> *root);
	void recursiveOrder(std::vector<T> *vector,Node<T> *node);
	void recursivePreOrder(std::vector<T> *vector,Node<T> *node);
	void recursivePostOrder(std::vector<T> *vector,Node<T> *node);
    

    public:
	void insert(T val);
	virtual bool remove(T element);		
	int size();    
    	int height();
	void setRoot(Node<T> *node);
	virtual Node<T> *search(T element);
	Node<T> *maximum();
	Node<T> *minimum();
	Node<T> *getRoot();
	Node<T> *predecessor(T element);
	Node<T> *sucessor(T element);
	std::vector<T> order();
	std::vector<T> preOrder();
	std::vector<T> postOrder();
	
    
};


//BinarySearchTree.cpp
/////////////////////////////////////////	PUBLIC METHODS		///////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////

template<class T>
void BinarySearchTree<T>::insert(T val) {
        if (root) {
            this->recursiveInsert(root, val);
        } else {
            root = new Node<T>(val);
        }
}

////////////////////////////////////////////////


///////////////////////////////////////////////

template<class T>
int BinarySearchTree<T>::size() {
        return this->recursiveSize(root);
}

//////////////////////////////////////////////


//////////////////////////////////////////////

template<class T>
int BinarySearchTree<T>::height() {
        return this->recursiveHeight(this->root);
}

/////////////////////////////////////////////

////////////////////////////////////////////

template<class T>
void BinarySearchTree<T>::setRoot(Node<T> *node){
	this->root = node;
}

////////////////////////////////////////////

/////////////////////////////////////////////

template<class T>
bool BinarySearchTree<T>::remove(T element) {
        return this->recursiveRemove(NULL, this->root, element);
}

/////////////////////////////////////////////


/////////////////////////////////////////////////

template<class T>
Node<T>* BinarySearchTree<T>::search(T val) {       
	if (root && val) {
            this->recursiveSearch(root, val);
        } else {
		return NULL;
        }
}

////////////////////////////////////////////////


///////////////////////////////////////////////
template<class T>
Node<T>* BinarySearchTree<T>::maximum(){
	if(!root){
		return NULL;	
	}else{
		return recursiveMaximum(this->root);
	}
}
///////////////////////////////////////////////


///////////////////////////////////////////////

template<class T>
Node<T>* BinarySearchTree<T>::minimum(){
	if(!root){
		return NULL;	
	}else{
		return recursiveMinimum(this->root);
	}
}

///////////////////////////////////////////////


//////////////////////////////////////////////////

template<class T>
Node<T> *BinarySearchTree<T>::getRoot(){
	return this->root;
}

///////////////////////////////////////////////////


////////////////////////////////////////////////////

template<class T>
Node<T> *BinarySearchTree<T>::predecessor(T element){
	Node<T> *foundNode = search(element);
	if(!foundNode){
		return NULL;		
	}else if(foundNode->left){
		return recursiveMaximum(foundNode->left);						
	}else{
		Node<T> *parentNode = foundNode->parent;		
		while(parentNode && foundNode->data != parentNode->right->data){
			parentNode = parentNode->parent;
			foundNode = foundNode->parent;		
		}
		return parentNode;	
	}
}

////////////////////////////////////////////////////


////////////////////////////////////////////////////

template<class T>
Node<T> *BinarySearchTree<T>::sucessor(T element){
	Node<T> *foundNode = search(element);
	if(!foundNode){
		return NULL;	
	}else if(foundNode->right){
		return recursiveMinimum(foundNode->right);	
	}else{
		Node<T> *parentNode = foundNode->parent;
		while(parentNode && foundNode->data != parentNode->left->data){
			parentNode = parentNode->parent;
			foundNode = foundNode->parent;	
		}
		return parentNode;
	}
}

////////////////////////////////////////////////////


////////////////////////////////////////////////////

template<class T>
std::vector<T> BinarySearchTree<T>::order(){
	std::vector<T> result;
	this->recursiveOrder(&result,root);
	return result;
}

////////////////////////////////////////////////////


////////////////////////////////////////////////////

template<class T>
std::vector<T> BinarySearchTree<T>::preOrder(){
	std::vector<T> result;
	this->recursivePreOrder(&result,root);
	return result;
}

////////////////////////////////////////////////////


////////////////////////////////////////////////////

template<class T>
std::vector<T> BinarySearchTree<T>::postOrder(){
	std::vector<T> result;
	this->recursivePostOrder(&result,root);
	return result;
}

////////////////////////////////////////////////////


///////////////////////////////////////////////		PRIVATE METHODS		//////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////

template<class T>
void BinarySearchTree<T>::recursiveInsert(Node<T> *node, T val) {        
	if (node->data > val) {
            if (!node->left) {
                node->left = new Node<T>(val,node);
            } else {
                recursiveInsert(node->left, val);
            }
        } else {
            if (!node->right) {
                node->right = new Node<T>(val,node);
            } else {
                recursiveInsert(node->right, val);
            }
        }
    }

///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template<class T>
int BinarySearchTree<T>::recursiveSize(Node<T> *root) {
        if (!root){
	    	return 0;
	}else{
		return 1 + recursiveSize(root->left) + recursiveSize(root->right);
	}
    }

///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template<class T>
int BinarySearchTree<T>::recursiveHeight(Node<T> *root) {
        if (!root){
		return 0;
	}else{ 
		return 1 + std::max(recursiveHeight(root->left), recursiveHeight(root->right));
	}
    }

///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template<class T>
bool BinarySearchTree<T>::recursiveRemove(Node<T>* parent, Node<T>* current, T element) {
        if (!current) return false;
        if (current->data == element) {
            if (current->left == NULL || current->right == NULL) {
                Node<T>* temp = current->left;
                if (current->right) temp = current->right;
                if (parent) {
                    if (parent->left == current) {
                        parent->left = temp;
                    } else {
                        parent->right = temp;
                    }
                } else {
                    this->root = temp;
                }
            } else {
                Node<T>* validSubs = current->right;
                while (validSubs->left) {
                    validSubs = validSubs->left;
                }
                T temp = current->data;
                current->data = validSubs->data;
                validSubs->data = temp;
                return recursiveRemove(current, current->right, temp);
            }
            delete current;
            return true;
        }
        return recursiveRemove(current, current->left, element) ||
               recursiveRemove(current, current->right, element);
    }

///////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////

template<class T>
Node<T>* BinarySearchTree<T>::recursiveSearch(Node<T> *root, T val) {
	if(root->data == val){
		return root;	
	}else if (root->data > val) {
            if (!root->left) {
                return NULL;
            } else {
                return recursiveSearch(root->left, val);
            }
        } else {
            if (!root->right) {
                return NULL;
            } else {
                return recursiveSearch(root->right, val);
            }
        }
    }

///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template<class T>
Node<T>* BinarySearchTree<T>::recursiveMaximum(Node<T> *root){
	if(!root->right){
		return root;	
	}else{
		return recursiveMaximum(root->right);	
	}
}

///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template<class T>
Node<T>* BinarySearchTree<T>::recursiveMinimum(Node<T> *root){
	if(!root->left){
		return root;	
	}else{
		return recursiveMinimum(root->left);	
	}
}

///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template<class T>
void BinarySearchTree<T>::recursiveOrder(std::vector<T> *vetor,Node<T> *node){
	if(node){
		recursiveOrder(vetor,node->left);
		vetor->push_back(node->data);
		recursiveOrder(vetor,node->right);
	}
}

///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template<class T>
void BinarySearchTree<T>::recursivePreOrder(std::vector<T> *vetor,Node<T> *node){
	if(node){
		vetor->push_back(node->data);
		recursiveOrder(vetor,node->left);
		recursiveOrder(vetor,node->right);
	}
}

///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template<class T>
void BinarySearchTree<T>::recursivePostOrder(std::vector<T> *vetor,Node<T> *node){
	if(node){
		recursiveOrder(vetor,node->left);
		recursiveOrder(vetor,node->right);		
		vetor->push_back(node->data);
	}
}

///////////////////////////////////////////////////////////////////

