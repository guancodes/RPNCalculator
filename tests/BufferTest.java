package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Test;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Buffer;

public class BufferTest {
	
	@Test
	public void testMakeWithNonPositiveCapacity() {
		for (int i=-10000; i<=0; i++) {
			try {
				Buffer.make(i);
				fail("Reject 0 and negative");
			} catch (IllegalArgumentException e) {}
		}
	}
	
	@Test
	public void testMakeHappyPath() {
		for (int i=1; i<=10000; i++) {
			Buffer<Integer> buffer = Buffer.make(i);
			assertTrue(buffer.capacity() == i);
			assertTrue(buffer.size() == 0);
		}
	}
	
	@Test
	public void testTopEmptyArray() {
		for (int i=1; i<=10000; i++) {
			Buffer<Integer> buffer = Buffer.make(i);
			try {
				buffer.top();
				fail("Empty buffer");
			} catch (NoSuchElementException e) {}
		}
	}
	
	@Test
	public void testTopHappyPath() {
		Buffer<Integer> buffer = Buffer.make(5);
		buffer.push(1);
		assertTrue(buffer.top() == 1);
		buffer.push(2);
		assertTrue(buffer.top() == 2);
		buffer.push(3);
		assertTrue(buffer.top() == 3);
		buffer.push(4);
		assertTrue(buffer.top() == 4);
		buffer.push(5);
		assertTrue(buffer.top() == 5);
	}
	
	@Test
	public void testTopFullCapacity() {
		Buffer<Integer> buffer = Buffer.make(5);
		buffer.push(1);
		assertTrue(buffer.top() == 1);
		buffer.push(2);
		assertTrue(buffer.top() == 2);
		buffer.push(3);
		assertTrue(buffer.top() == 3);
		buffer.push(4);
		assertTrue(buffer.top() == 4);
		buffer.push(5);
		assertTrue(buffer.top() == 5);
		for (int i=6; i<=10000; i++) {
			buffer.push(i);
			assertTrue(buffer.top() == i);
		}
		assertTrue(buffer.top() == 10000);
		buffer.pop();
		assertTrue(buffer.top() == 9999);
		buffer.pop();
		assertTrue(buffer.top() == 9998);
		buffer.pop();
		assertTrue(buffer.top() == 9997);
		buffer.pop();
		assertTrue(buffer.top() == 9996);
		buffer.pop();
		try {
			buffer.top();
			fail("Empty buffer");
		} catch (NoSuchElementException e) {}
		try {
			buffer.pop();
			fail("Empty buffer");
		} catch (NoSuchElementException e) {}
		
	}
	
	@Test
	public void testSize() {
		Buffer<Integer> buffer = Buffer.make(5);
		buffer.push(1);
		assertTrue(buffer.size() == 1);
		buffer.push(2);
		assertTrue(buffer.size() == 2);
		buffer.push(3);
		assertTrue(buffer.size() == 3);
		buffer.push(4);
		assertTrue(buffer.size() == 4);
		buffer.push(5);
		assertTrue(buffer.top() == 5);
		for (int i=6; i<=10000; i++) {
			buffer.push(i);
			assertTrue(buffer.size() == 5);
		}
		buffer.pop();
		assertTrue(buffer.size() == 4);
		buffer.pop();
		assertTrue(buffer.size() == 3);
		buffer.pop();
		assertTrue(buffer.size() == 2);
		buffer.pop();
		assertTrue(buffer.size() == 1);
		buffer.pop();
		assertTrue(buffer.size() == 0);
		try {
			buffer.pop();
			fail("Empty buffer");
		} catch (NoSuchElementException e) {}
		assertTrue(buffer.size() == 0);
	}
	
