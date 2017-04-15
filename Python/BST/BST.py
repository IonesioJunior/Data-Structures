from Node import Node

class BinarySearchTree():
	
	def __init__(self):
		self.__root = Node();
	
	
	def isEmpty(self):
		return self.__root.isEmpty()

	
	
	def insert(self,element):
		if(element != None):
			self.__recursiveInsert(element,self.__root,self.__root.getParent())
		
	def __recursiveInsert(self,data,node,parent):
		if(not(node.isEmpty())):
			if(node.getData() == data):
				return
			if(node.getData() > data):
				self.__recursiveInsert(data,node.getLeft(),node)
			else:
				self.__recursiveInsert(data,node.getRight(),node)
		else:
			node.setData(data)
			node.setRight(Node())
			node.setLeft(Node())
			node.setParent(parent)
	
	def remove(self,element):
		foundNode = self.search(element)
		if(not(foundNode.isEmpty())):
			self.__recursiveRemove(foundNode)
	
	def __recursiveRemove(self,node):
		if(node.isLeaf()):
			node.setData(None)
			node.setLeft(None)
			node.setRight(None)
		elif(node.getLeft().isEmpty()):
			node.setData(node.getRight().getData())
			node.getRight().getRight().setParent(node)
			node.getRight().getLeft().setParent(node)
			node.setLeft(node.getRight().getLeft())
			node.setRight(node.getRight().getRight())
		elif(node.getRight().isEmpty()):
			node.setData(node.getLeft().getData())
			node.getLeft().getRight().setParent(node)
			node.getLeft().getLeft().setParent(node)
			node.setLeft(node.getLeft().getLeft())
			node.setRight(node.getLeft().getRight())
		else:
			removedValue = node.getData()
			sucessor = self.sucessor(removedValue)
			node.setData(sucessor.getData())
			sucessor.setData(removedValue)
			recursiveRemove(sucessor)
	
	def search(self,element):
		if(element == None or self.__root.isEmpty()):
			return Node()
		else:
			return self.__recursiveSearch(element,self.__root)
	
	def __recursiveSearch(self,element,node):
		if(not(node.isEmpty())):
			if(node.getData() == element):
				return node
			elif(node.getData() > element):
				return self.__recursiveSearch(element,node.getLeft())
			else:
				return self.__recursiveSearch(element,node.getRight())
		else:
			return node

	def height(self):
		return self.__recursiveHeight(self.__root) - 1
	
	def __recursiveHeight(self,node):
		if(not(node.isEmpty())):
			leftHeight = self.__recursiveHeight(node.getLeft())
			rightHeight = self.__recursiveHeight(node.getRight())
			if(leftHeight > rightHeight):
				return leftHeight + 1
			else:
				return rightHeight + 1
		else:
			return 0
	
	def size(self):
		return self.__recursiveSize(self.__root)
	
	def __recursiveSize(self,node):
		if(not(node.isEmpty())):
			return 1 + self.__recursiveSize(node.getLeft()) + self.__recursiveSize(node.getRight())
		else:
			return 0
	
	
	def maximum(self):
		if(self.size() == 0):
			return None
		else:
			return self.__recursiveMaximum(self.__root)
	
	def __recursiveMaximum(self,node):
		if(not(node.getRight().isEmpty())):
			return self.__recursiveMaximum(node.getRight())
		else:
			return node
	
	def minimum(self):
		if(self.size() == 0):
			return None
		else:
			return self.__recursiveMinimum(self.__root)
	
	def __recursiveMinimum(self,node):
		if(not(node.getLeft().isEmpty())):
			return self.__recursiveMinimum(node.getLeft())
		else:
			return node
	
	
	def predecessor(self,element):
		foundNode = self.search(element)
		if(foundNode.isEmpty()):
			return None
		elif(not(foundNode.getLeft().isEmpty())):
			return self.__recursiveMaximum(foundNode.getLeft())
		else:
			parent = foundNode.getParent()
			while(parent != None and not(foundNode.getData() == parent.getRight().getData())):
				parent = parent.getParent()
				foundNode = foundNode.getParent()
			return parent
	
	def sucessor(self,element):
		foundNode = self.search(element)
		if(foundNode.isEmpty()):
			return None
		elif(not(foundNode.getRight().isEmpty())):
			return self.__recursiveMinimum(foundNode.getRight())
		else:
			parent = foundNode.getParent()
			while(parent != None and not(foundNode.getData() == parent.getLeft().getData())):
				parent = parent.getParent()
				foundNode = foundNode.getParent()
			return parent
	def getRoot(self):
		return self.__root
	
	def toArrayPreOrder(self):
		result = []
		self.__recursivePreOrder(result,self.__root)
		return result
	def __recursivePreOrder(self,array,node):
		if(not(node.isEmpty())):
			array.append(node.getData())
			self.__recursivePreOrder(node.getLeft(),array)
			self.__recursivePreOrder(node.getRight(),array)
			
	
	def toArrayOrder(self):
		result = []
		self.__recursiveOrder(result,self.__root)
		return result
	def __recursiveOrder(self,array,node):
		if(not(node.isEmpty())):
			self.__recursiveOrder(node.getLeft(),array)
			array.append(node.getData())
			self.__recursiveOrder(node.getRight(),array)
	
	
	
	def toArrayPostOrder(self):
		result = []
		self.__recursivePostOrder(result,self.__root)
		return result
	def __recursivePostOrder(self,array,node):
		if(not(node.isEmpty())):
			self.__recursivePostOrder(node.getLeft(),array)
			self.__recursivePostOrder(node.getRight(),array)
			array.append(node.getData())
	
	def __setRoot(self,newRoot):
		self.__root = newRoot
			
