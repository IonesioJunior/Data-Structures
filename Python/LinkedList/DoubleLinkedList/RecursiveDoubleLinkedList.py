

class RecursiveDoubleLinkedList():
		
	def __init__(self,data = None,nextNode = None,previous = None):
		self.__data = data
		self.__nextNode = nextNode
		self.__previous = previous
	
	
	def isEmpty(self):
		return (self.__data == None)
	
	
	def size(self):
		if(self.isEmpty()):
			return 0;
		else:
			return 1 + self.__nextNode.size()
	
	
	def search(self,element):
		if(self.isEmpty() or element == None):
			return None
		else:
			if(self.__data == element):
				return self.__data
			else:
				return self.__nextNode.search(element)
	
	
	def insert(self,element):
		if(element != None):
			if(self.isEmpty()):
				self.__data = element
				self.__nextNode = RecursiveDoubleLinkedList()
				self.__nextNode.setPrevious(self)
				if(self.__previous == None):
					self.__previous = RecursiveDoubleLinkedList(None,self,None)
			else:
				self.__nextNode.insert(element)
	
	
	def remove(self,element):
		if(element != None):
			if(not(self.isEmpty())):
				if(self.__data == element):
					self.removeFirst()
				else:
					self.__nextNode.remove(element)
	
	
	def toArray(self):
		result = []	
		if(self.size() > 0):
			result.append(self.__data)
			self.__nextNode.getList(result)
		return result
	
	
	def getList(self,array):
		if(self.__data != None):
			array.append(self.__data)
			self.__nextNode.getList(array)
	
	
	def insertFirst(self,element):
		if(element != None):
			if(self.isEmpty()):
				nextNode = RecursiveDoubleLinkedList()
				self.__data = element
				self.__nextNode = nextNode
				self.__previous = nextNode
			else:
				nextNode = RecursiveDoubleLinkedList(self.__data,self.__nextNode,self)
				self.__nextNode.setPrevious(nextNode) 
				self.__data = element
				self.__nextNode = nextNode
	
	def removeFirst(self):
		if(not(self.isEmpty())):
			if(self.__nextNode.isEmpty()):
				self.__data = None
				self.__nextNode = None
				self.__previous = None
			else:
				self.__data = self.__nextNode.getData()
				self.__nextNode.getNext().setPrevious(self)
				self.__nextNode = self.__nextNode.getNext()
	
	def removeLast(self):
		if(not(self.isEmpty())):
			if(self.__nextNode.isEmpty()):
				self.__data = None
				self.__next = None
				self.__previous = None
			else:
				self.__nextNode.removeLast();

	####################################	AUXILIAR METHODS	###############################################
	
	def getPrevious(self):
		return self.__previous
	def getNext(self):
		return self.__nextNode
	def getData(self):
		return self.__data
	
	
	def setPrevious(self,newPrevious):
		self.__previous = newPrevious
	def setNext(self,newNext):
		self.__nextNode = newNext
	def setData(self,newData):
		self.__data = data

