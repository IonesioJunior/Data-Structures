#coding: utf-8

class RecursiveLinkedList():
	
	def __init__(self,data=None,nextNode=None):
		self.__data = data
		self._nextNode = nextNode
	
	
	def isEmpty(self):
		return (self.__data == None)
	
	
	def size(self):
		if(self.isEmpty()):
			return 0;
		else:
			return 1 + self.__nextNode.size()
	
	
	def search(self,element):
		if(self.isEmpty()):
			return None
		else:
			if(self.__data == element):
				return self.__data
			else:
				return self.__next.search(element)
	
	
	def insert(self,element):
		if(element != None):
			if(self.isEmpty()):
				self.__data = element
				self.__nextNode = RecursiveLinkedList()
			else:
				self.__nextNode.insert(element)
	
	
	def remove(self,element):
		if(not(self.isEmpty())):
			if(self.__data == element):
				self.__data = self.__nextNode.getData()
				self.__nextNode = self.__nextNode.getNext()
			else:
				self.__nextNode.remove(element)
	
	
	def toArray(self):
		result = []
		if(self.size() > 0):
			self.__getList(result)
		return result

	
	def __getList(self,array):
		if(self.__data != None):
			array.append(self.__data)
			self.__nextNode.__getList(array)

	
	def getNext(self):
		return self.__nextNode


	def getData(self):
		return self.__data
	
	
