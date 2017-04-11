package Stack;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestStack {
	
	private Stack<Integer> stack1;
	private Stack<Integer> stack2;
	
	@Before
	public void before(){
		
		stack1 = new Stack<>(10);
		stack2 = new Stack<>(3);

	}
	
	@Test
	public void testPush() {
		
		Assert.assertTrue(stack1.isEmpty());
		Assert.assertFalse(stack1.isFull());
		
		try{
			Assert.assertTrue(stack1.top().equals(null));
			Assert.fail();
		}catch(NullPointerException npe){
			
		}
		
		try{
			stack1.push(null);
			Assert.assertTrue(stack1.isEmpty());
			Assert.assertFalse(stack1.isFull());		
			
		} catch(StackOverflowException soe){
				Assert.fail();
		}
		
		try{
			
			stack1.push(1);
			Assert.assertTrue(stack1.top().equals(1));
			
			stack1.push(2);
			Assert.assertFalse(stack1.isEmpty());
			Assert.assertFalse(stack1.isFull());
			
			Assert.assertTrue(stack1.top().equals(2));
			
			stack1.push(3);
			Assert.assertTrue(stack1.top().equals(3));
			
			stack1.push(4);
			Assert.assertTrue(stack1.top().equals(4));
			
			stack1.push(5);
			Assert.assertTrue(stack1.top().equals(5));
			
			stack1.push(6);
			Assert.assertTrue(stack1.top().equals(6));
			
			stack1.push(7);
			Assert.assertTrue(stack1.top().equals(7));
			
			stack1.push(8);
			Assert.assertTrue(stack1.top().equals(8));
			
			stack1.push(9);
			Assert.assertTrue(stack1.top().equals(9));
			
			stack1.push(10);
			Assert.assertTrue(stack1.top().equals(10));
			
			Assert.assertFalse(stack1.isEmpty());
			Assert.assertTrue(stack1.isFull());
			
			
		} catch(StackOverflowException soe){
				Assert.fail();
		}
		
		try {
			stack1.push(11);
			Assert.fail();
		} catch (StackOverflowException e) {
			
		}
	
	}
	
	@Test
	public void testPop(){
		
		try {
			stack1.push(10);
			stack1.push(-1);
			stack1.push(12);
			stack1.push(13);
			stack1.push(0);
			stack1.push(20);
			stack1.push(10);
			Assert.assertTrue(stack1.top().equals(10));
		} catch (StackOverflowException e) {
			Assert.fail();
		}
		
		try {
			Integer element = stack1.pop();
			Assert.assertTrue(element.equals(10));
			
			element = stack1.pop();
			Assert.assertTrue(element.equals(20));
			
			element = stack1.pop();
			Assert.assertTrue(element.equals(0));
			
			element = stack1.pop();
			Assert.assertTrue(element.equals(13));
			
			element = stack1.pop();
			Assert.assertTrue(element.equals(12));
			
			element = stack1.pop();
			Assert.assertTrue(element.equals(-1));
			
			element = stack1.pop();
			Assert.assertTrue(element.equals(10));
			
			
		} catch (StackUnderflowException e) {
			Assert.fail();
		}
		
		try {
			Integer element = stack1.pop();
			Assert.fail();
		} catch (StackUnderflowException e) {
			
		}
		
	}
	

}
