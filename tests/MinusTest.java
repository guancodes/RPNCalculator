package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Buffer;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Minus;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Stack;

public class MinusTest {

	@Test
	public void testExecuteNonNull () {
		for (long i=1; i<=10000; i++) {
			Minus minus = Minus.make();
			try {
				minus.execute(null);
				fail("Reject null");
			} catch (NullPointerException e) {}
		}
	}

	@Test
	public void testExecuteEmptyBuffer() {
		for (int i=1; i<=10000; i++) {
			Minus minus = Minus.make();
			Buffer<Long> buffer = Buffer.make(i);
			try {
				minus.execute(buffer);
				fail("Reject empty buffer");
			} catch (IllegalArgumentException e) {}
		}
	}
	
	@Test
	public void testExecuteHappyPath() {
		for(long i=1; i<=10000; i++) {
			Minus minus = Minus.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push(i);
			stack.push(i);
			assertTrue(stack.size() == 2);
			minus.execute(stack);
			assertTrue(stack.top() == 0);
			assertTrue(stack.size() == 1);
		}
	}
	
	@Test
	public void testExecuteSingleLiteralBuffer() {
		for(long i=1; i<=10000; i++) {
			Minus minus = Minus.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push (i);
			assertTrue(stack.size() == 1);
			minus.execute(stack);
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
		Minus minus = Minus.make();
		minus.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == -1);
		minus.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 2);
		stack.push((long)4);
		stack.push((long)5);
		minus.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == -1);
		minus.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 3);
	}
	
}
