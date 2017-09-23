package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Buffer;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Printable;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Program;
public class ProgramTest {
	
	@Test
	public void testCompileHappyPath() {
		Printable mp = MockPrinter.make();
		Program program= Program.compile("2 3 * 1 +", mp);
		Buffer<Long> buffer = Buffer.make(10);
		program.run(buffer);
		assertTrue(buffer.top() == 7);
		program= Program.compile("2 3 * 1 + 2 /", mp);
		program.run(buffer);
		assertTrue(buffer.top() == 3);
		program= Program.compile("2 3 * 1 + 2 / 6 -", mp);
		program.run(buffer);
		assertTrue(buffer.top() == -3);
		program= Program.compile("2 3 * 1 + 2 / 6 - .", mp);
		program.run(buffer);
		assertTrue(buffer.top() == -3);
		program= Program.compile("2 2 * 5 + 3 / 6 - .", mp);
		program.run(buffer);
		assertTrue(buffer.top() == -3);
	}
	
	@Test
	public void testCompileExtraSpaceInSrc() {
		Printable mp = MockPrinter.make();
		Program program= Program.compile("2 3     * 1 +", mp);
		Buffer<Long> buffer = Buffer.make(10);
		program.run(buffer);
		assertTrue(buffer.top() == 7);
		program= Program.compile("2  3  *   1 +   2 /  ", mp);
		program.run(buffer);
		assertTrue(buffer.top() == 3);
		program= Program.compile("2  3   * 1  + 2 / 6  -", mp);
		program.run(buffer);
		assertTrue(buffer.top() == -3);
		program= Program.compile("2 3 *  1 + 2 / 6 - .", mp);
		program.run(buffer);
		assertTrue(buffer.top() == -3);
		program= Program.compile(" 2 2 * 5   + 3 /  6  - .", mp);
		program.run(buffer);
		assertTrue(buffer.top() == -3);
	}
	
	@Test
	public void testCompileIllegalArgumentsInSrc() {
		Printable mp = MockPrinter.make();
		try {
		    Program.compile("2 3 d * 1 +", mp);
			fail("Reject illegal argument");
		} catch(IllegalArgumentException e) {}
		try {
		    Program.compile("& @ d * 1 + G ; "
		    		+ ": } | = - ! ~ % $ # ^ ( )", mp);
			fail("Reject illegal argument");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testCompileWrongSequenceInSrc() {
		Printable mp = MockPrinter.make();
		try {
		    Program.compile("2 * 1 +", mp);
			fail("Reject illegal argument");
		} catch (IllegalArgumentException e) {}
		
		try {
		    Program.compile(". 2 3 * 1 +", mp);
			fail("Reject illegal argument");
		} catch (IllegalArgumentException e) {}
		
		try {
		    Program.compile("2 3 3 1 +", mp);
			fail("Reject illegal argument");
		} catch (IllegalArgumentException e) {}
		
		try {
		    Program.compile("2 . 3 1 +", mp);
			fail("Reject illegal argument");
		} catch (IllegalArgumentException e) {}
		
		try {
		    Program.compile("* 2 3 * 1 +", mp);
			fail("Reject illegal argument");
		} catch (IllegalArgumentException e) {}
		
		try {
		    Program.compile("2 3 * / 1 +", mp);
			fail("Reject illegal argument");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testCompileWithComment() {
		Printable mp = MockPrinter.make();
		Program program= Program.compile("2 3 * 1 + #adfasdf", mp);
		Buffer<Long> buffer = Buffer.make(10);
		program.run(buffer);
		assertTrue(buffer.top() == 7);
		program= Program.compile("2 3 * 1 + 2 / #asdfas", mp);
		program.run(buffer);
		assertTrue(buffer.top() == 3);
		program= Program.compile("2 3 * 1 + 2 / 6 - #asdfas", mp);
		program.run(buffer);
		assertTrue(buffer.top() == -3);
		program= Program.compile("2 3 * 1 + 2 / 6 - . #asdfas", mp);
		program.run(buffer);
		assertTrue(buffer.top() == -3);
		program= Program.compile("2 2 * 5 + 3 / 6 - . #asdfas", mp);
		program.run(buffer);
		assertTrue(buffer.top() == -3);
	}
}
