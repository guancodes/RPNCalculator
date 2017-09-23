package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Buffer;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Divide;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Stack;
public class DivideTest {

	@Test
	public void testExecuteNonNull () {
		for (long i=1; i<=10000; i++) {
			Divide divide = Divide.make();
			try {
				divide.execute(null);
				fail("Reject null");
			} catch (NullPointerException e) {}
		}
	}

	@Test
	public void testExecuteEmptyBuffer() {
		for (int i=1; i<=10000; i++) {
			Divide divide = Divide.make();
			Buffer<Long> buffer = Buffer.make(i);
			try {
				divide.execute(buffer);
				fail("Reject empty buffer");
			} catch (IllegalArgumentException e) {}
		}
	}
	
	@Test
	public void testExecuteHappyPath() {
		for(long i=1; i<=10000; i++) {
			Divide divide = Divide.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push(i);
			stack.push(i);
			assertTrue(stack.size() == 2);
			divide.execute(stack);
			assertTrue(stack.top() == 1);
			assertTrue(stack.size() == 1);
		}
	}
	
	@Test
	public void testExecuteSingleLiteralBuffer() {
		for(long i=1; i<=10000; i++) {
			Divide divide = Divide.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push (i);
			assertTrue(stack.size() == 1);
			divide.execute(stack);
			assertTrue(stack.top() == i);
			assertTrue(stack.size() == 1);
		}
	}
	
	@Test
	public void testExecuteFullCapacityBuffer() {
		Stack<Long> stack= Buffer.make(3);
		stack.push((long)2);
		stack.push((long)12);
		stack.push((long)6);
		assertTrue(stack.size() == 3);
		Divide divide = Divide.make();
		divide.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == 2);
		divide.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 1);
		stack.push((long)24);
		stack.push((long)22);
		divide.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == 1);
		divide.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 1);
	}
}
