package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import org.junit.Test;

import nz.ac.aut.wbz8656.dsa2017.assignment02.ArrayList;

public class ArrayListTest {
	
	private Random rng = new Random(42);

	@Test
	public void testEmptyFactory () {
		ArrayList<Integer> al = ArrayList.empty();
		assertEquals(0, al.size());
		assertEquals(Optional.empty(), al.safeAt(0));
	}
	
	@Test
	public void testArrayFactoryNullArray () {
		try {
			ArrayList.fromArray(null);
			fail("Array conversion factory should reject nulls");
		} catch (NullPointerException e) {}
	}
	
	@Test
	public void testArrayFactoryEmptyFactory () {
		ArrayList<Integer> al1 = ArrayList.empty();
		Integer[] arr = new Integer [0];
		ArrayList<Integer> al2 = ArrayList.fromArray(arr);
		assertEquals(al1, al2);
	}
	
	@Test
	public void testArrayFactorySameContents () {
		Integer[] arr = new Integer[]{ 1, 2, 3 };
		ArrayList<Integer> al1 = ArrayList.fromArray(arr);
		assertEquals(arr.length, al1.size());
		for (int i = 0; i < arr.length; i++) {
			assertEquals(arr[i], al1.unsafeAt(i));
		}
	}
	
	@Test
	public void testListFactoryNull () {
		try {
			ArrayList.fromList(null);
			fail("List conversion factory should reject nulls");
		} catch (NullPointerException e) {}
	}
	
	@Test
	public void testListFactorySameContents () {
		Integer[] arr = new Integer[]{ 1, 2, 3 };
		ArrayList<Integer> al1 = ArrayList.fromArray(arr);
		ArrayList<Integer> al2 = ArrayList.fromList(al1);
		assertEquals(al1.size(), al2.size());
		for (int i = 0; i < arr.length; i++) {
			assertEquals(al1.unsafeAt(i), al2.unsafeAt(i));
		}
	}
	
