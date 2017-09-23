package nz.ac.aut.wbz8656.dsa2017.assignment02;

/**
 * Creates a bridge that wraps a real Console Printer in the object.
 * @author Guan Wang
 * @version 1.0
 * @param <T> the type of elements stored.
 */
public class ConsolePrinter implements Printable {
	
	//Command
	
	@Override 
	public void println(long value) {
		System.out.println(value);
	}

}
