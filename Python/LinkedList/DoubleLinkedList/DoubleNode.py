

class DoubleNode():
	
	def __init__(self,data=None,previous=None,nextNode=None):
		self.__data = data
		self.__previous = previous
		self.__nextNode = nextNode
	
	
	def isEmpty(self):
		return (self.__data == None)
	
	
	def getData(self):
		return self.__data
	
	
	def setData(self,newData):
		self.__data = newData

	
	def getNext(self):
		return self.__nextNode
	
	
	def setNext(self,newNext):
		self.__nextNode = newNext
	
	
	def getPrevious(self):
		return self.__previous
	
	
	def setPrevious(self,newPrevious):
		self.__previous = newPrevious
	
	
		
		