	@Test
	public void testSafeAtRejectNegative () {
		try {
			ArrayList<String> al = ArrayList.empty();
			al.safeAt(-1);
			fail("safeAt should reject negative arguments");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testSafeAtEmptyStructure () {
		ArrayList<String> al = ArrayList.empty();
		for (int i = 0; i < 1000000; i++) {
			assertEquals(Optional.empty(), al.safeAt(i));
		}
	}
	
	@Test
	public void testToArrayEmpty () {
		ArrayList<String> al = ArrayList.empty();
		Object[] arr = al.toArray();
		assertEquals(0, arr.length);
	}
	
	@Test
	public void testToArraySameContents () {
		for (int i = 0; i < 1000000; i++) {
			String[] arr = randStrings(10);
			ArrayList<String> al = ArrayList.fromArray(arr);
			assertEquals(arr.length, al.size());
			for (int j = 0; j < arr.length; j++) {
				assertEquals(arr[j], al.unsafeAt(j));
			}
		}
	}
	
	@Test
	public void testToArrayIdempotent () {
		for (int i = 0; i < 1000000; i++) {
			String[] arr = randStrings(10);
			ArrayList<String> al = ArrayList.fromArray(arr);
			Object[] test = al.toArray();
			assertTrue(Arrays.deepEquals(arr, test));
		}
	}
	
	@Test
	public void testEqualsNull () {
		for (int i = 0; i < 1000000; i++) {
			ArrayList<String> al = ArrayList.fromArray(randStrings(10));
			assertFalse(al.equals(null));
		}
	}
	
	@Test
	public void testEqualsSelf () {
		for (int i = 0; i < 1000000; i++) {
			ArrayList<String> al = ArrayList.fromArray(randStrings(10));
			assertTrue(al.equals(al));
		}
	}
	
	@Test
	public void testEqualsSameContents () {
		for (int i = 0; i < 1000000; i++) {
			String[] arr = randStrings(10);
			ArrayList<String> al1 = ArrayList.fromArray(arr);
			ArrayList<String> al2 = ArrayList.fromArray(arr);
			assertTrue(al1.equals(al2));
		}
	}
	
	@Test
	public void testEqualsCommutes () {
		for (int i = 0; i < 1000000; i++) {
			ArrayList<String> al1 = ArrayList.fromArray(randStrings(10));
			ArrayList<String> al2 = ArrayList.fromArray(randStrings(10));
			assertEquals(al1.equals(al2), al2.equals(al1));
		}
	}
	
	@Test
	public void testEqualsAssociates () {
		for (int i = 0; i < 1000000; i++) {
			String[] arr = randStrings(10);
			ArrayList<String> al1 = ArrayList.fromArray(arr);
			ArrayList<String> al2 = ArrayList.fromArray(arr);
			ArrayList<String> al3 = ArrayList.fromArray(arr);
			boolean base = al1.equals(al2) && al2.equals(al3);
			assertEquals(base, al1.equals(al3));
		}
	}
	
	@Test
	public void testHashCode () {
		for (int i = 0; i < 1000000; i++) {
			String[] arr = randStrings(10);
			ArrayList<String> al1 = ArrayList.fromArray(arr);
			ArrayList<String> al2 = ArrayList.fromArray(arr);
			assertTrue(al1.hashCode() == al2.hashCode());
		}
	}
	
	// write add and remove tests here!
	@Test
	public void testAddEnd () {
		ArrayList<String> al = ArrayList.empty();
		for (int i = 0; i < 1000000; i++) {
			String str = "nihao";
			al.add(str, al.size());
			assertEquals(str,al.safeAt(al.size()-1).get());
			assertEquals(str,al.unsafeAt(al.size()-1));
		}
	}
	
	@Test
	public void testAddBeginning () {
		String[] strArr= {"1","2","3","4","5","6","7"};
		ArrayList<String> al = ArrayList.fromArray(strArr);
		al.add("0",0);
		assertEquals("0",al.unsafeAt(0));
		assertEquals("0",al.safeAt(0).get());
	
		al.add("-1", 0);
		assertEquals("-1",al.unsafeAt(0));
		assertEquals("-1",al.safeAt(0).get());
	}
	
	@Test
	public void testAddMiddle () {
		String[] strArr= {"1","2","3","4","5","6","7"};
		ArrayList<String> al = ArrayList.fromArray(strArr);
		al.add("0",3);
		assertEquals("0",al.unsafeAt(3));
		assertEquals("0",al.safeAt(3).get());
	
		al.add("-1", 4);
		assertEquals("-1",al.unsafeAt(4));
		assertEquals("-1",al.safeAt(4).get());
	}
	
	@Test
	public void testAddnull () {
		ArrayList<String> al = ArrayList.empty();
		try{
			al.add(null,al.size());
			fail("assertion never passes");	
		}
		catch(NullPointerException e) {}
	}
	
	@Test
	public void testAddInvalidInput () {
		ArrayList<String> al = ArrayList.empty();
		try {
			al.add("nihao", al.size()+1);
			fail("assertion never passes");
		}
		catch(IllegalArgumentException e) {}
	}
	
	@Test
	public void testRemoveFromEmptyList() {
		ArrayList<String> al = ArrayList.empty();
		try {
			al.remove(al.size());
			fail("assertion never passes");
		}
		catch(IllegalArgumentException e) {}
	}
	
	@Test
	public void testResizeBehaviour() {
		String[] strArr= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
		ArrayList<String> al = ArrayList.fromArray(strArr);
		al.add("34", 3);
		assertEquals(17,al.size());
		al.add("34", 5);
		assertEquals(18,al.size());
	}
	
	
	@Test
	public void testRemoveFromBeginning() {
		String[] strArr= {"1","2","3","4","5","6","7"};
		ArrayList<String> al = ArrayList.fromArray(strArr);
		al.remove(0);
		assertEquals("2",al.unsafeAt(0));
		
	}
	
	@Test
	public void testRemoveFromMiddle() {
		String[] strArr= {"1","2","3","4","5","6","7"};
		ArrayList<String> al = ArrayList.fromArray(strArr);
		al.remove(3);
		assertEquals("5",al.unsafeAt(3));
		
	}
	
	@Test
	public void testRemoveFromEnd() {
		String[] strArr= {"1","2","3","4","5","6","7"};
		ArrayList<String> al = ArrayList.fromArray(strArr);
		al.remove(6);
		assertEquals(Optional.empty(),al.safeAt(6));	
		al.remove(5);
		assertEquals(Optional.empty(),al.safeAt(5));	
	}
	
	@Test
	public void testToString() {
		String[] strArr= {"1","2","3","4","5","6","7"};
		ArrayList<String> al = ArrayList.fromArray(strArr);
		assertEquals("ArrayList:\n" + 
				"1 2 3 4 5 6 7 \n",al.toString());
	}
	
	//private tool for Test convenience
	private String[] randStrings (int limit) {
		int howMany = rng.nextInt(10);
		String[] arr = new String[howMany];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.toString(rng.nextInt());
		}
		return arr;
	}
}
