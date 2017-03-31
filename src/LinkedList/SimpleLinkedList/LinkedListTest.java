package LinkedList.SimpleLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import LinkedList.DoubleLinkedList.DoubleLinkedList;
import LinkedList.DoubleLinkedList.DoubleLinkedListInterface;
import LinkedList.DoubleLinkedList.RecursiveDoubleLinkedList;

/**
 * @author Ionesio
 * */

/**
 * SingleLinkedList / DoubleLinkedList validation tests
 * */
public class LinkedListTest {
	public DoubleLinkedListInterface<Integer> list1;
	public LinkedListInterface<Integer> list2;
	
	@Before
	public void setUp(){
		//list1 = new DoubleLinkedList<Integer>();
		//list2 = new SimpleLinkedList<Integer>();
		list1 = new RecursiveDoubleLinkedList<Integer>();
		list2 = new RecursiveLinkedList<Integer>();
	}
	
	@Test
	public void testInsert(){
		this.list1.insert(new Integer(9));
		this.list1.insert(new Integer(10));
		this.list1.insert(new Integer(185));
	}
	
	@Test
	public void testIsEmpty(){
		Assert.assertTrue(list1.isEmpty());
		Assert.assertTrue(list2.isEmpty());
		list1.insert(new Integer(9));
		Assert.assertFalse(list1.isEmpty());
	}
	@Test
	public void testSize(){
		Assert.assertEquals(0,list1.size());
		list1.insert(new Integer(9));
		list1.insert(new Integer(10));
		list1.insert(new Integer(3));
		list2.insert(new Integer(5));
		
		Assert.assertEquals(3, list1.size());
		Assert.assertEquals(1, list2.size());
	}
	
	@Test
	public void testSearch(){
		list1.insert(new Integer(100));
		list1.insert(new Integer(200));
		list1.insert(new Integer(90));
		Assert.assertNull(list1.search(new Integer(210)));
		Assert.assertEquals(new Integer(90), list1.search(new Integer(90)));
		Assert.assertEquals(new Integer(100), list1.search(new Integer(100)));
		Assert.assertEquals(new Integer(200), list1.search(new Integer(200)));
	}
	
	@Test
	public void testRemove(){
		Assert.assertEquals(0, list1.size());
		list1.insert(new Integer(100));
		list1.insert(new Integer(200));
		list1.insert(new Integer(300));
		Assert.assertEquals(3, list1.size());
		Assert.assertEquals(new Integer(100), list1.search(new Integer(100)));
		Assert.assertEquals(new Integer(200),list1.search(new Integer(200)));
		Assert.assertEquals(new Integer(300),list1.search(new Integer(300)));
		list1.remove(new Integer(100));
		Assert.assertEquals(2,list1.size());
		list1.remove(new Integer(200));
		Assert.assertEquals(1, list1.size());
		list1.remove(new Integer(300));
		Assert.assertEquals(0, list1.size());
		Assert.assertNull(list1.search(new Integer(100)));
		Assert.assertNull(list1.search(new Integer(200)));
		Assert.assertNull(list1.search(new Integer(300)));
		
	}
	
	@Test
	public void testToArray(){
		Integer[] Array = {100,200,300,400,500,600,700,800,900};
		list2.insert(new Integer(100));
		list2.insert(new Integer(200));
		list2.insert(new Integer(300));
		list2.insert(new Integer(400));
		list2.insert(new Integer(500));
		list2.insert(new Integer(600));
		list2.insert(new Integer(700));
		list2.insert(new Integer(800));
		list2.insert(new Integer(900));
		Assert.assertArrayEquals(Array, list2.toArray());
	}
	
	
	@Test
	public void testInsertFirst(){
		list1.removeFirst();
		list1.insertFirst(new Integer(9));
		Assert.assertEquals(new Integer(9),list1.search(9));
		list1.insert(new Integer(100));
		list1.removeFirst();
		Assert.assertNull(list1.search(new Integer(9)));
		Assert.assertEquals(new Integer(100), list1.search(100));
	}
	
	@Test
	public void testRemoveFirst(){
		list1.insert(new Integer(100));
		list1.insert(new Integer(200));
		list1.insert(new Integer(300));
		list1.insert(new Integer(400));
		list1.insert(new Integer(500));
		list1.insert(new Integer(600));
		list1.insert(new Integer(700));
		list1.insert(new Integer(800));
		list1.insert(new Integer(900));
		Assert.assertEquals(new Integer(100), list1.search(new Integer(100)));
		list1.removeFirst();
		Assert.assertNull(list1.search(new Integer(100)));
		Assert.assertEquals(new Integer(200), list1.search(new Integer(200)));
		list1.removeFirst();
		Assert.assertNull(list1.search(new Integer(200)));
		Assert.assertEquals(new Integer(300), list1.search(new Integer(300)));
		list1.removeFirst();
		Assert.assertNull(list1.search(new Integer(300)));
	}
	
	@Test
	public void testRemoveLast(){
		list1.insert(new Integer(100));
		list1.insert(new Integer(200));
		list1.insert(new Integer(300));
		list1.insert(new Integer(400));
		list1.insert(new Integer(500));
		list1.insert(new Integer(600));
		list1.insert(new Integer(700));
		list1.insert(new Integer(800));
		list1.insert(new Integer(900));
		Assert.assertEquals(new Integer(900), list1.search(new Integer(900)));
		list1.removeLast();
		Assert.assertNull(list1.search(new Integer(900)));
		Assert.assertEquals(new Integer(800), list1.search(new Integer(800)));
		list1.removeLast();
		Assert.assertNull(list1.search(new Integer(800)));
		Assert.assertEquals(new Integer(700), list1.search(new Integer(700)));
		list1.removeLast();
		Assert.assertNull(list1.search(new Integer(700)));		
	}
}
