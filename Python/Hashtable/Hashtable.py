import sys,os
sys.path.append('/Method/')
sys.path.append('/Operation/')
'''
	Author: Ionésio Junior
'''
from Methods import *
from Operation import *
#coding: utf-8

'''
	Abstract Hashtable class
'''
class Hashtable():
	table = None
	elements = None
	__maxSize = None
	COLLISIONS = None
	function = None

	def __init__(self,size,operation,method = None):
		self.table = [None] * size
		self.elements = 0
		self.__tableSize = size
	 	self.COLLISIONS = 0
		if(method != None):
			if(method == Method.LINEAR_PROBING):
				self.function = LinearProbing(size,operation)
			elif(method == Method.QUADRATIC_PROBING):
				self.function = QuadraticProbing(size,operation,c1,c2)
		else:
			if(operation == Operation.MULTIPLICATION):
				self.function = Multiplication(self.__tableSize)
			else:
				self.function = Division(self.__tableSize)
		
	def isEmpty(self):
		return self.elements ==  0
		
	def isFull(self):
		return self.elements == self.__tableSize
	
	def capacity(self):
		return self.__tableSize
	
	
	def size(self):
		return self.elements
	
	def getCOLLISIONS(self):
		return self.COLLISIONS	
	
	def insert(self,element):
		raise NotImplementedError("Code not implemented")
		
	def remove(self,element):
		raise NotImplementedError("Code not implemented")
	
	def search(self,element):
		raise NotImplementedError("Code not implemented")
	
	def indexOf(self,element):
		raise NotImplementedError("Code not implemented")
