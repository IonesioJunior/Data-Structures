#include <exception>

class HeapUnderflowException : public std::exception{
	virtual const char* what() const throw(){
		return "Heap is Empty!!";
	}
};
