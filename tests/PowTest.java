package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Buffer;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Stack;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Pow;


public class PowTest {
	
	@Test
	public void testExecuteNonNull () {
		for (long i=1; i<=10000; i++) {
			Pow pow = Pow.make();
			try {
				pow.execute(null);
				fail("Reject null");
			} catch (NullPointerException e) {}
		}
	}
	
	@Test
	public void testExecuteEmptyBuffer() {
		for (int i=1; i<=10000; i++) {
			Pow pow = Pow.make();
			Buffer<Long> buffer = Buffer.make(i);
			try {
				pow.execute(buffer);
				fail("Reject empty buffer");
			} catch (IllegalArgumentException e) {}
		}
	}
	
	@Test
	public void testExecuteHappyPath() {
		for(long i=1; i<=10000; i++) {
			Pow pow = Pow.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push(i);
			stack.push(i);
			assertTrue(stack.size() == 2);
			pow.execute(stack);
			long expected = (long) Math.pow(i, i);
			long actual = stack.top();
			assertTrue(actual == expected);
			assertTrue(stack.size() == 1);
		}
	}
	
	@Test
	public void testExecuteSingleLiteralBuffer() {
		for(long i=1; i<=10000; i++) {
			Pow pow = Pow.make();
			Stack<Long> stack= Buffer.make(5);
			stack.push (i);
			assertTrue(stack.size() == 1);
			pow.execute(stack);
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
		Pow pow = Pow.make();
		pow.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == 8);
		pow.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 1);
		stack.push((long)4);
		stack.push((long)5);
		pow.execute(stack);
		assertTrue(stack.size() == 2);
		assertTrue(stack.top() == 1024);
		pow.execute(stack);
		assertTrue(stack.size() == 1);
		assertTrue(stack.top() == 1);
	}
	
	@Test
	public void testNonNegtiveExponent() {
		Stack<Long> stack= Buffer.make(3);
		stack.push((long)1);
		stack.push((long)-2);
		Pow pow = Pow.make();
		try {
			pow.execute(stack);
			fail("Exponent has to be non negative");
		} catch (ArithmeticException e) {}
	}
}
