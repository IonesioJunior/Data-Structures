'''
	Author: Ionésio Junior
'''

'''
	Node class implementation used Binary Search Tree/AVL/Splay
'''
class Node():
	
	def __init__(self,data=None,left=None,right=None,parent=None):
		self.__data=data
		self.__left = left
		self.__right = right
		self.__parent = parent
	
	
	def isEmpty(self):
		return (self.__data == None)
	
	
	def isLeaf(self):
		return (self.__left.isEmpty() and self.__right.isEmpty())
	
	
	def getData(self):
		return self.__data
	def getLeft(self):
		return self.__left
	def getRight(self):
		return self.__right
	def getParent(self):
		return self.__parent


	def setData(self,newData):
		self.__data = newData
	def setLeft(self,newLeft):
		self.__left = newLeft
	def setRight(self,newRight):
		self.__right = newRight
	def setParent(self,newParent):
		self.__parent = newParent

		
	
