'''
	Author: Ionésio Junior
'''
#coding: utf-8
'''
	Simple Queue implementation
'''
class SimpleQueue():
	
	def __init__(self,size = 10):
		self.__queueList = []
		self.__size = size
		self.__tail = -1
	
	def enqueue(self,element):
		if(not(self.isFull())):
			self.__tail = self.__tail + 1
			self.__queueList.insert(self.__tail,element)
		else:
			raise Exception("Queue is Full!!")
	
	def dequeue(self):
		if(not(self.isEmpty())):
			removedElement = self.__queueList[0]
			self.__shiftLeft()
			self.__tail = self.__tail - 1
			return removedElement
		else:
			raise Exception("Queue is Empty!!")
	
	def __shiftLeft(self):
		for i in range(self.__tail):
			self.__queueList[i] = self.__queueList[i+1]
	def head(self):
		if(not(self.isEmpty())):
			return self.__queueList[0]
		else:
			return None
	
	def isEmpty(self):
		return (self.__tail < 0)
	
	def isFull(self):
		return (self.__tail == self.__size - 1)
