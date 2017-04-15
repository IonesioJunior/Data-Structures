from DoubleNode import DoubleNode

class DoubleLinkedList():
	
	def __init__(self):
		self.__head = DoubleNode()
		self.__tail = DoubleNode()
		self.__head.setNext(self.__tail)
		self.__tail.setPrevious(self.__head)
	
	
	def isEmpty(self):
		return (self.__head.isEmpty())
	
	
	def size(self):
		size = 0
		aux = self.__head
		while(not(aux.isEmpty())):
			aux = aux.getNext()
			size = size + 1
		return size
	
	
	def search(self, element):
		if(element != None):
			aux = self.__head
			while(not(aux.isEmpty())):
				if(aux.getData() == element):
					return aux.getData()
				aux = aux.getNext()
	
	def insert(self,element):
		if(element != None):
			if(self.isEmpty()):
				self.insertFirst(element)
			else:
				node = DoubleNode(element,self.__tail,DoubleNode())
				self.__tail.setNext(node)
				self.__tail = node
	
	def remove(self,element):
		if(element != None and not(self.isEmpty())):
			if(self.__head.getData() == element):
				self.removeFirst()
			else:
				aux = self.__head
				while(not(aux.isEmpty())):
					if(aux.getData() == element):
						aux.getPrevious().setNext(aux.getNext())
						aux.getNext().setPrevious(aux.getPrevious())
						if(aux.getNext().isEmpty()):
							self.__tail = aux.getPrevious()
						break
					aux = aux.getNext()
	
	
	def toArray(self):
		result = []
		aux = self.__head
		while(not(aux.isEmpty())):
			result.append(aux.getData())
			aux = aux.getNext()
		return result
	
	
	def insertFirst(self,element):
		if(element != None):
			if(self.isEmpty()):
				newHead = DoubleNode(element, DoubleNode(),DoubleNode())
				self.__head = newHead
				self.__tail = newHead
			else:
				newHead = DoubleNode(element,DoubleNode(),self.__head())
				self.__head.setPrevious(newHead)
				self.__head = newHead
	
	
	def removeFirst(self):
		if(self.isEmpty()):
			return
		else:
			self.__head.getNext().setPrevious(DoubleNode())
			if(self.size() == 1):
				self.__tail = self.__head.getNext()
			self.__head = self.__head.getNext()
	
	
	def removeLast(self):
		if(self.isEmpty()):
			self.__tail.getPrevious().setNext(DoubleNode())
			if(self.size() == 1):
				self.__head = self.tail.getPrevious()
			self.__tail = self.tail.getPrevious()
	
		
