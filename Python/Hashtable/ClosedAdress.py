import sys,os
sys.path.append('/Operation/')
'''
	Author: Ionesio Junior
'''
from Hashtable import Hashtable
from Operation import Operation
'''
	Closed Adress Hashtable implementation
'''
class ClosedAdress(Hashtable):
	
	def __init__(self,tableSize,operationMethod):
		Hashtable.__init__(self,tableSize,operationMethod)

	
	def insert(self,element):
		if(self.isFull()):
			raise Exception("HashtableOverflowException")
		else:
			if(element == None):
				return
			else:
				hashKey = self.function.hashFunc(element)
				if(self.table[hashKey] == None):
						self.table[hashKey] = []
				self.table[hashKey].append(element)
				self.elements += 1


	def remove(self,element):
		if(self.isEmpty() or element == None):
			return
		else:
			hashKey = self.function.hashFunc(element)
			if(self.table[hashKey] == None):
				return
			else:
				try:
					self.table[hashKey].remove(element)
					self.elements -= 1
				except:
					return
	
	
	def search(self,element):
		if(element == None):
			return None
		else:
			hashKey = self.function.hashFunc(element)
			if(self.table[hashKey] == None):
				return None
			else:
				try:
					self.table[hashKey].index(element)
				except:
					return None
	

	def indexOf(self,element):
		if(element == None):
			return -1
		else:
			hashKey = self.function.hashFunc(element)
			if(self.table[hashKey] == None):
				return -1
			else:
				try:
					return (hashKey,self.table[hashKey].index(element))
				except:
					return -1
