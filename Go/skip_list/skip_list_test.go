package skiplist

import (
	"testing"
)

func TestSkipListInsert(t *testing.T) {
	newList := NewSkipList[int]()

	// Inserting elements in ascending order
	for i := 1; i <= 5; i++ {
		newList.Insert(i)
	}

	// Inserting elements in descending order
	for i := 5; i >= 1; i-- {
		newList.Insert(i)
	}

	// Inserting duplicate elements
	for i := 1; i <= 5; i++ {
		newList.Insert(i)
	}

	// // Testing length
	expectedLength := 15
	if newList.Len() != expectedLength {
		t.Errorf("Expected length of %d, got %d", expectedLength, newList.Len())
	}

	// // Testing head value
	expectedHeadValue := 1
	if newList.Head().Value() != expectedHeadValue {
		t.Errorf("Expected head value of %d, got %d", expectedHeadValue, newList.Head().Value())
	}

	// // Testing tail value
	expectedTailValue := 5
	if newList.Tail().Value() != expectedTailValue {
		t.Errorf("Expected tail value of %d, got %d", expectedTailValue, newList.Tail().Value())
	}
}

func TestSkipListSearch(t *testing.T) {
	newList := NewSkipList[int]()

	// Inserting elements
	for i := 1; i <= 5; i++ {
		newList.Insert(i)
	}

	// Searching for existing elements
	for i := 1; i <= 5; i++ {
		node, err := newList.Search(i)
		if err != nil {
			t.Errorf("Unexpected error while searching for element: %v", err)
		}
		if node.Value() != i {
			t.Errorf("Expected search result to be %d, got %d", i, node.Value())
		}
	}

	// Searching for non-existing elements
	for i := 6; i <= 10; i++ {
		_, err := newList.Search(i)
		if err == nil {
			t.Errorf("Expected error while searching for non-existing element %d", i)
		}
	}
}

func TestSkipListRemove(t *testing.T) {
	newList := NewSkipList[int]()

	//	Inserting elements
	for i := 1; i <= 5; i++ {
		newList.Insert(i)
	}

	for i := 1; i <= 5; i++ {
		newList.Insert(i)
	}

	for i := 1; i <= 5; i++ {
		newList.Insert(i)
	}

	_, exp_err := newList.Remove(87)
	if exp_err == nil {
		t.Errorf("Expected error while removing element  %v", exp_err)
	}

	removed, err := newList.Remove(5)

	if err != nil {
		t.Errorf("Unexpected error while removing element: %v", err)
	}

	if removed != 5 {
		t.Errorf("Expected removed element to be %d, got %d", 5, removed)
	}

	// Removing existing elements
	for i := 1; i <= 4; i++ {
		removed, err := newList.Remove(i)
		if err != nil {
			t.Errorf("Unexpected error while removing element: %v", err)
		}
		if removed != i {
			t.Errorf("Expected removed element to be %d, got %d", i, removed)
		}
	}

	// 	// Removing non-existing elements
	for i := 1; i <= 5; i++ {
		_, err := newList.Remove(i)
		if err == nil {
			t.Errorf("Expected error while removing non-existing element %d", i)
		}
	}

	// Removing from an empty list
	_, new_err := newList.Remove(42)

	if new_err == nil {
		t.Errorf("Expected error while removing from an empty list")
	}
}
