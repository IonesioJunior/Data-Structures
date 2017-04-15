#coding: utf-8

class BinaryHeap():
	
	def __init__(self):
		self.__heapArray = []
		self.__index = -1
	
	def insert(self,element):
		if(element != None):
			self.__index = self.__index + 1
			self.__heapArray.insert(self.__index,element)
			i = self.__index
			while(i > 0 and self.__heapArray[self.__parent(i)] < self.__heapArray[i]):
				aux = self.__heapArray[i]
				self.__heapArray[i] = self.__heapArray[self.__parent(i)]
				self.__heapArray[self.__parent(i)] = aux
				i = self.__parent(i)
	
	def extractRoot(self):
		if(self.isEmpty()):
			raise Exception("Heap is Empty!!")
		else:
			removedElement = self.__heapArray[0]
			self.__heapArray[0] = self.__heapArray[self.__index]
			self.__index = self.__index - 1
			self.__heapify(self.__heapArray,0)
			return removedElement
	
	
	def rootElement(self):
		if(self.isEmpty()):
			return None
		else:
			return self.__heapArray[0]
	
	
	def heapSort(self,array):
		self.buildHeap(array)
		for i in range(self.__index,0,-1):
			aux = self.__heapArray[0]
			self.__heapArray[0] = self.__heapArray[i]
			self.__heapArray[i] = aux
			self.__index = self.__index - 1
			self.__heapify(self.__heapArray,0)
		return self.__heapArray
	
	
	def buildHeap(self,array):
		self.__heapArray = []
		self.__index = -1
		for i in range(len(array)):
			self.insert(array[i])
	
	
	def toArray(self):
		return self.__heapArray
	
	
	def size(self):
		return self.__index + 1
	
	def isEmpty(self):
		return (self.__index  < 0)	

	############################	AUXILIAR METHODS	########################################################
	
	
	def __parent(self,index):
		return index / 2
	
	def __left(self,index):
		return (2 * index)
	
	def __right(self,index):
		return (2 * index) + 1
	
	
	def __heapify(self,array,index):
		left = self.__left(index)
		right = self.__right(index)
		largest = 0
		if(left <= self.__index and self.__heapArray[left] > self.__heapArray[index]):
			largest = left
		else:
			largest = index
		if(right <= self.__index and self.__heapArray[right] > self.__heapArray[largest]):
			largest = right
	
		if(largest != index):
			aux = self.__heapArray[index]
			self.__heapArray[index] = self.__heapArray[largest]
			self.__heapArray[largest] = aux
			self.__heapify(self.__heapArray,largest)

