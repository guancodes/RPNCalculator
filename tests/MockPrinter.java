package nz.ac.aut.wbz8656.dsa2017.assignment02.tests;

import java.util.Optional;
import nz.ac.aut.wbz8656.dsa2017.assignment02.Printable;

/**
 * Creates a bridge that wraps a mock printer in the object for unit testing purposes
 * @author Guan Wang
 * @version 1.0
 */
public class MockPrinter implements Printable {
	
	//private
	
	private Optional<Long> value = Optional.empty();
	private MockPrinter() {}
	
	//factory
	
	/**
	 * creates a new MockPrinter
	 * @return a MockPrinter
	 */
	public static MockPrinter make() {
		return new MockPrinter();
	}
	
	//Query
	
	/**
	 * Gives us the value
	 * @return the value
	 */
	public Optional<Long> value() {
		return this.value;
	}
	
	@Override 
	public void println(long value) {
		this.value = Optional.of(value);
	}
	
}
