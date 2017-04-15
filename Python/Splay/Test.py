from Splay import Splay

splay = Splay()
for i in range(10,20,2):
	splay.insert(i)

print splay.size()
print splay.height()
print splay.getRoot().getData()
splay.remove(10)
print splay.getRoot().getData()
splay.remove(16)
print splay.getRoot().getData()
print splay.toArrayOrder()
