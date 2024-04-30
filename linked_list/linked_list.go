package linkedlist

import (
	"fmt"
)

type LinkedList[T comparable] struct {
	head *Node[T]
	tail *Node[T]
	size int
}

func NewLinkedList[T comparable]() *LinkedList[T] {
	return &LinkedList[T]{}
}

func (l *LinkedList[T]) Head() *Node[T] {
	return l.head
}

func (l *LinkedList[T]) Tail() *Node[T] {
	return l.tail
}

func (l *LinkedList[T]) search(value T) *Node[T] {
	currentNode := l.head
	for currentNode != nil {
		if currentNode.Value() == value {
			return currentNode
		}
		currentNode = currentNode.Next()
	}
	return nil
}

func (l *LinkedList[T]) Insert(value T) {
	newNode := NewNode(value)

	// If there's no head, the list is empty
	if l.head == nil {
		// Set the new node as the head and tail
		l.head = newNode
		l.tail = newNode
		l.size++
		return
	}
	// Otherwise, add the new node to the end of the list
	// 1 - Update the current tail pointing it to the new node
	// 2 - Update the tail to be the new node
	// 3 - Increment Linked list Size
	l.tail.SetNext(newNode)
	newNode.SetPrevious(l.tail)
	l.tail = newNode
	l.size++
}

func (l *LinkedList[T]) InsertHead(value T) {
	newNode := NewNode(value)

	if l.head == nil {
		l.head = newNode
		l.tail = newNode
		l.size++
		return
	}

	newNode.SetNext(l.head)
	l.head.SetPrevious(newNode)
	l.head = newNode
	l.size++
}

func (l *LinkedList[T]) Remove(value T) (T, error) {
	// If the list is empty, return
	if l.head == nil {
		var zeroValue T
		return zeroValue, fmt.Errorf("Value %v not found in the list", value)
	}

	// If the head is the value to remove
	if l.head.Value() == value {
		// 1 - Save the value to return
		// 2 - Update the head to the next node
		// 3 - Decrement Linked list Size
		value := l.head.Value()
		l.head = l.head.Next()
		if l.head != nil {
			l.head.SetPrevious(nil)
		}
		l.size--
		return value, nil
	} else {
		// Otherwise, iterate over the list to find the value
		currentNode := l.head
		for currentNode != nil {
			if currentNode.Value() == value {
				// 1 - Save the value to return
				// 2 - Update the previous node to point to the next node
				// 3 - Update the next node to point to the previous node
				// 4 - Decrement Linked list Size
				value := currentNode.Value()
				currentNode.Previous().SetNext(currentNode.Next())
				currentNode.Next().SetPrevious(currentNode.Previous())
				l.size--
				return value, nil
			} else {
				currentNode = currentNode.Next()
			}
		}
		var zeroValue T
		return zeroValue, fmt.Errorf("Value %v not found in the list", value)
	}
}

func (l *LinkedList[T]) Len() int {
	return l.size
}

func (l *LinkedList[T]) IsEmpty() bool {
	return l.size == 0
}

func (l *LinkedList[T]) String() string {
	var finalString string = "LinkedList ["
	currentNode := l.head
	for currentNode != nil {
		finalString += fmt.Sprintf("%v", currentNode.Value())
		currentNode = currentNode.Next()
		if currentNode != nil {
			finalString += " -> "
		}
	}
	finalString += "]"
	return finalString
}
