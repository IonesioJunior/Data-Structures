


class Node(object):
	
	def __init__(self,key,height,data):
		self.__data = data
		self.__key = key
		self.__forward = [None] * height
	
	def height(self):
		return len(self.__forward)
	
	def getValue(self):
		return self.__data
		
	def setValue(self,newValue):
		self.__data = newValue
	
	def getKey(self):
		return self.__key
	
	def setKey(self,newKey):
		self.__key = newKey
	
	def getForward(self):
		return self.__forward
	
	def getForwardNode(self,level):
		return self.__forward[level]
	
	def setForwardNode(self,level,newNode):
		self.__forward[level] = newNode

	def setForward(self,newForward):
		self.__forward = newForward
		
	def showForward(self):
		for i in range(len(self.__forward)):
			print self.__forward[i].getValue()
