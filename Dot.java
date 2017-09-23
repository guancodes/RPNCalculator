package nz.ac.aut.wbz8656.dsa2017.assignment02;

import java.util.Objects;

/**
 * A dot instruction. When executed, should take the top value of the stack
 * and print it to the console.
 * @author Guan Wang
 * @version 1.0
 */
public class Dot implements Instruction {
	
    //Private
	
	private Printable printer;
	
	private Dot(Printable printer) {
		this.printer = printer;
	}
	
	private static void checkNotEmpty (Stack<Long> stack, String name) {
		if (stack.size() == 0) {
			throw new IllegalArgumentException(name + " must not be empty");
		}
	}
	
	//Factory
	
	/**
	 * Creates a new dot instruction 
	 * @return a new Dot object
	 */
	public static Dot make(Printable printer) {
		return new Dot(printer);
	}
	
	//Command
		
	@Override
	public void execute(Stack<Long> stack) {
		Objects.requireNonNull(stack);
		checkNotEmpty(stack, "Stack");
		printer.println(stack.top());
	}
}
