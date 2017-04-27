'''
	Author: Ionésio Junior
'''


'''
	Node class implementation used in Iterative Single Linked List implementation
'''
class Node():
	
	def __init__(self,nextNode = None,data = None):
		self.__nextNode = nextNode
		self.__data = data
	
	
	def isEmpty(self):
		return (self.__data == None)
	
	
	def getNext(self):
		return self.__nextNode
	
	def setNext(self, nextNode):
		self.__nextNode = nextNode
	
	def getData(self):
		return self.__data
	
	def setData(self,newData):
		self.__data = newData