	@Test 
	public void testPushHappyPath() {
		Buffer<Integer> buffer = Buffer.make(5);
		assertTrue(buffer.size() == 0);
		buffer.push(1);
		assertTrue(buffer.size() == 1);
		assertTrue(buffer.top() == 1);
		buffer.push(2);
		assertTrue(buffer.size() == 2);
		assertTrue(buffer.top() == 2);
		buffer.push(3);
		assertTrue(buffer.top() == 3);
		assertTrue(buffer.size() == 3);
		buffer.push(4);
		assertTrue(buffer.top() == 4);
		assertTrue(buffer.size() == 4);
		buffer.push(5);
		assertTrue(buffer.top() == 5);
		assertTrue(buffer.size() == 5);
		
	}
	@Test
	public void testPushFullCapacity() {
		Buffer<Integer> buffer = Buffer.make(5);
		assertTrue(buffer.size() == 0);
		buffer.push(1);
		assertTrue(buffer.size() == 1);
		assertTrue(buffer.top() == 1);
		buffer.push(2);
		assertTrue(buffer.size() == 2);
		assertTrue(buffer.top() == 2);
		buffer.push(3);
		assertTrue(buffer.top() == 3);
		assertTrue(buffer.size() == 3);
		buffer.push(4);
		assertTrue(buffer.top() == 4);
		assertTrue(buffer.size() == 4);
		buffer.push(5);
		assertTrue(buffer.top() == 5);
		assertTrue(buffer.size() == 5);
		for (int i=6; i<=10000; i++) {
			buffer.push(i);
			assertTrue(buffer.top() == i);
			assertTrue(buffer.size() == 5);
		}
	}
	
	@Test
	public void testPopEmptybuffer() {
		Buffer<Integer> buffer = Buffer.make(5);
		for (int i=1; i<=10000; i++) {
			try {
				buffer.pop();
				fail("Empty buffer");
			} catch (NoSuchElementException e) {}
			assertTrue(buffer.size() == 0);
		}
	}
	
	@Test
	public void testPopHappyPath() {
		Buffer<Integer> buffer = Buffer.make(5);
		buffer.push(1);
		assertTrue(buffer.top() == 1);
		buffer.push(2);
		assertTrue(buffer.top() == 2);
		buffer.push(3);
		assertTrue(buffer.top() == 3);
		buffer.push(4);
		assertTrue(buffer.top() == 4);
		buffer.push(5);
		assertTrue(buffer.top() == 5);
		for (int i=6; i<=10000; i++) {
			buffer.push(i);
			buffer.pop();
			assertTrue(buffer.top() == i-1);
			buffer.push(i);
		}
	}
	@Test
	public void testHashCode () {
		for (int i = 1; i < 1000; i++) {
			Buffer<Integer> buffer1 = Buffer.make(i);
			Buffer<Integer> buffer2 = Buffer.make(i);
			assertTrue(buffer1.hashCode() == buffer2.hashCode());
		}
		Buffer<Integer> buffer3 = Buffer.make(5);
		buffer3.push(1);
		buffer3.push(2);
		buffer3.push(3);
		buffer3.push(4);
		buffer3.push(5);
		Buffer<Integer> buffer4 = Buffer.make(5);
		buffer4.push(1);
		buffer4.push(2);
		buffer4.push(3);
		buffer4.push(4);
		buffer4.push(5);
		assertTrue(buffer3.hashCode() == buffer4.hashCode());
		buffer3.pop();
		buffer4.pop();
		assertTrue(buffer3.hashCode() == buffer4.hashCode());
		buffer3.pop();
		assertFalse(buffer3.hashCode() == buffer4.hashCode());
	}
	
