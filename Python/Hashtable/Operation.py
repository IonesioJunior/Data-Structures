'''
	Author : Ionésio Junior
'''
from enum import Enum
from math import sqrt
'''
	ENUM for select some method to generate the key
'''
class Operation(Enum):
	MULTIPLICATION = 1
	DIVISION = 2


'''
	Division Method
'''
class Division(object):
	__tableSize = None
	
	def __init__(self,tableSize):
		self.__tableSize = tableSize
	
	
	def hashFunc(self,element):
		hashKey = -1
		key = hash(element)
		hashKey = int(key % self.__tableSize)
		return hashKey

'''
	Multiplication Method
'''
class Multiplication(object):
	__tableSize = None
	__CONSTANT = float(float(sqrt(5) - 1)  / 2)
	def __init__(self,tableSize):
		self.__tableSize = tableSize

	def hashFunc(self,element):
		hasKey = -1
		key = hash(element)
		fractionalPart = key * self.__CONSTANT - int(key *self.__CONSTANT)
		hashKey = int(self.__tableSize * fractionalPart)
		return hashKey
