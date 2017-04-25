#include "IterativeDoubleLinkedList.hpp"


int main(){
	LinkedList<int> *linked = new DoubleLinkedList<int>();
	for(int i = 0 ; i < 30;i++){
		linked->insert(i);
	}
	std::vector<int> result = linked->toVector();
	for(int i = 0 ; i < result.size();i++){
		std::cout << result[i] << " ";
	}
	std::cout << std::endl;
}
