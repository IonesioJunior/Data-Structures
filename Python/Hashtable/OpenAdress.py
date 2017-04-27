'''
 Author: Ionésio Junior
'''
#coding : utf-8
from Hashtable import Hashtable
from Methods import *

'''
	Open Adress Hastable implementation
'''
class OpenAdress(Hashtable):
	
	def __init__(self,size,probingMethod,operationMethod):
		Hashtable.__init__(self,size,operationMethod,probingMethod)


	def insert(self,element):
		if(element == None  or self.indexOf(element) != -1):
			return;
		elif(self.isFull()):
			raise Exception("HashtableOverflow!!!")
		else:
			probe = 0
			index = self.getIndex(element,probe)
			if(self.isNone(index)):
				self.table[index] = element
				self.elements += 1
			else:
				print "Colisao em " , element
				while(not(self.isNone(index)) and probe < self.capacity()):
					probe += 1
					index = self.getIndex(element,probe)
					self.COLLISIONS += 1
				if(self.isNone(index)):
					self.table[index] = element
					self.elements += 1

	def isNone(self,index):
		return self.table[index] == None
	
	def getIndex(self,element,probe):
		return self.function.hashMethod(element,probe)
	
	def search(self,element):
		if(element != None):
			index = self.indexOf(element)
			if(index == -1):
				return None
			else:
				return self.table[index]
	
	def remove(self,element):
		if(element != None):
			index = self.indexOf(element)
			if(index == -1):
				return
			else:
				self.table[index] = None
				self.elements -= 1
	
	def indexOf(self,element):
		if(element == None):
			return -1
		else:
			prob = 0
			index = self.getIndex(element,prob)
			if(self.isNone(index)):
				return -1;
			else:
				if(self.table[index] == element):
					return index;
				else:
					prob = 1
					index = self.getIndex(element,prob)
					while(not(self.isNone(index)) and prob < self.capacity()):
						if(self.table[index] == element):
							return index
						else:
							prob += 1
							index = self.getIndex(element,prob)
					return -1
