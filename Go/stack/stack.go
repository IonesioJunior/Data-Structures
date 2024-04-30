package stack

import (
	"errors"
	"fmt"
)

// Stack represents a generic stack data structure.
// It is implemented using a slice of generic type T.
// The zero value for Stack is an empty stack ready to use.
// Methods available for the Stack type are:
// - IsEmpty: checks if the stack is empty.
// - Len: returns the length of the stack.
// - Push: adds an item to the top of the stack.
// - Pop: removes and returns the item at the top of the stack.
// - Top: returns the item at the top of the stack without removing it.
// - String: returns a string representation of the stack.
// - Equals: checks if two stacks are equal.
type Stack[T comparable] struct {
	items []T
}

func NewStack[T comparable](item []T) Stack[T] {
	return Stack[T]{items: item}
}

func (s *Stack[T]) IsEmpty() bool {
	return len(s.items) == 0
}

func (s *Stack[T]) Init(item []T) {
	s.items = item
}

func (s *Stack[T]) Len() int {
	return len(s.items)
}

func (s *Stack[T]) Push(item T) {
	s.items = append(s.items, item)
}

func (s *Stack[T]) Pop() (T, error) {
	items_lenght := len(s.items)
	if items_lenght == 0 {
		var zeroValue T
		return zeroValue, errors.New("Stack is empty")
	}

	lastItem := s.items[len(s.items)-1]
	s.items = s.items[:len(s.items)-1]
	return lastItem, nil
}

func (s *Stack[T]) Top() (T, error) {
	items_lenght := len(s.items)
	if items_lenght == 0 {
		var zeroValue T
		return zeroValue, errors.New("Stack is empty")
	}

	return s.items[items_lenght-1], nil
}

func (s *Stack[T]) String() string {
	var finalString string = " Stack<"
	finalString += fmt.Sprint(s.items)
	finalString += ">"
	return finalString
}

func (s *Stack[T]) Equals(other Stack[T]) bool {
	if len(s.items) != len(other.items) {
		return false
	}

	for i, v := range s.items {
		if v != other.items[i] {
			return false
		}
	}

	return true
}
