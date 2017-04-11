package HashTable.Tests;

import org.junit.*;
import HashTable.HashTableInterface;
import HashTable.ClosedAddress.HashTableClosedAddress;
import HashTable.ClosedAddress.HashFunction.HashFunctionMethod;
import HashTable.Exception.HashTableOverflowException;

/**
 * @author Ionesio Junior
 * */

/**
 * Hash table Closed Adress validation Tests
 * */
public class HashTableClosedAdressTest {
	public HashTableInterface<Integer> hash1;
	public HashTableInterface<Integer> hash2;
	@Before
	public void setUp() throws Exception {
		this.hash1 = new HashTableClosedAddress<Integer>(HashFunctionMethod.DIVISION,15);
		this.hash2 = new HashTableClosedAddress<Integer>(HashFunctionMethod.DIVISION,15);
	}

	@Test
	public void testInsert() throws HashTableOverflowException {
		Assert.assertTrue(hash1.isEmpty());
		hash1.insert(new Integer(10));
		hash1.insert(new Integer(30));
		Assert.assertFalse(hash1.isEmpty());
		Assert.assertEquals(2, hash1.size());
		Assert.assertTrue(hash2.isEmpty());
		Assert.assertEquals(0, hash2.size());
		
	}
	
	@Test
	public void testRemove() throws HashTableOverflowException {
		hash1.insert(new Integer(100));
		hash1.insert(new Integer(200));
		Assert.assertFalse(hash1.isEmpty());
		Assert.assertEquals(new Integer(100), hash1.search(new Integer(100)));
		Assert.assertEquals(2, hash1.size());
		hash1.remove(new Integer(100));
		Assert.assertNull(hash1.search(new Integer(100)));
		Assert.assertEquals(1, hash1.size());
		hash1.remove(new Integer(900));
		Assert.assertEquals(1, hash1.size());
		hash1.remove(new Integer(200));
		Assert.assertEquals(0, hash1.size());
		
	}

	@Test
	public void testSearch() throws HashTableOverflowException {
		Assert.assertNull(hash1.search(new Integer(100)));
		hash1.insert(new Integer(100));
		hash1.insert(new Integer(300));
		Assert.assertEquals(new Integer(100), hash1.search(new Integer(100)));
		Assert.assertEquals(new Integer(300),hash1.search(new Integer(300)));
		Assert.assertNull(hash1.search(new Integer(200)));
	}

	@Test
	public void testIndexOf() throws HashTableOverflowException {
		hash1.insert(new Integer(100));
		hash1.insert(new Integer(300));
		hash1.insert(new Integer(500));
		Assert.assertNotEquals(-1, hash1.indexOf(new Integer(100)));
		Assert.assertNotEquals(-1, hash1.indexOf(new Integer(300)));
		Assert.assertNotEquals(-1, hash1.indexOf(new Integer(500)));
		Assert.assertEquals(-1, hash1.indexOf(null));
		Assert.assertEquals(-1, hash1.indexOf(new Integer(3000)));
	}

}
