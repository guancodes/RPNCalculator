package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Buffer;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Literal;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Stack;

public class LiteralTest {

	@Test
	public void testFactoryOf() {
		for (long i=1; i<=10000; i++) {
			Literal nL = Literal.of(i);
			assertEquals(i, nL.val());
		}
	}
	
	@Test
	public void testVal() {
		for (long i=(long) 1; i<=10000; i++) {
			Literal nL = Literal.of(i);
			assertTrue(nL.val() ==i );
		}
	}
	
	@Test
	public void testEqualitBasic() {
		Literal nL1;
		Literal nL2;
		for (long i=(long) 1; i<=10000; i++) {
			nL1 = Literal.of(i);
			nL2 = Literal.of(i);
			assertTrue(nL1.equals(nL2));
		}
		for (long i=(long) 1; i<=10000; i++) {
			nL1 = Literal.of(i);
			nL2 = Literal.of(i+1);
			assertFalse(nL1.equals(nL2));
		}
	}
	
	@Test
	public void testEqualitySelf() {
		for (long i=(long) 1; i<=10000; i++) {
			Literal nL = Literal.of(i);
			assertTrue(nL.equals(nL));
		}
	}
	
	@Test
	public void testEqualityNull() {
		for (long i=(long) 1; i<=10000; i++) {
			Literal nL = Literal.of(i);
			assertFalse(nL.equals(null));
		}
	}
	
	@Test
	public void testEqualityCommutes() {
		Literal nL1;
		Literal nL2;
		for (long i=(long) 1; i<=10000; i++) {
			nL1 = Literal.of(i);
			nL2 = Literal.of(i);
			assertEquals(nL1.equals(nL2), nL2.equals(nL1));
		}
		for (long i=(long) 1; i<=10000; i++) {
			nL1 = Literal.of(i);
			nL2 = Literal.of(i+1);
			assertEquals(nL1.equals(nL2), nL2.equals(nL1));
		}
	}
	
	@Test
	public void testEqualityAssociates () {
		Literal nL1;
		Literal nL2;
		Literal nL3;
		for (int i = 1; i < 1000; i++) {
			nL1 = Literal.of(i);
			nL2 = Literal.of(i);
			nL3 = Literal.of(i);
			boolean eq = nL1.equals(nL2) && nL2.equals(nL3);
			assertEquals(eq, nL1.equals(nL3));
		}
		for (int i = 1; i < 1000; i++) {
			nL1 = Literal.of(i);
			nL2 = Literal.of(i+1);
			nL3 = Literal.of(i+2);
			boolean eq = nL1.equals(nL2) && nL2.equals(nL3);
			assertEquals(eq, nL1.equals(nL3));
		}
	}
	
	@Test
	public void testHashCode () {
		Literal nL1;
		Literal nL2;
		Literal nL3;
		for (int i = 1; i < 1000; i++) {
			nL1 = Literal.of(i);
			nL2 = Literal.of(i);
			nL3 = Literal.of(i);
			assertTrue(nL1.hashCode() == nL2.hashCode());
			assertTrue(nL2.hashCode() == nL3.hashCode());
			assertTrue(nL1.hashCode() == nL3.hashCode());
		}
		for (int i = 1; i < 1000; i++) {
			nL1 = Literal.of(i);
			nL2 = Literal.of(i+1);
			nL3 = Literal.of(i+2);
			assertFalse(nL1.hashCode() == nL2.hashCode());
			assertFalse(nL2.hashCode() == nL3.hashCode());
			assertFalse(nL1.hashCode() == nL3.hashCode());
		}
	}
	
	@Test
	public void testExecuteNonNull () {
		for (long i=1; i<=10000; i++) {
			Literal nL = Literal.of(i);
			//Stack<Long> stack= Buffer.make(5);
			try {
				nL.execute(null);
				fail("Reject null");
			} catch (NullPointerException e) {}
		}
	}
	
	@Test
	public void testExecuteHappyPath() {
		for (long i=1; i<=10000; i++) {
			Literal nL = Literal.of(i);
			Stack<Long> stack= Buffer.make(5);
			nL.execute(stack);
			assertTrue(stack.top() == i);
		}
	}
	
	@Test
	public void testExecuteFullCapacityBuffer() {
		Stack<Long> stack= Buffer.make(5);
		stack.push((long)1);
		stack.push((long)2);
		stack.push((long)3);
		stack.push((long)4);
		stack.push((long)5);
		for (long i=1; i<=10000; i++) {
			Literal nL = Literal.of(i);		
			nL.execute(stack);
			assertTrue(stack.top() == i);
		}
	}
	
	@Test
	public void testToString() {
		for (long i=(long) 1; i<=10000; i++) {
			Literal nL = Literal.of(i);
			assertEquals(""+i, nL.toString());
		}
	}
}	
