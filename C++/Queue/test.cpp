#include "SimpleQueue.hpp"

int main(){
	SimpleQueue<int> queue(10);
	for(int i = 0 ; i < 10;i++){
		queue.enqueue(i);
	}
	for(int i = 0 ; i < 10;i++){
		std::cout << queue.dequeue() << std::endl;
	}
}
