'''
	Author: Ionésio Junior
'''
#coding:utf-8

'''
	Stack Implementation
'''
class Stack():

	def __init__(self,size = 10):
		self.__stackList = []
		self.__size = size
		self.__top = -1
		
	def push(self,element):
		if(element != None and  not(self.isFull())):
			self.__top = self.__top + 1
			self.__stackList.insert(self.__top,element)
		else:
			raise Exception("Stack is Full!!")
	def pop(self):
		if(not(self.isEmpty())):
			removedElement = self.__stackList[self.__top]
			del self.__stackList[self.__top]
			self.__top = self.__top - 1
			return removedElement
		else:
			raise Exception("Stack is empty!!")
	def top(self):
		if(not(self.isEmpty())):
			return self.__stackList[self.__top]
		else:
			return None
	def isEmpty(self):
		return (self.__top < 0)
	def isFull(self):
		return (self.__size - 1 ==  self.__top)
