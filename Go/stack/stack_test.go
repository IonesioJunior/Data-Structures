package stack

import (
	"testing"
)

// TestPush tests the Push method of the Stack type.
func TestPush(t *testing.T) {
	var tests = []struct {
		input []int
		want  Stack[int]
	}{
		{[]int{1}, NewStack[int]([]int{1})},
		{[]int{1, 2, 3, 4, 5, 6}, NewStack[int]([]int{1, 2, 3, 4, 5, 6})},
		{[]int{}, NewStack[int]([]int{})},
		{[]int{2, 2, 2, 2, 2, 2}, NewStack[int]([]int{2, 2, 2, 2, 2, 2})},
	}

	for _, tt := range tests {
		s := Stack[int]{}
		for _, v := range tt.input {
			s.Push(v)
		}
		if !s.Equals(tt.want) {
			t.Errorf("got %v, want %v", s, tt.want)
		}
	}
}

// TestPop is a unit test function that tests the Pop method of the Stack type.
// It verifies that the Pop method correctly removes the top element from the stack.
func TestPop(t *testing.T) {
	var tests = []struct {
		input []int
		want  Stack[int]
	}{
		{[]int{1}, NewStack[int]([]int{})},
		{[]int{1, 2, 3, 4, 5, 6}, NewStack[int]([]int{1, 2, 3, 4, 5})},
		{[]int{}, NewStack[int]([]int{})},
		{[]int{2, 2, 2, 2, 2, 2}, NewStack[int]([]int{2, 2, 2, 2, 2})},
		{[]int{}, NewStack[int]([]int{})},
	}

	for _, tt := range tests {
		s := NewStack[int](tt.input)
		_, _ = s.Pop()
		if !s.Equals(tt.want) {
			t.Errorf("got %v, want %v", s, tt.want)
		}
	}
}

// TestTop is a unit test function that tests the Top method of the Stack type.
// It verifies that the Top method returns the expected top element of the stack.
func TestTop(t *testing.T) {
	var tests = []struct {
		input []int
		want  int
	}{
		{[]int{1}, 1},
		{[]int{1, 2, 3, 4, 5, 6}, 6},
		{[]int{}, 0},
		{[]int{2, 2, 2, 2, 2, 2}, 2},
	}

	for _, tt := range tests {
		s := NewStack[int](tt.input)
		got, _ := s.Top()
		if got != tt.want {
			t.Errorf("got %v, want %v", got, tt.want)
		}
	}
}

// TestIsEmpty is a unit test function that tests the IsEmpty method of the Stack type.
// It verifies that the IsEmpty method returns the expected result for different input scenarios.
func TestIsEmpty(t *testing.T) {
	var tests = []struct {
		input []int
		want  bool
	}{
		{[]int{1}, false},
		{[]int{1, 2, 3, 4, 5, 6}, false},
		{[]int{}, true},
		{[]int{2, 2, 2, 2, 2, 2}, false},
	}

	for _, tt := range tests {
		s := NewStack[int](tt.input)
		got := s.IsEmpty()
		if got != tt.want {
			t.Errorf("got %v, want %v", got, tt.want)
		}
	}
}

// TestLen is a unit test function that tests the Len method of the Stack type.
// It verifies that the Len method returns the correct length of the stack.
func TestLen(t *testing.T) {
	var tests = []struct {
		input []int
		want  int
	}{
		{[]int{1}, 1},
		{[]int{1, 2, 3, 4, 5, 6}, 6},
		{[]int{}, 0},
		{[]int{2, 2, 2, 2, 2, 2}, 6},
	}

	for _, tt := range tests {
		s := NewStack[int](tt.input)
		got := s.Len()
		if got != tt.want {
			t.Errorf("got %v, want %v", got, tt.want)
		}
	}
}

// TestString tests the String method of the Stack type.
func TestString(t *testing.T) {
	var tests = []struct {
		input []int
		want  string
	}{
		{[]int{1}, " Stack<[1]>"},
		{[]int{1, 2, 3, 4, 5, 6}, " Stack<[1 2 3 4 5 6]>"},
		{[]int{}, " Stack<[]>"},
		{[]int{2, 2, 2, 2, 2, 2}, " Stack<[2 2 2 2 2 2]>"},
	}

	for _, tt := range tests {
		s := NewStack[int](tt.input)
		got := s.String()
		if got != tt.want {
			t.Errorf("got %v, want %v", got, tt.want)
		}
	}
}

// TestEquals tests the Equals method of the Stack type.
// It compares two stacks and checks if they are equal.
// The test cases include stacks with different lengths and values.
// TestEquals tests the Equals method of the Stack type.
// It compares two stacks and checks if they are equal.
// The test cases include stacks with different elements and sizes.
func TestEquals(t *testing.T) {
	var tests = []struct {
		input1 []int
		input2 []int
		want   bool
	}{
		{[]int{1}, []int{1}, true},
		{[]int{1, 2, 3, 4, 5, 6}, []int{1, 2, 3, 4, 5, 6}, true},
		{[]int{}, []int{}, true},
		{[]int{2, 2, 2, 2, 2, 2}, []int{2, 2, 2, 2, 2, 2}, true},
		{[]int{2, 2, 2, 2, 2, 2}, []int{2, 2, 2, 2, 2, 3}, false},
	}

	for _, tt := range tests {
		s1 := NewStack[int](tt.input1)
		s2 := NewStack[int](tt.input2)
		got := s1.Equals(s2)
		if got != tt.want {
			t.Errorf("got %v, want %v", got, tt.want)
		}
	}
}

// TestPopEmpty tests the Pop method of the Stack when it is empty.
func TestPopEmpty(t *testing.T) {
	s := NewStack[int]([]int{})
	_, err := s.Pop()
	if err == nil {
		t.Errorf("got %v, want %v", err, "Stack is empty")
	}
}

// TestTopEmpty tests the behavior of the Top method when the stack is empty.
func TestTopEmpty(t *testing.T) {
	s := NewStack[int]([]int{})
	_, err := s.Top()
	if err == nil {
		t.Errorf("got %v, want %v", err, "Stack is empty")
	}
}

// TestInit tests the initialization of a stack with a given slice of integers.
func TestInit(t *testing.T) {
	s := Stack[int]{}
	s.Init([]int{1, 2, 3, 4, 5, 6})
	want := NewStack[int]([]int{1, 2, 3, 4, 5, 6})
	if !s.Equals(want) {
		t.Errorf("got %v, want %v", s, want)
	}
}
