#coding : utf-8
import sys,os
sys.path.append('../Operation/')
'''
	Author: Ionesio Junior
'''
from enum import Enum
from Operation import *
'''
	ENUM for select method to select key with prob in Open Adress Hastable implementation
'''
class Method(Enum):
	LINEAR_PROBING = 1
	QUADRATIC_PROBING = 2


'''
	Linear Method
'''
class LinearProbing(object):
	__tableSize = None
	__operation = None
	
	def __init__(self,tableSize,method):
		self.__tableSize = tableSize
		if(method == Operation.MULTIPLICATION):
			self.__operation = Multiplication(self.__tableSize)
		else:
			self.__operation  = Division(self.__tableSize)

	def hashMethod(self,element,prob):
		return ((self.__operation.hashFunc(element) + prob) % self.__tableSize)

'''
	Quadratic Method
'''
class QuadraticProbing(object):
	__tableSize = None
	__c1 = None
	__c2 = None
	__operation = None
	
	def __init__(self,tableSize,method,c1,c2):
		self.__tableSize = tableSize
		self.__c1 = c1
		self.__c2 = c2
		if(method == Operation.MULTIPLICATION):
			self.__operation = Multiplication(self.__tableSize)
		else:
			self.__operation = Division(self.__tableSize)


	def hashMethod(self,element,prob):
		generatedIndex = self.__operation.hashFunc(element)
		return ((generatedIndex + self.__c1 * prob + self.__c2 * prob * prob) % self.__tableSize)
