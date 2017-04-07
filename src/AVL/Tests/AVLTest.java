package AVL.Tests;

import org.junit.*;

import AVL.AVL;
/*
* @author Ionésio Junior
*/
public class AVLTest {
	public AVL<Integer> avl;
	
	@Before
	public void setUp(){
		avl = new AVL();
	}
	
	@Test
	public void testInsert(){
		//Test Left Left
		avl.insert(new Integer(10));
		avl.insert(new Integer(5));
		Assert.assertEquals(new Integer(10), avl.getRoot().getData());
		avl.insert(new Integer(3));
		Assert.assertEquals(new Integer(5), avl.getRoot().getData());
		Assert.assertEquals(new Integer(3), avl.getRoot().getLeft().getData());
		Assert.assertEquals(new Integer(10), avl.getRoot().getRight().getData());
		Assert.assertEquals(1, avl.height());
		avl.insert(new Integer(1));
		Assert.assertEquals(2, avl.height());
		avl.insert(new Integer(0));
		Assert.assertEquals(new Integer(1), avl.getRoot().getLeft().getData());
		Assert.assertEquals(new Integer(5), avl.getRoot().getLeft().getParent().getData());
		Assert.assertEquals(new Integer(3),avl.getRoot().getLeft().getRight().getData());
		Assert.assertEquals(new Integer(1), avl.getRoot().getLeft().getRight().getParent().getData());
		Assert.assertEquals(2,avl.height());
		
		//Test Right Right
		avl.insert(new Integer(15));
		avl.insert(new Integer(20));
		Assert.assertEquals(new Integer(15), avl.getRoot().getRight().getData());
		Assert.assertEquals(new Integer(5),avl.getRoot().getRight().getParent().getData());
		Assert.assertEquals(new Integer(10), avl.getRoot().getRight().getLeft().getData());
		Assert.assertEquals(new Integer(15), avl.getRoot().getRight().getLeft().getParent().getData());
		Assert.assertEquals(2, avl.height());
		
		//Test Left-Right
		
	}
}
