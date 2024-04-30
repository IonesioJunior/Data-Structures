package linkedlist

import (
	"fmt"
)

type Node[T comparable] struct {
	value    T
	next     *Node[T]
	previous *Node[T]
}

func NewNode[T comparable](value T) *Node[T] {
	return &Node[T]{value: value}
}

func (n *Node[T]) Value() T {
	return n.value
}

func (n *Node[T]) SetValue(value T) {
	n.value = value
}

func (n *Node[T]) Next() *Node[T] {
	return n.next
}

func (n *Node[T]) SetNext(next *Node[T]) {
	n.next = next
}

func (n *Node[T]) Previous() *Node[T] {
	return n.previous
}

func (n *Node[T]) SetPrevious(previous *Node[T]) {
	n.previous = previous
}

func (n *Node[T]) String() string {
	return fmt.Sprint(n.value)
}

func (n *Node[T]) Equals(other *Node[T]) bool {
	return n.value == other.value
}

func (n *Node[T]) IsEmpty() bool {
	return n == nil
}

func (n *Node[T]) IsLast() bool {
	return n.next == nil
}

func (n *Node[T]) IsFirst() bool {
	return n.previous == nil
}
