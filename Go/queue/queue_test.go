package queue

import (
	"testing"
)

func TestEnqueueDequeue(t *testing.T) {
	q := NewQueue[int]([]int{})
	q.Enqueue(1)
	q.Enqueue(2)
	q.Enqueue(3)

	item, err := q.Dequeue()
	if err != nil {
		t.Errorf("unexpected error: %v", err)
	}
	if item != 1 {
		t.Errorf("got %v, want %v", item, 1)
	}

	item, err = q.Dequeue()
	if err != nil {
		t.Errorf("unexpected error: %v", err)
	}
	if item != 2 {
		t.Errorf("got %v, want %v", item, 2)
	}

	item, err = q.Dequeue()
	if err != nil {
		t.Errorf("unexpected error: %v", err)
	}
	if item != 3 {
		t.Errorf("got %v, want %v", item, 3)
	}

	_, err = q.Dequeue()
	if err == nil {
		t.Errorf("expected error, got nil")
	}
}

func TestString(t *testing.T) {
	q := NewQueue[int]([]int{1, 2, 3, 4, 5})
	want := "Queue<[1 2 3 4 5]>"
	if q.String() != want {
		t.Errorf("got %v, want %v", q.String(), want)
	}
}

func TestIsEmpty(t *testing.T) {
	q := NewQueue[int]([]int{})
	if !q.IsEmpty() {
		t.Errorf("expected queue to be empty")
	}

	q.Enqueue(1)
	if q.IsEmpty() {
		t.Errorf("expected queue not to be empty")
	}
}

func TestLen(t *testing.T) {
	q := NewQueue[int]([]int{1, 2, 3, 4, 5})
	want := 5
	if q.Len() != want {
		t.Errorf("got %v, want %v", q.Len(), want)
	}

	q.Enqueue(6)
	want = 6
	if q.Len() != want {
		t.Errorf("got %v, want %v", q.Len(), want)
	}

	_, _ = q.Dequeue()
	want = 5
	if q.Len() != want {
		t.Errorf("got %v, want %v", q.Len(), want)
	}
}

func TestEquals(t *testing.T) {
	q1 := NewQueue[int]([]int{1, 2, 3})
	q2 := NewQueue[int]([]int{1, 2, 3})
	if !q1.Equals(q2) {
		t.Errorf("expected queues to be equal")
	}

	q3 := NewQueue[int]([]int{1, 2, 3, 4})
	if q1.Equals(q3) {
		t.Errorf("expected queues to be different")
	}
}
