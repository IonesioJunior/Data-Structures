package linkedlist

import (
	"fmt"
	"testing"
)

func TestLinkedListInsert(t *testing.T) {
	// Test Insert into an empty list
	list := NewLinkedList[int]()
	list.Insert(42)
	if list.Len() != 1 {
		t.Errorf("Expected length of 1, got %d", list.Len())
	}
	if list.Head().Value() != 42 {
		t.Errorf("Expected head value of 42, got %d", list.Head().Value())
	}
	if list.Tail().Value() != 42 {
		t.Errorf("Expected tail value of 42, got %d", list.Tail().Value())
	}

	list.Insert(10)
	if list.Len() != 2 {
		t.Errorf("Expected length of 2, got %d", list.Len())
	}
	if list.Tail().Value() != 10 {
		t.Errorf("Expected tail value of 10, got %d", list.Tail().Value())
	}
	if list.Head().Value() != 42 {
		t.Errorf("Expected head value of 42, got %d", list.Head().Value())
	}

	list.Insert(30)
	if list.Len() != 3 {
		t.Errorf("Expected length of 3, got %d", list.Len())
	}
	if list.Tail().Value() != 30 {
		t.Errorf("Expected head value of 30, got %d", list.Head().Value())
	}
	if list.Head().Value() != 42 {
		t.Errorf("Expected tail value of 30, got %d", list.Tail().Value())
	}

	list.Insert(20)
	if list.Len() != 4 {
		t.Errorf("Expected length of 4, got %d", list.Len())
	}
	if list.Tail().Value() != 20 {
		t.Errorf("Expected head value of 20, got %d", list.Head().Value())
	}
	if list.Head().Value() != 42 {
		t.Errorf("Expected tail value of 30, got %d", list.Tail().Value())
	}
}

func TestLinkedListLen(t *testing.T) {
	// Test Len() on an empty list
	list := NewLinkedList[int]()
	if list.Len() != 0 {
		t.Errorf("Expected length of 0, got %d", list.Len())
	}

	// Test Len() after inserting elements
	list.Insert(10)
	list.Insert(20)
	list.Insert(30)
	if list.Len() != 3 {
		t.Errorf("Expected length of 3, got %d", list.Len())
	}

	// Test Len() after removing elements
	list.Remove(20)
	if list.Len() != 2 {
		t.Errorf("Expected length of 2, got %d", list.Len())
	}

	// Test Len() on a list with only one element
	list.Remove(10)
	if list.Len() != 1 {
		t.Errorf("Expected length of 1, got %d", list.Len())
	}

	// Test Len() on a list with multiple elements
	list.Insert(40)
	list.Insert(50)
	list.Insert(60)
	if list.Len() != 4 {
		t.Errorf("Expected length of 4, got %d", list.Len())
	}
}
func TestLinkedListSearch(t *testing.T) {
	// Test search on an empty list
	list := NewLinkedList[int]()
	node := list.search(10)
	if node != nil {
		t.Errorf("Expected nil, got %v", node)
	}

	// Test search on a list with one element
	list.Insert(20)
	node = list.search(20)
	if node == nil || node.Value() != 20 {
		t.Errorf("Expected node with value 20, got %v", node)
	}

	// Test search on a list with multiple elements
	list.Insert(10)
	list.Insert(30)
	list.Insert(40)
	node = list.search(30)
	if node == nil || node.Value() != 30 {
		t.Errorf("Expected node with value 30, got %v", node)
	}

	// Test search for a value that doesn't exist in the list
	node = list.search(50)
	if node != nil {
		t.Errorf("Expected nil, got %v", node)
	}
}

