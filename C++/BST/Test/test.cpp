#define BOOST_TEST_DYN_LINK
#define BOOST_TEST_MODULE BSTTest
/*
* @author : Ionesio Junior
*/

#include <boost/test/unit_test.hpp>
#include "../BinarySearchTree.hpp"
#include <cstdlib>
#include <iostream>
#include <time.h>
#include <vector>


struct setUp{
	BinarySearchTree<int> *bst = new BinarySearchTree<int>();
};

BOOST_FIXTURE_TEST_SUITE(BSTTest,setUp)

BOOST_AUTO_TEST_CASE(testInit){
	BOOST_CHECK(0 == bst->size());
	BOOST_CHECK(-1 == bst->height());
	BOOST_CHECK(NULL == bst->getRoot());
}

BOOST_AUTO_TEST_CASE(testSearch){
	//Test search in empty tree
	for(int i = 0 ; i < 100;i++){
		BOOST_CHECK(NULL == bst->search(rand() % 1000));
	}

	bst->insert(10);
	BOOST_CHECK(10 == bst->search(10)->data);
	BOOST_CHECK(NULL == bst->search(15));
	bst->insert(9);
	bst->insert(15);
	bst->insert(30);
	bst->insert(7);
	
	BOOST_CHECK(9 == bst->search(9)->data);
	BOOST_CHECK(15 == bst->search(15)->data);
	BOOST_CHECK(30 == bst->search(30)->data);
	BOOST_CHECK(7 == bst->search(7)->data);
	BOOST_CHECK(NULL == bst->search((rand() % 1000) + 31));
}
	
BOOST_AUTO_TEST_CASE(testInsert){
	BOOST_CHECK(0 == bst->size());
	BOOST_CHECK(-1 == bst->height());
	BOOST_CHECK(NULL == bst->getRoot());
	bst->insert(10);
	BOOST_CHECK(10 == bst->getRoot()->data);
	BOOST_CHECK(0 == bst->height());
	BOOST_CHECK(1 == bst->size());
	bst->insert(20);
	bst->insert(30);
	bst->insert(5);
	bst->insert(7);
	bst->insert(3);
	bst->insert(15);
	bst->insert(25);
	
	BOOST_CHECK(10 == bst->getRoot()->data);
	BOOST_CHECK(20 == bst->getRoot()->right->data);
	BOOST_CHECK(5 == bst->getRoot()->left->data);
	BOOST_CHECK(30 == bst->getRoot()->right->right->data);
	BOOST_CHECK(7 == bst->getRoot()->left->right->data);
	BOOST_CHECK(3 == bst->getRoot()->left->left->data);
	BOOST_CHECK(15 == bst->getRoot()->right->left->data);
	BOOST_CHECK(25 == bst->getRoot()->right->right->left->data);
	
	//Test Leaf
	BOOST_CHECK(NULL == bst->getRoot()->left->left->left);
	BOOST_CHECK(NULL == bst->getRoot()->left->left->right);
	BOOST_CHECK(NULL == bst->getRoot()->left->right->left);
	BOOST_CHECK(NULL == bst->getRoot()->left->right->right);
	BOOST_CHECK(NULL == bst->getRoot()->right->left->left);
	BOOST_CHECK(NULL == bst->getRoot()->right->left->right);
	BOOST_CHECK(NULL == bst->getRoot()->right->right->right);
	BOOST_CHECK(NULL == bst->getRoot()->right->right->left->left);
	BOOST_CHECK(NULL == bst->getRoot()->right->right->left->right);

	//Test insert repeated element
	BOOST_CHECK(8 == bst->size());
	bst->insert(25);
	bst->insert(30);
	bst->insert(15);
	bst->insert(3);
	bst->insert(5);
	bst->insert(20);
	bst->insert(7);
	bst->insert(10);
	BOOST_CHECK(8 == bst->size());
}

BOOST_AUTO_TEST_CASE(testRemove){
        bst->insert(10);
        bst->insert(20);
        bst->insert(30);
        bst->insert(5);
        bst->insert(7);
        bst->insert(3);
        bst->insert(15);
        bst->insert(25);
	BOOST_CHECK(8 == bst->size());
	BOOST_CHECK(3 == bst->height());

	bst->remove(10);
	BOOST_CHECK(15 == bst->getRoot()->data);
	BOOST_CHECK(7 == bst->size());
	BOOST_CHECK(3 == bst->height());
	BOOST_CHECK(NULL == bst->search(10));
        bst->remove(30);
	BOOST_CHECK(NULL == bst->sucessor(25));
	BOOST_CHECK(25 == bst->maximum()->data);
	BOOST_CHECK(NULL == bst->search(30));
	BOOST_CHECK(2 == bst->height());
	BOOST_CHECK(6 == bst->size());
	bst->remove(5);
	BOOST_CHECK(7 == bst->sucessor(3)->data);
	BOOST_CHECK(NULL == bst->search(5));
	BOOST_CHECK(2 == bst->height());
	BOOST_CHECK(5 == bst->size());
	bst->remove(20);
	BOOST_CHECK(NULL == bst->search(20));
	BOOST_CHECK(25 == bst->sucessor(15)->data);
	BOOST_CHECK(25 == bst->getRoot()->right->data);
	BOOST_CHECK(2 == bst->height());
	BOOST_CHECK(4 == bst->size());
	bst->remove(15);
	BOOST_CHECK(25 == bst->getRoot()->data);
	BOOST_CHECK(2 == bst->height());
	BOOST_CHECK(7 == bst->predecessor(25)->data);
	BOOST_CHECK(25 == bst->sucessor(7)->data);
	BOOST_CHECK(NULL == bst->search(15));
	BOOST_CHECK(3 == bst->size());
	bst->remove(7);
	BOOST_CHECK(NULL == bst->search(7));
	BOOST_CHECK(3 == bst->predecessor(25)->data);
	BOOST_CHECK(25 == bst->sucessor(3)->data);
	BOOST_CHECK(1 == bst->height());
	BOOST_CHECK(2 == bst->size());
	bst->remove(25);
	BOOST_CHECK(3 == bst->getRoot()->data);
	BOOST_CHECK(0 == bst->height());
	BOOST_CHECK(1 == bst->size());	
	BOOST_CHECK(3 == bst->minimum()->data);
	BOOST_CHECK(3 == bst->maximum()->data);
	BOOST_CHECK(NULL == bst->predecessor(3));
	BOOST_CHECK(NULL == bst->sucessor(3));
	
	bst->remove(3);
	BOOST_CHECK(0 == bst->size());
	BOOST_CHECK(-1 == bst->height());
	BOOST_CHECK(NULL == bst->getRoot());
}

