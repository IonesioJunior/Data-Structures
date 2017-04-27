'''
	Author: Ionésio Junior
'''
import sys,os
sys.path.append('../BST/')
from BST import BinarySearchTree
from Node import Node

'''
	AVL class Implementation
'''
class AVL(BinarySearchTree):
	
	def __calculateBalance(self,node):
		if(node.isEmpty()):
			return 0;
		else:
			return self.recursiveHeight(node.getLeft()) - self.recursiveHeight(node.getRight())
	

	def __rebalance(self,node):
		balance = self.__calculateBalance(node)
		balanceLeft = self.__calculateBalance(node.getLeft())
		balanceRight = self.__calculateBalance(node.getRight())
	
		if(balance  > 1):
			if(balanceLeft < 0):
				self.__leftRotation(node.getLeft())
			self.__rightRotation(node)
		elif(balance < -1):
			if(balanceRight > 0):
				self.__rightRotation(node.getRight())
			self.__leftRotation(node)
	

	def recursiveInsert(self,element,node,parent):
		if(node.isEmpty()):
			node.setData(element)
			node.setLeft(Node())
			node.setRight(Node())
			node.setParent(parent)
		else:
			nodeData = node.getData()
			if(nodeData == element):
				return;
			elif(nodeData > element):
				self.recursiveInsert(element,node.getLeft(),node)
			else:
				self.recursiveInsert(element,node.getRight(),node)
		self.__rebalance(node)


	def recursiveRemove(self,node):
		if(not(node.isEmpty())):
			if(node.isLeaf()):
				node.setData(None)
				self.__rebalanceUp(node)
			else:
				if(not(node.getRight.isEmpty())):
					minNode = self.recursiveMinimum(node.getRight())
					node.setData(minNode.getData())
					self.recursiveRemove(minNode)
				else:
					maxNode = self.recursiveMinimum(node.getLeft())
					node.setData(maxNode.getData())
					self.recursiveRemove(maxNode)
			self.__rebalanceUp(node)


	def __rebalanceUp(self,node):
		parent = node.getParent()
		while(parent != None):
			self.__rebalance(parent)
			parent = parent.getParent()	



	def __leftRotation(self,node):
		newRoot = self.__leftRotationNodes(node)
		if(node.getData() == self.getRoot().getData()):
			self.setRoot(newRoot)

	
	def __rightRotation(self,node):
		newRoot = self.__rightRotationNodes(node)
		if(node.getData() == self.getRoot().getData()):
			self.setRoot(newRoot)


	def __leftRotationNodes(self,node):
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

	
	def __rightRotationNodes(self,node):
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
