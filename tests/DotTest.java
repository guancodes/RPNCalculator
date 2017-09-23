package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Buffer;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Dot;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Stack;

public class DotTest {

	@Test
	public void testExecuteNonNull () {
		for (long i=1; i<=10000; i++) {
			Dot dot = Dot.make(MockPrinter.make());
			try {
				dot.execute(null);
				fail("Reject null");
			} catch (NullPointerException e) {}
		}
	}
	
	@Test
	public void testExecuteEmptyBuffer() {
		for (int i=1; i<=10000; i++) {
			Dot dot = Dot.make(MockPrinter.make());
			Buffer<Long> buffer = Buffer.make(i);
			try {
				dot.execute(buffer);
				fail("Reject empty buffer");
			} catch (IllegalArgumentException e) {}
		}
	}
	
	@Test
	public void testExecuteHappyPath() {
		for(long i=1; i<=10; i++) {
			MockPrinter mp = MockPrinter.make();
			Dot dot = Dot.make(mp);
			Stack<Long> stack= Buffer.make(5);
			stack.push(i);
			stack.push(i);
			assertTrue(stack.size() == 2);
			dot.execute(stack);
			assertTrue(mp.value().get() == i);
			assertTrue(stack.top() == i);
			assertTrue(stack.size() == 2);
		}
	}
	
	@Test
	public void testExecuteCircularBuffer() {
		MockPrinter mp = MockPrinter.make();
		Stack<Long> stack= Buffer.make(3);
		stack.push((long)1);
		stack.push((long)2);
		stack.push((long)3);
		assertTrue(stack.size() == 3);
		Dot dot = Dot.make(mp);
		dot.execute(stack);
		assertTrue(mp.value().get() == 3);
		assertTrue(stack.size() == 3);
		assertTrue(stack.top() == 3);
		stack.push((long)4);
		stack.push((long)5);
		dot.execute(stack);
		assertTrue(mp.value().get() == 5);
		assertTrue(stack.size() == 3);
		assertTrue(stack.top() == 5);
	}
}
