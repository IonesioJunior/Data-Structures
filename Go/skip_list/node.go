package skiplist

import (
	"fmt"

	"golang.org/x/exp/constraints"
)

type Node[T constraints.Ordered] struct {
	value        *T
	next         map[int]*Node[T]
	previous     map[int]*Node[T]
	infinityNode int
}

func NewNode[T constraints.Ordered](value T) *Node[T] {
	return &Node[T]{value: &value, next: make(map[int]*Node[T]), previous: make(map[int]*Node[T]), infinityNode: 0}
}

func NewPositiveInfinityNode[T constraints.Ordered]() *Node[T] {
	return &Node[T]{value: nil, next: make(map[int]*Node[T]), previous: make(map[int]*Node[T]), infinityNode: 1}
}

func NewNegativeInfinityNode[T constraints.Ordered]() *Node[T] {
	return &Node[T]{value: nil, next: make(map[int]*Node[T]), previous: make(map[int]*Node[T]), infinityNode: -1}
}

func (n *Node[T]) Value() T {
	return *n.value
}

func (n *Node[T]) isNegativeInfinityNode() bool {
	return n.infinityNode < 0
}

func (n *Node[T]) isPositiveInfinityNode() bool {
	return n.infinityNode > 0
}

func (n *Node[T]) isInfinityNode() bool {
	return n.isNegativeInfinityNode() || n.isPositiveInfinityNode()
}

func (n *Node[T]) getHeight() int {
	// if len(n.next) == 0 {
	// 	return 0
	// }

	// max := math.MinInt64
	// for k := range n.next {
	// 	if k > max {
	// 		max = k
	// 	}
	// }

	return len(n.next) - 1
}

func (n *Node[T]) Next(level int) *Node[T] {
	return n.next[level]
}

func (n *Node[T]) SetNext(next *Node[T], level int) {
	n.next[level] = next
}

func (n *Node[T]) Previous(level int) *Node[T] {
	return n.previous[level]
}

func (n *Node[T]) SetPrevious(previous *Node[T], level int) {
	n.previous[level] = previous
}

func (n *Node[T]) String() string {
	if n.value != nil {
		return fmt.Sprintf("<Node %v >", *n.value)
	} else if n.infinityNode < 0 {
		return fmt.Sprintf("< Node -inf>")
	}
	return fmt.Sprintf("< Node inf>")
}
