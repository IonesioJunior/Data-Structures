import sys,os
sys.path.append('../BST/')
from BST import BinarySearchTree
from Node import Node

class Splay(BinarySearchTree):
		
	def search(self,element):
		if(element != None):
			node = self.getRoot()
			while(not(node.isEmpty())):
				if(node.getData() == element):
					if(node.getData() != self.getRoot().getData()):
						self.__splay(node)
					return node
				elif(node.getData() > element):
					node = node.getLeft()
				else:
					node = node.getRight()
			if node.getParent() != None:
				self.__splay(node.getParent())
		return Node()
	
	
	def insert(self,element):
		if(element != None):
			node = self.getRoot()
			while(not(node.isEmpty())):
				if(node.getData() == element):
					self.__splay(node)
					return
				elif(node.getData() > element):
					node = node.getLeft()
				else:
					node = node.getRight()
			node.setData(element)
			node.setLeft(Node(None,None,None,node))
			node.setRight(Node(None,None,None,node))
			if(node.getParent() != None):
				self.__splay(node)

	def remove(self,element):
		if(element != None):
			foundNode = self.search(element)
			if(foundNode.isEmpty()):
				self.__splay(foundNode.getParent())
				return
			else:
				parent = foundNode.getParent()
				self.recursiveRemove(foundNode)
				if(parent != None):
					self.__splay(parent)
				


	def __splay(self,node):
		if(node == None or node.isEmpty() or node.getData == self.getRoot().getData()):
			return
		parent = node.getParent()
		grandParent = parent.getParent()
		
		while(parent != None):
			if(parent.getData() == self.getRoot().getData()):
				if(parent.getLeft().getData() == node.getData()):
					self.__rightRotation(parent)
				else:
					self.__leftRotation(parent)
				self.setRoot(node)
			else:
				if(grandParent.getRight().getData() == parent.getData()):
					if(parent.getRight().getData() == node.getData()):
						self.__leftRotation(grandParent)
						self.__leftRotation(parent)
					else:
						self.__rightRotation(parent)
						self.__leftRotation(grandParent)
				else:
					if(parent.getLeft().getData() == node.getData()):
						self.__rightRotation(grandParent)
						self.__rightRotation(parent)
					else:
						self.__leftRotation(parent)
						self.__rightRotation(grandParent)
			
			parent = node.getParent()
			if(parent != None):
				grandParent = parent.getParent()
				if(grandParent == None):
					self.setRoot(parent)
			else:
				self.setRoot(node)

	
	def __leftRotation(self,node):
		if(node != None and not(node.isEmpty())):
			parent = node.getParent()
			right = node.getRight()
			
			right.setParent(parent)
			
			if(parent != None):
				if(parent.getRight().getData() == node.getData()):
					parent.setRight(right)
				else:
					parent.setLeft(right)
			node.setRight(right.getLeft())
			if(node.getRight() != None):
				node.getRight().setParent(node)
			right.setLeft(node)
			node.setParent(right)
			
			return right
	
	def __rightRotation(self,node):
		if(node != None and not(node.isEmpty())):
			parent = node.getParent()
			left = node.getLeft()
			
			left.setParent(parent)
			if(parent != None):
				if(parent.getLeft().getData() == node.getData()):
					parent.setLeft(left)
				else:
					parent.setRight(left)
			node.setLeft(left.getRight())
			if(node.getLeft() != None):
				node.getLeft().setParent(node)
			left.setRight(node)
			node.setParent(left)
			
			return left
