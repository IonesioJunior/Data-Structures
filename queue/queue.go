package queue

import (
	"errors"
	"fmt"
)

// Queue represents a generic queue data structure.
type Queue[T comparable] struct {
	items []T
}

// NewQueue creates a new queue with the given items.
func NewQueue[T comparable](item []T) Queue[T] {
	return Queue[T]{items: item}
}

// Enqueue adds an item to the end of the queue.
func (s *Queue[T]) Enqueue(item T) {
	s.items = append(s.items, item)
}

// Dequeue removes and returns the first item from the queue.
// It returns an error if the queue is empty.
func (s *Queue[T]) Dequeue() (T, error) {
	items_lenght := len(s.items)
	if items_lenght == 0 {
		var zeroValue T
		return zeroValue, errors.New("Queue is empty")
	}

	firstItem := s.items[0]
	s.items = s.items[1:]
	return firstItem, nil
}

// String returns a string representation of the queue.
func (s *Queue[T]) String() string {
	var finalString string = "Queue<"
	finalString += fmt.Sprint(s.items)
	finalString += ">"
	return finalString
}

// IsEmpty checks if the queue is empty.
func (s *Queue[T]) IsEmpty() bool {
	return len(s.items) == 0
}

// Len returns the number of items in the queue.
func (s *Queue[T]) Len() int {
	return len(s.items)
}

// Equals checks if the queue is equal to another queue.
func (s *Queue[T]) Equals(other Queue[T]) bool {
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
