from RecursiveDoubleLinkedList import RecursiveDoubleLinkedList
linkedList = RecursiveDoubleLinkedList()

for i in range(100):
	linkedList.insertLast(i)

print linkedList.toArray()
