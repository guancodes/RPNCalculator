
package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Buffer;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Plus;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Stack;

public class PlusTest {

	@Test
	public void testExecuteNonNull () {
		for (long i=1; i<=10000; i++) {
			Plus plus = Plus.make();
			try {
				plus.execute(null);
				fail("Reject null");
			} catch (NullPointerException e) {}
		}
	}
	
	@Test
	public void testExecuteEmptyBuffer() {
		for (int i=1; i<=10000; i++) {
			Plus plus = Plus.make();
			Buffer<Long> buffer = Buffer.make(i);
			try {
				plus.execute(buffer);
				fail("Reject empty buffer");
			} catch (IllegalArgumentException e) {}
		}
	}
	
	@Test
	public void testExecuteHappyPath() {
		for(long i=1; i<=10000; i++) {
			Plus plus = Plus.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push(i);
			stack.push(i);
			assertTrue(stack.size() == 2);
			plus.execute(stack);
			assertTrue(stack.top() == 2*i);
			assertTrue(stack.size() == 1);
		}
	}
	
	@Test
	public void testExecuteSingleLiteralBuffer() {
		for(long i=1; i<=10000; i++) {
			Plus plus = Plus.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push (i);
			assertTrue(stack.size() == 1);
			plus.execute(stack);
			assertTrue(stack.top() == i);
			assertTrue(stack.size() == 1);
		}
	}
	
	@Test
	public void testExecuteFullCapacityBuffer() {
		Stack<Long> stack= Buffer.make(3);
		stack.push((long)1);
		stack.push((long)2);
		stack.push((long)3);
		assertTrue(stack.size() == 3);
		Plus plus = Plus.make();
		plus.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == 5);
		plus.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 6);
		stack.push((long)4);
		stack.push((long)5);
		plus.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == 9);
		plus.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 15);
	}
	
}
