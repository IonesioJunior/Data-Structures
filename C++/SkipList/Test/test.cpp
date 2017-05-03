#define BOOST_TEST_DYN_LINK
#define BOOST_TEST_MODULE SkipListTest
/*
* @author : Ionesio Junior
*/

#include <boost/test/unit_test.hpp>
#include "../Skiplist.hpp"
#include <cstdlib>
#include <vector>
#include <time.h>

struct setUp{
	SkipList<std::string> *skip1 = new SkipList<std::string>(5);
	SkipList<std::string> *skip2 = new SkipList<std::string>(3);
	std::string array[20] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"};
};

BOOST_FIXTURE_TEST_SUITE(SkipListTest,setUp)

BOOST_AUTO_TEST_CASE(testInit){
	BOOST_CHECK(0 == skip1->height());
	BOOST_CHECK(0 == skip1->size());
	std::vector<Node<std::string> *> empty;
	BOOST_CHECK(empty == skip1->toVector());
	BOOST_CHECK(NULL == skip1->search(rand() % 10001));

        BOOST_CHECK(0 == skip2->height());
        BOOST_CHECK(0 == skip2->size());
        BOOST_CHECK(empty == skip2->toVector());
        BOOST_CHECK(NULL == skip2->search(rand() % 1001));
}

BOOST_AUTO_TEST_CASE(testSize){
	BOOST_CHECK(0 == skip1->size());
	BOOST_CHECK(0 == skip2->size());
	

	for(int i = 0 ; i < 20;i++){
		if(i < 10){
			int height = rand() %15 + 1;
			skip1->insert(i,array[i],height);
			skip2->insert(i,array[i],height);
		}else{
			skip2->insert(i,array[i],rand() % 15 +1);
		}
	}
	
	BOOST_CHECK(10 == skip1->size());
	BOOST_CHECK(20 == skip2->size());
	
	//Insert repeated key
	skip1->insert(5,array[3],9);
	BOOST_CHECK(10 == skip1->size());
	
	for(int i = 0; i < 10;i++){
		skip1->remove(i);
		skip2->remove(i);
	}
	
	BOOST_CHECK(0 == skip1->size());
	BOOST_CHECK(10 == skip2->size());
}

BOOST_AUTO_TEST_CASE(testInsert){
	BOOST_CHECK(0 == skip1->size());
	BOOST_CHECK(0 == skip2->size());
	
	//Brute force test
	for(int i = 0 ; i < 10000;i++){
		int height = rand() % 1000;
		if(height == 0){
			height = 1;
		}
		skip1->insert(i,array[rand() % 19 + 1],height);
                skip2->insert(i,array[rand() % 19 + 1],height);
	}
	
	BOOST_CHECK(10000 == skip1->size());
	BOOST_CHECK(10000 == skip2->size());
}

BOOST_AUTO_TEST_CASE(testRemove){
	skip1->remove(rand() % 1001);
	skip2->remove(rand() % 10001);
	
	skip1->insert(20,array[rand() % 20],rand() % 1001);
	skip2->insert(20,array[rand() % 20],rand() % 1001);
	
	BOOST_CHECK(1 == skip1->size());
	BOOST_CHECK(1 == skip1->size());
	
	for(int i = 0 ; i < 20;i++){
		int height = rand() % 100;
		if(height == 0){
			height = 1;
		}
		skip1->insert(i,array[rand() % 20],height);
		skip2->insert(i,array[rand() % 20],height);
	}
	
	for(int i = 0 ; i < 1000;i++){
		skip1->remove(i);
		skip2->remove(i);
	}
	
	BOOST_CHECK(0 == skip1->size());
	BOOST_CHECK(0 == skip2->size());
}

BOOST_AUTO_TEST_CASE(testSearch){
	//Search in empty skip list
	BOOST_CHECK(NULL == skip1->search(rand() % 10000));
	BOOST_CHECK(NULL == skip2->search(rand() % 10000));
		
	for(int i = 0; i < 20;i++){
		int height = rand() % 1000;
		int key = i;
		if(height == 0){
			height = 1;
		}
		skip1->insert(key,array[rand() % 20],height);
		skip2->insert(key,array[rand() % 20],height);
	}

	int foundElements = 0;
	for(int i = 0 ; i < 10001;i++){
		if(skip1->search(i) && skip2->search(i)){
			foundElements++;
		}
	}
	BOOST_CHECK(20 == foundElements);
}

BOOST_AUTO_TEST_CASE(testToVector){
	for(int i = 0 ; i < 20;i++){
		skip1->insert(i,array[i],i + 1);
		skip2->insert(i,array[i],i + 1);
	}
	
	std::vector<Node<std::string> *> result1 = skip1->toVector();
	std::vector<Node<std::string> *> result2 = skip2->toVector();
	
	for(int i = 0; i < result1.size();i++){
		BOOST_CHECK(result1[i]->key == result2[i]->key);
		BOOST_CHECK(result1[i]->data == result2[i]->data);
		BOOST_CHECK(result1[i]->height == result2[i]->height);
		BOOST_CHECK(i == result1[i]->key);
		BOOST_CHECK(i == result2[i]->key);
		BOOST_CHECK(array[i] == result1[i]->data);
		BOOST_CHECK(array[i] == result2[i]->data);
		BOOST_CHECK(i + 1 == result1[i]->height);
		BOOST_CHECK(i + 1 == result2[i]->height);
	}
}

BOOST_AUTO_TEST_CASE(testHeight){
	BOOST_CHECK(0 == skip1->height());
	BOOST_CHECK(0 == skip2->height());
	
	for(int i = 1 ; i < 21;i++){
		skip1->insert(i,array[rand() % 20],i);
		skip2->insert(i,array[rand() % 20],i);
		BOOST_CHECK(i == skip1->height());
		BOOST_CHECK(i == skip2->height());
	}
}
BOOST_AUTO_TEST_SUITE_END()
