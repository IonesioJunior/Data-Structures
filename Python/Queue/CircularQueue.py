'''
	Author: Ionésio Junior
'''
#coding:utf -8
'''
	Circular Queue Implementation
'''
class CircularQueue():
	
	def __init__(self,size = 10):
		self.__queueArray = []
		self.__tail = -1
		self.__head = -1
		self.__elements = 0
		self.__size = size
	
	
	def enqueue(self,element):
		if(self.isFull()):
			raise Exception("Queue is Full!!")
		else:
			if(element != None):
				self.__elements = self.__elements + 1
				if(self.__head == -1 and self.__tail == -1):
					self.__head = (self.__head + 1) % self.__size
				
				self.__tail = (self.__tail + 1) % self.__size
				self.__queueArray.insert(self.__tail,element)	
			
				
	def dequeue(self):
		if(self.isEmpty()):
			raise Exception("Queue is Empty!!")
		else:
			self.__elements = self.__elements - 1
			removedElement = self.__queueArray[self.__head]
			self.__head = (self.__head + 1) % self.__size
			return removedElement
		

	def head(self):
		if(self.isEmpty()):
			return None
		else:
			return self.__queueArray[self.__head]
	
	
	def isEmpty(self):
		return (self.__elements == 0)
	
	
	def isFull(self):
		return (self.__elements == self.__size)

