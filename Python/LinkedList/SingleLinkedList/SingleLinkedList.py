from Node import Node

class SimpleLinkedList():
	
	def __init__(self):
		self.__head = Node()
	
	
	def isEmpty(self):
		return self.__head.isEmpty()
	

	def size(self):
		size = 0
		aux = self.__head
		while(not(self.__head.isEmpty())):
			aux = aux.getNext()
			size = size + 1
		return size

	def search(self,element):
		aux = self.__head
		while(not(aux.isEmpty())):
			if(aux.getData() == element):
				return aux.getData()
			aux = aux.getNext()
		return None

	
	def insert(self,element):
		if(element != None):
			aux = self.__head
			while(not(aux.isEmpty())):
				aux = aux.getNext()
			aux.setData(element)
			aux.setNext(Node())
	
	def remove(self,element):
		if(element != None):
			if(element == self.__head.getData()):
				self.__head = self.__head.getNext()
			else:
				aux = self.__head
				while(not(aux.isEmpty())):
					if(aux.getData() == element):
						aux.setData(aux.getNext().getData())
						aux.setNext(aux.getNext().getNext())
						break;
					aux = aux.getNext()

	
	def toArray(self):
		array = []
		aux = self.__head
		while(not(aux.isEmpty())):
			array.append(aux.getData())
			aux = aux.getNext()
		return array
	
