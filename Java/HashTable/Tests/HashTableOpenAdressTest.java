package HashTable.Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import HashTable.ClosedAddress.HashFunction.HashFunctionMethod;
import HashTable.Exception.HashTableOverflowException;
import HashTable.OpenAddress.HashTableLinearProbing;
import HashTable.OpenAddress.Elements.Element;
/**
 * @author Ionesio Junior
 * */

/**
 * Hash table Open Adress Validation Tests
 * */
public class HashTableOpenAdressTest {
	protected HashTableLinearProbing<Element> table1;
	protected HashTableLinearProbing<Element> table2;

	protected final int PROPOSED_SIZE = 10;

	@Before
	public void setUp() throws Exception {
		table1 = new HashTableLinearProbing(PROPOSED_SIZE, HashFunctionMethod.DIVISION);
		// o tamanho real utilizado vai ser PROPOSED_SIZE
		table1.insert(new Element(2)); // coloca no slot indexado com 2
		table1.insert(new Element(3)); // coloca no slot indexado com 3
		table1.insert(new Element(4)); // coloca no slot indexado com 4
		table1.insert(new Element(5)); // coloca no slot indexado com 5

		table2 = new HashTableLinearProbing(PROPOSED_SIZE, HashFunctionMethod.DIVISION);
	}
	  @Test
	    public void testInsert() throws HashTableOverflowException {

		  	
	        for(int i = 0 ; i < 100 ; i++){
	            table1.insert(null);
	            table1.insert(new Element(2));
	            table1.insert(new Element(3));
	            table1.insert(new Element(4));
	            table1.insert(new Element(5));
	        }

	        assertEquals(0, table1.getCOLLISIONS());
	        table1.insert(new Element(7)); // nao produz colisao. coloca no
	        // slot indexado com 7
	        assertEquals(7, table1.indexOf(new Element(7)));
	        assertEquals(0, table1.getCOLLISIONS());

	        table1.insert(new Element(9)); // nao produz colisao. coloca no
	        // slot indexado com 9
	        assertEquals(9, table1.indexOf(new Element(9)));
	        assertEquals(0, table1.getCOLLISIONS());

	        table1.insert(new Element(12)); // produz colisao com o 2.
	        // coloca no slot indexado
	        // com 6 (prox disponivel)
	        assertEquals(6, table1.indexOf(new Element(12)));
	        assertEquals(4, table1.getCOLLISIONS());
	        table1.remove(new Element(12));
	        assertEquals(4, table1.getCOLLISIONS());

	        assertEquals(6, table1.size());
	        table1.remove(new Element(9));
	        assertEquals(5, table1.size());
	        table1.remove(new Element(7));
	        assertEquals(4, table1.size());
	        table1.remove(new Element(5));
	        assertEquals(3, table1.size());
	        table1.remove(new Element(4));
	        assertEquals(2, table1.size());
	        table1.remove(new Element(3));
	        assertEquals(1, table1.size());
	        table1.remove(new Element(2));
	        assertEquals(0, table1.size());

	        assertTrue(table1.isEmpty());

	        table2.insert(new Element(14)); // nao produz colisao. coloca
	        // no slot indexado com 4
	        assertEquals(4, table2.indexOf(new Element(14)));
	        assertEquals(0, table2.getCOLLISIONS());

	        table2.insert(new Element(24)); // nao produz colisao. coloca
	        // no slot indexado com 4
	        assertEquals(5, table2.indexOf(new Element(24)));
	        assertEquals(1, table2.getCOLLISIONS());

	        table2.insert(new Element(34)); // nao produz colisao. coloca
	        // no slot indexado com 4
	        assertEquals(6, table2.indexOf(new Element(34)));
	        assertEquals(3, table2.getCOLLISIONS());

	    }
		@Test
		public void testRemove() throws HashTableOverflowException {


		    for(int i = 0 ; i < 100 ; i++){
		        table1.remove(null);
	            table1.remove(new Element(9328));
	            table1.remove(new Element(4325));
	            table1.remove(new Element(3213));
	        }

	        assertEquals(4, table1.size());

			table1.remove(new Element(12)); // elemento inexistente
			assertEquals(4, table1.size());

			table1.insert(new Element(12)); // produz colisao com o 2.
			// coloca no slot indexado
			// com 6 (prox disponivel)

			assertEquals(4, table1.getCOLLISIONS());
			table1.remove(new Element(12)); // Zerar colisões
			assertEquals(4, table1.getCOLLISIONS());

			table1.remove(new Element(5)); // elemento existente
			assertEquals(3, table1.size());
			assertNull(table1.search(new Element(5)));

		}
		@Test
		public void testSearch() {

	        assertEquals(new Element(4),
	                table1.search(new Element(4)));

	        assertEquals(new Element(3),
	                table1.search(new Element(3)));

	        assertEquals(new Element(2),
	                table1.search(new Element(2)));


			assertEquals(new Element(5),
					table1.search(new Element(5))); // elemento que existe
			assertNull(table1.search(new Element(15))); // elemento que nao
																	// existe

			assertNull(table1.search(null));
	        assertNull(table1.search(new Element(1)));

		}
		@Test
		public void testIsEmpty() {
			assertFalse(table1.isEmpty());
			table1.remove(new Element(2)); // esvazia
			table1.remove(new Element(3));
			table1.remove(new Element(4));
			table1.remove(new Element(5));
			assertTrue(table1.isEmpty());

			assertTrue(table2.isEmpty());
		}
		@Test
	    public void testIsFull() throws HashTable.Exception.HashTableOverflowException {
	        assertFalse(table1.isFull());
	        table1.insert(new Element(1)); // enche a tabela
	        table1.insert(new Element(6));
	        table1.insert(new Element(7));
	        table1.insert(new Element(8));
	        table1.insert(new Element(9));
	        table1.insert(new Element(10));
	        assertTrue(table1.isFull());

	        assertFalse(table2.isFull());

	        for(int i = 0 ; i < 100 ; i++){
	            table2.insert(null);
	            if(i <= 9){
	                assertFalse(table2.isFull());
	                table2.insert(new Element(i));
	            } else {
	                assertTrue(table2.isFull());
	            }

	        }

	        assertTrue(table2.isFull());
	        assertEquals(0, table2.getCOLLISIONS());
	    }
	    public int colisoes(int index){

	        if(index == 0){
	            return 0;
	        }

	        return (index == 1) ? 1 : index + colisoes(index - 1);
	    }		
		@Test
		public void testCollisions() throws HashTable.Exception.HashTableOverflowException {

		        table1.insert(new Element(1));
		        table1.insert(new Element(6));
		        table1.insert(new Element(7));
		        table1.insert(new Element(8));
		        table1.insert(new Element(9));
		        table1.insert(new Element(10));
		        assertEquals(0, table1.getCOLLISIONS());
		        assertTrue(table1.isFull());

		        try{
		            table1.insert(new Element(99));
		            fail();
		        } catch (HashTableOverflowException e){
		            assertEquals(0, table1.getCOLLISIONS());
		        }

		        table1.remove(new Element(10));
		        table1.insert(new Element(11)); //vai dar colisao com tudo de 1 ate 9
		        assertTrue(table1.isFull());
		        assertEquals(9, table1.getCOLLISIONS());
		        assertEquals(0, table2.size());

		        for (int i = 0; i < 10; i++) {

		            table2.insert(null);
		            table2.insert(new Element((i * 10) + 2));
		            assertEquals(i + 1, table2.size());
		            int j = table2.getCOLLISIONS();
		            assertEquals(j, colisoes(i));
		        }

		        assertEquals(10, table2.size());

		    }
		@Test
	    public void testIndexOf() throws HashTableOverflowException{

		        //2 3 4 5
		        assertEquals(2, table1.indexOf(new Element(2)));
		        assertEquals(3, table1.indexOf(new Element(3)));
		        assertEquals(4, table1.indexOf(new Element(4)));
		        assertEquals(5, table1.indexOf(new Element(5)));

		        for(int i = 23 ; i < 1203 ; i++) {
		            assertEquals(-1, table1.indexOf(new Element(i)));
		            assertEquals(-1, table1.indexOf(null));
		        }

		        table1.insert(new Element(12));
		        assertEquals(6, table1.indexOf(new Element(12)));

		        for (int i = 0; i < 10; i++) {

		            table2.insert(null);
		            table2.insert(new Element(i));
		            assertEquals(i, table2.indexOf(new Element(i)));
		        }

		        
		        table1.remove(new Element(2)); // esvazia
		        table1.remove(new Element(3));
		        table1.remove(new Element(4));
		        table1.remove(new Element(5));
		        table1.remove(new Element(12));
		        assertTrue(table1.isEmpty());

		        for (int i = 0; i < 10; i++) {

		            table1.insert(null);
		            table1.insert(new Element((i * 10) + 2));
		            assertEquals(((2 + i) % 10), table1.indexOf(new Element((i * 10) + 2)));
		        }
		    }
}
