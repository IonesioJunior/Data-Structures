#ifndef _BST_H_
#define _BST_H_

/*
*@author Ionésio Junior
*/

#include <iostream>
#include <math.h>
#include <vector>

//BST Node implementation
template <class T>
struct Node {
    T data;
    Node *left;
    Node *right;
    Node *parent;

    Node(T val,Node<T> *parent) {
        this->data = val;
	this->parent = parent;
	this->left = NULL;
	this->right = NULL;
    }

    Node(T val){
	this->data = val;
    	this->left = NULL;
	this->right = NULL;
	this->parent = NULL;
    }

    Node(T val, Node<T> left, Node<T> right) {
        this->data = val;
        this->left = left;
        this->right = right;
	this->parent = NULL;
    }
	
    Node(){
 	this->left = NULL;
	this->right = NULL;
	this->parent = NULL;	
    };
};

//BST Class Implementation
//BinarySearchTree.hpp
template <class T>
class BinarySearchTree {

    private:
    	Node<T> *root;
    	void recursiveInsert(Node<T> *root, T val);
    	int recursiveSize(Node<T> *root);
    	int recursiveHeight(Node<T> *root);
	bool recursiveRemove(Node<T>* parent, Node<T>* current, T element);
    	Node<T>* recursiveSearch(Node<T> *root,T val);
	Node<T>* recursiveMaximum(Node<T> *root);
	Node<T>* recursiveMinimum(Node<T> *root);
	void recursiveOrder(std::vector<T> *vector,Node<T> *node);
	void recursivePreOrder(std::vector<T> *vector,Node<T> *node);
	void recursivePostOrder(std::vector<T> *vector,Node<T> *node);
    

    public:
	BinarySearchTree();
	void insert(T val);
	bool remove(T element);		
	int size();    
    	int height();
	Node<T> *search(T element);
	Node<T> *maximum();
	Node<T> *minimum();
	Node<T> *getRoot();
	void setRoot(Node<T> *newRoot);
	Node<T> *predecessor(T element);
	Node<T> *sucessor(T element);
   	std::vector<T> order();
	std::vector<T> preOrder();
	std::vector<T> postOrder();

    
};


//BinarySearchTree.cpp
/////////////////////////////////////////	PUBLIC METHODS		///////////////////////////////////////////////////////////////////////

template<class T>
BinarySearchTree<T>::BinarySearchTree(){
	this->root = NULL;
}
/*
* Insert and element in leaf correct position
* @param value
*/
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

/*
* Return how many elements have in tree
* @return size of tree
*/
///////////////////////////////////////////////

template<class T>
int BinarySearchTree<T>::size() {
        return this->recursiveSize(root);
}

//////////////////////////////////////////////

/*
* Return max height of tree
* @return maxHeight
*/
//////////////////////////////////////////////

template<class T>
int BinarySearchTree<T>::height() {
        return this->recursiveHeight(this->root);
}

/////////////////////////////////////////////

/*
* Remove and element of the tree
* @param element to be removed
*/
/////////////////////////////////////////////

template<class T>
bool BinarySearchTree<T>::remove(T element) {
        return this->recursiveRemove(NULL, this->root, element);
}

/////////////////////////////////////////////

/*
* Search an specific element in the tree
* @param value to be searched
* @return FoundNodePointer / NULL
*/
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

/*
* Return maximum element value in the three, or NULL if tree is empty
* @return MaxNodePointer / NULL
*/
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

/* Return minimum element value in the tree,or NULL if tree is empty
*  @return MinNodePointer / NULL
*/
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


//////////////////////////////////////////////

template<class T>
void BinarySearchTree<T>::setRoot(Node<T> *newNode){
	this->root = newNode;	
}

//////////////////////////////////////////////

//////////////////////////////////////////////////

template<class T>
Node<T> *BinarySearchTree<T>::getRoot(){
	return this->root;
}

///////////////////////////////////////////////////

/* Return a predecessor of element in tree,or NULL.
* @param element 
* @return NodePointer of predecessor
*/
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
		while(parentNode){
			if(parentNode->right && foundNode->data == parentNode->right->data){
			
				break;
			}else{
				parentNode = parentNode->parent;
				foundNode = foundNode->parent;
			}
		}
		return parentNode;	
	}
}

////////////////////////////////////////////////////

/*
* Return a sucessor of element in tree,or NULL.
* @param elment
* @return NodePointer of sucessor
*/
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
		while(parentNode){
			if(parentNode->left && parentNode->left->data == foundNode->data){
					break;
			}else{
				parentNode = parentNode->parent;
				foundNode = foundNode->parent;
			}
		}
		return parentNode;
	}
}

////////////////////////////////////////////////////

/*
* Return an vector with BST elements in order (left,node,right)
* Complexity : O(n)
* @return vector with elements
*/
////////////////////////////////////////////////////

template<class T>
std::vector<T> BinarySearchTree<T>::order(){
	std::vector<T> result;
	this->recursiveOrder(&result,root);
	return result;
}

////////////////////////////////////////////////////

/*
* Return an vector with BST elements in pre-order(node,left,right)
* Complexity : O(n)
* @return vector with elements
*/
////////////////////////////////////////////////////

template<class T>
std::vector<T> BinarySearchTree<T>::preOrder(){
	std::vector<T> result;
	this->recursivePreOrder(&result,root);
	return result;
}

////////////////////////////////////////////////////

/*
* Return an vector with BST elements in post-Order (left,right,node)
* Complexity : O(n)
* @return vector with elements
*/
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
        } else if(node->data < val){
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
		return -1;
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
                    	if(temp) temp->parent = parent;
		    } else {
                        parent->right = temp;
                  	if(temp) temp->parent = parent;
		    }
                } else {
                    this->root = temp;
		    if(temp) temp->parent = NULL;
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
		recursivePreOrder(vetor,node->left);
		recursivePreOrder(vetor,node->right);
	}
}

///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////

template<class T>
void BinarySearchTree<T>::recursivePostOrder(std::vector<T> *vetor,Node<T> *node){
	if(node){
		recursivePostOrder(vetor,node->left);
		recursivePostOrder(vetor,node->right);		
		vetor->push_back(node->data);
	}
}

///////////////////////////////////////////////////////////////////
#endif