	@Test
	public void testEqualityBasic () {
		for (int i = 1; i < 1000; i++) {
			Buffer<Integer> buffer1 = Buffer.make(i);
			Buffer<Integer> buffer2 = Buffer.make(i);
			assertTrue(buffer1.equals(buffer2));
		}
		Buffer<Integer> buffer3 = Buffer.make(5);
		buffer3.push(1);
		buffer3.push(2);
		buffer3.push(3);
		buffer3.push(4);
		buffer3.push(5);
		Buffer<Integer> buffer4 = Buffer.make(5);
		buffer4.push(1);
		buffer4.push(2);
		buffer4.push(3);
		buffer4.push(4);
		buffer4.push(5);
		assertTrue(buffer3.equals(buffer4));
		buffer3.pop();
		buffer4.pop();
		assertTrue(buffer3.equals(buffer4));
		buffer3.push(5);
		buffer4.push(5);
		assertTrue(buffer3.equals(buffer4));
		buffer3.push(1);
		assertFalse(buffer3.equals(buffer4));
		Buffer<Integer> buffer5 = Buffer.make(5);
		Buffer<Integer> buffer6 = Buffer.make(5);
		buffer5.push(1);
		buffer5.push(2);
		buffer5.push(3);
		buffer5.push(4);
		buffer5.push(5);
		buffer6.push(1);
		buffer6.push(2);
		buffer6.push(3);
		buffer6.push(4);
		buffer6.push(6);
		assertFalse(buffer5.equals(buffer6));
		}
	
	@Test
	public void testEqualitySelf() {
		Buffer<Integer> buffer;
		for (int i = 1; i < 1000; i++) {
			buffer = Buffer.make(i);
			assertTrue(buffer.equals(buffer));
		}
		buffer = Buffer.make(5);
		assertTrue(buffer.equals(buffer));
		buffer.push(1);
		assertTrue(buffer.equals(buffer));
		buffer.push(2);
		assertTrue(buffer.equals(buffer));
		buffer.push(3);
		assertTrue(buffer.equals(buffer));
		buffer.push(4);
		assertTrue(buffer.equals(buffer));
		buffer.push(5);
		assertTrue(buffer.equals(buffer));
	}
	
	@Test
	public void testEqualityNull () {
		for (int i = 1; i < 1000; i++) {
			Buffer<Integer> buffer = Buffer.make(i);
			assertFalse(buffer.equals(null));
		}
	}
	
	@Test
	public void testEqualityCommutes() {
		for (int i = 1; i < 1000; i++) {
			Buffer<Integer> buffer1 = Buffer.make(i);
			Buffer<Integer> buffer2 = Buffer.make(i);
			assertEquals(buffer1.equals(buffer2), buffer2.equals(buffer1));
			buffer2.push(3);
			buffer2.push(4);
			buffer2.push(5);
			assertEquals(buffer1.equals(buffer2), buffer2.equals(buffer1));
		}		
	}
	
	@Test
	public void testEqualityAssociates () {
		for (int i = 1; i < 1000; i++) {
			Buffer<Integer> buffer1 = Buffer.make(i);
			Buffer<Integer> buffer2 = Buffer.make(i);
			Buffer<Integer> buffer3 = Buffer.make(i);
			boolean eq = buffer1.equals(buffer2) && buffer2.equals(buffer3);
			assertEquals(eq, buffer1.equals(buffer3));
			buffer2.push(3);
			buffer2.push(4);
			buffer2.push(5);
			buffer3.push(3);
			buffer3.push(4);
			buffer3.push(5);
			buffer1.push(3);
			buffer1.push(4);
			buffer1.push(5);
			eq = buffer1.equals(buffer2) && buffer2.equals(buffer3);
			assertEquals(eq, buffer1.equals(buffer3));
		}
	}
	
	@Test
	public void testToString() {
		Buffer<Integer> buffer = Buffer.make(3);
		assertEquals("", buffer.toString());
		buffer.push(1);
		assertEquals("1 ", buffer.toString());
		buffer.push(2);
		assertEquals("1 2 ", buffer.toString());
		buffer.push(3);
		assertEquals("1 2 3 ", buffer.toString());
		buffer.push(4);
		assertEquals("2 3 4 ", buffer.toString());
		buffer.push(5);
		assertEquals("3 4 5 ", buffer.toString());
		buffer.pop();
		assertEquals("3 4 ", buffer.toString());
		buffer.pop();
		assertEquals("3 ", buffer.toString());
		buffer.pop();
		assertEquals("", buffer.toString());
	}

}
