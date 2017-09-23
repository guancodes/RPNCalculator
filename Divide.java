package nz.ac.aut.wbz8656.dsa2017.assignment02;

import java.util.Objects;

/**
 * A divide instruction. When executed, this should take the top two values on the stack passed in, 
 * divide the second value by the first, and push the answer back on the stack.
 * @author Guan Wang
 * @version 1.0
 */
public class Divide implements Instruction{
	
	//Private

	private Divide(){}
	
	private static void checkNotEmpty (Stack<Long> stack, String name) {
		if (stack.size() == 0) {
			throw new IllegalArgumentException(name + " must not be empty");
		}
	}
	
	//Factory
	
	/**
	 * Creates a new divide instruction 
	 * @return a new Divide object
	 */
	public static Divide make() {
		return new Divide();
	}
	
	//Command
		
	@Override
	public void execute(Stack<Long> stack) {
		Objects.requireNonNull(stack);
		checkNotEmpty(stack, "Stack");
		if (stack.size() == 1) {
			return;
		} else {
			long a = stack.top();
			stack.pop();
			long b = stack.top();
			stack.pop();
			stack.push(b / a);
		}
	}
}
