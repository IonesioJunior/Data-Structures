package skiplist

import (
	"errors"
	"fmt"

	"golang.org/x/exp/constraints"
)

type SkipList[T constraints.Ordered] struct {
	head   *Node[T]
	tail   *Node[T]
	size   int
	height int
}

func NewSkipList[T constraints.Ordered]() *SkipList[T] {
	newSkipList := &SkipList[T]{height: 0}
	newSkipList.addInfinityNodes()
	return newSkipList
}

func (l *SkipList[T]) addInfinityNodes() *SkipList[T] {
	l.head = NewNegativeInfinityNode[T]()
	l.tail = NewPositiveInfinityNode[T]()
	l.head.SetNext(l.tail, 0)
	l.tail.SetPrevious(l.head, 0)
	return l
}

func (l *SkipList[T]) Search(element T) (*Node[T], error) {
	currentHeight := l.head.getHeight()
	var zeroValue *Node[T]
	if l.head.Next(currentHeight) == l.tail {
		return zeroValue, errors.New("SkipList is empty")
	}

	for currentHeight >= 0 {
		currentNode := l.head.Next(currentHeight)
		for currentNode != l.tail {
			if currentNode.Value() == element {
				return currentNode, nil
			}
			currentNode = currentNode.Next(currentHeight)
		}
		currentHeight--
	}

	return zeroValue, errors.New("Element not found!")
}

func (l *SkipList[T]) Insert(element T) {
	newNode := NewNode(element)
	currentHeight := l.head.getHeight()
	currentNode := l.head.Next(currentHeight)

	if currentNode == l.tail {
		l.head.SetNext(newNode, currentHeight)
		l.tail.SetPrevious(newNode, currentHeight)
		newNode.SetPrevious(l.head, currentHeight)
		newNode.SetNext(l.tail, currentHeight)
		l.size++
		return
	}

	upperLowerNeighboor := l.head
	upperGreaterNeighboor := l.tail
	// Decreasing the height of the skip list
	for currentHeight >= 0 {
		// Until we get to the end of the current layer
		for currentNode != l.tail {
			// Element is smaller to the current Node
			if currentNode.Value() > element {
				if currentHeight == 0 {
					// -> current Node is "head"
					if currentNode.Previous(currentHeight) == l.head {
						l.head.SetNext(newNode, currentHeight)
						currentNode.SetPrevious(newNode, currentHeight)
						newNode.SetNext(currentNode, currentHeight)
						newNode.SetPrevious(l.head, currentHeight)
					} else { // -> current Node isn't head
						currentNode.Previous(currentHeight).SetNext(newNode, currentHeight)
						newNode.SetPrevious(currentNode.Previous(currentHeight), currentHeight)
						currentNode.SetPrevious(newNode, l.height)
						newNode.SetNext(currentNode, currentHeight)
					}
					l.size++
				} else {
					upperLowerNeighboor = currentNode.Previous(currentHeight)
					upperGreaterNeighboor = currentNode
					currentNode = l.head.Next(currentHeight - 1)
				}
				break
			} else if currentNode.Value() == element {
				currentNode.SetNext(upperGreaterNeighboor, currentHeight+1)
				currentNode.SetPrevious(upperLowerNeighboor, currentHeight+1)
				upperGreaterNeighboor.SetPrevious(currentNode, currentHeight+1)
				upperLowerNeighboor.SetNext(currentNode, currentHeight+1)
				l.size++
				return
			} else { // Element is greater than the current Node
				// Current Node is tail
				if currentNode.Next(currentHeight) == l.tail && currentHeight != 0 {
					for currentNode.Next(currentHeight) == l.tail && currentHeight != 0 {
						currentHeight--
					}
					upperGreaterNeighboor = l.tail
					upperLowerNeighboor = currentNode
					currentNode = currentNode.Next(currentHeight)
				} else if currentNode.Next(currentHeight) == l.tail && currentHeight == 0 {
					currentNode.SetNext(newNode, currentHeight)
					newNode.SetPrevious(currentNode, currentHeight)
					newNode.SetNext(l.tail, currentHeight)
					l.tail.SetPrevious(newNode, currentHeight)
					l.size++
					break
				} else { // current node is not tail yet
					currentNode = currentNode.Next(currentHeight)
				}
			}
		}
		currentHeight--
	}
}

func (l *SkipList[T]) Remove(element T) (T, error) {
	currentHeight := l.height
	var zeroValue T
	if l.head.Next(currentHeight) == l.tail {
		return zeroValue, errors.New("SkipList is empty")
	}

	currentNode := l.head.Next(currentHeight)

	for currentHeight >= 0 {
		// While we're not at the end of the list
		for currentNode != l.tail {

			// If node contains the element to be deleted
			if currentNode.Value() == element {
				// If node is in the middle of the list
				if currentNode.Previous(currentHeight) != l.head && currentNode.Next(currentHeight) != l.tail {
					currentNode.Previous(currentHeight).SetNext(currentNode.Next(currentHeight), currentHeight)
					currentNode.Next(currentHeight).SetPrevious(currentNode.Previous(currentHeight), currentHeight)
				} else if currentNode.Previous(currentHeight) == l.head && currentNode.Next(currentHeight) != l.tail { // If it's head
					currentNode.Next(currentHeight).SetPrevious(l.head, currentHeight)
					l.head.SetNext(currentNode.Next(currentHeight), currentHeight)
				} else if currentNode.Previous(currentHeight) != l.head && currentNode.Next(currentHeight) == l.tail { // If it's tail
					currentNode.Previous(currentHeight).SetNext(l.tail, currentHeight)
					l.tail.SetPrevious(currentNode.Previous(currentHeight), currentHeight)
				} else { // If there's only one element in our skip list
					l.head.SetNext(l.tail, currentHeight)
					l.tail.SetPrevious(l.head, currentHeight)
				}
				l.size--
				return currentNode.Value(), nil
			}
			currentNode = currentNode.Next(currentHeight)
		}
		currentHeight--
	}
	return zeroValue, errors.New("Couldn't find this element in the SkipList")
}

func (l *SkipList[T]) isEmpty() bool {
	return l.size == 0
}

func (l *SkipList[T]) Len() int {
	return l.size
}

func (l *SkipList[T]) Head() *Node[T] {
	return l.head.Next(l.height)
}

func (l *SkipList[T]) Tail() *Node[T] {
	return l.tail.previous[l.height]
}

func (l *SkipList[T]) String() string {
	var finalString string = "SkipList: \n"

	currentHeight := l.head.getHeight()
	for currentHeight >= 0 {
		finalString += "["
		currentNode := l.head.Next(currentHeight)
		for currentNode != l.tail {
			finalString += fmt.Sprintf("%v", currentNode.Value())
			currentNode = currentNode.Next(currentHeight)
			if currentNode != l.tail {
				finalString += " -> "
			}
		}
		finalString += "]\n"
		currentHeight--
	}
	return finalString
}