BOOST_AUTO_TEST_CASE(testMaximum){
	BOOST_CHECK(NULL == bst->maximum());
	
	for(int i = 0 ;   i < 1000;i++){
		bst->insert(i);
		BOOST_CHECK(i == bst->maximum()->data);
	}	
	for(int i = 999;i >= 0;i--){
		BOOST_CHECK(i == bst->maximum()->data);
		bst->remove(i);
	}
}

BOOST_AUTO_TEST_CASE(testMinimum){
	BOOST_CHECK(NULL == bst->minimum());
	
	for(int i = 999;i >= 0;i--){
		bst->insert(i);
		BOOST_CHECK(i == bst->minimum()->data);
	}
	
	for(int i = 0; i < 1000;i++){
		BOOST_CHECK(i == bst->minimum()->data);
		bst->remove(i);
	}
}

BOOST_AUTO_TEST_CASE(testSucessorPredecessor){
        bst->insert(10);
        bst->insert(20);
        bst->insert(30);
        bst->insert(5);
        bst->insert(7);
        bst->insert(3);
        bst->insert(15);
        bst->insert(25);
	
	BOOST_CHECK(NULL == bst->predecessor(3));
	BOOST_CHECK(5 == bst->sucessor(3)->data);
	BOOST_CHECK(3 == bst->predecessor(5)->data);
	BOOST_CHECK(7 == bst->sucessor(5)->data);
	BOOST_CHECK(5 == bst->predecessor(7)->data);
	BOOST_CHECK(10 == bst->sucessor(7)->data);
	BOOST_CHECK(7 == bst->predecessor(10)->data);
	BOOST_CHECK(15 == bst->sucessor(10)->data);
	BOOST_CHECK(10 == bst->predecessor(15)->data);
	BOOST_CHECK(20 == bst->sucessor(15)->data);
	BOOST_CHECK(15 == bst->predecessor(20)->data);
	BOOST_CHECK(25 == bst->sucessor(20)->data);
	BOOST_CHECK(20 == bst->predecessor(25)->data);
	BOOST_CHECK(30 == bst->sucessor(25)->data);
	BOOST_CHECK(25 == bst->predecessor(30)->data);
	BOOST_CHECK(NULL == bst->sucessor(30));
}

BOOST_AUTO_TEST_CASE(testHeight){
        BOOST_CHECK(-1 == bst->height());
	bst->insert(10);
 	BOOST_CHECK(0 == bst->height());
        bst->insert(20);
	BOOST_CHECK(1 == bst->height());
        bst->insert(30);
	BOOST_CHECK(2 == bst->height());
        bst->insert(5);
	BOOST_CHECK(2 == bst->height());
        bst->insert(7);
	BOOST_CHECK(2 == bst->height());
        bst->insert(3);
	BOOST_CHECK(2 == bst->height());
        bst->insert(15);
	BOOST_CHECK(2 == bst->height());
        bst->insert(25);
	BOOST_CHECK(3 == bst->height());
}

BOOST_AUTO_TEST_CASE(testToVectorOrder){
	bst->insert(10);
        bst->insert(20);
        bst->insert(30);
        bst->insert(5);
        bst->insert(7);
        bst->insert(100);
        bst->insert(3);
        bst->insert(15);
        bst->insert(25);
	
	int resultArray[9] = {3,5,7,10,15,20,25,30,100};
	std::vector<int> result(resultArray,resultArray + 9);
	BOOST_CHECK(result == bst->order());
}

BOOST_AUTO_TEST_CASE(testToVectorPreOrder){
        bst->insert(10);
        bst->insert(20);
        bst->insert(30);
        bst->insert(5);
        bst->insert(7);
        bst->insert(100);
        bst->insert(3);
        bst->insert(15);
        bst->insert(25);
	int resultArray[9] = {10,5,3,7,20,15,30,25,100};
	std::vector<int> result(resultArray,resultArray + 9);
	BOOST_CHECK(result == bst->preOrder());
}

BOOST_AUTO_TEST_CASE(testToVectorPostOrder){
        bst->insert(10);
        bst->insert(20);
        bst->insert(30);
        bst->insert(5);
        bst->insert(7);
        bst->insert(100);
        bst->insert(3);
        bst->insert(15);
        bst->insert(25);
	int resultArray[9] = {3,7,5,15,25,100,30,20,10};
	std::vector<int> result(resultArray,resultArray + 9);
	BOOST_CHECK(result == bst->postOrder());
}

BOOST_AUTO_TEST_SUITE_END()