func TestLinkedList(t *testing.T) {
	// Test NewLinkedList with empty list
	list := NewLinkedList[int]()
	if list.Len() != 0 {
		t.Errorf("Expected length of 0, got %d", list.Len())
	}

	// Test Insert and Len with single element
	list.Insert(42)
	if list.Len() != 1 {
		t.Errorf("Expected length of 1, got %d", list.Len())
	}

	// Test Head and Tail with single element
	if list.Head().Value() != 42 {
		t.Errorf("Expected head value of 42, got %d", list.Head().Value())
	}
	if list.Tail().Value() != 42 {
		t.Errorf("Expected tail value of 42, got %d", list.Tail().Value())
	}

	// Test Insert and Len with multiple elements
	list.Insert(10)
	list.Insert(20)
	list.Insert(30)
	if list.Len() != 4 {
		t.Errorf("Expected length of 4, got %d", list.Len())
	}

	// Test IsEmpty after inserting elements
	if list.IsEmpty() {
		t.Errorf("Expected IsEmpty to be false, got true")
	}

	// Test String representation
	expectedString := "LinkedList [42 -> 10 -> 20 -> 30]"
	if list.String() != expectedString {
		t.Errorf("Expected string representation %q, got %q", expectedString, list.String())
	}
}
func TestLinkedList_InsertHead(t *testing.T) {
	// Test InsertHead on an empty list
	list := NewLinkedList[int]()
	list.InsertHead(42)
	if list.Len() != 1 {
		fmt.Println(list)
		t.Errorf("Expected length of 1, got %d", list.Len())
	}
	if list.Head().Value() != 42 {
		t.Errorf("Expected head value of 42, got %d", list.Head().Value())
	}
	if list.Tail().Value() != 42 {
		t.Errorf("Expected tail value of 42, got %d", list.Tail().Value())
	}

	// Test InsertHead at the beginning of a non-empty list
	list.InsertHead(10)
	if list.Len() != 2 {
		t.Errorf("Expected length of 2, got %d", list.Len())
	}
	if list.Head().Value() != 10 {
		t.Errorf("Expected head value of 10, got %d", list.Head().Value())
	}
	if list.Tail().Value() != 42 {
		t.Errorf("Expected tail value of 42, got %d", list.Tail().Value())
	}

	// Test InsertHead at the end of a non-empty list
	list.InsertHead(30)
	if list.Len() != 3 {
		t.Errorf("Expected length of 3, got %d", list.Len())
	}
	if list.Head().Value() != 30 {
		t.Errorf("Expected tail value of 30, got %d", list.Head().Value())
	}
	if list.Tail().Value() != 42 {
		t.Errorf("Expected tail value of 42, got %d", list.Tail().Value())
	}

	// Test InsertHead in the middle of a non-empty list
	list.InsertHead(20)
	if list.Len() != 4 {
		t.Errorf("Expected length of 4, got %d", list.Len())
	}
	if list.Head().Value() != 20 {
		t.Errorf("Expected tail value of 30, got %d", list.Tail().Value())
	}
	if list.Tail().Value() != 42 {
		t.Errorf("Expected tail value of 42, got %d", list.Tail().Value())
	}
}

func TestLinkedList_Remove(t *testing.T) {
	// Test Remove on an empty list
	list := NewLinkedList[int]()
	_, err := list.Remove(42)
	if err == nil {
		t.Error("Expected error, got nil")
	}

	// Test Remove on a list with a single element
	list.Insert(42)
	value, err := list.Remove(42)
	if err != nil {
		t.Errorf("Expected no error, got %v", err)
	}
	if value != 42 {
		t.Errorf("Expected removed value of 42, got %d", value)
	}
	if list.Len() != 0 {
		t.Errorf("Expected length of 0, got %d", list.Len())
	}

	// Test Remove on a list with multiple elements
	list.Insert(10)
	list.Insert(20)
	list.Insert(30)
	value, err = list.Remove(20)

	if err != nil {
		t.Errorf("Expected no error, got %v", err)
	}

	if value != 20 {
		t.Errorf("Expected removed value of 20, got %d", value)
	}

	if list.Len() != 2 {
		t.Errorf("Expected length of 2, got %d", list.Len())
	}

	if list.String() != "LinkedList [10 -> 30]" {
		t.Errorf("Expected string representation %q, got %q", "10 -> 30", list.String())
	}
}
