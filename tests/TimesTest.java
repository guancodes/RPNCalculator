package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Buffer;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Stack;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Times;


public class TimesTest {
	
	@Test
	public void testExecuteNonNull () {
		for (long i=1; i<=10000; i++) {
			Times times = Times.make();
			try {
				times.execute(null);
				fail("Reject null");
			} catch (NullPointerException e) {}
		}
	}
	
	@Test
	public void testExecuteEmptyBuffer() {
		for (int i=1; i<=10000; i++) {
			Times times = Times.make();
			Buffer<Long> buffer = Buffer.make(i);
			try {
				times.execute(buffer);
				fail("Reject empty buffer");
			} catch (IllegalArgumentException e) {}
		}
	}
	
	@Test
	public void testExecuteHappyPath() {
		for(long i=1; i<=10000; i++) {
			Times times = Times.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push(i);
			stack.push(i);
			assertTrue(stack.size() == 2);
			times.execute(stack);
			assertTrue(stack.top() == i*i);
			assertTrue(stack.size() == 1);
		}
	}
	
	@Test
	public void testExecuteSingleLiteralBuffer() {
		for(long i=1; i<=10000; i++) {
			Times times = Times.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push (i);
			assertTrue(stack.size() == 1);
			times.execute(stack);
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
		Times times = Times.make();
		times.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == 6);
		times.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 6);
		stack.push((long)4);
		stack.push((long)5);
		times.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == 20);
		times.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 120);
	}
	
}
